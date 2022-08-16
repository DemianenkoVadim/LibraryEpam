package ua.com.epam.library.dao;

import ua.com.epam.library.dao.impl.*;

public class DaoFactory {

    private static DaoFactory instance;
    private final UserDao userDao = new UserDaoImpl();
    private final GenreDao genreDao = new GenreDaoImpl();
    private final RoleDaoImpl roleDao = new RoleDaoImpl();
    private final BookDaoImpl bookDao = new BookDaoImpl();
    private final CartDao cartDao = new CartDaoImpl();
    private final AuthorDao authorDao = new AuthorDaoImpl();
    private final ReceiptDao receiptDao = new ReceiptDaoImpl();
    private final PublishingHouseDao publishingHouseDao = new PublishingHouseDaoImpl();

    private DaoFactory() {
    }

    public static synchronized DaoFactory getInstance() {
        if (instance == null)
            instance = new DaoFactory();
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public RoleDaoImpl getRoleDao() {
        return roleDao;
    }

    public GenreDao getGenreDao() {
        return genreDao;
    }

    public CartDao getCartDao() {
        return cartDao;
    }

    public ReceiptDao getReceiptDao() {
        return receiptDao;
    }

    public BookDaoImpl getBookDao() {
        return bookDao;
    }

    public AuthorDao getAuthorDao() {
        return authorDao;
    }

    public PublishingHouseDao getPublishingHouseDao() {
        return publishingHouseDao;
    }
}
