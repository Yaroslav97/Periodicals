package ua.nure.poliakov.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.utils.email.SendEmail;
import ua.nure.poliakov.utils.pay.Pay;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subscribe")
public class Subscribe extends HttpServlet {

    private static final Logger log = Logger.getLogger(Subscribe.class);

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EditionDAO editionDAO = new EditionDAOImplement();
        UserDAO userDAO = new UserDAOImplement();
        Integer id = Integer.valueOf(req.getParameter("id"));
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));

        log.info("Subscribe page");

        if (editionDAO.isContains(id) && login != null) {
            if (!editionDAO.isSubscribe(login, id)) {
                if (Pay.isCanPay(login, id)) {
                    editionDAO.subscribe(String.valueOf(req.getSession().getAttribute("authenticatedLogin")), id);
                    req.getSession().setAttribute("authenticatedScore", userDAO.getByLogin(login).getScore());
                    log.info(login + " subscribe to " + editionDAO.getEdition(id).getName() + "(" + id + ")");
                    req.setAttribute("subscribeInfo", "You subscribe to " + editionDAO.getEdition(id).getName());
                    if (userDAO.getSettings(login) == true) {
                        try {
                            SendEmail.sendEmail(userDAO.getByLogin(login).getEmail(),
                                    "Hello " + userDAO.getByLogin(login).getFullName() +
                                            ", you successfully subscribe to " + editionDAO.getEdition(id).getName() +
                                            " " + editionDAO.getEdition(id).getPrice() + "$.");
                        } catch (MessagingException e) {
                            log.error("Can not send email to " + login);
                        }
                    } else {
                        log.info("Notifications is off");
                    }

                    resp.sendRedirect("/index");
                } else {
                    log.info(login + " can not pay for subscribe ==> " + editionDAO.getEdition(id).getName());
                    req.setAttribute("subscribeInfo", "You have not required balance");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
            } else {
                log.info(login + " already subscribes to this edition");
                req.setAttribute("subscribeInfo", "You already subscribe for " +
                        editionDAO.getEdition(id).getName());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            log.info(id + " does not exist edition or null user");
            resp.sendRedirect("/index");
        }
    }
}