package ua.nure.poliakov.logic.admin;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editionInfo")
public class EditionInfo extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditionInfo.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionDAO editionDAO = new EditionDAOImplement();
        UserDAO userDAO = new UserDAOImplement();
        Integer id = Integer.parseInt(req.getParameter("info"));

        log.info("EditionInfo page: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getSession().setAttribute("editionInfo", editionDAO.getEditionInfo(id));
        req.getSession().setAttribute("subList", userDAO.getSubscribers(id));
        req.getRequestDispatcher("admin//edition_info.jsp").forward(req, resp);
    }
}
