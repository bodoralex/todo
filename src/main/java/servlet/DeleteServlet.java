package servlet;

import dao.DBDAO;
import dao.ToDoDAO;
import util.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bodor on 2017.05.11..
 */
@WebServlet("/delete/*")
public class DeleteServlet extends HttpServlet {

    //ToDoDAO dao = MemoryDAO.getInstance();
    private ToDoDAO dao = new DBDAO(ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.ToDo));

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deleteTodo(id, request.getSession());
    }

    @Override
    public void destroy() {
        if (dao instanceof DBDAO) ((DBDAO) dao).closeConnection();
    }

}
