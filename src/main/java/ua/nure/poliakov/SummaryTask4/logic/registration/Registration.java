package ua.nure.poliakov.SummaryTask4.logic.registration;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.entity.User;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.SummaryTask4.utils.email.SendEmail;
import ua.nure.poliakov.SummaryTask4.utils.encodind.Password;
import ua.nure.poliakov.SummaryTask4.utils.validations.Validator;
import ua.nure.poliakov.SummaryTask4.utils.validations.user.ValidateUser;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    private static final Logger log = Logger.getLogger(Registration.class);
    private UserDAO userDAO;
    private Validator<User> validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String password = req.getParameter("password");
        userDAO = new UserDAOImplement();
        validator = new ValidateUser();

        try {
            if (validator.validate(new User(fullName, login, email, password)) && !userDAO.isContainsLogin(login)) {
                if (role.equals("admin")) {
                    userDAO.addUser(new User(fullName, login, email, .0, role, true, Password.encodePassword(password)));
                    log.info("Added new admin: " + userDAO.getByLogin(login).getFullName());
                    resp.sendRedirect("/index");
                } else if (role.equals("user")) {
                    userDAO.addUser(new User(fullName, login, email, .0, role, true, Password.encodePassword(password)));
                    log.info("Added new user: " + userDAO.getByLogin(login).getFullName());
                    try {
                        SendEmail.sendEmail(email, "For confirmation registration click to link " +
                                "//http://localhost:8080/link?login=" + login + "&email=" + email);
                        log.info("Send registration email to " + fullName);
                    } catch (MessagingException e) {
                        log.error("Failed to send a message to: " + userDAO.getByLogin(login).getFullName());
                    }
                    resp.sendRedirect("/index");
                }
            } else if (userDAO.isContainsLogin(login)) {
                req.setAttribute("regInfo", "This login already exist");
                log.info("This login already exist");
                req.getRequestDispatcher("registration.jsp").forward(req, resp);
            }
        } catch (ValidationException e) {
            log.error(e);
            log.info("No valid data");
            req.setAttribute("regInfo", "You try enter incorrect data");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
