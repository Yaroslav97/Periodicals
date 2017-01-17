package ua.nure.poliakov.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.entity.User;
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

@WebServlet("/editProfile")
public class EditProfile extends HttpServlet {

    private static final Logger log = Logger.getLogger(EditProfile.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("EditProfile: " + req.getSession().getAttribute("authenticatedLogin"));
        req.getRequestDispatcher("user//edit_profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDAO userDAO = new UserDAOImplement();
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String login = String.valueOf(session.getAttribute("authenticatedLogin"));
        Boolean notification = Boolean.valueOf(req.getParameter("notification"));

        if (UserValidation.updateValidation(req)) {
            userDAO.updateUser(new User(fullName, login, email, Password.encodePassword(password)));
            userDAO.updateSettings(login, notification);
            log.info(login + " profile was updated");
            session.setAttribute("authenticatedFullName", userDAO.getByLogin(login).getFullName());
            session.setAttribute("authenticatedEmail", userDAO.getByLogin(login).getEmail());
            session.setAttribute("notification", userDAO.getSettings(login));
            resp.sendRedirect("/userCabinet");
        } else {
            log.info("Not valid data");
            req.setAttribute("editInfo", "Not valid data");
            req.getRequestDispatcher("user//edit_profile.jsp").forward(req, resp);
        }
    }
}