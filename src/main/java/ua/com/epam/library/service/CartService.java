package ua.com.epam.library.service;

import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.service.ServiceException;

import java.util.List;

public interface CartService {

    /**
     * Method create cart with item by book ID and user ID
     *
     * @param bookId book ID
     * @param userId user ID
     * @return true if cart created and item is added otherwise false
     * @throws ServiceException Exception
     */

    boolean createCart(long bookId, long userId) throws ServiceException;

    /**
     * Method finds all books adding to the cart by user
     *
     * @param id user ID
     * @return List of items in Cart
     * @throws ServiceException Exception
     */

    List<Cart> getAllBooksInCartByUserId(long id) throws ServiceException, DaoException;

    /**
     * Method finds all book in cart by book id
     *
     * @param id book ID
     * @return List of items book in Cart
     * @throws ServiceException Exception
     * @throws DaoException Exception
     */
    List<Cart> getAllItemsOfBookInCartByBookId(long id) throws ServiceException, DaoException;

    /**
     * Method remove book from cart
     *
     * @param bookId book ID
     * @param userId user ID
     * @param cartId cart ID
     * @return true if book removed from the cart otherwise false
     * @throws ServiceException Exception
     */

    boolean delete(long bookId, long userId, long cartId) throws ServiceException;
}
