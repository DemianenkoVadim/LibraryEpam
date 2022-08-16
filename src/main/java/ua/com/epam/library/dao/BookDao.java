package ua.com.epam.library.dao;

import ua.com.epam.library.entity.Book;
import ua.com.epam.library.exception.dao.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookDao extends Dao<Book> {

    /**
     * Method add new Book
     *
     * @param book object to add
     * @return true if book is added otherwise false
     * @throws DaoException Exception
     */
    boolean addBook(Book book) throws DaoException, SQLException;

    /**
     * Method delete book by ID
     *
     * @param id book ID
     * @return true if book is deleted otherwise false
     * @throws DaoException Exception
     */
    boolean delete(Long id) throws DaoException;

    /**
     * Method gets all Books from database
     *
     * @return List of all books from database
     * @throws DaoException Exception
     */
    List<Book> getAll() throws DaoException;

    /**
     * Method gets all book by genre
     *
     * @param name genre of book
     * @return List books
     * @throws DaoException Exception
     */
    List<Book> getAllBooksByGenre(String name) throws DaoException;

    /**
     * Method gets book by ID
     *
     * @param id book ID
     * @return Optional of book
     * @throws DaoException Exception
     */
    Optional<Book> getBookById(long id) throws DaoException;

    /**
     * Method sort book by criterion
     *
     * @param sortCriteria criterion to sort book
     * @return List of book
     * @throws DaoException Exception
     */
    List<Book> sort(String sortCriteria, int page) throws DaoException;

    /**
     * Method get books by book title or by author
     *
     * @param search criterion for search
     * @return List of books
     * @throws DaoException Exception
     */
    List<Book> getBookByTitleAndAuthorSearch(String search) throws DaoException;

    /**
     * Method change quantity books in library when book is given to user
     *
     * @param bookId book id given to user;
     * @return true if book quantity is changed otherwise false
     * @throws DaoException Exception
     */
    boolean changeQuantityBooksInLibraryWhenBookIsGivenToUser(long bookId, int totalQuantityBook) throws DaoException;

    /**
     * Method change quantity books in library when book is returned user
     *
     * @param bookId book id returned by user;
     * @return true if book quantity is changed otherwise false
     * @throws DaoException Exception
     */
    boolean changeQuantityBooksInLibraryWhenBookIsReturnedByUser(long bookId, int totalBookQuantity) throws DaoException;

    /**
     * Method update book information
     *
     * @param book object to update
     * @return true if book is updated otherwise false
     * @throws DaoException Exception
     */
    boolean updateBook(Book book) throws DaoException;

    /**
     * Method gets number of books
     *
     * @return numbers of books
     * @throws DaoException Exception
     */
    double getSize() throws DaoException;

    /**
     * Method finds book by title
     *
     * @param title book title
     * @return Optional of book
     * @throws DaoException Exception
     */
    Optional<Book> findBookByTitle(String title) throws DaoException;
}
