package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;

@WebServlet("/admin/deleteBook")
public class DeleteBookServlet extends HttpServlet {

    private final BookService bookService;

    public DeleteBookServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public DeleteBookServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        boolean bookDeleted = bookService.delete(id);
        HttpSession session = request.getSession();
        if (bookDeleted) {
            session.setAttribute("successMessage", "Book successfully deleted!");
        } else {
            session.setAttribute("failedMessage", "Something wrong on server");
        }
        getServletContext().getRequestDispatcher("/admin/booksToDelete").forward(request, response);
    }
}