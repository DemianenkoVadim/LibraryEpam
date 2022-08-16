package ua.com.epam.library.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.CartService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/checkout")
public class CheckoutServlet extends HttpServlet {

    CartService cartService = ServiceFactory.getInstance().getCartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();
        List<Cart> books = null;
        try {
            books = cartService.getAllBooksInCartByUserId(userId);
            System.out.println(books);
        } catch (ServiceException | DaoException e) {
            e.printStackTrace();
        }
        request.setAttribute("books", books);
        getServletContext().getRequestDispatcher("/user/checkout.jsp").forward(request, response);
    }
}
