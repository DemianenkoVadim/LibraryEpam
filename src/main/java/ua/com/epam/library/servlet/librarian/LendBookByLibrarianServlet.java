package ua.com.epam.library.servlet.librarian;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.epam.library.service.ReceiptService;
import ua.com.epam.library.service.ServiceFactory;

import java.io.IOException;

@WebServlet("/librarian/lend")
public class LendBookByLibrarianServlet extends HttpServlet {

    private final ReceiptService receiptService;

    public LendBookByLibrarianServlet(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    public LendBookByLibrarianServlet() {
        this(ServiceFactory.getInstance().getReceiptService());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long bookId = Long.parseLong(request.getParameter("bookId"));
        long userId = Long.parseLong(request.getParameter("userId"));
        long receiptId = Long.parseLong(request.getParameter("receiptId"));

        boolean lendBook = receiptService.lendBookToUser(bookId, userId, receiptId);
        HttpSession session = request.getSession();

        if (lendBook) {
            session.setAttribute("successMessage", "Book issued");
        } else {
            session.setAttribute("failedMessage", "Something wrong on server");
        }
        getServletContext().getRequestDispatcher("/librarian/allReceipts").forward(request, response);
    }
}
