package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {

    @Override
    public Author map(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getLong(Column.ID));
        author.setFirstName(resultSet.getString(Column.AUTHOR_FIRST_NAME));
        author.setLastName(resultSet.getString(Column.AUTHOR_LAST_NAME));
        return author;
    }
}
