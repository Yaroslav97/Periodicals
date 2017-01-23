package ua.nure.poliakov.SummaryTask4.utils.pay;

import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAO;
import ua.nure.poliakov.SummaryTask4.dao.user_dao.UserDAOImplement;

public class Pay {

    private static UserDAO userDAO;
    private static EditionDAO editionDAO;

    public static boolean isCanPay(String login, int idEdition) {
        userDAO = new UserDAOImplement();
        editionDAO = new EditionDAOImplement();
        if (userDAO.getByLogin(login).getScore() - editionDAO.getEdition(idEdition).getPrice() >= 0) {
            return true;
        }
        return false;
    }
}
