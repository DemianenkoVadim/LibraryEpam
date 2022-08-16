package ua.com.epam.library.service;

import ua.com.epam.library.service.impl.*;

public class ServiceFactory {

    private static ServiceFactory instance;
    private UserService userService;
    private RoleService roleService;
    private ReceiptService receiptService;
    private CartService cartService;
    private BookService bookService;
    private GenreService genreService;
    private AuthorService authorService;
    private PublishingHouseService publishingHouseService;

    private ServiceFactory() {
    }

    public static synchronized ServiceFactory getInstance() {
        if (instance == null)
            instance = new ServiceFactory();
        return instance;
    }

    public UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public RoleService getRoleService() {
        if (roleService == null) {
            roleService = new RoleServiceImpl();
        }
        return roleService;
    }

    public ReceiptService getReceiptService() {
        if (receiptService == null) {
            receiptService = new ReceiptServiceImpl();
        }
        return receiptService;
    }

    public CartService getCartService() {
        if (cartService == null) {
            cartService = new CartServiceImpl();
        }
        return cartService;
    }

    public BookService getBookService() {
        if (bookService == null) {
            bookService = new BookServiceImpl();
        }
        return bookService;
    }

    public GenreService getGenreService() {
        if (genreService == null) {
            genreService = new GenreServiceImpl();
        }
        return genreService;
    }

    public AuthorService getAuthorService() {
        if (authorService == null) {
            authorService = new AuthorServiceImpl();
        }
        return authorService;
    }

    public PublishingHouseService getPublishingHouseService() {
        if (publishingHouseService == null) {
            publishingHouseService = new PublishingHouseServiceImpl();
        }
        return publishingHouseService;
    }
}
