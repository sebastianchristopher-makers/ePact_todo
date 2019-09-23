package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LabelTest {
    Label label;

    @Before
    public void setUp(){
        label = new Label("Chores");
    }

    @Test
    public void itInstantiates(){
        assertTrue(label instanceof Label);
    }

    @Test
    public void itHasAName(){
        assertEquals("Chores", label.getName());
    }

    @After
    public void tearDown(){
        label = null;
    }
}
