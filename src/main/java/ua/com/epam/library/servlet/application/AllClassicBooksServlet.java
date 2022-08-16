package ua.com.epam.library.servlet.application;

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

@WebServlet("/allClassic")
public class AllClassicBooksServlet extends HttpServlet {

    private final BookService bookService;

    public AllClassicBooksServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public AllClassicBooksServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> classicBooks = bookService.getAllBooksByGenre("classic");
        request.setAttribute("classicBooks", classicBooks);
        getServletContext().getRequestDispatcher("/allClassicBooks.jsp").forward(request, response);
    }
}
