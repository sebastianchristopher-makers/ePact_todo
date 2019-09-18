package dao;

import models.User;

public interface UserDao {

    User create(String username, String password);
    boolean userExists(String username);
    User authenticate(String username, String password);

}
