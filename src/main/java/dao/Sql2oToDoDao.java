package dao;
import models.ToDo;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import org.sql2o.Sql2oException;

public class Sql2oToDoDao implements ToDoDao {

    private final Sql2o sql2o;

    public Sql2oToDoDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(ToDo todo) {
        String sql = "INSERT INTO todo(content, complete, userId, labelId) VALUES (:content, :complete, :userId, :labelId)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(todo)
                    .executeUpdate()
                    .getKey();
            todo.setId(id);
        } catch(Sql2oException e){
            System.out.println(e);
        }

    }

    @Override
    public void complete(int id, boolean isComplete) {

    }

    @Override
    public ToDo find(int id) {
        String sql = "SELECT * FROM todo WHERE id = (:id)";
        try (Connection conn = sql2o.open()) {
           return conn.createQuery(sql)
                .addParameter("id", id)
                .executeAndFetchFirst(ToDo.class);
        } catch(Sql2oException e){
            System.out.println(e);
        }

        return null;

    }
}