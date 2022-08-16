package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/update")
public class BooksListToUpdateServlet extends HttpServlet {

    private final BookService bookService;

    public BooksListToUpdateServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public BooksListToUpdateServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Book> books = bookService.getAll();
        session.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/admin/booksToEdit.jsp").forward(request, response);
    }
}
