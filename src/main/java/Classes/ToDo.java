package Classes;

/**
 * Created by bodor on 2017.05.10..
 */
public class ToDo {
    private String text;
    private boolean done;
    private int id;


    public ToDo(String text, int id) {
        this.text = text;
        this.done = false;
        this.id = id;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDo{" +
            "text='" + text + '\'' +
            ", done=" + done +
            ", id=" + id +
            '}';
    }
}
