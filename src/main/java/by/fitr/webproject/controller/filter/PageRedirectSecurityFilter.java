package by.fitr.webproject.controller.filter;

import by.fitr.webproject.controller.command.ParameterName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


//@WebFilter(/*urlPatterns = {"/pages/*"},*/ initParams = {@WebInitParam(name = "INDEX", value = "/index.jsp")})
@WebFilter(urlPatterns = {"/startbootstrap/password-change.jsp", "/startbootstrap/profile.jsp",
        "/startbootstrap/user-table.jsp", "/startbootstrap/home.jsp", "/startbootstrap/header.jsp"},
        initParams = {@WebInitParam(name = "INDEX", value = "/index.jsp")})
public class PageRedirectSecurityFilter implements Filter {
    private String indexPath;
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter("INDEX");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        if(session.getAttribute(ParameterName.USER) == null) {
            logger.info("[PageRedirectSecurityFilter]{}{}{}{}{} USER_FILTERED " + httpRequest.getContextPath() + httpRequest.getServletPath());
            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
