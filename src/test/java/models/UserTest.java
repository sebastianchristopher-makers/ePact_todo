package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    User user;

    @Before
    public void setUp(){
        user = new User("Chris");
    }

    @Test
    public void itHasAName(){
        assertEquals("Chris", user.getName());
    }

    @Test
    public void itCanHaveAnId(){
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @After
    public void tearDown(){
        user = null;
    }
}
