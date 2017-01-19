package ua.nure.poliakov.logic;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.utils.edition.EditionsList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class Index extends HttpServlet {

    private static final Logger log = Logger.getLogger(Logger.class);
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionsList.editionList(req);
        userDAO = new UserDAOImplement();
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));
        if (!login.equals("null")) {
            req.getSession().setAttribute("authenticatedBan", userDAO.getByLogin(login).getBan());
        }
        log.info("Index page");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
