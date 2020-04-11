package hibernate.dao;

import hibernate.model.Gender;
import hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserListHRepository implements IUserListRepository {

    private SessionFactory sessionFactory;

    public UserListHRepository(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public List<User> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT u FROM User u", User.class).getResultList();
        }
    }

    @Override
    public List<User> findAll(Gender gender) {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> table = query.from(User.class);
            CriteriaQuery<User> unitQuery = query.select(table)
                                                  .where(builder.equal(table.get("gender"), gender));
            Query<User> allQuery = session.createQuery(unitQuery);
            return allQuery.getResultList();
        }
    }

    @Override
    public List<User> findAll(int leftEdge, int rightEdge) {
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root<User> table = query.from(User.class);
            CriteriaQuery<User> unitQuery = query.select(table)
                                                 .where(builder.between(table.get("age"), leftEdge, rightEdge));
            Query<User> allQuery = session.createQuery(unitQuery);
            return allQuery.getResultList();
        }
    }

}
