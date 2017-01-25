package ua.nure.poliakov.SummaryTask4.logic.admin.users;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeStatus")
public class EditStatus extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditStatus.class);
    private UserDAO userDAO = UserDAOImplement.getInstance();;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        log.trace("EditStatus page: " + req.getSession().getAttribute("authenticatedLogin"));
        if (userDAO.getByLogin(login).getBan().equals(true)) {
            userDAO.banUser(login, false);
            log.trace("Status was change ==> " + login);
            resp.sendRedirect("/userList");
        } else {
            userDAO.banUser(login, true);
            log.trace("Status was change ==> " + login);
            resp.sendRedirect("/userList");
        }
    }
}