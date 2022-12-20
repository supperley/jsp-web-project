package by.fitr.webproject.controller.command.impl;

import by.fitr.webproject.controller.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.fitr.webproject.controller.command.Command;
import by.fitr.webproject.controller.command.ParameterName;
import by.fitr.webproject.controller.command.exception.CommandException;
import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.entity.user.UserRole;
import by.fitr.webproject.service.UserService;
import by.fitr.webproject.service.exception.ServiceException;
import by.fitr.webproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FindAllUsersCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        logger.info("session current page parameter is " + session.getAttribute(ParameterName.CURRENT_PAGE));
        Router router;
        try {
            if (isAdmin(session)) {
                List<User> users = userService.findAll();
                if (users != null) {
                    session.setAttribute(ParameterName.USERS, users);
                    session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_USERS_LIST_TABLE);
                    router = new Router(ParameterName.BOOTSTRAP_USERS_LIST_TABLE, Router.Type.FORWARD);
                    return router;
                }
            }
            session.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.BOOTSTRAP_HOME_PAGE);
            router = new Router(ParameterName.BOOTSTRAP_HOME_PAGE, Router.Type.REDIRECT);

        } catch (ServiceException e) {
            logger.error("error in getting all users from database", e);
            throw new CommandException(e);
        }
        return router;
    }

    @Override
    public boolean isAdmin(HttpSession session) {
        return session.getAttribute(ParameterName.ROLE).equals(UserRole.ADMIN);
    }

}
