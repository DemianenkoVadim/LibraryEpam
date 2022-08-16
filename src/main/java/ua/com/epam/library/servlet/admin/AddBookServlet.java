package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.GenreService;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.validation.ValidationService;
import ua.com.epam.library.service.validation.ValidationServiceImpl;
import ua.com.epam.library.service.validation.model.ValidationResult;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;
import ua.com.epam.library.util.RequestMapper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addBook")
@MultipartConfig
public class AddBookServlet extends HttpServlet {

    BookService bookService = ServiceFactory.getInstance().getBookService();
    ValidationService validationService = ValidationServiceImpl.getInstance();

    private final GenreService genreService;

    public AddBookServlet(GenreService genreService) {
        this.genreService = genreService;
    }

    public AddBookServlet() {
        this(ServiceFactory.getInstance().getGenreService());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AddBookRequest addBookRequest = RequestMapper.mapToRequest(request, AddBookRequest.class);
        ValidationResult<AddBookRequest> validationResult = validationService.validate(addBookRequest);

        if (validationResult.hasErrors()) {
            request.setAttribute("errors", validationResult.getErrors());
            request.setAttribute("book", validationResult.getEntity());
            getServletContext().getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
            return;
        }

        try {
            bookService.addBook(addBookRequest);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        pathFileCreator(request);

        request.setAttribute("successesMessage", "Book add Successfully");
        getServletContext().getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
    }

    private void pathFileCreator(HttpServletRequest request) throws IOException, ServletException {
        String path = getServletContext().getRealPath("") + "image" + File.separator + "library";
        Part part = request.getPart("bookImage");
        part.write(path + File.separator + part.getSubmittedFileName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
    }
}
