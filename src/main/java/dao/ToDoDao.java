package dao;

import models.ToDo;
import java.util.List;

public interface ToDoDao {

    //add

    //edit
    void edit(int id, String newContent);
    //complete

    //delete
    void delete(int id);

    //all
    List<ToDo> all();
}
