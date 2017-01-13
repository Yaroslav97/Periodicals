package ua.nure.poliakov.logic;

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

@WebServlet("/index")
public class Index extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImplement();
        EditionDAO editionDAO = new EditionDAOImplement();
        req.getSession().setAttribute("editionList", editionDAO.getAllEditions());
        /*req.getSession().setAttribute("userList", userDAO.getByLogin("authenticatedLogin"));*/
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
