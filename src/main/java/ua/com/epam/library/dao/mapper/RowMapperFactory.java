package ua.com.epam.library.dao.mapper;

import ua.com.epam.library.dao.mapper.impl.*;
import ua.com.epam.library.entity.*;

import java.util.Map;

public class RowMapperFactory {

    private static RowMapperFactory instance;
    private final RowMapper<Book> bookRowMapper = new BookRowMapper();
    private final RowMapper<Author> authorRowMapper = new AuthorRowMapper();
    private final RowMapper<Genre> genreRowMapper = new GenreRowMapper();
    private final RowMapper<PublishingHouse> publishingHouseRowMapper = new PublishingHouseRowMapper();
    private final RowMapper<Role> roleRowMapper = new RoleRowMapper();
    private final RowMapper<User> userRowMapper = new UserRowMapper();
    private final RowMapper<Receipt> orderRowMapper = new ReceiptRowMapper();
    private final RowMapper<Cart> cartRowMapper = new CartRowImpl();

    private final Map<String, RowMapper> maps;

    private RowMapperFactory() {
        maps = Map.of("Author", authorRowMapper);
    }

    public <Q extends Identifiable> RowMapper<Q> getMapper(Class<Q> type) {
        //noinspection unchecked
        return (RowMapper<Q>) maps.get(type.getSimpleName());
    }

    public static synchronized RowMapperFactory getInstance() {
        if (instance == null)
            instance = new RowMapperFactory();
        return instance;
    }

    public RowMapper<Book> getBookRowMapper() {
        return bookRowMapper;
    }

    public RowMapper<Author> getAuthorRowMapper() {
        return authorRowMapper;
    }

    public RowMapper<Genre> getGenreRowMapper() {
        return genreRowMapper;
    }

    public RowMapper<PublishingHouse> getPublishingHouseRowMapper() {
        return publishingHouseRowMapper;
    }

    public RowMapper<Role> getRoleRowMapper() {
        return roleRowMapper;
    }

    public RowMapper<User> getUserRowMapper() {
        return userRowMapper;
    }

    public RowMapper<Receipt> getOrderRowMapper() {
        return orderRowMapper;
    }

    public RowMapper<Cart> getCartRowMapper() {
        return cartRowMapper;
    }
}
