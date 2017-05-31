package dao;

import Classes.ToDo;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by bodor on 2017.05.10..
 */
public class MemoryDAO implements ToDoDAO {
    private static MemoryDAO ourInstance = new MemoryDAO();
    private Map<HttpSession, List<ToDo>> toDos = new HashMap<>();
    private int idCounter = 0;

    private MemoryDAO() {
    }

    public static MemoryDAO getInstance() {
        return ourInstance;
    }

    @Override
    public List<ToDo> getTodos(HttpSession session) {
        return toDos.get(session);
    }

    @Override
    public List<ToDo> getDoneToDos(HttpSession session) {
        List<ToDo> doneToDos = new ArrayList<>();
        for (ToDo todo : toDos.get(session)) {
            if (todo.isDone() == 1) doneToDos.add(todo);
        }
        return doneToDos;
    }

    @Override
    public List<ToDo> getInProgressToDos(HttpSession session) {
        List<ToDo> inProgressToDos = new ArrayList<>();
        for (ToDo todo : toDos.get(session)) {
            if (todo.isDone() == 0) inProgressToDos.add(todo);
        }
        return inProgressToDos;
    }

    @Override
    public void addTodo(String text, HttpSession session) {

        if (text.length() < 1) return;
        if (!toDos.containsKey(session)) {
            toDos.put(session, new ArrayList<>());
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
                toDo.setDone(Math.abs(toDo.isDone() - 1));
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
