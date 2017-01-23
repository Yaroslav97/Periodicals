package ua.nure.poliakov.SummaryTask4.logic.registration;

import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Link from registration email
 */

@WebServlet("/link")
public class RegistrationLink extends HttpServlet {

    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDAO = new UserDAOImplement();
        if (userDAO.isContainsLogin(req.getParameter("login")) &&
                userDAO.getByLogin(req.getParameter("login")).getEmail().equals(req.getParameter("email"))) {
            userDAO.banUser(req.getParameter("login"), false);
            resp.sendRedirect("/index");
        }
    }
}
