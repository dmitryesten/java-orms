package hibernate.dao;

import hibernate.model.Gender;
import hibernate.model.User;

import java.util.List;

public interface IUserListRepository {

    List<User> findAll();

    List<User> findAll(Gender gender);

    List<User> findAll(int leftEdge, int rightEdge);

}

