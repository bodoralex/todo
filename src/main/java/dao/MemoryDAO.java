package dao;

import Classes.ToDo;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by bodor on 2017.05.10..
 */
public class MemoryDAO implements ToDoDAO {
    private static MemoryDAO ourInstance = new MemoryDAO();

    public static MemoryDAO getInstance() {
        return ourInstance;
    }

    private Map<HttpSession, List<ToDo>> toDos = new HashMap<HttpSession, List<ToDo>>();
    private int idCounter = 0;

    private MemoryDAO() {
    }

    @Override
    public List<ToDo> getTodos(HttpSession session) {
        return toDos.get(session);
    }

    @Override
    public List<ToDo> getDoneToDos(HttpSession session) {
        List<ToDo> doneToDos = new ArrayList<>();
        for (ToDo todo : toDos.get(session)) {
            if (todo.isDone()) doneToDos.add(todo);
        }
        return doneToDos;
    }

    @Override
    public List<ToDo> getInProgressToDos(HttpSession session) {
        List<ToDo> inProgressToDos = new ArrayList<>();
        for (ToDo todo : toDos.get(session)) {
            if (!todo.isDone()) inProgressToDos.add(todo);
        }
        return inProgressToDos;
    }

    @Override
    public void addTodo(String text, HttpSession session) {

        if (text.length() < 1) return;
        if (!toDos.containsKey(session)) {
            System.out.println(text);
            toDos.put(session, new ArrayList<ToDo>());
        }
        toDos.get(session).add(new ToDo(text, idCounter++));
    }

    @Override
    public void toggleTodo(int id, HttpSession session) {

        Iterator<ToDo> iterator = getTodos(session).iterator();
        ToDo toDo;
        while (iterator.hasNext()) {
            toDo = iterator.next();
            if (toDo.getId() == id) {
                toDo.setDone(!toDo.isDone());
            }
        }
    }

    @Override
    public void deleteTodo(int id, HttpSession session) {
        Iterator<ToDo> iterator = getTodos(session).iterator();
        ToDo toDo;
        while (iterator.hasNext()) {
            toDo = iterator.next();
            if (toDo.getId() == id) {
                iterator.remove();
                return;
            }
        }
        toDos.get(session).remove(id);
    }
}
