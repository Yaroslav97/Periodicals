package ua.nure.poliakov.SummaryTask4.logic.admin.editions;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAOImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeEdition")
public class RemoveEdition extends HttpServlet {

    private static final Logger log = Logger.getLogger(RemoveEdition.class);
    private EditionDAO editionDAO = EditionDAOImplement.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        log.debug("RemoveEdition: " + req.getSession().getAttribute("authenticatedLogin"));
        if (editionDAO.isContains(id)) {
            editionDAO.deleteEdition(id);
            log.debug("Edition " + editionDAO.getEdition(id) + " was deleteEdition");
            resp.sendRedirect("/index");
        } else {
            log.debug("Edition " + id + " not exist");
            resp.sendRedirect("/index");
        }
    }
}
