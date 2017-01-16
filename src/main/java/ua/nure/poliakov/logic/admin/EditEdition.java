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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/editEdition")
public class EditEdition extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditEdition.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("editId", req.getParameter("eId"));
        session.setAttribute("editName", req.getParameter("eName"));
        session.setAttribute("editSubject", req.getParameter("eSubject"));
        session.setAttribute("editPrice", req.getParameter("ePrice"));
        log.info("EditEdition page: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getRequestDispatcher("admin//edit_addition.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionDAO editionDAO = new EditionDAOImplement();
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        Double price = Double.valueOf(req.getParameter("price"));
        Integer id = Integer.parseInt(String.valueOf(req.getSession().getAttribute("editId")));

        if (EditionValidation.editionValidation(req)) {
            editionDAO.update(new Edition(id, name, subject, price));
            log.info("Edition " + editionDAO.get(id).getName() + " was change");
            resp.sendRedirect("/index");
        } else {
            log.info("Not valid data ==> " + req.getSession().getAttribute("editId"));
            resp.sendRedirect("/editEdition");
        }
    }
}