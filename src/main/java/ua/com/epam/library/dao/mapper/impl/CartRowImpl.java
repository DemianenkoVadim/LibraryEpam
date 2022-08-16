package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Cart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartRowImpl implements RowMapper<Cart> {

    @Override
    public Cart map(ResultSet resultSet) throws SQLException {
        Cart cart = new Cart();
        cart.setId(resultSet.getLong(Column.ID));
        cart.setBookId(resultSet.getLong(Column.BOOK_ID));
        cart.setTitle(resultSet.getString(Column.TITLE));
        cart.setPhotoName(resultSet.getString(Column.PHOTO_NAME));
        cart.setAuthorFirstName(resultSet.getString(Column.AUTHOR_FIRST_NAME));
        cart.setAuthorLastName(resultSet.getString(Column.AUTHOR_LAST_NAME));
        cart.setUserId(resultSet.getLong(Column.USER_ID));
        cart.setQuantity(resultSet.getInt(Column.QUANTITY));
        cart.setTotal(resultSet.getInt(Column.TOTAL));
        return cart;
    }
}