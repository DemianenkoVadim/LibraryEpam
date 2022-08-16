package ua.com.epam.library.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "LocalizationFilter", urlPatterns = "*")
public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        String lang = request.getParameter("lang");
        if (session.getAttribute("lang") == null && lang == null) {
            session.setAttribute("lang", "en");

        }
        if (lang != null) {
            session.setAttribute("lang", lang);
        }
        filterChain.doFilter(request, response);
    }
}
