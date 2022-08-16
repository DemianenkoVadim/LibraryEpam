package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book map(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getLong(Column.ID));
        book.setTitle(resultSet.getString(Column.TITLE));
        book.setPublished(resultSet.getString(Column.PUBLISHED));
        book.setQuantity(resultSet.getInt(Column.QUANTITY));
        book.setDescription(resultSet.getString(Column.DESCRIPTION));
        book.setPhotoName(resultSet.getString(Column.PHOTO_NAME));
        book.setGenreId(resultSet.getLong(Column.GENRE_ID));
        book.setGenreName(resultSet.getString(Column.GENRE));
        book.setGenreId(resultSet.getLong(Column.AUTHOR_ID));
        book.setAuthorFirstName(resultSet.getString(Column.AUTHOR_FIRST_NAME));
        book.setAuthorLastName(resultSet.getString(Column.AUTHOR_LAST_NAME));
        book.setGenreId(resultSet.getLong(Column.PUBLISHING_HOUSE_ID));
        book.setPublishingHouseName(resultSet.getString(Column.PUBLISHING_HOUSE));
        book.setCreated((LocalDateTime) resultSet.getObject(Column.CREATED));
        return book;
    }
}
