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

@WebServlet("/main")
public class MainServlet extends HttpServlet {

    private final BookService bookService;

    public MainServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public MainServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> classicBooks = bookService.getAllBooksByGenre("classic");
        List<Book> romanceBooks = bookService.getAllBooksByGenre("romance");
        List<Book> fantasyBooks = bookService.getAllBooksByGenre("fantasy");

        request.setAttribute("classicBooks", classicBooks);
        request.setAttribute("fantasyBooks", fantasyBooks);
        request.setAttribute("romanceBooks", romanceBooks);

        getServletContext().getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
