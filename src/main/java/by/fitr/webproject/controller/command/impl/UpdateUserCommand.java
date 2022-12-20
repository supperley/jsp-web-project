package by.fitr.webproject.controller.command.impl;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class UpdateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ALREADY_EXISTING_LOGIN = " - already existing login";
    private static final String ALREADY_EXISTING_EMAIL = " - already existing email";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        User userToUpdate;
        String id = request.getParameter(ParameterName.USER_ID);
        try {
            Optional<User> optionalUser = userService.findById(Long.parseLong(id));
            if (optionalUser.isEmpty()) {
                throw new CommandException("could not find the user with id number: " + id);
            }
            userToUpdate = optionalUser.get();
            logger.info("retrieved user from database is " + userToUpdate.toString());

            String login = request.getParameter(ColumnName.LOGIN);
            logger.info("login from input: " + login);
            String firstName = request.getParameter(ColumnName.FIRST_NAME);
            logger.info("login from first_name: " + firstName);
            String lastName = request.getParameter(ColumnName.LAST_NAME);
            logger.info("login from last_name: " + lastName);
            String email = request.getParameter(ColumnName.EMAIL);
            logger.info("login from email: " + email);

            if (userToUpdate.getLogin().equals(login)) {
                userToUpdate.setLogin(login);
                logger.info("as the logins are the same: " + login + " is set");
            } else {
                if (userService.isLoginAvailable(login)) {
                    userToUpdate.setLogin(login);
                } else {
                    request.setAttribute(ParameterName.UNAVAILABLE_LOGIN, login + ALREADY_EXISTING_LOGIN);
                    return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
                }
            }

            if (userToUpdate.getEmail().equals(email)) {
                userToUpdate.setEmail(email);
                logger.info("as the emails are the same: " + email + " is set");
            } else {
                if (userService.isEmailAvailable(email)) {
                    userToUpdate.setEmail(email);
                } else {
                    request.setAttribute(ParameterName.UNAVAILABLE_EMAIL_ADDRESS, email + ALREADY_EXISTING_EMAIL);
                    return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
                }
            }

            userToUpdate.setFirstName(firstName);
            logger.info(firstName + " is set as firstName");
            userToUpdate.setLastName(lastName);
            logger.info(lastName + " is set as lastName");

            if (userService.updateUser(userToUpdate)) {
                logger.info("update operation is successful " + userToUpdate.toString());
                session.setAttribute(ParameterName.UPDATED_USER, userToUpdate);
                return new Router(ParameterName.USERS_PAGE, Router.Type.FORWARD);
            } else {
                logger.info("unsuccessful update operation " + userToUpdate.toString());
                return new Router(ParameterName.BOOTSTRAP_PROFILE_PAGE, Router.Type.FORWARD);
            }

        } catch (ServiceException e) {
            logger.error("error in updating the user ", e);
            throw new CommandException(e);
        }

    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }

}
