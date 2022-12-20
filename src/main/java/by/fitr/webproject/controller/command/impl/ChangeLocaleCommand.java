package by.fitr.webproject.controller.command.impl;

import by.fitr.webproject.controller.command.Command;
import by.fitr.webproject.controller.command.ParameterName;
import by.fitr.webproject.controller.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String LANGUAGE_ENGLISH = "EN";
    private static final String LANGUAGE_RUSSIAN = "RU";
    private static final String LOCALE_ENGLISH = "en_US";
    private static final String LOCALE_RUSSIAN = "ru_RU";

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String locale = request.getParameter(ParameterName.LOCALE);
        session.setAttribute(ParameterName.LOCALE, locale);
        logger.info("session.getAttribute(ParameterName.CURRENT_PAGE is " + session.getAttribute(ParameterName.CURRENT_PAGE));
        return new Router(String.valueOf(session.getAttribute(ParameterName.CURRENT_PAGE)));
//        return new Router(ParameterName.BOOTSTRAP_HOME_PAGE);
    }
}
