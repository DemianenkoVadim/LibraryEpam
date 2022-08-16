package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;
import ua.com.epam.library.service.validation.ValidationService;
import ua.com.epam.library.service.validation.ValidationServiceImpl;
import ua.com.epam.library.service.validation.model.ValidationResult;
import ua.com.epam.library.servlet.admin.request.CreateLibrarianRequest;
import ua.com.epam.library.util.RequestMapper;

import java.io.IOException;

@WebServlet("/admin/createLibrarian")
public class CreateLibrarianServlet extends HttpServlet {

    private final UserService userService;

    public CreateLibrarianServlet(UserService userService) {
        this.userService = userService;
    }

    public CreateLibrarianServlet() {
        this(ServiceFactory.getInstance().getUserService());
    }

    ValidationService validationService = ValidationServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CreateLibrarianRequest createLibrarianRequest = RequestMapper.mapToRequest(request, CreateLibrarianRequest.class);
        ValidationResult<CreateLibrarianRequest> validationResult = validationService.validate(createLibrarianRequest);

        if (checksValidationResultForErrors(request, response, validationResult)) return;

        userService.createLibrarian(createLibrarianRequest);

        request.setAttribute("successMessage", "Librarian registered success!");
        getServletContext().getRequestDispatcher("/admin/addLibrarian.jsp").forward(request, response);
    }


    private boolean checksValidationResultForErrors(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    ValidationResult<CreateLibrarianRequest> validationResult) throws IOException {
        if (validationResult.getErrors().isEmpty()) return false;

        request.setAttribute("errors", validationResult.getErrors());
        response.sendRedirect("addLibrarian.jsp");
        return true;
    }
}
