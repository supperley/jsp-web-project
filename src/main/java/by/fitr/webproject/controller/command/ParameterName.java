package by.fitr.webproject.controller.command;

public enum ParameterName {
    ;
    /*
     *user parameter
     *  */
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String CURRENT_PASSWORD = "current_password";
    public static final String EMAIL = "email";
    public static final String PAGES_PATH = "/config/page";
    public static final String COMMAND = "command";
    public static final String ERROR_MESSAGE_SIGN_IN = "error_message";
    public static final String USER = "user";
    public static final String USERS = "users";
    public static final String NEW_PASSWORD = "new_password";
    public static final String CONFIRM_NEW_PASSWORD = "confirm_new_password";
    public static final String LOGIN = "login";
    public static final String USER_DELETED = "user_deleted";
    public static final String USER_NOT_DELETED = "user_not_deleted";
    public static final String UPDATED_USER = "updated_user";
    public static final String USER_ID = "user_id";
    public static final String TEMPORARY_USER = "temp_user";

    /*
     * page parameters
     * */
    public static final String HOME_PAGE = "/pages/home.jsp";
    public static final String INDEX_PAGE = "/";
    public static final String USERS_PAGE = "/pages/users.jsp";
    public static final String REGISTRATION_PAGE = "/pages/registration.jsp";
    public static final String LIST_OF_USERS = "/pages/list-users.jsp";
    public static final String PASSWORD_UPDATE_PAGE = "/pages/password-update.jsp";
    public static final String ERROR_500_PAGE = "/pages/error/error_500.jsp";
    public static final String ERROR_404_PAGE = "/pages/error/error_404.jsp";
    public static final String NEW_LIST_OF_USERS_PAGE = "/pages/new-list-users.jsp";
    public static final String SIDEBAR_PAGE = "/pages/sidebar.jsp";

    /*
    * bootstrap pages
    * */
    public static final String BOOTSTRAP_REGISTRATION_PAGE = "/startbootstrap/register.jsp";
    public static final String BOOTSTRAP_HOME_PAGE = "/startbootstrap/home.jsp";
    public static final String REGISTRATION_CONFIRMATION_PAGE = "/startbootstrap/registration-confirmation-page.jsp";
    public static final String BOOTSTRAP_USERS_LIST_TABLE = "/startbootstrap/user-table.jsp";
    public static final String BOOTSTRAP_PROFILE_PAGE = "/startbootstrap/profile.jsp";
    public static final String BOOTSTRAP_PASSWORD_CHANGE = "/startbootstrap/password-change.jsp";

    /*
     * Session attributes
     * */
    public static final String LOCALE = "locale";
    public static final String CURRENT_PAGE = "current_page";
    public static final String AUTHORIZED_USER = "authUser";
    public static final String LANGUAGE = "language";
    public static final String ROLE = "role";

    /*
     * error messages
     * */
    public static final String UNAVAILABLE_EMAIL_ADDRESS = "unavailable_email_address";
    public static final String UNAVAILABLE_LOGIN = "unavailable_login";
    /*
    * operation
    * */
    public static final String OPERATION_MESSAGE = "operation_message";
    public static final String PASSWORD_CHANGE_MESSAGE = "password_change_message";

    /*
    * permission parameters
    * */
    public static final String NO_PERMISSION = "no permission";
}
