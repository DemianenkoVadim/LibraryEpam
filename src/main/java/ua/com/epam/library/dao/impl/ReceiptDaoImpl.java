package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.ReceiptDao;
import ua.com.epam.library.dao.connection.ConnectionPool;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.entity.Stage;
import ua.com.epam.library.exception.dao.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.DataBaseQueriesConstants.*;

public class ReceiptDaoImpl extends AbstractDao<Receipt> implements ReceiptDao {

    public ReceiptDaoImpl() {
        super(RowMapperFactory.getInstance().getOrderRowMapper(), DatabaseTableTitle.RECEIPT);
    }

    @Override
    public List<Receipt> getAllReceiptsBookOnSubscriptionByUserId(long id) throws DaoException {
        return executeQuery(FIND_ALL_SUBSCRIPTION_RECEIPTS_BY_USER_ID, id);
    }

    @Override
    public List<Receipt> getAllUsersDescriptionReceipts() throws DaoException {
        return executeQuery(FIND_ALL_USERS_DESCRIPTION_RECEIPTS);
    }

    @Override
    public List<Receipt> getAllUsersReceipts() throws DaoException {
        return executeQuery(FIND_ALL_USERS_RECEIPTS);
    }

    @Override
    public boolean proceedReceipt(long bookId, long userId, long receiptId) throws DaoException {
        return executeToUpdateQuery(CHANGE_STAGE_READY_FOR_ISSUANCE, bookId, userId, receiptId);
    }

    @Override
    public boolean lendBookToUser(long bookId, long userId, long receiptId, LocalDateTime receivingDate, LocalDateTime estimateReturnDate) throws DaoException {
        return executeToUpdateQuery(CHANGE_STAGE_READING, receivingDate, estimateReturnDate, bookId, userId, receiptId);
    }

    @Override
    public boolean returnBookFromUser(long bookId, long userId, long receiptId, LocalDateTime realReturnedDate, double penalty) throws DaoException {
        return executeToUpdateQuery(CHANGE_STAGE_RETURNED, realReturnedDate, penalty, bookId, userId, receiptId);
    }

    @Override
    public Optional<Receipt> getRealReturnedDateTime(long bookId, long userId, long receiptId) throws DaoException {
        return executeSingleResult(FIND_REAL_RETURNED_DATE, bookId, userId, receiptId);
    }

    @Override
    public Optional<Receipt> getEstimateDateTime(long bookId, long userId, long receiptId) throws DaoException {
        return executeSingleResult(FIND_ESTIMATE_DATE, bookId, userId, receiptId);
    }

    @Override
    public List<Receipt> getAllReceiptsByUserId(long id) throws DaoException {
        return executeQuery(FIND_ALL_RECEIPTS_BY_USER_ID, id);
    }

    @Override
    public boolean addReceipt(List<Receipt> receipts) throws DaoException, SQLException {
        boolean orderIsAdded = false;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(ADD_BOOK_TO_ORDER);
            for (Receipt order : receipts) {
                statement.setLong(1, order.getUserId());
                statement.setLong(2, order.getBookId());
                statement.setString(3, order.getReceiptNumber());
                statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
                statement.setString(7, String.valueOf(Stage.PENDING));
                statement.setDouble(8, order.getPenalty());
                statement.setString(9, String.valueOf(order.getRent()));
                statement.addBatch();
            }
            int[] count = statement.executeBatch();
            connection.commit();
            orderIsAdded = true;
            connection.setAutoCommit(true);
        } catch (Exception exception) {
            if (connection != null) {
                connection.rollback();
                connection.close();
            }
            exception.printStackTrace();
        }
        return orderIsAdded;
    }
}