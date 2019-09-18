public class ToDo {

    private int id;
    private String content;
    private boolean complete;
    private int labelId;
    private int userId;

    public ToDo(String content){
        this.content = content;
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
}
