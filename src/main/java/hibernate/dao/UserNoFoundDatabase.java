package hibernate.dao;

public class UserNoFoundDatabase extends Exception {

    public UserNoFoundDatabase(String cause){
        super(cause);
    }

}
