package ua.com.epam.library.servlet.application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;

import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService;

    public RegistrationServlet(UserService userService) {
        this.userService = userService;
    }

    public RegistrationServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String check = request.getParameter("check");

        HttpSession session = request.getSession();

        if (check != null) {
            boolean userIsRegister = userService.register(name, email, phone, password);

            if (userIsRegister) {
                session.setAttribute("successMessage", "Registration Success..");
            } else {
                session.setAttribute("failedMessage", "Something  went wrong");
            }
        } else {
            session.setAttribute("failedMessage", "Please check Agree & Terms Conditions");
        }
        response.sendRedirect("registration.jsp");
    }
}
