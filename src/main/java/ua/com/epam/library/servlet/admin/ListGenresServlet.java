package ua.com.epam.library.servlet.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.epam.library.entity.Genre;
import ua.com.epam.library.service.GenreService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/addBook")
public class ListGenresServlet extends HttpServlet {

    private final GenreService genreService;

    public ListGenresServlet(GenreService genreService) {
        this.genreService = genreService;
    }

    public ListGenresServlet() {
        this(ServiceFactory.getInstance().getGenreService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genre> genres = genreService.findAllGenres();
        request.setAttribute("genres", genres);
        getServletContext().getRequestDispatcher("/admin/addBook.jsp").forward(request, response);
    }
}
