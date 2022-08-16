package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.CartDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.DataBaseQueriesConstants.*;

public class CartDaoImpl extends AbstractDao<Cart> implements CartDao {

    public CartDaoImpl() {
        super(RowMapperFactory.getInstance().getCartRowMapper(), DatabaseTableTitle.CART);
    }

    @Override
    public boolean createCart(Cart cart) throws DaoException {
        return executeInsertQuery(INSERT_BOOK_TO_CART, cart.getBookId(), cart.getUserId(), cart.getTotal(), cart.getQuantity());
    }

    @Override
    public List<Cart> getAllBooksInCartByUserId(long id) throws DaoException {
        return executeQuery(FIND_ALL_BOOK_FROM_CART, id);
    }

    @Override
    public boolean delete(long bookId, long userId, long cartId) throws DaoException {
        return executeToUpdateQuery(DELETE_BOOK_FROM_CART, bookId, userId, cartId);
    }

    @Override
    public Optional<Cart> getReceiptByUserIdAndByBookIdAndCartId(long userId, long bookId, long cartId) {
        return executeSingleResult(FIND_CART_WITH_RECEIPT_BY_USER_ID_AND_BOOK_ID, userId, bookId, cartId);
    }

    @Override
    public List<Cart> getAllBooksInCartByBookId(long id) throws DaoException {
        return executeQuery(FIND_ALL_ITEMS_BY_BOOK_ID, id);
    }
}
