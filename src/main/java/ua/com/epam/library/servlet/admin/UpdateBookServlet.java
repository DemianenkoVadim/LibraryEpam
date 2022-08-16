package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.exception.myexception.BookNotFoundException;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.validation.ValidationService;
import ua.com.epam.library.service.validation.ValidationServiceImpl;
import ua.com.epam.library.service.validation.model.ValidationResult;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;
import ua.com.epam.library.util.RequestMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/updateBook")
@MultipartConfig
public class UpdateBookServlet extends HttpServlet {

    private final BookService bookService;
    ValidationService validationService = ValidationServiceImpl.getInstance();

    public UpdateBookServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public UpdateBookServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddBookRequest addBookRequest = RequestMapper.mapToRequest(request, AddBookRequest.class);
        ValidationResult<AddBookRequest> validationResult = validationService.validate(addBookRequest);

        if (checksValidationResultForErrors(request, response, validationResult)) return;
        try {
            bookService.updateBook(addBookRequest);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        pathFileCreator(request);

        request.setAttribute("successMessage", "Book is updated successfully!");
        getServletContext().getRequestDispatcher("/admin/editBook.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        Book book = bookService.getBookById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        request.setAttribute("book", book);
        getServletContext().getRequestDispatcher("/admin/editBook.jsp").forward(request, response);
    }

    private boolean checksValidationResultForErrors(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    ValidationResult<AddBookRequest> validationResult) throws IOException {
        if (validationResult.getErrors().isEmpty())
            return false;

        request.setAttribute("errors", validationResult.getErrors());
        response.sendRedirect("editBook.jsp");
        return true;
    }

    private void pathFileCreator(HttpServletRequest request) throws IOException, ServletException {
        String path = getServletContext().getRealPath("") + "image" + File.separator + "library";
        Part part = request.getPart("bookImage");
        part.write(path + File.separator + part.getSubmittedFileName());
    }
}
