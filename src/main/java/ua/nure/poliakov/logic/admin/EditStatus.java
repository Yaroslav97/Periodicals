package ua.nure.poliakov.logic.admin;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeStatus")
public class EditStatus extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditStatus.class);
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDAO = new UserDAOImplement();
        String login = req.getParameter("login");
        log.info("EditStatus page: " + req.getSession().getAttribute("authenticatedLogin"));
        if (userDAO.getByLogin(login).getBan().equals(true)) {
            userDAO.banUser(login, false);
            log.info("Status was change ==> " + login);
            resp.sendRedirect("/userList");
        } else {
            userDAO.banUser(login, true);
            log.info("Status was change ==> " + login);
            resp.sendRedirect("/userList");
        }
    }
}