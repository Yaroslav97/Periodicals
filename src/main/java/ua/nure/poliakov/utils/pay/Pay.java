package ua.nure.poliakov.utils.pay;

import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;
import ua.nure.poliakov.dao.user_dao.UserDAO;
import ua.nure.poliakov.dao.user_dao.UserDAOImplement;

public class Pay {

    public static boolean isCanPay(String login, int idEdition) {
        UserDAO userDAO = new UserDAOImplement();
        EditionDAO editionDAO = new EditionDAOImplement();
        if (userDAO.getByLogin(login).getScore() - editionDAO.getEdition(idEdition).getPrice() >= 0) {
            return true;
        }
        return false;
    }
}
