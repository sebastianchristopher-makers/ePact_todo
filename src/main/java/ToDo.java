public class ToDo {

    private int id;
    private String content;
    private boolean complete;

    public ToDo (String content) {
        this.content = content;
        this.complete = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String newContent) {
        this.content = newContent;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }



}
