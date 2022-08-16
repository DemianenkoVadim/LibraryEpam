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

@WebServlet("/librarian/allReceipts")
public class AllUsersReceiptsServlet extends HttpServlet {

    private final ReceiptService RECEIPT_SERVICE;

    public AllUsersReceiptsServlet(ReceiptService receiptService) {
        RECEIPT_SERVICE = receiptService;
    }

    public AllUsersReceiptsServlet() {
        this(ServiceFactory.getInstance().getReceiptService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receipt> receipt = RECEIPT_SERVICE.getAllUsersReceipts();
        request.setAttribute("receipt", receipt);
        getServletContext().getRequestDispatcher("/librarian/readerReceiptsList.jsp").forward(request, response);
    }
}
