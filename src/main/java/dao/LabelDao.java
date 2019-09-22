package dao;

import models.Label;

import java.util.List;

public interface LabelDao {

    List<Label> all();

    void add(Label label);

    Label find(int id);

    void delete(int id);

    void edit(int id, String name);

    void clearAll();

}
