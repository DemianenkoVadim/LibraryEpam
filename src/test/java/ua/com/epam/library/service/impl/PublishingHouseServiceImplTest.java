package ua.com.epam.library.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.epam.library.dao.PublishingHouseDao;
import ua.com.epam.library.entity.PublishingHouse;
import ua.com.epam.library.exception.myexception.PublishingHouseNotFoundException;
import ua.com.epam.library.service.PublishingHouseService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PublishingHouseServiceImplTest {

    private PublishingHouseService publishingHouseService;

    @Mock
    private PublishingHouseDao publishingHouseDao;

    @BeforeEach
    void setUp() {
        publishingHouseService = new PublishingHouseServiceImpl(publishingHouseDao);
    }

    @Test
    void testFindByName_ShouldReturnSuccessfullPublishingHouse() {
        //given
        String publishingHouseName = "Black tomato";
        given(publishingHouseDao.findByName(publishingHouseName))
                .willReturn(Optional.of(new PublishingHouse()));

        //when
        when(publishingHouseDao.findByName(publishingHouseName))
                .thenReturn(Optional.of(new PublishingHouse()));
        Optional<PublishingHouse> publishingHouseOptional = publishingHouseService.findByName(publishingHouseName);

        //then
        assertTrue(publishingHouseOptional.isPresent(), "Publishing house should be present");

        verify(publishingHouseDao).findByName(publishingHouseName);
    }

    @Test
    void testFindByName_ShouldReturnThrownWithPublishingHouseNameIsNull() {
        //given
        given(publishingHouseDao.findByName(null))
                .willThrow(new PublishingHouseNotFoundException("Publishing house with name null is not found"));

        //when
        //then
        assertThrows(PublishingHouseNotFoundException.class, () -> Optional
                .ofNullable(publishingHouseService.findByName(null))
                .orElseThrow(() -> new PublishingHouseNotFoundException("Publishing house with name null is not found")));
    }

    @Test
    void testFindByName_ShouldReturnThrownWithEmptyParameter() {
        //given
        String publishingHouseName = "";
        given(publishingHouseDao.findByName(publishingHouseName))
                .willThrow(new PublishingHouseNotFoundException("Publishing house with empty name is not found"));
        //when
        //then
        assertThrows(PublishingHouseNotFoundException.class, () -> Optional
                .ofNullable(publishingHouseService.findByName(publishingHouseName))
                .orElseThrow(() -> new PublishingHouseNotFoundException("Publishing house with empty name is not found")));
    }
}