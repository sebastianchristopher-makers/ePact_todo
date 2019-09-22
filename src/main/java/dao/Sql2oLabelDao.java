package dao;

import models.Label;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.Collections;
import java.util.List;

public class Sql2oLabelDao implements LabelDao{

    private Sql2o sql2o;

    public Sql2oLabelDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Label> all(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM label")
                    .executeAndFetch(Label.class);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
        return Collections.emptyList();
    }

    @Override
    public void add(Label label){
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery("INSERT INTO label (name) VALUES (:name)", true)
                    .bind(label)
                    .executeUpdate()
                    .getKey();
            label.setId(id);
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public Label find(int id){
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM label WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Label.class);
        }
        catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void delete(int id){
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM label WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll(){
        try(Connection con = sql2o.open()){
            con.createQuery("DELETE FROM label WHERE id != 1").executeUpdate();
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void edit(int id, String name){
        try(Connection con = sql2o.open()) {
            con.createQuery("UPDATE label SET name = :name WHERE id = :id")
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch(Sql2oException ex){
            System.out.println(ex);
        }
    }
}
