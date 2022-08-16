package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.dao.CartDao;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.entity.Cart;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.CartService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CartServiceImpl implements CartService {

    private static final Logger log = (Logger) LogManager.getLogger(CartServiceImpl.class.getName());

    private final BookDao bookDao;
    private final CartDao cartDao;

    public CartServiceImpl() {
        this(
                DaoFactory.getInstance().getBookDao(),
                DaoFactory.getInstance().getCartDao()
        );
    }

    public CartServiceImpl(BookDao bookDao, CartDao cartDao) {
        this.bookDao = bookDao;
        this.cartDao = cartDao;
    }

    @Override
    public boolean createCart(long bookId, long userId) throws ServiceException {
        try {
            if (checksForBookAvailability(bookId) && !parametersAreNull(bookId, userId)) {
                log.info("Creating cart by user id: {} with item - book id: {}", bookId, userId);
                Cart item = buildCart(bookId, userId);
                log.info("Adding item to the cart with id: {}", item.getId());
                return cartDao.createCart(item);
            }
        } catch (DaoException exception) {
            log.error("Can not add item and create cart: item - book with id: {} user with id: {}", bookId, userId);
            throw new ServiceException(exception.getMessage(), exception);
        }
        return false;
    }

    @Override
    public List<Cart> getAllBooksInCartByUserId(long id) throws ServiceException, DaoException {
        try {
            log.info("Get all book in cart choosing by user id: {} ", id);
            return cartDao.getAllBooksInCartByUserId(id);
        } catch (DaoException exception) {
            log.error("Can not find carts by user id: {}", id);
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Cart> getAllItemsOfBookInCartByBookId(long id) throws ServiceException, DaoException {
        try {
            log.info("Get all items book with id: {} in cart choosing by user", id);
            return cartDao.getAllBooksInCartByBookId(id);
        } catch (DaoException exception) {
            log.error("Can not find book with id: {} ", id);
            throw new ServiceException(exception.getMessage());
        }
    }

    @Override
    public boolean delete(long bookId, long userId, long cartId) throws ServiceException {
        Optional<Cart> receipt = getReceiptByUserIdAndByBookIdAndCartId(userId, bookId, cartId);
        log.info("Removing user`s cart with id: {}, user id: {} receipt book id: {} ", cartId, userId, bookId);
        log.info("Cart id: {} created by user id: {} with book id: {} successfully removed", cartId, userId, bookId);
        receipt.ifPresent(r -> cartDao.delete(bookId, userId, cartId));
        return receipt.isPresent();
    }

    private boolean checksForBookAvailability(long bookId) {
        int booksQuantity = getBooksQuantity(bookId);
        int amountItemsBookInCart = getAmountItemBookInCart(bookId);
        if (booksQuantity <= 0 || booksQuantity <= amountItemsBookInCart) {
            log.info("Can not add book with id: {} to the cart", bookId);
            return false;
        }
        return true;
    }

      int getAmountItemBookInCart(long bookId) {
        log.info("Getting all items book with id: {} ", bookId);
        return getAllItemsOfBookInCartByBookId(bookId).size();
    }

    private int getBooksQuantity(long id) throws DaoException, ServiceException {
        log.info("Getting total quantity books with id: {} in library", id);
        return getBookById(id).map(Book::getQuantity).orElseThrow(() -> new ServiceException("Book with id: {} %d does`t found".formatted(id)));
    }

    private Optional<Book> getBookById(long id) throws DaoException, ServiceException {
        log.info("Searching book with id: {}", id);
        return Optional.ofNullable(bookDao.getBookById(id)
                .orElseThrow(() -> new ServiceException("Book with id %d does`t found".formatted(id))));
    }

    private Optional<Cart> getReceiptByUserIdAndByBookIdAndCartId(long userId, long bookId, long cartId) {
        log.info("Searching receipt in cart with id:{} by user with id: {} and book with id: {}", cartId, userId, bookId);


        return cartDao.getReceiptByUserIdAndByBookIdAndCartId(userId, bookId, cartId);

    }

    private boolean parametersAreNull(Object... parameters) {
        log.info("Checking entered parameters for null");
        return Arrays.stream(parameters).anyMatch(Objects::isNull);
    }

    private Cart buildCart(long bookId, long userId) {
        Cart item = new Cart();
        item.setBookId(bookId);
        item.setUserId(userId);
        item.setTotal(1);
        item.setQuantity(1);
        log.info("Cart with book id: {} by user with id: {} created", bookId, userId);
        return item;
    }
}
