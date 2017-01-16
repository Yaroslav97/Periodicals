package ua.nure.poliakov.utils.user;

import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UsersList {

    public static void getUsers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImplement();
        String role = req.getParameter("role");
        String search = req.getParameter("search");

        if (role != null) {
            switch (role) {
           /* case "all":
                req.getSession().setAttribute("userList", userDAO.getAllUsers());
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
                break;*/
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
                req.getSession().setAttribute("userList", userDAO.search(search));
                req.getRequestDispatcher("admin//user_list.jsp").forward(req, resp);
            }
        }
    }
}
