package ua.nure.poliakov.SummaryTask4.logic.admin.editions;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.entity.Edition;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.SummaryTask4.logic.common.paths.WebPath;
import ua.nure.poliakov.SummaryTask4.utils.validations.Validator;
import ua.nure.poliakov.SummaryTask4.utils.validations.edition.ValidateEdition;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/addEdition")
public class AddEdition extends HttpServlet {

    private static final Logger log = Logger.getLogger(AddEdition.class);
    private EditionDAO editionDAO = EditionDAOImplement.getInstance();
    private Validator<Edition> validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.trace("AddEdition page: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getRequestDispatcher(WebPath.ADD_EDITION_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        Double price = Double.valueOf(req.getParameter("price"));
        validator = new ValidateEdition();

        try {
            if (validator.validate(new Edition(name, subject, price)) && !editionDAO.isSameEdition(name, subject)) {
                editionDAO.addEdition(new Edition(name, subject, price));
                log.debug(req.getSession().getAttribute("authenticatedLogin") + " Added new edition: " + name);
                resp.sendRedirect("/index");
            } else if (editionDAO.isSameEdition(name, subject)){
                log.debug("The same edition already exist");
                req.setAttribute("addEditionInfo", "The same edition already exist");
                req.getRequestDispatcher(WebPath.ADD_EDITION_PAGE).forward(req, resp);
            }
        } catch (ValidationException e) {
            log.error("Not valid data", e);
            req.setAttribute("addEditionInfo", "Not valid data");
            req.getRequestDispatcher(WebPath.ADD_EDITION_PAGE).forward(req, resp);
        }
    }
}
