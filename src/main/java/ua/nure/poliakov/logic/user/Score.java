package ua.nure.poliakov.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.utils.encodind.Password;
import ua.nure.poliakov.utils.validations.UserValidation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/score")
public class Score extends HttpServlet {

    private static final Logger log = Logger.getLogger(Score.class);
    private UserDAO userDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user//score_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double score = Double.valueOf(req.getParameter("score"));
        String password = req.getParameter("password");
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));
        userDAO = new UserDAOImplement();

        if (UserValidation.refillScoreValidation(req)) {
            if (userDAO.getByLogin(login).getPassword().equals(Password.encodePassword(password))) {
                userDAO.updateScore(login, "refill", score);
                req.getSession().setAttribute("authenticatedScore", userDAO.getByLogin(login).getScore());
                log.info("Refill score account " + login);
                resp.sendRedirect("/userCabinet");
            } else {
                req.setAttribute("scoreInfo", "Wrong password");
                log.info("Not valid input data");
                req.getRequestDispatcher("user//score_page.jsp").forward(req, resp);
            }
        } else {
            log.info("Not valid input data");
            req.getRequestDispatcher("user//score_page.jsp").forward(req, resp);
        }
    }
}