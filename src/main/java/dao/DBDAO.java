package dao;

import Classes.ToDo;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBDAO implements ToDoDAO {
    private Connection conn;

    public DBDAO(Connection conn) {
        this.conn = conn;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ToDo> getTodos(HttpSession session) {
        return executeQuery(String.format("SELECT * FROM todos WHERE session =\"%s\"", session.getId()));
    }

    @Override
    public List<ToDo> getDoneToDos(HttpSession session) {
        return executeQuery(String.format("SELECT * FROM todos WHERE session = \"%s\" AND done = 1", session.getId()));
    }

    @Override
    public List<ToDo> getInProgressToDos(HttpSession session) {
        return executeQuery(String.format("SELECT * FROM todos WHERE session = \"%s\" AND done = 0", session.getId()));
    }

    @Override
    public void addTodo(String text, HttpSession session) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO todos (text,session) VALUES(?, ?)");
            preparedStatement.setString(1, text);
            preparedStatement.setString(2, session.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<ToDo> executeQuery(String query) {
        List<ToDo> toDoList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                ToDo toDo = new ToDo(resultSet.getString("text"), resultSet.getInt("id"), resultSet.getInt("done"));
                toDoList.add(toDo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDoList;
    }

    @Override
    public void toggleTodo(int id, HttpSession session) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE todos SET done = ABS(done-1) WHERE id = ? AND session = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, session.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTodo(int id, HttpSession session) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM todos WHERE id = ? AND session = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, session.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
