package by.fitr.webproject.controller.filter;

import by.fitr.webproject.controller.command.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/startbootstrap/*")
public class CurrentPageFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        logger.info("[FILTER]{}{}{}{}{} "+httpRequest.getContextPath() + httpRequest.getServletPath());
        HttpSession session = httpRequest.getSession();
        session.setAttribute(ParameterName.CURRENT_PAGE, httpRequest.getServletPath());
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
    }
}
