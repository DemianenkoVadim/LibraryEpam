package ua.com.epam.library.servlet.application;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.service.BookService;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.util.Constant;
import ua.com.epam.library.util.pagination.PaginationPage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/allBooks")
public class AllBooksServlet extends HttpServlet {

    private final BookService bookService;

    public AllBooksServlet(BookService bookService) {
        this.bookService = bookService;
    }

    public AllBooksServlet() {
        this(ServiceFactory.getInstance().getBookService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sort = Optional.ofNullable(request.getParameter("sort")).orElse("");
        String paramPage = Optional.ofNullable(request.getParameter("page")).orElse("1");

        int currentPage = Integer.parseInt(paramPage);
        int totalPage = (int) Math.ceil(bookService.getSize() / ((double) Constant.PAGE_SIZE));

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setTotalPage(totalPage);
        paginationPage.setPage(currentPage);


        if (currentPage == 1) {
            paginationPage.setActivePage(currentPage);
            paginationPage.setPreviousPageUi(currentPage);
            paginationPage.setCurrentPageUi(currentPage + 1);
            paginationPage.setNextPageUi(currentPage + 2);
        }

        if (currentPage > 1 && currentPage != totalPage) {
            paginationPage.setPreviousPageUi(currentPage - 1);
            paginationPage.setCurrentPageUi(currentPage);
            paginationPage.setNextPageUi(currentPage + 1);
            paginationPage.setActivePage(currentPage);
        }

        if (currentPage == totalPage) {
            paginationPage.setPreviousPageUi(currentPage - 2);
            paginationPage.setCurrentPageUi(currentPage - 1);
            paginationPage.setNextPageUi(currentPage);
            paginationPage.setActivePage(currentPage);
        }

        paginationPage.setFirstPage(currentPage == 1);
        paginationPage.setLastPage(currentPage == totalPage);

        List<Book> catalog = bookService.sort(sort, currentPage);

        request.setAttribute("catalog", catalog);
        request.setAttribute("sort", sort);
        request.setAttribute("pagination", paginationPage);

        getServletContext().getRequestDispatcher("/allBooks.jsp").forward(request, response);
    }
}
