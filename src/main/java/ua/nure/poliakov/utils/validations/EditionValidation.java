package ua.nure.poliakov.utils.validations;

import javax.servlet.http.HttpServletRequest;

public class EditionValidation {

    public static boolean editionValidation(HttpServletRequest req) {

        return req.getParameter("name").length() >= 3 && req.getParameter("subject").length() >= 3 &&
                req.getParameter("price").length() >= 1 && req.getParameter("name").length() <= 35 &&
                req.getParameter("subject").length() <= 25 && req.getParameter("price").length() <= 4 &&
                !req.getParameter("name").isEmpty() && !req.getParameter("subject").isEmpty() &&
                !req.getParameter("price").isEmpty();
    }
}