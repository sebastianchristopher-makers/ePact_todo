public class ToDo {
    String content;
    boolean complete;

    public ToDo(String content){
        this.content = content;
        this.complete = false;
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
}
