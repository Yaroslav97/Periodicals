package ua.nure.poliakov.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUser extends HttpServlet{

    private static final Logger log = Logger.getLogger(DeleteUser.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImplement();
        userDAO.delete(req.getParameter("login"));
        log.info(req.getParameter("login") + " removed his account");
        req.getSession().invalidate();
        resp.sendRedirect("/index");
    }
}
