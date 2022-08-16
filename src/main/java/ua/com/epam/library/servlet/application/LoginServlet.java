package ua.com.epam.library.servlet.application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.entity.Status;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.service.RoleService;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserService usersService = ServiceFactory.getInstance().getUserService();
    RoleService roleService = ServiceFactory.getInstance().getRoleService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        Optional<User> user = usersService.login(email, password);
        if (user.isEmpty()) {
            session.setAttribute("failedMessage", "Email or Password are invalid");
            response.sendRedirect("login.jsp");
            return;
        }

        User consumer = user.get();
        roleService.retrieveRoleById(consumer.getRoleId())
                .ifPresent(value -> request.setAttribute("role", value));

        if (consumer.getRole().equals("admin")) {
            session.setAttribute("user", consumer);
            session.setAttribute("admin", consumer);
            response.sendRedirect("admin/adminHome.jsp");
        }
        if (consumer.getRole().equals("librarian")) {
            session.setAttribute("user", consumer);
            response.sendRedirect("librarian/librarianHome.jsp");
        }
        if (consumer.getRole().equals("user") && consumer.getStatus() == Status.ACTIVE) {
            session.setAttribute("user", consumer);
            response.sendRedirect("user/userHome.jsp");
        }
        if (consumer.getRole().equals("user") && consumer.getStatus() == Status.BLOCKED) {
            session.setAttribute("failedMessage", "User is BLOCKED");
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
