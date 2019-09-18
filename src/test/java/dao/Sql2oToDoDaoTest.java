package dao;

import models.ToDo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;

public class Sql2oToDoDaoTest {

    private Sql2oToDoDao todoDao;
    private Connection conn;
    ToDo todo;
    @Before
    public void setUp(){
        todo = new ToDo("Pet Milk");
        String connectionString = "jdbc:postgresql://localhost:5432/epacttodoapptest";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        todoDao = new Sql2oToDoDao(sql2o);
        conn = sql2o.open();
    }
    @Test
    public void canSetId(){
        int ogId = todo.getId();
        todoDao.add(todo);
        int newId = todo.getId();
        assertNotEquals(ogId, newId);
    }

    @Test
    public void canFindFromId() {
        todoDao.add(todo);
        assertEquals(todo, todoDao.find(1));
    }

    @After
    public void tearDown() {
        conn.createQuery("TRUNCATE todo  RESTART IDENTITY CASCADE").executeUpdate();
        conn.close();
    }
}
