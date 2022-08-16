package ua.com.epam.library.service;

import ua.com.epam.library.entity.Book;
import ua.com.epam.library.exception.myexception.BookNotFoundException;
import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookService {

    /**
     * Method adds book to the library
     *
     * @param addBookRequest book parameters
     * @return true if book is added in the library otherwise false
     * @throws ServiceException Exception
     * @throws SQLException Exception
     */
    boolean addBook(AddBookRequest addBookRequest) throws ServiceException, SQLException;

    /**
     * Method updates book information in the library
     *
     * @param addBookRequest book parameters
     * @return true if book information is updated otherwise false
     * @throws ServiceException Exception
     * @throws SQLException Exception
     */
    boolean updateBook(AddBookRequest addBookRequest) throws ServiceException, SQLException;

    /**
     * Method delete book from library
     *
     * @param id book ID
     * @return true if book successfully deleted
     * @throws ServiceException Exception
     */
    boolean delete(long id) throws ServiceException;

    /**
     * Method finds all books in library
     *
     * @return List of books
     * @throws ServiceException Exception
     */
    List<Book> getAll() throws ServiceException;

    /**
     * Method gets all books by genre
     *
     * @param name genre
     * @return List of books findings by genre
     * @throws ServiceException Exception
     */
    List<Book> getAllBooksByGenre(String name) throws ServiceException;

    /**
     * Method gets book by ID
     *
     * @param id book ID
     * @return Optional of book
     * @throws ServiceException Exception
     */
    Optional<Book> getBookById(long id) throws ServiceException;

    /**
     * Method sorted books
     *
     * @param sortType type to sort
     * @param page     page
     * @return List of sorted books
     * @throws ServiceException Exception
     */
    List<Book> sort(String sortType, int page) throws ServiceException;

    /**
     * Method search books by Title and Author
     *
     * @param search parameter for searching
     * @return List of Books
     * @throws ServiceException Exception
     */
    List<Book> getBookByTitleAndAuthorSearch(String search) throws ServiceException;

    /**
     * Method gets number of books
     *
     * @return numbers of books
     * @throws ServiceException Exception
     */
    double getSize() throws ServiceException;

    /**
     * Method find book by title
     *
     * @param title book title
     * @return Optional of Book
     * @throws BookNotFoundException Exception
     */
    Optional<Book> findBookByTitle(String title) throws BookNotFoundException;
}
