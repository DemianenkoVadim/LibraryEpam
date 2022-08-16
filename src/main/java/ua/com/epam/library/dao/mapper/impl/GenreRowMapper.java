package ua.com.epam.library.dao.mapper.impl;

import ua.com.epam.library.dao.mapper.Column;
import ua.com.epam.library.dao.mapper.RowMapper;
import ua.com.epam.library.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<Genre> {

    @Override
    public Genre map(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getLong(Column.ID));
        genre.setGenre(resultSet.getString(Column.GENRE));
        return genre;
    }
}
