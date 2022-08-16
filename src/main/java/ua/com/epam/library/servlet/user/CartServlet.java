package ua.com.epam.library.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.CartService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;

@WebServlet("/user/cart")
public class CartServlet extends HttpServlet {

    CartService cartService = ServiceFactory.getInstance().getCartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            long bookId = Long.parseLong(request.getParameter("bookId"));
            long userId = Long.parseLong(request.getParameter("userId"));

            boolean bookIsAddToCart = cartService.createCart(bookId, userId);

            HttpSession session = request.getSession();
            if (bookIsAddToCart) {
                session.setAttribute("addCart", "Book Added to Cart");
            } else {
                session.setAttribute("failed", "Something went wrong check the availability of books in the library");
            }
            getServletContext().getRequestDispatcher("/allBooks").forward(request, response);
        } catch (ServletException | ServiceException | DaoException exception) {
            exception.printStackTrace();
        }
    }
}
