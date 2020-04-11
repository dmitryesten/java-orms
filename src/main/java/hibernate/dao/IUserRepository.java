package hibernate.dao;

import hibernate.model.User;

import java.util.Collection;
import java.util.List;

public interface IUserRepository {

    User create(User user);

    void delete(User user);

    void update(User user);

    User getById(long id) throws UserNoFoundDatabase;

}
