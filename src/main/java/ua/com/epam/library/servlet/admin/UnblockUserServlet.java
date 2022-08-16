package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;

import java.io.IOException;

@WebServlet("/admin/unblockUser")
public class UnblockUserServlet extends HttpServlet {

    private final UserService userService;

    public UnblockUserServlet(UserService userService) {
        this.userService = userService;
    }

    public UnblockUserServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        boolean userIsUnblocked = userService.unblockUser(id);
        HttpSession session = request.getSession();
        if (userIsUnblocked) {
            session.setAttribute("successMessage", "User successfully unblocked");
        } else {
            session.setAttribute("failedMessage", "Something wrong on server");
        }
        getServletContext().getRequestDispatcher("/admin/unblock").forward(request, response);
    }
}
