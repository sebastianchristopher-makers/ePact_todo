import dao.Sql2oToDoDao;
import dao.ToDoDao;
import models.ToDo;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {

    public static void main(String[] args) {
        ToDo toDo = new ToDo("Hello", 1);
        String connectionString = "jdbc:postgresql://localhost:5432/epacttodoapp";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        ToDoDao todoDao = new Sql2oToDoDao(sql2o);

        get("/todos/:id", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
          int id = Integer.parseInt(request.params("id"));
            System.out.println(id);
            ToDo todo1 = todoDao.find(id);
            System.out.println(todo1);
            model.put("todo",todo1);
            return new ModelAndView(model, "templates/todofind.vtl");
        }, new VelocityTemplateEngine());
    }
}
