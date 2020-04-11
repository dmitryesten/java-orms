package hibernate.dao

import hibernate.HibernateUtil
import hibernate.model.Gender
import hibernate.model.User
import org.hibernate.SessionFactory
import spock.lang.Specification

class UserHRepositoryTest extends Specification {

    private static SessionFactory factory = HibernateUtil.getSessionFactory();


    def "Create new a user"() {
        when: "initialize a user pojo"
            def user = new User();
        and: "set data the user"
            user.setNickname("testUser1");
            user.setPassword("password123");
            user.setGender(Gender.MALE);
            user.setAge(22);
        and: "init repository"
            def userRep = new UserHRepository(factory);
        then: "insert user to database"
            def actualUser = userRep.create(user);
            userRep.delete(actualUser);
        assert actualUser.getId() != 0
    }

    def "Delete the user"() {
        when: "initialize a user pojo"
            def user = new User();
        and: "set data the user"
            user.setNickname("testDeletedUser2");
            user.setPassword("password123");
            user.setGender(Gender.MALE);
            user.setAge(22);
        and: "init repository"
            def userRep = new UserHRepository(factory);
        and: "insert user to database"
            def createdUser = userRep.create(user);
        then:
            userRep.delete(createdUser);
        assert userRep.getById(createdUser.getId()) == null;
    }

    def "Update the user"() {
        when: "initialize a user pojo"
            def user = new User();
            def expectedNewPassword = "123qwerty";
        and: "set data the user"
            user.setNickname("testUpdateUser4");
            user.setPassword("password123");
            user.setGender(Gender.MALE);
            user.setAge(22);
        and: "init repository"
            def userRep = new UserHRepository(factory);
        and: "insert user to database"
            def createdUser = userRep.create(user);
        then:
            createdUser.setPassword(expectedNewPassword);
            userRep.update(createdUser);
            def actualUpdatedUser = userRep.getById(createdUser.getId());
        assert expectedNewPassword.equals(actualUpdatedUser.getPassword());
    }

    def "GetById"() {
        when: "initialize a user pojo"
            def user = new User();
        and: "set data the user"
            user.setNickname("testGetByIdUser3");
            user.setPassword("password123");
            user.setGender(Gender.MALE);
            user.setAge(22);
        and: "init repository"
            def userRep = new UserHRepository(factory);
        and: "insert user to database"
            def expectedCreatedUser = userRep.create(user);
        then:
            def actualUser = userRep.getById(expectedCreatedUser.getId());
        assert expectedCreatedUser.getNickname().equals(actualUser.getNickname());
    }

}
