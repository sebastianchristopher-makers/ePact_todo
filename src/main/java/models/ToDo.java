package models;

import java.util.ArrayList;
import java.util.Objects;

public class ToDo {

    private int id;
    private String content;
    private boolean complete;
    private int labelId;
    private int userId;

    public ToDo(String content, int userId, int labelId){
        this.content = content;
        this.userId = userId;
        this.labelId = labelId;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContent(String newContent){
        this.content = newContent;
    }

    public String getContent(){
        return this.content;
    }

    public void setComplete(boolean isComplete){
        this.complete = isComplete;
    }

    public boolean getComplete() {
        return this.complete;
    }

    public void setLabelId(int newLabelId){
        this.labelId = newLabelId;
    }

    public int getLabelId() {
        return this.labelId;
    }

    public void setUserId(int newUserId){
        this.userId = newUserId;
    }

    public int getUserId() {
        return this.userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDo toDo = (ToDo) o;
        return id == toDo.id &&
                complete == toDo.complete &&
                labelId == toDo.labelId &&
                userId == toDo.userId &&
                Objects.equals(content, toDo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, complete, labelId, userId);
    }
}
