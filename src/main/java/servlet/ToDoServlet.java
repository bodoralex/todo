package servlet;

import com.google.gson.Gson;
import dao.MemoryDAO;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemoryDAO.getInstance().addTodo(request.getParameter("text"), request.getSession());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("json");
        String status = request.getParameter("state");
        PrintWriter writer = response.getWriter();
        Gson gson = new Gson();
        MemoryDAO dao = MemoryDAO.getInstance();
        HttpSession session = request.getSession();

        switch (status){
            case "a":
                writer.println(gson.toJson(dao.getTodos(session)));
                break;
            case "p":
                writer.println(gson.toJson(dao.getInProgressToDos(session)));
                break;
            case "d":
                writer.println(gson.toJson(dao.getDoneToDos(session)));
        }

//        response.getWriter().println(new Gson().toJson(dao.getTodos(session)));
    }
}
