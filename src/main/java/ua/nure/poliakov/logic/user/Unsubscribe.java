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

@WebServlet("/unsubscribe")
public class Unsubscribe extends HttpServlet {

    private static final Logger log = Logger.getLogger(Unsubscribe.class);
    private EditionDAO editionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));
        Integer idEdition = Integer.valueOf(req.getParameter("id"));
        editionDAO = new EditionDAOImplement();
        log.info("Unsubscribe: " + req.getSession().getAttribute("authenticatedLogin"));
        if (editionDAO.isSubscribe(login, idEdition)) {
            editionDAO.unsubscribe(login, idEdition);
            req.getSession().setAttribute("subscribesList", editionDAO.getAllSubscribes
                    (String.valueOf(req.getSession().getAttribute("authenticatedLogin"))));
            log.info(login + " unsubscribes " + editionDAO.getEdition(idEdition).getName());
            resp.sendRedirect("user//user_cabinet.jsp");
        } else {
            log.info(login + " does not subscribes to " + editionDAO.getEdition(idEdition).getName() + " or edition not exist");
            resp.sendRedirect("user//user_cabinet.jsp");
        }
    }
}
