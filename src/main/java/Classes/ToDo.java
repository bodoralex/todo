package Classes;

/**
 * Created by bodor on 2017.05.10..
 */
public class ToDo {
    private String text;
    private int done;
    private int id;


    public ToDo(String text, int id) {
        this.text = text;
        this.done = 0;
        this.id = id;

    }

    public ToDo(String text, int id, int done) {
        this.text = text;
        this.done = done;
        this.id = id;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int isDone() {
        return done;
    }

    public void setDone(int done) {
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
