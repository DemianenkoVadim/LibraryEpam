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

@WebServlet("/admin/block")
public class UsersListToBlockServlet extends HttpServlet {

    private final UserService userService;

    public UsersListToBlockServlet(UserService userService) {
        this.userService = userService;
    }

    public UsersListToBlockServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> customers = userService.findAllActiveUsers();
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/admin/usersToBlock.jsp").forward(request, response);
    }
}
