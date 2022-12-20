package by.fitr.webproject.controller.command;

import by.fitr.webproject.controller.command.exception.CommandException;
import by.fitr.webproject.entity.user.User;
import by.fitr.webproject.entity.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@FunctionalInterface
public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;

    default User getAuthUser(HttpSession session) {
        return (User) session.getAttribute(ParameterName.AUTHORIZED_USER);
    }

    default boolean isAdmin(HttpSession session){
    return getAuthUser(session).getRole().equals(UserRole.ADMIN);
    }
}
