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

@WebServlet("/admin/deleteLibrarian")
public class DeleteLibrarianServlet extends HttpServlet {

    private final UserService userService;

    public DeleteLibrarianServlet(UserService userService) {
        this.userService = userService;
    }

    public DeleteLibrarianServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        boolean librarianIsDelete = userService.remove(id);
        HttpSession session = request.getSession();
        if (librarianIsDelete) {
            session.setAttribute("successMessage", "Librarian successfully deleted");
        } else {
            session.setAttribute("failedMessage", "Error, librarian is not deleted");
        }
        getServletContext().getRequestDispatcher("/admin/librariansDelete").forward(request, response);
    }
}
