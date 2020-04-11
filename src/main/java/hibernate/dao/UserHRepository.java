package hibernate.dao;

import hibernate.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserHRepository implements IUserRepository {

    private final SessionFactory sessionFactory;

    public UserHRepository(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    public User create(User user) {
        long generatedId;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                generatedId = (long) session.save(user);
                user.setId(generatedId);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
            }
        return user;
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                session.delete(user);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void update(User user) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                session.update(user);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
            }

        }
    }

    @Override
    public User getById(long id) throws UserNoFoundDatabase {
        User user = null;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            try {
                user = session.find(User.class, id);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                throw new UserNoFoundDatabase("The user isn't found");
            }

        }
        return user;
    }

}
