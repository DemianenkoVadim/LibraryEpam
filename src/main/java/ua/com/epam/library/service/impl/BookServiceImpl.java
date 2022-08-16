package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.exception.dao.DaoException;
import ua.com.epam.library.exception.myexception.BookNotFoundException;
import ua.com.epam.library.service.*;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {

    private static final Logger log = (Logger) LogManager.getLogger(BookServiceImpl.class.getName());

    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;

    public BookServiceImpl() {
        this(DaoFactory.getInstance().getBookDao(),
                ServiceFactory.getInstance().getGenreService(),
                ServiceFactory.getInstance().getAuthorService(),
                ServiceFactory.getInstance().getPublishingHouseService());
    }

    public BookServiceImpl(
            BookDao bookDao,
            GenreService genreService,
            AuthorService authorService,
            PublishingHouseService publishingHouseService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
        this.publishingHouseService = publishingHouseService;
    }


    @Override
    public boolean addBook(AddBookRequest addBookRequest) throws SQLException {
        log.info("Adding book to the library");
        Book book = bookForAdd(addBookRequest);
        log.info("Book is added successfully");
        return bookDao.addBook(book);
    }

    @Override
    public boolean updateBook(AddBookRequest addBookRequest) throws SQLException {
        log.info("Updating book in the library");
        Book book = bookForEdit(addBookRequest);
        log.info("Book is updated successfully");
        return bookDao.updateBook(book);
    }


//
//    @Override
//    public boolean updateBook(long id, String title, String published, int quantity, String description,
//                              long genreId, String genreName,
//                              long authorId, String firstName, String lastName,
//                              long publishingHouseId, String publishingHouseName, String photoName) {

//        log.info("Checks book parameters for null");
//        if (parametersAreNull(title, published, quantity, description,
//                genreId, genreName,
//                authorId, firstName, lastName,
//                publishingHouseId, publishingHouseName, photoName)) {
//            log.warn("Book is not updated, because some of parameters are null");
//            return false;
//        }
//        try {
//            Book book = createBookToUpdatedBook(id, title, published, quantity, description,
//                    genreId, genreName,
//                    authorId, firstName, lastName,
//                    publishingHouseId, publishingHouseName, photoName);
//            bookDao.updateBook(book);
//            log.info("Book is updated");
//            return true;
//        } catch (DaoException exception) {
//            log.error("Can`t update book to library");
//            throw new ServiceException(exception.getMessage(), exception);
//        }
//    }

    @Override
    public boolean delete(long id) {
        Optional<Book> book = getBookById(id);
        log.info("Deleting book with id: {} ", id);
//        if (book.isEmpty())
//            throw new ServiceException("Book with id: %d does`t found".formatted(id));
        log.info("Book with id: {} successfully deleted", id);
        return bookDao.delete(id);
    }

    @Override
    public List<Book> getAll() {
        log.info("Getting all books");
        return bookDao.getAll();
    }

    @Override
    public List<Book> getAllBooksByGenre(String genreName) {
        log.info("Searching all books by genre name: {}", genreName);
        return bookDao.getAllBooksByGenre(genreName);
    }

    @Override
    public Optional<Book> getBookById(long id) {
        log.info("Searching Book with id: {}", id);
        return Optional.ofNullable(bookDao.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id: %d does not found".formatted(id))));
    }

    @Override
    public List<Book> sort(String sortType, int page) {
        log.info("Sorting books by: {}", sortType);
        return bookDao.sort(sortType, page);
    }

    @Override
    public List<Book> getBookByTitleAndAuthorSearch(String search) {
        log.info("Searching book by title and author with search parameter: {}", search);
        return bookDao.getBookByTitleAndAuthorSearch(search);
    }

//    @Override
//    public boolean changeQuantityBooksInLibraryWhenBookIsGivenToUser(long bookId, int totalBookQuantity) {
//        log.info("Changing quantity of books in library when book with id: {} is given to user. " +
//                "Total book quantity: {}", bookId, totalBookQuantity);
//        return changeQuantityBooksInLibraryWhenBookIsGivenToUser(bookId, totalBookQuantity);
//    }
//
//    @Override
//    public boolean changeQuantityBooksInLibraryWhenBookIsReturnedByUser(long bookId, int totalBookQuantity) {
//        log.info("Changing quantity of books in library when book with id: {} is returned by user. " +
//                "Total book quantity: {}", bookId, totalBookQuantity);
//        return changeQuantityBooksInLibraryWhenBookIsReturnedByUser(bookId, totalBookQuantity);
//    }

    @Override
    public double getSize() throws DaoException {
        log.info("Getting the number of books in the list");
        return bookDao.getSize();
    }

    @Override
    public Optional<Book> findBookByTitle(String title) throws BookNotFoundException {
        log.info("Finding book by title in database");
        return bookDao.findBookByTitle(title);
    }

    private long findGenreIdByGenreName(String genre) {
        return genreService
                .findByName(genre)
                .orElseThrow()
                .getId();
    }

    private long findAuthorIdByAuthorFirstAndLastName(String firstName, String lastName) {
        return authorService
                .findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow()
                .getId();
    }

    private long findPublishingHouseByName(String publishingHouseName) {
        return publishingHouseService
                .findByName(publishingHouseName)
                .orElseThrow()
                .getId();
    }

    private Book bookForAdd(AddBookRequest addBookRequest) {
        Book book = new Book();
        createsBook(addBookRequest, book);
        book.setCreated(LocalDateTime.now());
        return book;
    }

    private Book bookForEdit(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setId(addBookRequest.getId());
        createsBook(addBookRequest, book);
        return book;
    }

    private void createsBook(AddBookRequest addBookRequest, Book book) {
        book.setTitle(addBookRequest.getTitle());
        book.setPublished(addBookRequest.getPublished());
        book.setQuantity(addBookRequest.getQuantity());
        book.setDescription(addBookRequest.getDescription());
        book.setGenreId(findGenreIdByGenreName(addBookRequest.getGenre()));
        book.setGenreName(addBookRequest.getGenre());
        book.setAuthorsId(findAuthorIdByAuthorFirstAndLastName(addBookRequest.getAuthorFirstName(), addBookRequest.getAuthorLastName()));
        book.setAuthorFirstName(addBookRequest.getAuthorFirstName());
        book.setAuthorLastName(addBookRequest.getAuthorLastName());
        book.setPublishingHouseId(findPublishingHouseByName(addBookRequest.getPublishingHouse()));
        book.setPublishingHouseName(addBookRequest.getPublishingHouse());
        book.setPhotoName(addBookRequest.getBookImage());
    }
}
