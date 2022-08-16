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

@WebServlet("/viewBook")
public class ViewBookServlet extends HttpServlet {

    private final BookService bookService;

    public ViewBookServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public ViewBookServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("bookId"));
        Book book = bookService.getBookById(id).get();
        request.setAttribute("book", book);
        getServletContext().getRequestDispatcher("/viewBook.jsp").forward(request, response);
    }
}
