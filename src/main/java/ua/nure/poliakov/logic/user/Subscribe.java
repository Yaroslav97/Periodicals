package ua.nure.poliakov.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subscribe")
public class Subscribe extends HttpServlet {

    private static final Logger log = Logger.getLogger(Subscribe.class);

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionDAO editionDAO = new EditionDAOImplement();
        Integer id = Integer.valueOf(req.getParameter("id"));
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));

        if (editionDAO.contains(id) && !login.equals("null")) {
            if (!editionDAO.isSubscribe(login, id)) {
                editionDAO.subscribe(String.valueOf(req.getSession().getAttribute("authenticatedLogin")), id);
                log.info(login + " subscribe to " + editionDAO.get(id).getName() + "(" + id + ")");
                resp.sendRedirect("/index");
            } else {
                log.info(login + " already subscribes to this edition");
                resp.sendRedirect("/index");
            }
        } else {
            log.info(id + " does not exist edition or null user");
            resp.sendRedirect("/index");
        }
    }
}
