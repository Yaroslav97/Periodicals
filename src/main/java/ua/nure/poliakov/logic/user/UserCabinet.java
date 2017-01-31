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

@WebServlet("/userCabinet")
public class UserCabinet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UserCabinet.class);
    private EditionDAO editionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        editionDAO = new EditionDAOImplement();
        req.getSession().setAttribute("subscribesList",
                editionDAO.getAllSubscribes(String.valueOf(req.getSession().getAttribute("authenticatedLogin"))));
        log.info("UserCabinet: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getRequestDispatcher("user//user_cabinet.jsp").forward(req, resp);
    }
}
