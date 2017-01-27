package ua.nure.poliakov.SummaryTask4.logic.common.paths;

/**
 * JSP Relative path
 */

public interface WebPath {

    String INDEX_PAGE = "index.jsp";
    String REGISTRATION_PAGE = "WEB-INF/registration.jsp";
    String LOGIN_PAGE = "WEB-INF/login_page.jsp";
    String LOGIN_ERROR_PAGE = "WEB-INF/login_error.jsp";
    String ACCESS_DENIED_PAGE = "WEB-INF/access_denied.jsp";
    String RESTORE_ACCESS_PAGE = "WEB-INF/restore_access.jsp";

    String ADD_EDITION_PAGE = "WEB-INF/admin/add_edition.jsp";
    String EDIT_EDITION_PAGE = "WEB-INF/admin/edit_edition.jsp";
    String EDITION_INFO_PAGE = "WEB-INF/admin/edition_profile.jsp";
    String USER_LIST_PAGE = "WEB-INF/admin/user_list.jsp";
    String USER_PROFILE_PAGE = "WEB-INF/admin/user_profile.jsp";

    String EDIT_PROFILE_PAGE = "WEB-INF/user/edit_profile.jsp";
    String REFILL_SCORE_PAGE = "WEB-INF/user/refill_score_page.jsp";
    String USER_CABINET_PAGE = "WEB-INF/user/user_cabinet.jsp";
}
