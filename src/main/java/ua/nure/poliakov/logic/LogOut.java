package ua.nure.poliakov.logic;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class LogOut extends HttpServlet {

    private static final Logger log = Logger.getLogger(LogOut.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info(req.getSession().getAttribute("authenticatedLogin") + " was log out");
        req.getSession().invalidate();
        resp.sendRedirect("/index");
    }
}
