package ua.com.epam.library.service;

import ua.com.epam.library.entity.PublishingHouse;

import java.util.List;
import java.util.Optional;

public interface PublishingHouseService {

    /**
     * Method retrieve publishing house;
     *
     * @param publishingHouse publishing house name;
     * @return Optional of publishing house;
     */

    Optional<PublishingHouse> findByName(String publishingHouse);

    /**
     * Method retrieve all publishing house;
     *
     * @return List of Publishing house;
     */
    List<PublishingHouse> findAllPublishingHouse();
}
