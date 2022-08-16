package ua.com.epam.library.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.entity.User;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/main/*")
public class LoginFilter implements Filter {

    Logger log = (Logger) LogManager.getLogger(LoginFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Getting session data");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        log.info("Request/Redirect URL to Login Servlet");
        User user = (User) session.getAttribute("user");

        String loginURI = request.getContextPath() + "/login";

        boolean loggedIn = user != null && user.getEmail() != null && user.getRole() != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        log.info("Session is has been already created");
        if (loggedIn || loginRequest) {
            log.info("the request came from the login page or the session is not empty, =>  go ahead");
        } else {
            log.info("redirect to the main page");
            request.getRequestDispatcher("/main").forward(request, response);
            return;
        }
        filterChain.doFilter(request, response);
    }
}
