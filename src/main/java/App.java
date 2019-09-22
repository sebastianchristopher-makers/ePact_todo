import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Sql2oLabelDao;
import dao.Sql2oToDoDao;
import dao.Sql2oUserDao;
import models.Label;
import models.ToDo;
import models.User;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        String connectionString = "jdbc:postgresql://localhost:5432/epacttodoapp";
        Sql2o sql2o = new Sql2o(connectionString, "student", "");
        Sql2oToDoDao todoDao = new Sql2oToDoDao(sql2o);
        Sql2oUserDao userDao = new Sql2oUserDao(sql2o);
        Sql2oLabelDao labelDao = new Sql2oLabelDao(sql2o);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>(); // allows us to pass objects into the vtl template
            User user = req.session().attribute("user");
            List<ToDo> todos = null;
            if(user != null){
                todos = todoDao.findByUser(user.getId());
            }
            model.put("todos", todos); // pass all ToDos into template
            model.put("user", user);
            List<Label> labels = labelDao.all();
            model.put("labels", labels);
            model.put("labelDao", labelDao);
            return new ModelAndView(model, "templates/index.vtl");
        }, new VelocityTemplateEngine());

        get("/todos/new", (request,response)->{
            Map<String, Object> model = new HashMap<>();
            User user = request.session().attribute("user");
            model.put("user", user);
            return new ModelAndView(model, "templates/todos/todoadd.vtl");
        }, new VelocityTemplateEngine());

        post("/todos/new", (request,response) -> {
            String content = request.queryParams("content");
            int id = Integer.parseInt(request.queryParams("id"));
            ToDo toDo = new ToDo(content, id, 1);
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
            todoDao.complete(id, iscomplete);
//            model.put("complete",)
            response.redirect("/");
            return null;
        });

        get("/todos/:id/edit", (request,response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.params(":id"));
            ToDo todo = todoDao.find(id);
            model.put("todo", todo);
            return new ModelAndView(model, "templates/todos/edit.vtl");
        }, new VelocityTemplateEngine());

        post("/todos/:id/edit", (request,response) -> {
            int id = Integer.parseInt(request.params(":id"));
            String newContent = request.queryParams("content");
            todoDao.edit(id, newContent);
            response.redirect("/");
            return null;
        });

        get("/sessions/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "templates/sessions/new.vtl");
        }, new spark.template.velocity.VelocityTemplateEngine());

        get("/users/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String notices = req.session().attribute("userexists");
            model.put("userexists", notices);
            return new ModelAndView(model, "templates/users/new.vtl");
        }, new spark.template.velocity.VelocityTemplateEngine());

        post("/users", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            req.session().removeAttribute("userexists");
            if(userDao.userExists(username)){
                req.session().attribute("userexists", "User already exists!");
                res.redirect("/users/new");
                halt();
            }
            User user = userDao.create(username, password);
            req.session().attribute("user", user);
            res.redirect("/");
            return null;
        });

        post ("/sessions", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            User user = userDao.authenticate(username, password);
            if(user != null){
                req.session().attribute("user", user);
            }
            res.redirect("/");
            return null;
        });

        post("/sessions/destroy", (req, res) -> {
            req.session().removeAttribute("user");
            res.redirect("/");
            return null;
        });

        get ("/labels/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("labels", labelDao.all());
            return new ModelAndView(model, "templates/new-label.vtl");
        }, new spark.template.velocity.VelocityTemplateEngine());

        post("/labels", (req, res) -> {
            String name = req.queryParams("name");
            Label label = new Label(name);
            labelDao.add(label);
            res.redirect("/");
            return null;
        });
    }
}

