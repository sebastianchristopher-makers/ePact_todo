package dao;

import models.ToDo;
import java.util.List;

public interface ToDoDao {

    //add
    void add(ToDo todo);
    //edit
    void edit(int id, String newContent);
    //complete
    void complete(int id, boolean isComplete);
    //delete
    void delete(int id);

    //all
    List<ToDo> all();

    //find
    ToDo find(int id);
}
