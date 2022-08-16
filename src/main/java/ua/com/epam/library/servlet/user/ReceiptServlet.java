package ua.com.epam.library.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.entity.Rent;
import ua.com.epam.library.entity.Stage;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.service.CartService;
import ua.com.epam.library.service.ReceiptService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet("/user/receipt")
public class ReceiptServlet extends HttpServlet {

    ReceiptService receiptService = ServiceFactory.getInstance().getReceiptService();
    CartService cartService = ServiceFactory.getInstance().getCartService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            long userId = Long.parseLong(request.getParameter("userId"));
            String service = request.getParameter("rent");

            List<Cart> books = cartService.getAllBooksInCartByUserId(userId);

            if (books.isEmpty()) {
                session.setAttribute("failedMessage", "Add items");
                getServletContext().getRequestDispatcher("/user/checkout.jsp").forward(request, response);
            } else {
                List<Receipt> receipts = createListOfReceipts(userId, service, books);
                if ("UNSELECT".equals(service)) {
                    session.setAttribute("failedMessage", "Please choose Service Rent Type");
                    getServletContext().getRequestDispatcher("/user/checkout.jsp").forward(request, response);
                } else {
                    boolean receiptIsAdded = receiptService.addReceipt(receipts);

                    if (receiptIsAdded) {
                        getServletContext().getRequestDispatcher("/user/orderSuccessfullyAddedPage.jsp").forward(request, response);
                    } else {
                        session.setAttribute("failedMessage", "Your Order is Failed");
                        getServletContext().getRequestDispatcher("/user/checkout.jsp").forward(request, response);
                    }
                }
            }
        } catch (DaoException | SQLException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Receipt> createListOfReceipts(long userId, String service, List<Cart> books) {
        Receipt receipt;
        ArrayList<Receipt> receipts = new ArrayList<>();
        Random random = new Random();
        for (Cart cart : books) {
            receipt = new Receipt();
            receipt.setUserId(userId);
            receipt.setBookId(cart.getBookId());
            receipt.setReceiptNumber("BOOK-RECEIPT-000" + random.nextInt(1000));
            receipt.setStage(Stage.PENDING);
            receipt.setReceivingDate(LocalDateTime.now());
            receipt.setEstimateReturnDate(LocalDateTime.now());
            receipt.setRealReturnDate(LocalDateTime.now());
            receipt.setPenalty(0);
            receipt.setRent(Rent.valueOf(service));
            receipts.add(receipt);
        }
        return receipts;
    }
}
