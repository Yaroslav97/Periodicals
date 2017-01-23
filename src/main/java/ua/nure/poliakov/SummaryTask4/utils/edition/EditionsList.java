package ua.nure.poliakov.SummaryTask4.utils.edition;

import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAO;
import ua.nure.poliakov.SummaryTask4.dao.edition_dao.EditionDAOImplement;

import javax.servlet.http.HttpServletRequest;

public class EditionsList {

    private static EditionDAO editionDAO = new EditionDAOImplement();

    public static void editionList(HttpServletRequest req) {
        String sort = req.getParameter("sort");
        String search = req.getParameter("search");
        String filter1 = req.getParameter("filter1");
        String filter2 = req.getParameter("filter2");
        String subject = req.getParameter("subject");
        req.getSession().setAttribute("countSub", editionDAO.getAllSortEditions("rank"));

        if (sort != null) {
            req.getSession().setAttribute("editionList", editionDAO.getAllSortEditions(sort));
        } else if (search != null) {
            req.getSession().setAttribute("editionList", editionDAO.searchByName(search));
        } else if (filter1 != null && filter2 != null && Double.valueOf(filter1) < Double.valueOf(filter2)) {
            req.getSession().setAttribute("editionList",
                    editionDAO.filterByPrice(Double.valueOf(filter1), Double.valueOf(filter2)));
        } else if (subject != null) {
            req.getSession().setAttribute("editionList", editionDAO.getEditionsBySubject(subject));
        } else {
            req.getSession().setAttribute("editionList", editionDAO.getAllSortEditions("subject"));
        }
    }
}
