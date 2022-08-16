package ua.com.epam.library.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.service.CartService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;

@WebServlet("/user/remove")
public class RemoveBookFromCartServlet extends HttpServlet {

    private final CartService CART_SERVICE;

    public RemoveBookFromCartServlet(CartService CART_SERVICE) {
        this.CART_SERVICE = CART_SERVICE;
    }

    public RemoveBookFromCartServlet() {
        this(ServiceFactory.getInstance().getCartService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long bookId = Long.parseLong(request.getParameter("bookId"));
        long userId = Long.parseLong(request.getParameter("userId"));
        long cartId = Long.parseLong(request.getParameter("cartId"));

        try {
            boolean bookRemoveFromCart = CART_SERVICE.delete(bookId, userId, cartId);
            HttpSession session = request.getSession();

            if (bookRemoveFromCart) {
                session.setAttribute("successMessage", "Book remove from Cart");
            } else {
                session.setAttribute("failedMessage", "Something wrong on server");
            }
            getServletContext().getRequestDispatcher("/user/checkout").forward(request, response);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
