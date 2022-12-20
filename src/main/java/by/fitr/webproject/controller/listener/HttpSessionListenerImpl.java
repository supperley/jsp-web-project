package by.fitr.webproject.controller.listener;

import by.fitr.webproject.controller.command.ParameterName;
import by.fitr.webproject.entity.user.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_LOCALE = "en_US";
    private static final String DEFAULT_LANGUAGE = "EN";

    public HttpSessionListenerImpl() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        HttpSessionListener.super.sessionCreated(se);
        HttpSession httpSession = se.getSession();
        httpSession.setAttribute(ParameterName.CURRENT_PAGE, ParameterName.INDEX_PAGE);
        httpSession.setAttribute(ParameterName.LOCALE, DEFAULT_LOCALE);
        httpSession.setAttribute(ParameterName.LANGUAGE, DEFAULT_LANGUAGE);
        logger.info("Current locale is " + httpSession.getAttribute(ParameterName.LOCALE));
        httpSession.setAttribute(ParameterName.ROLE, UserRole.GUEST.name().toLowerCase());
        logger.log(Level.INFO, "<><><> Session created: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
//        HttpSessionListener.super.sessionDestroyed(se);
        logger.log(Level.INFO, "<><><> Session destroyed: " + se.getSession().getId());
    }
}
