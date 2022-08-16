package ua.com.epam.library.dao.impl;

import ua.com.epam.library.dao.AbstractDao;
import ua.com.epam.library.dao.DatabaseTableTitle;
import ua.com.epam.library.dao.PublishingHouseDao;
import ua.com.epam.library.dao.mapper.RowMapperFactory;
import ua.com.epam.library.entity.PublishingHouse;
import ua.com.epam.library.exception.dao.DaoException;

import java.util.List;
import java.util.Optional;

import static ua.com.epam.library.util.DataBaseQueriesConstants.FIND_ALL_PUBLISHING_HOUSE;
import static ua.com.epam.library.util.DataBaseQueriesConstants.FIND_PUBLISHING_HOUSE_BY_COMPANY_NAME;

public class PublishingHouseDaoImpl extends AbstractDao<PublishingHouse> implements PublishingHouseDao {

    public PublishingHouseDaoImpl() {
        super(RowMapperFactory.getInstance().getPublishingHouseRowMapper(), DatabaseTableTitle.PUBLISHING_HOUSE);
    }

    @Override
    public Optional<PublishingHouse> findByName(String name) throws DaoException {
        return executeSingleResult(FIND_PUBLISHING_HOUSE_BY_COMPANY_NAME, name);
    }

    @Override
    public List<PublishingHouse> getAllPublishingHouse() throws DaoException {
        return  executeQuery(FIND_ALL_PUBLISHING_HOUSE);
    }
}
