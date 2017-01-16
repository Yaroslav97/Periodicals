package ua.nure.poliakov.utils.edition;

import ua.nure.poliakov.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.dao.edition_dao.EditionDAOImplement;

import javax.servlet.http.HttpServletRequest;

public class EditionsList {

    public static void getEditionList(HttpServletRequest req) {
        EditionDAO editionDAO = new EditionDAOImplement();
        String sort = req.getParameter("sort");
        String search = req.getParameter("search");

        String filter1 = req.getParameter("filter1");
        String filter2 = req.getParameter("filter2");

        req.getSession().setAttribute("countSub", editionDAO.getAllEditions("rank"));

        if (sort != null) {
            req.getSession().setAttribute("editionList", editionDAO.getAllEditions(sort));
        } else {
            if (search != null) {
                req.getSession().setAttribute("editionList", editionDAO.search(search));
            } else {
                if (filter1 != null && filter2 != null) {
                    if (Double.valueOf(filter1) < Double.valueOf(filter2)) {
                        req.getSession().setAttribute("editionList",
                                editionDAO.filter(Double.valueOf(filter1), Double.valueOf(filter2)));
                    }
                } else {
                    req.getSession().setAttribute("editionList", editionDAO.getAllEditions("subject"));
                }
            }
        }
    }
}
