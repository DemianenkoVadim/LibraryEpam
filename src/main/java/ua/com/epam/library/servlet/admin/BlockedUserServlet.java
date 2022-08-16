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

@WebServlet("/admin/blockUser")
public class BlockedUserServlet extends HttpServlet {

    private final UserService userService;

    public BlockedUserServlet(UserService userService) {
        this.userService = userService;
    }

    public BlockedUserServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long id = Long.parseLong(request.getParameter("id"));
        boolean userIsBlocked = userService.blockUser(id);
        if (userIsBlocked) {
            session.setAttribute("successMessage", "User successfully blocked");
        } else {
            session.setAttribute("failedMessage", "Something wrong on server");
        }
        getServletContext().getRequestDispatcher("/admin/block").forward(request, response);
    }
}
