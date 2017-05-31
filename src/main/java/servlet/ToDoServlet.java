package servlet;

import com.google.gson.Gson;
import dao.DBDAO;
import dao.ToDoDAO;
import util.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by bodor on 2017.05.11..
 */
@WebServlet("/todo/*")
public class ToDoServlet extends HttpServlet {

    //ToDoDAO dao = MemoryDAO.getInstance();
    private ToDoDAO dao = new DBDAO(ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.ToDo));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao.addTodo(request.getParameter("text"), request.getSession());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=UTF-8");
        String status = request.getParameter("state");
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        HttpSession session = request.getSession();

        switch (status) {
            case "a":
                writer.println(gson.toJson(dao.getTodos(session)));
                break;
            case "p":
                writer.println(gson.toJson(dao.getInProgressToDos(session)));
                break;
            case "d":
                writer.println(gson.toJson(dao.getDoneToDos(session)));
                break;
        }
        writer.close();
    }

    @Override
    public void destroy() {
        if (dao instanceof DBDAO) ((DBDAO) dao).closeConnection();
    }
}
