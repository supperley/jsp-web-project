package by.fitr.webproject.controller.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.fitr.webproject.controller.command.Command;
import by.fitr.webproject.controller.command.ParameterName;
import by.fitr.webproject.controller.command.Router;
import by.fitr.webproject.controller.command.exception.CommandException;
import by.fitr.webproject.dao.mapper.ColumnName;
import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.entity.user.UserRole;
import by.fitr.webproject.service.UserService;
import by.fitr.webproject.service.exception.ServiceException;
import by.fitr.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;


public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_EXISTING_LOGIN = " - already existing login";
    private static final String ALREADY_EXISTING_EMAIL = " - already existing email";
    private static final String SUCCESSFUL_REGISTRATION = " successful_registration: Login with new user ";
    private static final String UNSUCCESSFUL_REGISTRATION = " unsuccessful_registration ";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        String login = request.getParameter(ColumnName.LOGIN);
        String password = request.getParameter(ColumnName.PASSWORD);
        String firstName = request.getParameter(ColumnName.FIRST_NAME);
        String lastName = request.getParameter(ColumnName.LAST_NAME);
        String email = request.getParameter(ColumnName.EMAIL);
        String role = request.getParameter(ColumnName.ROLE);

        logger.info("retrieved login is: " + login);
        logger.info("retrieved password is: " + password);
        logger.info("retrieved firstName is: " + firstName);
        logger.info("retrieved lastName is: " + lastName);
        logger.info("retrieved role is: " + role);

        User user = new User();

        try {
            if (userService.isLoginAvailable(login)) {
                user.setLogin(login);
            } else {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION + login + ALREADY_EXISTING_LOGIN);
                request.setAttribute(ParameterName.UNAVAILABLE_LOGIN, login + ALREADY_EXISTING_LOGIN);
                return new Router(ParameterName.BOOTSTRAP_REGISTRATION_PAGE, Router.Type.FORWARD);
            }
            if (userService.isEmailAvailable(email)) {
                logger.info("service level : " + email + " is available");
                user.setEmail(email);
            } else {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION + email + ALREADY_EXISTING_EMAIL);
                request.setAttribute(ParameterName.UNAVAILABLE_EMAIL_ADDRESS, email + ALREADY_EXISTING_EMAIL);
                return new Router(ParameterName.BOOTSTRAP_REGISTRATION_PAGE, Router.Type.FORWARD);
            }
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setRole(UserRole.valueOf(role));
            logger.info("user.toString() ===> " + user.toString());

            request.setAttribute(ParameterName.USER, user);
            if (userService.registerUser(user)) {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, SUCCESSFUL_REGISTRATION);
//                return new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
                return new Router(ParameterName.REGISTRATION_CONFIRMATION_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(ParameterName.OPERATION_MESSAGE, UNSUCCESSFUL_REGISTRATION);
                return new Router(ParameterName.BOOTSTRAP_REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("error in registering a new user", e);
            throw new CommandException(e);
        }
    }
}
