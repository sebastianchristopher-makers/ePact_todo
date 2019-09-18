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
    @Test
    public void canSetComplete(){
        toDo.setComplete(true);
        assertTrue(toDo.getComplete());
    }

    @Test
    public void defaultCompleteIsFalse() {
        assertFalse(toDo.getComplete());
    }

    @Test
    public void canSetNotComplete(){
        toDo.setComplete(true);
        toDo.setComplete(false);
        assertFalse(toDo.getComplete());
    }

    @Test
    public void defaultLabelIdis0() {
        assertEquals(0 ,toDo.getLabelId());
    }

    @Test
    public void canSetLabelId() {
        toDo.setLabelId(1);
        assertEquals(1, toDo.getLabelId());
    }

    @Test
    public void defaultUserIdis0() {
        assertEquals(0 ,toDo.getUserId());
    }

    @Test
    public void canSetUserId() {
        toDo.setUserId(1);
        assertEquals(1, toDo.getUserId());
    }

}
