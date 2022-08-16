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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private final BookService bookService;

    public SearchServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public SearchServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Book> findResult = bookService.getBookByTitleAndAuthorSearch(search);
        request.setAttribute("findResult", findResult);
        getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
    }
}
