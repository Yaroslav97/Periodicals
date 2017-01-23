package ua.nure.poliakov.SummaryTask4.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;
import ua.nure.poliakov.SummaryTask4.utils.email.SendEmail;
import ua.nure.poliakov.SummaryTask4.utils.pay.Pay;

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
    private UserDAO userDAO;
    private EditionDAO editionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        String login = String.valueOf(req.getSession().getAttribute("authenticatedLogin"));
        userDAO = new UserDAOImplement();
        editionDAO = new EditionDAOImplement();
        log.info("Subscribe page");

        if (editionDAO.isContains(id) && !login.equals("null") && !editionDAO.isSubscribe(login, id) && Pay.isCanPay(login, id)) {
            editionDAO.subscribe(String.valueOf(req.getSession().getAttribute("authenticatedLogin")), id);
            req.getSession().setAttribute("authenticatedScore", userDAO.getByLogin(login).getScore());
            log.info(login + " subscribe to " + editionDAO.getEdition(id).getName() + "(" + id + ")");
            req.setAttribute("subscribeInfo", "You subscribe to " + editionDAO.getEdition(id).getName());
            if (userDAO.getSettings(login)) {
                // todo maybe create separate func?
                try {
                    SendEmail.sendEmail(userDAO.getByLogin(login).getEmail(), "Hello " +
                            userDAO.getByLogin(login).getFullName() + ", you successfully subscribe to " +
                            editionDAO.getEdition(id).getName() + " " + editionDAO.getEdition(id).getPrice() + "$.");
                } catch (MessagingException e) {
                    log.error("Can not send email to " + login);
                }
            }
            resp.sendRedirect("/index");
        } else if (editionDAO.isSubscribe(login, id)) {
            log.info(login + " already subscribes to this edition");
            req.setAttribute("subscribeInfo", "You already subscribe for " + editionDAO.getEdition(id).getName());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (!login.equals("null") && !Pay.isCanPay(login, id)) {
            log.info(login + " can not pay for subscribe ==> " + editionDAO.getEdition(id).getName());
            req.setAttribute("subscribeInfo", "You have not required balance");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (!editionDAO.isContains(id)) {
            log.info(id + " ==> not exist edition");
            req.setAttribute("subscribeInfo", "Wrong id edition");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (login.equals("null")){
            log.info("User is not authenticated");
            resp.sendRedirect("/signIn");
        }
    }
}
