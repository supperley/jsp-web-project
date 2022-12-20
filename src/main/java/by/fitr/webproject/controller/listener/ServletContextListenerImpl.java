package by.fitr.webproject.controller.listener;

import by.fitr.webproject.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        logger.log(Level.INFO, ">>>>> context initialized " + sce.getServletContext().getServerInfo(),
                connectionPool.name());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.INSTANCE.destroyPool();
        logger.log(Level.INFO, "<<<<<<< context destroyed " + sce.getServletContext().getContextPath());
    }
}
