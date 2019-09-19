import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oToDoDao;
import models.ToDo;
import org.slf4j.Logger;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

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

//        get("/todos/:id", (request,response) -> {
//            Map<String, Object> model = new HashMap<>();
//            int id = Integer.parseInt(request.params("id"));
//            System.out.println(id);
//            ToDo todo1 = todoDao.find(id);
//            System.out.println(todo1);
//            model.put("todo",todo1);
//            return new ModelAndView(model, "templates/todofind.vtl");
//        }, new VelocityTemplateEngine());

        get("/todos/new", (request,response)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/todoadd.vtl");
        }, new VelocityTemplateEngine());

        post("/todos/new", (request,response) -> {
            String newToDo = request.queryParams("newtodo");
            ToDo toDo = new ToDo(newToDo, 1);
            todoDao.add(toDo);
            response.redirect("/");
            return null;
        });

        post("/todos/:id/delete",(request,response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params(":id"));
            todoDao.delete(id);
            response.redirect("/");
            return null;
        });

        post("/todos/:id/complete", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params(":id"));
            Boolean iscomplete = true;
            todoDao.complete(id,iscomplete);
//            model.put("complete",)
            response.redirect("/");
            return null;
        });

        get("/todos/:id/edit", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params(":id"));
            ToDo todo = todoDao.find(id);
            model.put("todo", todo);
            System.out.println("********");
            return new ModelAndView(model, "templates/edit.vtl");
        }, new VelocityTemplateEngine());

        post("/todos/:id/edit", (request,response) -> {
            int id = Integer.parseInt(request.params(":id"));

            System.out.println("****");
            System.out.println(id);
            String newContent = request.queryParams("newcontent");
            todoDao.edit(id, newContent);
            response.redirect("/");
            return null;
        });
    }
}

