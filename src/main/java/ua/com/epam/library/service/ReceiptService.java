package ua.com.epam.library.service;

import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.service.ServiceException;

import java.sql.SQLException;
import java.util.List;


public interface ReceiptService {

    boolean addReceipt(List<Receipt> receipts) throws DaoException, SQLException;

    /**
     * Method proceed user receipt
     *
     * @param bookId    book ID
     * @param userId    user ID
     * @param receiptId receipt ID
     * @return true if receipt is proceed otherwise false
     * @throws ServiceException Service Exception
     */
    boolean proceedReceipt(long bookId, long userId, long receiptId) throws ServiceException;

    /**
     * Method lend book to user
     *
     * @param bookId    book ID
     * @param userId    user ID
     * @param receiptId receipt ID
     * @return true if book lend to user
     * @throws ServiceException Service Exception
     */
    boolean lendBookToUser(long bookId, long userId, long receiptId) throws ServiceException;

    /**
     * Method return book from user
     *
     * @param bookId    book ID
     * @param userId    user ID
     * @param receiptId receipt ID
     * @return true if book returned from user
     * @throws ServiceException Service Exception
     */
    boolean returnBookFromUser(long bookId, long userId, long receiptId) throws ServiceException;

    /**
     * Method change quantity books in library when user make order
     *
     * @param bookId book ID
     * @throws ServiceException Service Exception
     * @return true if quantity book in library is changed when book is ordered otherwise false
     */
    boolean changeQuantityBooksInLibraryWhenBooksAreOrdered(long bookId) throws ServiceException;

    /**
     * Method change quantity books in library when user return book
     *
     * @param bookId book ID
     * @throws ServiceException Service Exception
     * @return true if quantity book in library is changed when book is returned by user otherwise false
     */
    boolean changeQuantityBooksInLibraryWhenBooksIsReturnedByUser(long bookId) throws ServiceException;

    /**
     * Method finds all user description receipts
     *
     * @return List of description receipts
     * @throws ServiceException Service Exception
     */
    List<Receipt> getAllUsersDescriptionReceipts() throws ServiceException;

    /**
     * Method gets all users receipts
     *
     * @return List of users receipts
     * @throws ServiceException service exception
     */
    List<Receipt> getAllUsersReceipts() throws ServiceException;
}
