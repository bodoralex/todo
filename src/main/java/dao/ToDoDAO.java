package dao;

import Classes.ToDo;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface ToDoDAO {

    List<ToDo> getTodos(HttpSession session);
    List<ToDo> getDoneToDos(HttpSession session);
    List<ToDo> getInProgressToDos(HttpSession session);
    void addTodo(String text, HttpSession session);
    void toggleTodo(int id, HttpSession session);
    void deleteTodo(int id, HttpSession session);

}
