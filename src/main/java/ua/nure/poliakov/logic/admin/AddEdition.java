package ua.nure.poliakov.logic.admin;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.dao.entity.Edition;
import ua.nure.poliakov.utils.validations.EditionValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addEdition")
public class AddEdition extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddEdition.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("AddEdition page: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getRequestDispatcher("admin//add_addition.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionDAO editionDAO = new EditionDAOImplement();
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        Double price = Double.valueOf(req.getParameter("price"));

        if (EditionValidation.editionValidation(req) && !editionDAO.isSameEdition(name, subject)) {
            editionDAO.addEdition(new Edition(name, subject, price));
            log.debug(req.getSession().getAttribute("authenticatedLogin") + " Added new edition: " + name);
            resp.sendRedirect("/index");
        } else {
            log.debug("Not valid data");
            req.setAttribute("addEditionInfo", "Not valid data or the same edition already exist");
            req.getRequestDispatcher("admin//add_addition.jsp").forward(req, resp);
        }
    }
}
