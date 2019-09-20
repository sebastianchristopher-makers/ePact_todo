package dao;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private Sql2oUserDao userDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/epacttodoapptest";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    @Test
    public void creatingAUserSetsId(){
        User user = userDao.create("Chris", "Password1234");
        assertEquals(1, user.getId());
    }

    @Test
    public void returnsTrueIfUserExists(){
        userDao.create("Chris", "Password1234");
        boolean userExists = userDao.userExists("Chris");
        assertTrue(userExists);
    }

    @Test
    public void returnsFalseIfUserNotExists(){
        boolean userExists = userDao.userExists("Chris");
        assertFalse(userExists);
    }

    @Test
    public void cannotCreateAnExistingUser(){
        userDao.create("Chris", "Password1234");
        User user = userDao.create("Chris", "Password1234");
        assertTrue(user == null);
    }

    @Test
    public void authenticateRecognizesACorrectPassword(){
        User user = new User("Chris");
        user.setId(1);
        userDao.create("Chris", "Password1234");
        User createdUser = userDao.authenticate("Chris", "Password1234");
        System.out.println(createdUser);
        assertEquals(user, createdUser);
    }

    @Test
    public void authenticateRejectsAnIncorrectPassword(){
        userDao.create("Chris", "Password1234");
        User user = userDao.authenticate("Chris", "SecretPassword");
        assertNull(user);
    }

    @Test
    public void authenticateRejectsAnIncorrectUser(){
        userDao.create("Chris", "Password1234");
        User user = userDao.authenticate("NewUser", "Password1234");
        assertNull(user);
    }

    @After
    public void tearDown() throws Exception {
        conn.createQuery("TRUNCATE users RESTART IDENTITY CASCADE").executeUpdate();
        conn.close();
    }
}
