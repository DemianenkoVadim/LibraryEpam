package ua.com.epam.library.servlet.librarian;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.service.ReceiptService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/librarian/usersDescriptions")
public class AllUsersDescriptionReceipts extends HttpServlet {

    private final ReceiptService RECEIPT_SERVICE;

    public AllUsersDescriptionReceipts(ReceiptService receiptService) {
        RECEIPT_SERVICE = receiptService;
    }

    public AllUsersDescriptionReceipts() {
        this(ServiceFactory.getInstance().getReceiptService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receipt> receipt = RECEIPT_SERVICE.getAllUsersDescriptionReceipts();
        request.setAttribute("receipt", receipt);
        getServletContext().getRequestDispatcher("/librarian/readersSubscriptionList.jsp").forward(request, response);
    }
}
