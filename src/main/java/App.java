import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oToDoDao;
import models.ToDo;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost:5432/epacttodoapp";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        Sql2oToDoDao todoDao = new Sql2oToDoDao(sql2o);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>(); // allows us to pass objects into the vtl template
            List<ToDo> todos = todoDao.all();
            model.put("todos", todos); // pass all ToDos into template
            return new ModelAndView(model, "index.vtl");
        }, new VelocityTemplateEngine());

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
