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

@WebServlet("/removeEdition")
public class RemoveEdition extends HttpServlet {

    private static final Logger log = Logger.getLogger(RemoveEdition.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionDAO editionDAO = new EditionDAOImplement();
        Integer id = Integer.valueOf(req.getParameter("id"));
        if (editionDAO.contains(id)) {
            editionDAO.delete(id);
            log.debug("Edition " + editionDAO.get(id) + " was delete");
            resp.sendRedirect("/index");
        } else {
            log.debug("Edition " + id + " not exist");
            resp.sendRedirect("/index");
        }
    }
}
