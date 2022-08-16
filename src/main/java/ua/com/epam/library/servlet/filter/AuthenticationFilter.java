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

@WebFilter(filterName = "AuthFilter", urlPatterns = "/admin/*")
public class AuthenticationFilter implements Filter {

    Logger log = (Logger) LogManager.getLogger(AuthenticationFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        log.info("Checks for the existence of the session");
        User customer = (User) session.getAttribute("user");
        boolean loggedIn = customer != null && customer.getEmail() != null && customer.getRole() != null;

        if (loggedIn) {
            log.info("Session is exists => getting user`s role");
            if (customer.getRole().equals("admin")) {
                log.info("Send to admin`s home page");
                filterChain.doFilter(request, response);
            }
            if (customer.getRole().equals("librarian")) {
                response.sendRedirect(request.getContextPath() + "/librarian/librarianHome.jsp");
                log.info("Redirect to librarian`s home page");
            }
            if (customer.getRole().equals("user")) {
                response.sendRedirect(request.getContextPath() + "/user/userHome.jsp");
                log.info("Redirect to user`s home page");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        log.trace("Session is not exist --> to enter page");
    }
}
