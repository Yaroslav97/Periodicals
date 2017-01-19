package ua.nure.poliakov.logic.admin;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userInfo")
public class UserInfo extends HttpServlet {

    private static final Logger log = Logger.getLogger(UserInfo.class);
    private EditionDAO editionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        editionDAO = new EditionDAOImplement();
        req.getSession().setAttribute("usersInfo", editionDAO.getAllSubscribes(login));
        log.info("Information about ==> " + login);
        req.getRequestDispatcher("admin//user_profile.jsp").forward(req, resp);
    }
}
