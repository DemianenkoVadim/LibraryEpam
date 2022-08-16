package ua.com.epam.library.dao;

import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

public interface CartDao extends Dao<Cart> {

    /**
     * Method add new Cart
     *
     * @param cart cart to add
     * @return true id cart is added otherwise false
     * @throws DaoException DAO exception
     */
    boolean createCart(Cart cart) throws DaoException;

    /**
     * Method gets all Book by User ID
     *
     * @param id user ID
     * @return List carts
     * @throws DaoException DAO exception
     */
    List<Cart> getAllBooksInCartByUserId(long id) throws DaoException;

    /**
     * Method delete book
     *
     * @param bookId book ID
     * @param userId user ID
     * @return true is cart deleted otherwise false
     * @throws DaoException DAO exception
     */
    boolean delete(long bookId, long userId, long cartId) throws DaoException;

    Optional<Cart> getReceiptByUserIdAndByBookIdAndCartId(long userId, long bookId, long cartId);

    /**
     * Method gets all item of book by book ID
     *
     * @param id book id
     * @return List of carts with items of book
     * @throws DaoException DAO exception
     */
    List<Cart> getAllBooksInCartByBookId(long id) throws DaoException;
}
