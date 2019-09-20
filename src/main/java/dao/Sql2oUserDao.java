package dao;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import org.sql2o.data.Row;

import java.util.List;

public class Sql2oUserDao implements UserDao {

    private Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public User create(String username, String password){
        if(userExists(username)){
            System.out.println("User already exists");
            return null;
        }
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery("INSERT INTO users (name, password) VALUES (:name, :password)", true)
                    .addParameter("name", username)
                    .addParameter("password", hashPassword)
                    .executeUpdate()
                    .getKey();
            return con.createQuery("SELECT id, name FROM users WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public boolean userExists(String username){
        try(Connection con = sql2o.open()){
            return(con.createQuery("SELECT count(*) FROM users WHERE name = :name")
                    .addParameter("name", username)
                    .executeScalar(Integer.class) > 0);
        } catch(Sql2oException ex){
            System.out.println(ex);
            return true;
        }
    }

    public User authenticate(String username, String password){
        if (!userExists(username)) {
            return null;
        }
        try(Connection con = sql2o.open()) {
            List<Row> pass = con.createQuery("SELECT * FROM users WHERE name = :name")
                    .addParameter("name", username)
                    .executeAndFetchTable()
                    .rows();
            int id = pass.get(0).getInteger("id");
            String hashPassword = pass.get(0).getString("password");
            if(BCrypt.checkpw(password, hashPassword)) {
                return con.createQuery("SELECT id, name FROM users WHERE id = :id")
                        .addParameter("id", id)
                        .executeAndFetchFirst(User.class);
                } else {
                return null;
            }
        } catch(Sql2oException ex){
            System.out.println(ex);
            return null;
        }
    }
}
