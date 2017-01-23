package ua.nure.poliakov.SummaryTask4.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.entity.User;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.SummaryTask4.utils.encodind.Password;
import ua.nure.poliakov.SummaryTask4.utils.validations.Validator;
import ua.nure.poliakov.SummaryTask4.utils.validations.user.ValidateUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/editProfile")
public class EditProfile extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditProfile.class);
    private UserDAO userDAO;
    private Validator<User> validator;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("EditProfile: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getRequestDispatcher("user//edit_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String login = String.valueOf(session.getAttribute("authenticatedLogin"));
        Boolean notification = Boolean.valueOf(req.getParameter("notification"));
        userDAO = new UserDAOImplement();
        validator = new ValidateUser();

        try {
            if (validator.validate(new User(fullName, login, email, password))) {
                userDAO.updateUser(new User(fullName, login, email, Password.encodePassword(password)));
                userDAO.updateSettings(login, notification);
                log.info(login + " profile was updated");
                session.setAttribute("authenticatedFullName", userDAO.getByLogin(login).getFullName());
                session.setAttribute("authenticatedEmail", userDAO.getByLogin(login).getEmail());
                session.setAttribute("notification", userDAO.getSettings(login));
                resp.sendRedirect("/userCabinet");
            }
        } catch (ValidationException e) {
            log.error(e);
            log.info("No valid data");
            req.setAttribute("editInfo", "You try enter incorrect data");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
            req.getRequestDispatcher("user//edit_profile.jsp").forward(req, resp);
        }
    }
}
