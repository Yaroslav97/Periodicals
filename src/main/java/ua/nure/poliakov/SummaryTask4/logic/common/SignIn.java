package ua.nure.poliakov.SummaryTask4.logic.common;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.SummaryTask4.logic.common.paths.WebPath;
import ua.nure.poliakov.SummaryTask4.utils.encodind.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignIn extends HttpServlet {

    private static final Logger log = Logger.getLogger(SignIn.class);
    private UserDAO userDAO = UserDAOImplement.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(WebPath.LOGIN_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userDAO.isContainsLogin(login) && userDAO.getByLogin(login).getPassword().
                equals(Password.encodePassword(password)) && !userDAO.getByLogin(login).getBan()) {
            session.setAttribute("authenticatedLogin", userDAO.getByLogin(login).getLogin());
            session.setAttribute("authenticatedFullName", userDAO.getByLogin(login).getFullName());
            session.setAttribute("authenticatedEmail", userDAO.getByLogin(login).getEmail());
            session.setAttribute("authenticatedRole", userDAO.getByLogin(login).getRole());
            session.setAttribute("authenticatedBan", userDAO.getByLogin(login).getBan());
            session.setAttribute("authenticatedScore", userDAO.getScore(login));
            session.setAttribute("notification", userDAO.getSettings(login));
            log.info("sign in ==> " + login);
            resp.sendRedirect("/index");
        } else if (!userDAO.isContainsLogin(login)) {
            log.info("Incorrect login or login not exist");
            req.getRequestDispatcher(WebPath.LOGIN_ERROR_PAGE).forward(req, resp);
        } else if (!userDAO.getByLogin(login).getPassword().equals(Password.encodePassword(password))) {
            req.setAttribute("signInInfo", "Wrong password");
            log.info("Wrong password ==> " + login);
            req.getRequestDispatcher(WebPath.LOGIN_PAGE).forward(req, resp);
        } else if (userDAO.getByLogin(login).getBan()) {
            log.info("Access denied ==> " + login);
            req.getRequestDispatcher(WebPath.ACCESS_DENIED_PAGE).forward(req, resp);

        }
    }
}
