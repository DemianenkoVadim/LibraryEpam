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

@WebServlet("/admin/librariansDelete")
public class LibrarianListToDeleteServlet extends HttpServlet {

    private final UserService userService;

    public LibrarianListToDeleteServlet(UserService userService) {
        this.userService = userService;
    }

    public LibrarianListToDeleteServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> librarians = userService.findAllLibrarians();
        request.setAttribute("librarians", librarians);
        getServletContext().getRequestDispatcher("/admin/librariansToDelete.jsp").forward(request, response);
    }
}
