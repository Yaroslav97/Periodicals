package ua.nure.poliakov.logic.admin;

import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userList")
public class UserList extends HttpServlet {

    private UserDAO userDAO = new UserDAOImplement();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().setAttribute("userList", userDAO.getAllUsers());
        req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");

        switch (role) {
            case "all":
                req.getSession().setAttribute("userList", userDAO.getAllUsers());
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
                break;
            case "users":
                req.getSession().setAttribute("userList", userDAO.getAllUsersByRole("user"));
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
                break;
            case "admins":
                req.getSession().setAttribute("userList", userDAO.getAllUsersByRole("admin" +
                        ""));
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
        }
    }
}