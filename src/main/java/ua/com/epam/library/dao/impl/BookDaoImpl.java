package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.BookDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.connection.ConnectionPool;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.Author;
import ua.com.epam.library.entity.Book;
import ua.com.epam.library.exception.dao.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.Constant.PAGE_SIZE;
import static ua.com.epam.library.util.Constant.TABLE_SIZE_COUNT;
import static ua.com.epam.library.util.DataBaseQueriesConstants.*;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    public BookDaoImpl() {
        super(RowMapperFactory.getInstance().getBookRowMapper(), DatabaseTableTitle.BOOK);
    }

    @Override
    public boolean addBook(Book book) throws DaoException, SQLException {
        boolean bookIsAdded = false;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(ADD_BOOK_TO_THE_LIBRARY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getPublished());
            statement.setInt(3, book.getQuantity());
            statement.setString(4, book.getDescription());
            statement.setString(5, book.getPhotoName());
            statement.setLong(6, book.getGenreId());
            statement.setLong(7, book.getAuthorsId());
            statement.setLong(8, book.getPublishingHouseId());
            statement.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            boolean created = statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            long bookId = 0;
            if (generatedKeys.next()) {
                bookId = generatedKeys.getLong(1);
            }
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK_AND_AUTHOR);
            preparedStatement.setLong(1, bookId);
            preparedStatement.setLong(2, book.getAuthorsId());
            boolean count2 = preparedStatement.execute();
            connection.commit();
            bookIsAdded = true;
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            connection.rollback();
            connection.close();
            exception.printStackTrace();
        }
        return bookIsAdded;
    }

    @Override
    public boolean updateBook(Book book) {
        return executeInsertQuery(
                UPDATE_BOOK,
                book.getTitle(),
                book.getPublished(),
                book.getDescription(),
                book.getPhotoName(),
                book.getGenreId(),
                book.getAuthorsId(),
                book.getPublishingHouseId(),
                book.getQuantity(),
                book.getId()
        );
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        return executeToUpdateQuery(DELETE_BOOK, id);
    }

    @Override
    public List<Book> getAll() throws DaoException {
        return executeQuery(FIND_ALL_BOOKS);
    }

    @Override
    public List<Book> getAllBooksByGenre(String name) throws DaoException {
        return executeQuery(FIND_ALL_BOOKS_BY_GENRE, name);
    }

    @Override
    public Optional<Book> getBookById(long id) throws DaoException {
        Optional<Book> book = executeSingleResult(FIND_BOOK_BY_ID, id);
        List<Author> authors = getListEntities(Author.class, FIND_BOOK_AUTHORS_BY_BOOK_ID, id);
        book.ifPresent(b -> b.setAuthors(authors));
        return book;
    }

    @Override
    public List<Book> sort(String sortType, int page) throws DaoException {
        String query = switch (sortType) {
            case "titleAsc" -> SORT_BY_TITLE_ASC;
            case "titleDesc" -> SORT_BY_TITLE_DESC;
            case "authorAsc" -> SORT_BY_AUTHOR_ASC;
            case "authorDesc" -> SORT_BY_AUTHOR_DESC;
            case "publishingAsc" -> SORT_BY_PUBLISHED_YEAR_ASC;
            case "publishingDesc" -> SORT_BY_PUBLISHED_YEAR_DESC;
            case "publishingHouseAsc" -> SORT_BY_PUBLISHING_HOUSE_ASC;
            case "publishingHouseDesc" -> SORT_BY_PUBLISHING_HOUSE_DESC;
            default -> FIND_ALL_BOOKS_LIMIT;
        };
        int offset = PAGE_SIZE * (page - 1);
        return executeQuery(query, PAGE_SIZE, offset);
    }

    @Override
    public List<Book> getBookByTitleAndAuthorSearch(String search) throws DaoException {
        String parameters = "%" + search + "%";
        return executeQuery(FIND_BOOKS_BY_TITLE_AND_AUTHOR, parameters, parameters, parameters);
    }

    @Override
    public boolean changeQuantityBooksInLibraryWhenBookIsGivenToUser(long bookId, int totalBookQuantity) throws DaoException {
        return executeToUpdateQuery(UPDATE_QUANTITY_BOOKS_WHEN_BOOK_IS_GIVEN_OR_RETURNED, totalBookQuantity, bookId);
    }

    @Override
    public boolean changeQuantityBooksInLibraryWhenBookIsReturnedByUser(long bookId, int totalBookQuantity) throws DaoException {
        return executeToUpdateQuery(UPDATE_QUANTITY_BOOKS_WHEN_BOOK_IS_GIVEN_OR_RETURNED, totalBookQuantity, bookId);
    }

    @Override
    public double getSize() throws DaoException {
        return executeQueryGetSize(TABLE_SIZE_COUNT);
    }

    @Override
    public Optional<Book> findBookByTitle(String title) throws DaoException {
        return executeSingleResult(FIND_BOOK_BY_TITLE, title);
    }
}
