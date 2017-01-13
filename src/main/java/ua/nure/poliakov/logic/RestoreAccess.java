package ua.nure.poliakov.logic;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.utils.email.SendEmail;
import ua.nure.poliakov.utils.encodind.Password;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/restoreAccess")
public class RestoreAccess extends HttpServlet {

    private static final Logger log = Logger.getLogger(RestoreAccess.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("restore_access.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAOImplement();
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = SendEmail.randomPassword();

        if (userDAO.contains(login) && userDAO.getByLogin(login).getEmail().equals(email)) {
            try {
                SendEmail.sendMail(userDAO.getByLogin(login).getEmail(),
                        SendEmail.restoreAccess(userDAO.getByLogin(login).getFullName(), password));
                log.info("Message sent successfully to " + userDAO.getByLogin(login).getFullName());
                userDAO.updatePassword(login, Password.encodePassword(password));
                log.info("Password was change");
                resp.sendRedirect("/userCabinet");
            } catch (MessagingException e) {
                log.info("Can't send restore message to " + userDAO.getByLogin(login).getFullName());
            }
        } else {
            log.info("Not valid data");
            req.getRequestDispatcher("restore_access.jsp").forward(req, resp);
        }
    }
}