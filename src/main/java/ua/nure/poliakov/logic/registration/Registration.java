package ua.nure.poliakov.logic.registration;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.entity.User;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.utils.email.SendEmail;
import ua.nure.poliakov.utils.encodind.Password;
import ua.nure.poliakov.utils.validations.UserValidation;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    private static final Logger log = Logger.getLogger(Registration.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImplement();

        String fullName = req.getParameter("fullName");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String role = req.getParameter("role");
        String password = req.getParameter("password");

        if (UserValidation.signUpValidation(req) && !userDAO.contains(login)) {
            if (role.equals("admin")) {
                userDAO.add(new User(fullName, login, email, .0, role, true, Password.encodePassword(password)));
                log.info("Add new admin: " + userDAO.getByLogin(login).getFullName());
                resp.sendRedirect("/index");
            } else {
                userDAO.add(new User(fullName, login, email, .0, role, true, Password.encodePassword(password)));
                log.info("Add new user: " + userDAO.getByLogin(login).getFullName());
                try {
                    SendEmail.sendEmail(email, "//http://localhost:8080/link?login=" + login + "&email=" + email);
                    log.info("Send registration email to " + fullName);
                } catch (MessagingException e) {
                    log.error("Fell sent email to: " + userDAO.getByLogin(login).getFullName());
                }
                resp.sendRedirect("/index");
            }
        } else {
            req.setAttribute("regInfo", "You try enter incorrect data");
            log.info("No valid data");
            resp.sendRedirect("/registration");
        }
    }
}