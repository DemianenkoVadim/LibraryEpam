package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/booksToDelete")
public class ListBooksToDeleteServlet extends HttpServlet {

    private final BookService bookService;

    public ListBooksToDeleteServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public ListBooksToDeleteServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAll();
        request.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/admin/booksToDelete.jsp").forward(request, response);
    }
}
