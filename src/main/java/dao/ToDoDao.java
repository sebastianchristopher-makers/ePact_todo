package dao;

import models.ToDo;

public interface ToDoDao {

    //add
    void add(ToDo todo);
    //edit

    //complete
    void complete(int id, boolean isComplete);
    //delete

    //all

    ToDo find(int id);
}
