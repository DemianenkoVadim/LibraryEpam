package ua.com.epam.library.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.dao.ReceiptDao;
import ua.com.epam.library.dao.impl.ReceiptDaoImpl;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.entity.User;
import ua.com.epam.library.exception.dao.DaoException;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/userSubscription")
public class ListUserBooksOnSubscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long id = user.getId();

        ReceiptDao dao = new ReceiptDaoImpl();
        List<Receipt> receipts = null;

        try {
            receipts = dao.getAllReceiptsBookOnSubscriptionByUserId(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        request.setAttribute("receipts", receipts);
        getServletContext().getRequestDispatcher("/user/booksOnSubscription.jsp").forward(request, response);
    }
}
