package ua.nure.poliakov.utils.validations;

import javax.servlet.http.HttpServletRequest;

public class UserValidation {

    public static boolean signUpValidation(HttpServletRequest req) {

        return req.getParameter("fullName").length() >= 4 && req.getParameter("login").length() >= 4 &&
                req.getParameter("email").length() >= 4 && req.getParameter("password").length() >= 4 &&
                !req.getParameter("fullName").isEmpty() && !req.getParameter("login").isEmpty() &&
                !req.getParameter("email").isEmpty() && !req.getParameter("password").isEmpty();
    }

    public static boolean signInValidation(HttpServletRequest req) {

        return req.getParameter("login").length() >= 4 && req.getParameter("password").length() >= 4 &&
                !req.getParameter("login").isEmpty() && !req.getParameter("password").isEmpty();
    }

    public static boolean updateValidation(HttpServletRequest req) {

        return req.getParameter("fullName").length() >= 4 && req.getParameter("email").length() >= 4 &&
                req.getParameter("password").length() >= 4 && !req.getParameter("fullName").isEmpty() &&
                !req.getParameter("email").isEmpty() && !req.getParameter("password").isEmpty();
    }

    public static boolean refillScoreValidation(HttpServletRequest req) {

        return req.getParameter("score").length() >= 1 && req.getParameter("score").length() <= 4 &&
                req.getParameter("password").length() >= 4 && !req.getParameter("score").isEmpty() &&
                !req.getParameter("password").isEmpty();
    }
}