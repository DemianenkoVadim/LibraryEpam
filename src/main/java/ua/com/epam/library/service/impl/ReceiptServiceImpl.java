package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.dao.ReceiptDao;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.ReceiptService;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.Constant.*;

public class ReceiptServiceImpl implements ReceiptService {

    private static final Logger log = LogManager.getLogger(ReceiptServiceImpl.class.getName());

    private final ReceiptDao receiptDao;
    private final BookDao bookDao;

    public ReceiptServiceImpl() {
        this(
                DaoFactory.getInstance().getReceiptDao(),
                DaoFactory.getInstance().getBookDao()
        );
    }

    public ReceiptServiceImpl(ReceiptDao receiptDao, BookDao bookDao) {
        this.receiptDao = receiptDao;
        this.bookDao = bookDao;
    }

    @Override
    public boolean addReceipt(List<Receipt> receipts) throws DaoException, SQLException {
        receipts.stream()
                .mapToLong(Receipt::getBookId)
                .forEach(this::changeQuantityBooksInLibraryWhenBooksAreOrdered);
        log.info("Receipt is added successfully");
        return receiptDao.addReceipt(receipts);
    }

    @Override
    public boolean proceedReceipt(long bookId, long userId, long receiptId) throws DaoException {
        log.info("Receipt with id: {} for user with id: {} has been successfully processed and book with id: {} is ready for pickup", receiptId, userId, bookId);
        return receiptDao.proceedReceipt(bookId, userId, receiptId);
    }

    @Override
    public boolean lendBookToUser(long bookId, long userId, long receiptId) throws DaoException {
        log.info("Obtains date and time issuance book with id: {} by receipt with id: {} to user with id: {}", bookId, receiptId, userId);
        LocalDateTime receivingDate = LocalDateTime.now();
        log.info("Obtains estimate date time to return book with id: {} by receipt with id: {} from user with id: {}", bookId, receiptId, userId);
        LocalDateTime estimateReturnDate = receivingDate.plusWeeks(2);
        log.info("User with id {} with receipt id: {} got book with id: {}. Receiving date: {} , estimate return date: {} ", userId, receiptId, bookId, receivingDate, estimateReturnDate);
        return receiptDao.lendBookToUser(bookId, userId, receiptId, receivingDate, estimateReturnDate);
    }

    @Override
    public boolean returnBookFromUser(long bookId, long userId, long receiptId) throws DaoException {
        log.info("Obtain real returned date and time book with id: {}, receipt with id: {}, user with id: {}",
                bookId, receiptId, userId);
        LocalDateTime realReturnedDate = LocalDateTime.now().plusMonths(1);
        log.info("Starts count penalty to receipt with id: {}, book with id: {} , user with id: {}", receiptId, bookId, userId);
        double penalty = countPenalty(bookId, userId, receiptId);
        log.info("User with id {} with receipt id: {} returned book with id: {}. Returned date: {}, penalty: {}",
                userId, receiptId, bookId, realReturnedDate, penalty);
        boolean bookIsReturned = receiptDao.returnBookFromUser(bookId, userId, receiptId, realReturnedDate, penalty);
        if (bookIsReturned)
            changeQuantityBooksInLibraryWhenBooksIsReturnedByUser(bookId);
        return bookIsReturned;
    }

    @Override
    public boolean changeQuantityBooksInLibraryWhenBooksAreOrdered(long bookId) throws DaoException, ServiceException {
        int booksQuantityInLibrary = getBooksQuantity(bookId);
        log.info("Checks for books in the library");
        if (booksQuantityInLibrary > ZERO_BOOK_COPIES) {
            log.info("Subtract one book with id: {} from the total quantity in library", bookId);
            int totalBookQuantity = booksQuantityInLibrary - ONE_BOOK;
            return bookDao.changeQuantityBooksInLibraryWhenBookIsGivenToUser(bookId, totalBookQuantity);
        }
        log.info("Can`t change total quantity books with id: {} in library, because there are no copies", bookId);
        throw new IllegalStateException("Can`t change total quantity books with id: %s in library, because there are no copies".formatted(bookId));
    }

    @Override
    public boolean changeQuantityBooksInLibraryWhenBooksIsReturnedByUser(long bookId) throws DaoException, ServiceException {
        int booksQuantityInLibrary = getBooksQuantity(bookId);
        log.info("Return one book with id: {} to then library", bookId);
        int totalBookQuantity = booksQuantityInLibrary + ONE_BOOK;
        return bookDao.changeQuantityBooksInLibraryWhenBookIsReturnedByUser(bookId, totalBookQuantity);
    }

    @Override
    public List<Receipt> getAllUsersDescriptionReceipts() throws ServiceException {
        log.info("Getting all users description receipts");
        return receiptDao.getAllUsersDescriptionReceipts();
    }

    @Override
    public List<Receipt> getAllUsersReceipts() throws ServiceException {
        log.info("Getting all users receipts");
        return receiptDao.getAllUsersReceipts();
    }

    private long countDifferenceBetweenTwoDatesInADays(LocalDateTime realReturned, LocalDateTime estimateReturned) {
        log.info("Counts difference between real returned date time: {} and estimate returned date time: {} ", realReturned, estimateReturned);
        Duration difference = Duration.between(realReturned, estimateReturned);
        log.info("Returns result between real returned date time and estimate date time in day");
        return difference.toDays();
    }

    private LocalDateTime getRealReturned(long bookId, long userId, long receiptId) throws DaoException {
        log.info("Gets real returned date time book with id: {} in receipt with id: {} by user with id: {} ", bookId, receiptId, userId);
        return receiptDao.getRealReturnedDateTime(bookId, userId, receiptId).get().getRealReturnDate();
    }

    private LocalDateTime getEstimateReturn(long bookId, long userId, long receiptId) throws DaoException {
        log.info("Gets estimate date time book with id: {} in receipt with id: {} by user with id: {} ", bookId, receiptId, userId);
        return receiptDao.getEstimateDateTime(bookId, userId, receiptId).get().getEstimateReturnDate();
    }

    private double countPenalty(long bookId, long userId, long receiptId) throws DaoException {
        long days = countDifferenceBetweenTwoDatesInADays(getEstimateReturn(bookId, userId, receiptId), getRealReturned(bookId, userId, receiptId));
        log.info("Checks if the book with id: {} in receipt with id: {} by user with id: {} was returned on time", bookId, receiptId, userId);
        if (days > 0) {
            log.info("Book with id: {} in receipt with id: {} returned by user with id: {} not in time, counting penalty", bookId, receiptId, userId);
            return days * PENALTY_FOR_DAY;
        }
        log.info("Book with id: {} in receipt with id: {} returned by user with id: {} in time, penalty = 0", bookId, receiptId, userId);
        return NO_PENALTY;
    }

    private Optional<Book> getBookById(long id) throws DaoException, ServiceException {
        log.info("Starts searching book with id: {}", id);
        return Optional.ofNullable(bookDao.getBookById(id)
                .orElseThrow(() -> new ServiceException("Book with id: {} does`t found")));
    }

    private int getBooksQuantity(long id) throws DaoException, ServiceException {
        log.info("Gets total quantity books with id: {} in library", id);
        return getBookById(id).map(Book::getQuantity).orElseThrow(() -> new ServiceException("Book with id: {} does`t found"));
    }
}
