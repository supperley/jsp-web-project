package by.fitr.webproject.controller.listener;

import by.fitr.webproject.controller.command.ParameterName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class HttpSessionAttributeListenerImpl implements HttpSessionAttributeListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

        logger.log(Level.INFO, "--attribute added " + se.getSession().getAttribute(ParameterName.USERNAME));
        logger.log(Level.INFO, "--attribute added " + se.getSession().getAttribute(ParameterName.PASSWORD));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

//    @Override
//    public void attributeRemoved(HttpSessionBindingEvent se) {
//        if (se.getSession().isNew())
//        logger.log(Level.INFO, "_+_+attribute removed " + se.getSession().getAttributeNames());
//    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        logger.log(Level.INFO, "<><>attribute replaced " + se.getSession().getAttribute(ParameterName.USERNAME));
        logger.log(Level.INFO, "<><>attribute replaced " + se.getSession().getAttribute(ParameterName.PASSWORD));
    }
}
