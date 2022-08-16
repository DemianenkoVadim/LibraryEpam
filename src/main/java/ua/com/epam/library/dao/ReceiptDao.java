package ua.com.epam.library.dao;

import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.exception.dao.DaoException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReceiptDao extends Dao<Receipt> {

    /**
     * Method add new Receipt
     *
     * @param receipt list of receipts
     * @return true if receipt is add otherwise false
     * @throws DaoException DAO Exception
     * @throws SQLException SQL Exception
     */
    boolean addReceipt(List<Receipt> receipt) throws DaoException, SQLException;

    /**
     * Method gets all receipts by user ID
     *
     * @param id user ID
     * @return List of receipts
     * @throws DaoException DAO Exception
     */
    List<Receipt> getAllReceiptsByUserId(long id) throws DaoException;

    /**
     * Method gets all receipts on subscriptions by user ID
     *
     * @param id user id
     * @return List of receipts
     * @throws DaoException DAO Exception
     */
    List<Receipt> getAllReceiptsBookOnSubscriptionByUserId(long id) throws DaoException;

    /**
     * Method gets all receipts by description
     *
     * @return List of receipts
     * @throws DaoException DAO Exception
     */
    List<Receipt> getAllUsersDescriptionReceipts() throws DaoException;

    /**
     * Method gets all user`s receipts
     *
     * @return List of receipts
     * @throws DaoException DAO Exception
     */
    List<Receipt> getAllUsersReceipts() throws DaoException;

    /**
     * Method proceed receipt
     *
     * @param bookId    book ID
     * @param userId    user ID
     * @param receiptId receipt ID
     * @return true is receipt proceed otherwise false
     * @throws DaoException DAO Exception
     */
    boolean proceedReceipt(long bookId, long userId, long receiptId) throws DaoException;

    /**
     * Method lend book to user
     *
     * @param bookId             book ID
     * @param userId             user ID
     * @param receiptId          receipt ID
     * @param receivingDate      date of receiving
     * @param estimateReturnDate estimate date to return book
     * @return true is book lend to user otherwise false
     * @throws DaoException DAO Exception
     */
    boolean lendBookToUser(long bookId, long userId, long receiptId, LocalDateTime receivingDate, LocalDateTime estimateReturnDate) throws DaoException;

    /**
     * Method determines if a book has been returned to the library
     *
     * @param bookId           book ID
     * @param userId           user ID
     * @param receiptId        receipt ID
     * @param realReturnedDate real Date book was returned
     * @param penalty          penalty for not returning book on time
     * @return true is book returned to library otherwise false
     * @throws DaoException DAO Exception
     */
    boolean returnBookFromUser(long bookId, long userId, long receiptId, LocalDateTime realReturnedDate, double penalty) throws DaoException;

    /**
     * Method get receipt with real returned datetime
     *
     * @param bookId    book ID
     * @param userId    user ID
     * @param receiptId receipt ID
     * @return Optional of receipt
     * @throws DaoException DAO Exception
     */

    Optional<Receipt> getRealReturnedDateTime(long bookId, long userId, long receiptId) throws DaoException;

    /**
     * Method get receipt with estimate datetime
     *
     * @param bookId    book ID
     * @param userId    user ID
     * @param receiptId receipt ID
     * @return Optional of receipt
     * @throws DaoException DAO Exception
     */
    Optional<Receipt> getEstimateDateTime(long bookId, long userId, long receiptId) throws DaoException;
}
