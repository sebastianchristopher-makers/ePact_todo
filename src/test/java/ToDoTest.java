import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;


public class ToDoTest {
    ToDo toDo;

    @Before
    public void setUpToDo(){
        toDo = new ToDo("Pet dog");
    }

    @Test
    public void canSetContent() {
        toDo.setContent("Buy Milk");
        assertFalse(toDo.getContent().isEmpty());
    }

    @Test
    public void setContentisCorrect(){
        toDo.setContent("Buy Milk");
        assertEquals("Buy Milk", toDo.getContent());
    }
    

}
