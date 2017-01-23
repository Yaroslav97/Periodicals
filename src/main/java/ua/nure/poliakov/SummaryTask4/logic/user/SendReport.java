package ua.nure.poliakov.SummaryTask4.logic.user;

import org.apache.log4j.Logger;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.SummaryTask4.dao.entity.Edition;
import ua.nure.poliakov.SummaryTask4.utils.email.SendEmail;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/sendReport")
public class SendReport extends HttpServlet {

    private static final Logger log = Logger.getLogger(Logger.class);
    private EditionDAO editionDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        editionDAO = new EditionDAOImplement();

        List<Edition> list = editionDAO.getAllSubscriptions(
                String.valueOf(req.getSession().getAttribute("authenticatedLogin")));

        PrintWriter out = resp.getWriter();

        for (Edition edition : list) {
            out.write(edition.getName() + ", ");
            out.write(edition.getSubject() + ", ");
            out.print(edition.getPrice() + "; ");
            out.println();
        }

        try {
            SendEmail.sendEmail(String.valueOf(req.getSession().getAttribute("authenticatedEmail")), "");
        } catch (MessagingException e) {
            log.error("Can not send report");
        }
    }
}
