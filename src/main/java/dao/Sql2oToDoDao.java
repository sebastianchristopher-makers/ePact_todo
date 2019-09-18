package dao;
import models.ToDo;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.List;

public class Sql2oToDoDao implements ToDoDao {

    private final Sql2o sql2o;

    public Sql2oToDoDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void edit(int id, String newContent) {
        System.out.println("Successfully edited");
    }

    @Override
    public void delete(int id) {
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM todo WHERE id = id;")
                    .executeUpdate();
        }
        System.out.println("Successfully deleted");
    }

    @Override
    public List<ToDo> all() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM todo;")
                    .executeAndFetch(ToDo.class);

        }
    }
}