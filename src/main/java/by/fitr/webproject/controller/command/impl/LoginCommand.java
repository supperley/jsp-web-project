package by.fitr.webproject.controller.command.impl;

import by.fitr.webproject.controller.command.Command;
import by.fitr.webproject.controller.command.ParameterName;
import by.fitr.webproject.controller.command.Router;
import by.fitr.webproject.controller.command.exception.CommandException;
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

public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";
    private static final String ERROR_MESSAGE = "Incorrect login or password";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String userName = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        String defaultLocale = request.getParameter(ParameterName.LOCALE);

        HttpSession session = request.getSession();
        session.setAttribute(ParameterName.LOCALE, defaultLocale);
        Router router;

        UserService userService = UserServiceImpl.getInstance();

        session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.INDEX_PAGE);
        logger.info("current page now ::::: " + session.getAttribute(ParameterName.CURRENT_PAGE));
        logger.info("request.contextPath is " + request.getContextPath());
        logger.info("request.servletPath is " + request.getServletPath());
        try {
            if (userService.authenticate(userName, password)) {
                session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
                logger.info("current page now ::::: " + session.getAttribute(ParameterName.CURRENT_PAGE));
                UserRole userRole = userService.findUserRole(userName);
                Optional<User> optionalUser = userService.findByLogin(userName);

                User user;

                if (optionalUser.isEmpty()) {
                    return new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
                }

                user = optionalUser.get();
                session.setAttribute(ParameterName.USER, user);
                session.setAttribute(ParameterName.USERNAME, userName);
                session.setAttribute(ParameterName.ROLE, userRole);
                router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.FORWARD);
            } else {
                request.setAttribute(ParameterName.ERROR_MESSAGE_SIGN_IN, ERROR_MESSAGE);
                router = new Router(ParameterName.INDEX_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException e) {
            logger.error("error in login", e);
            throw new CommandException(e);
        }
        return router;
    }
}
