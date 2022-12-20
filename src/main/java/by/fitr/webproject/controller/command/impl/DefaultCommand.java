package by.fitr.webproject.controller.command.impl;

import by.fitr.webproject.controller.command.Command;
import by.fitr.webproject.controller.command.Router;
import by.fitr.webproject.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
