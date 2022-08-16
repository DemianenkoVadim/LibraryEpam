package ua.com.epam.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.dao.DaoFactory;
import ua.com.epam.library.dao.PublishingHouseDao;
import ua.com.epam.library.entity.PublishingHouse;
import ua.com.epam.library.service.PublishingHouseService;

import java.util.List;
import java.util.Optional;

public class PublishingHouseServiceImpl implements PublishingHouseService {

    Logger log = (Logger) LogManager.getLogger(PublishingHouseServiceImpl.class.getName());

    private final PublishingHouseDao publishingHouseDao;

    public PublishingHouseServiceImpl() {
        this(DaoFactory.getInstance().getPublishingHouseDao());
    }

    public PublishingHouseServiceImpl(PublishingHouseDao publishingHouseDao) {
        this.publishingHouseDao = publishingHouseDao;
    }

    @Override
    public Optional<PublishingHouse> findByName(String publishingHouse) {
        log.info("Starts searching publishing house name: {} ", publishingHouse);
        return publishingHouseDao.findByName(publishingHouse);
    }

    @Override
    public List<PublishingHouse> findAllPublishingHouse() {
        log.info("Searching all publishing house in database");
        return publishingHouseDao.getAllPublishingHouse();
    }
}
