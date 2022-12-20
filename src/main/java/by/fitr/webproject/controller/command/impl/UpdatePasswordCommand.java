package by.fitr.webproject.controller.command.impl;

import by.fitr.webproject.controller.command.Command;
import by.fitr.webproject.controller.command.ParameterName;
import by.fitr.webproject.controller.command.Router;
import by.fitr.webproject.service.UserService;
import by.fitr.webproject.service.exception.ServiceException;
import by.fitr.webproject.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.fitr.webproject.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdatePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String PASSWORD_CHANGE_SUCCESS = "Password has been successfully changed";
    private static final String PASSWORD_CHANGE_FAILURE = "Incorrect current OR new password";
    private static final String NEW_PASSWORDS_ARE_NOT_THE_SAME = "Two new passwords are not the same";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();

        String username = request.getParameter(ParameterName.USERNAME);
        String currentPassword = request.getParameter(ParameterName.CURRENT_PASSWORD);
        String newPassword = request.getParameter(ParameterName.NEW_PASSWORD);
        String confirmNewPassword = request.getParameter(ParameterName.CONFIRM_NEW_PASSWORD);
        if (!newPassword.equals(confirmNewPassword)){
            request.setAttribute(ParameterName.PASSWORD_CHANGE_MESSAGE, NEW_PASSWORDS_ARE_NOT_THE_SAME);
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_PASSWORD_CHANGE);
            router = new Router(ParameterName.BOOTSTRAP_PASSWORD_CHANGE);
            return router;
        }
        logger.info("retrieved login is " + username);
        logger.info("retrieved newPassword is " + newPassword);
        try {
                if (userService.authenticate(username, currentPassword) && userService.updatePassword(username, newPassword)) {
                    request.setAttribute(ParameterName.PASSWORD_CHANGE_MESSAGE, PASSWORD_CHANGE_SUCCESS);
                    router = new Router(ParameterName.HOME_PAGE);
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.HOME_PAGE);
                } else {
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_PASSWORD_CHANGE);
                    router = new Router(ParameterName.BOOTSTRAP_PASSWORD_CHANGE);
                    request.setAttribute(ParameterName.PASSWORD_CHANGE_MESSAGE, PASSWORD_CHANGE_FAILURE);
                }
        } catch (ServiceException e) {
            logger.error("error in updating the password", e);
            throw new CommandException(e);
        }
        return router;
    }
}
