package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/unblock")
public class UsersListToUnblockServlet extends HttpServlet {

    private final UserService userService;

    public UsersListToUnblockServlet(UserService userService) {
        this.userService = userService;
    }

    public UsersListToUnblockServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> customers = userService.findAllBlockedUsers();
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/admin/usersToUnblock.jsp").forward(request, response);
    }
}
