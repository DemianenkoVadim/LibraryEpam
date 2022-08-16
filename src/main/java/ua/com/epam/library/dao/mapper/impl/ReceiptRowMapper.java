package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Receipt;
import ua.com.epam.library.entity.Rent;
import ua.com.epam.library.entity.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ReceiptRowMapper implements RowMapper<Receipt> {

    @Override
    public Receipt map(ResultSet resultSet) throws SQLException {
        Receipt receipt = new Receipt();
        receipt.setId(resultSet.getLong(Column.ID));
        receipt.setUserId(resultSet.getLong(Column.USER_ID));
        receipt.setBookId(resultSet.getLong(Column.BOOK_ID));
        receipt.setPhotoName(resultSet.getString(Column.PHOTO_NAME));
        receipt.setTitle(resultSet.getString(Column.TITLE));
        receipt.setEmail(resultSet.getString(Column.EMAIL));
        receipt.setAuthorFirstName(resultSet.getString(Column.AUTHOR_FIRST_NAME));
        receipt.setAuthorLastName(resultSet.getString(Column.AUTHOR_LAST_NAME));
        receipt.setReceiptNumber(resultSet.getString(Column.RECEIPT_NUMBER));
        receipt.setReceivingDate((LocalDateTime) resultSet.getObject(Column.RECEIVING_DATE));
        receipt.setEstimateReturnDate((LocalDateTime) resultSet.getObject(Column.ESTIMATE_RETURN_DATE));
        receipt.setRealReturnDate((LocalDateTime) resultSet.getObject(Column.REAL_RETURN_DATE));
        receipt.setStage(Stage.valueOf(resultSet.getString(Column.STAGE)));
        receipt.setPenalty(resultSet.getDouble(Column.PENALTY));
        receipt.setRent(Rent.valueOf(resultSet.getString(Column.RENT)));
        return receipt;
    }
}
