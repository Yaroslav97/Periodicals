package ua.nure.poliakov.SummaryTask4.utils.user;

import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersList {

    private static UserDAO userDAO;

    public static void getUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        String search = req.getParameter("search");
        userDAO = new UserDAOImplement();
        if (role != null) {
            switch (role) {
                case "users":
                    req.getSession().setAttribute("userList", userDAO.getAllUsersByRole("user"));
                    req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
                    break;
                case "admins":
                    req.getSession().setAttribute("userList", userDAO.getAllUsersByRole("admin"));
                    req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
                    break;
                default:
                    req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
            }
        } else {
            if (search != null) {
                req.getSession().setAttribute("userList", userDAO.searchByName(search));
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
            }
        }
    }
}
