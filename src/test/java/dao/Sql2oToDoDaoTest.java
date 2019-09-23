package dao;
import models.ToDo;
import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

public class Sql2oToDoDaoTest {

    private Sql2oToDoDao todoDao;
    private Connection conn;
    ToDo todo;

    @Before
    public void setUp(){
        todo = new ToDo("Pet Milk", 1, 1);
        String connectionString = "jdbc:postgresql://localhost:5432/epacttodoapptest";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        todoDao = new Sql2oToDoDao(sql2o);
        conn = sql2o.open();
        String sql = "INSERT INTO users (name) VALUES (:name)";
        conn.createQuery(sql)
                .addParameter("name", "UserOne")
                .executeUpdate();
    }

    @Test
    public void addingAToDoSetsId(){
        int ogId = todo.getId();
        todoDao.add(todo);
        int newId = todo.getId();
        assertNotEquals(ogId, newId);
    }

    @Test
    public void itCanEditAToDo(){
        String originalToDoContent = todo.getContent();
        todoDao.add(todo);
        todoDao.edit(todo.getId(), "I say something else now.");
        String newToDoContent = todoDao.find(todo.getId()).getContent();
        assertNotEquals(originalToDoContent, newToDoContent);
    }

    @Test
    public void allReturnsAllToDos() {
        ToDo toDo1 = new ToDo("Buy bread", 1, 1);
        toDo1.setId(1);
        ToDo toDo2 = new ToDo("Buy milk", 1, 1);
        toDo2.setId(2);
        todoDao.add(toDo1);
        todoDao.add(toDo2);
        List<ToDo> list = todoDao.all();
//        assertEquals(toDo1, list.get(0));
//        assertEquals(toDo2, list.get(1));
//        System.out.println(list.size());
        assertThat(list, hasItems(toDo1,toDo2));
    }

    @Test
    public void allReturnsNothingIfNoToDos(){
        assertTrue(todoDao.all().isEmpty());
    }

    @Test
    public void itCanDeleteAToDoById() {
        conn.createQuery("INSERT INTO todo(content) VALUES (:content)")
                .addParameter("content", "Buy bread")
                .executeUpdate();
        ToDo toDo = new ToDo("Buy bread!", 1, 1);
        toDo.setId(1);
        todoDao.delete(1);
    }

    @Test
    public void canFindFromId(){
        todoDao.add(todo);
        assertEquals(todo, todoDao.find(1));
    }

    @Test
    public void toDosAreNotCompleteByDefault(){
        todoDao.add(todo);
        ToDo foundToDo = todoDao.find(1);
        assertFalse(foundToDo.getComplete());
    }

    @Test
    public void itCanMarkAnItemAsComplete(){
        todoDao.add(todo);
        todoDao.complete(1, true);
        ToDo foundToDo = todoDao.find(1);
        assertTrue(foundToDo.getComplete());
    }

    @Test
    public void itCanMarkAnItemAsIncomplete(){
        todoDao.add(todo);
        todoDao.complete(1, true);
        todoDao.complete(1, false);
        ToDo foundToDo = todoDao.find(1);
        assertFalse(foundToDo.getComplete());
    }

    @Test
    public void itCanReturnToDosByUser(){
        String sql = "INSERT INTO users (name) VALUES (:name)";
        conn.createQuery(sql)
                .addParameter("name", "UserTwo")
                .executeUpdate();
        ToDo twoDo = new ToDo("foo", 1, 1);
        ToDo threeDo = new ToDo("bar", 2, 1);
        ToDo fourDo = new ToDo("four", 1, 1);
        todoDao.add(twoDo);
        todoDao.add(threeDo);
        todoDao.add(fourDo);

        assertEquals(2, todoDao.findByUser(1).size());
        Assert.assertThat(todoDao.findByUser(1), hasItems(twoDo, fourDo));
    }

    @After
    public void tearDown() {
        conn.createQuery("TRUNCATE todo RESTART IDENTITY CASCADE").executeUpdate();
        conn.createQuery("TRUNCATE users RESTART IDENTITY CASCADE").executeUpdate();
    }
}
