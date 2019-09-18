import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;



public class ToDoTest {
    ToDo toDo;

    @Before
    public void setup() {

       toDo = new ToDo("Buy Milk");
    }

    @Test
    public void createANewToDo() {

        assertEquals("Buy Milk", toDo.getContent());
    }

    @Test
    public void addContentToToDo() {
        toDo.setContent("Walk the dog");
        assertEquals( "Walk the dog", toDo.getContent());
    }

//    @Test
//    public void checkDefaultComplete() {
//        assertEquals(false,toDo.complete);
//    }

    @Test
    public void checkIDIsSet() {
        toDo.setId(1);
        assertEquals(1, toDo.getId());
    }

    @After
    public void tearDown() {
        toDo = null;
    }

}
