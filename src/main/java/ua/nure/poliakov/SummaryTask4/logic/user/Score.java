package ua.nure.poliakov.SummaryTask4.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.SummaryTask4.utils.encodind.Password;
import ua.nure.poliakov.SummaryTask4.utils.validations.user.UserValidate;
import ua.nure.poliakov.SummaryTask4.utils.validations.user.ValidateUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/score")
public class Score extends HttpServlet {

    private static final Logger log = Logger.getLogger(Score.class);
    private UserDAO userDAO;
    private UserValidate<String, Double> userValidate;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user//refill_score_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double score = Double.valueOf(req.getParameter("score"));
        String password = req.getParameter("password");
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));
        userDAO = new UserDAOImplement();
        userValidate = new ValidateUser();

        try {
            if (userValidate.score(score)) {
                if (userDAO.getByLogin(login).getPassword().equals(Password.encodePassword(password))) {
                    userDAO.updateScore(login, "refill", score);
                    req.getSession().setAttribute("authenticatedScore", userDAO.getByLogin(login).getScore());
                    log.info("Refill score account " + login);
                    resp.sendRedirect("/userCabinet");
                } else {
                    log.info("Wrong password");
                    req.setAttribute("scoreInfo", "Wrong password");
                    req.getRequestDispatcher("user//refill_score_page.jsp").forward(req, resp);
                }
            }
        } catch (ValidationException e) {
            log.error(e);
            log.info("Not valid input data");
            req.setAttribute("scoreInfo", "Not valid data");
            req.getRequestDispatcher("user//refill_score_page.jsp").forward(req, resp);
        }
    }
}