package ua.nure.poliakov.logic;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signIn")
public class SignIn extends HttpServlet {

    private static final Logger log = Logger.getLogger(SignIn.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login_page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDAO userDAO = new UserDAOImplement();
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userDAO.isContainsLogin(login) && UserValidation.signInValidation(req)){
            if (!userDAO.getByLogin(login).getBan()) {
                if (userDAO.getByLogin(login).getPassword().equals(Password.encodePassword(password))) {
                    session.setAttribute("authenticatedLogin", userDAO.getByLogin(login).getLogin());
                    session.setAttribute("authenticatedFullName", userDAO.getByLogin(login).getFullName());
                    session.setAttribute("authenticatedEmail", userDAO.getByLogin(login).getEmail());
                    session.setAttribute("authenticatedRole", userDAO.getByLogin(login).getRole());
                    session.setAttribute("authenticatedScore", userDAO.getByLogin(login).getScore());
                    session.setAttribute("notification", userDAO.getSettings(login));
                    resp.sendRedirect("/index");
                } else {
                    req.setAttribute("signInInfo", "Wrong password");
                    log.info("Wrong password ==> " + login);
                    //resp.sendRedirect("/signIn");
                    req.getRequestDispatcher("login_page.jsp").forward(req, resp);
                }
            }
            else {
                log.info("Access denied ==> " + login);
                resp.sendRedirect("access_denied.jsp");
            }
        }else {
            resp.sendRedirect("login_error.jsp");
        }
    }
}
