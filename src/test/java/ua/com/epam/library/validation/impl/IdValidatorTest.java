package ua.com.epam.library.validation.impl;

import org.junit.jupiter.api.Test;
import ua.com.epam.library.service.validation.AbstractValidator;
import ua.com.epam.library.service.validation.impl.IdValidatorImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IdValidatorTest {

    private static final String EMPTY_ID = "";
    private static final String ID_IS_NULL = null;

    AbstractValidator validator = new IdValidatorImpl();

    @Test
    void testIdValidator_IdIsCorrect_ShouldReturnTrue() {
        List<String> correctIdValues = List.of("1", "10", "100", "1000", "10000000", "45", "234", "111", "999", "2342345");
        for (String correct : correctIdValues) {
            boolean actual = validator.isValid(correct);
            assertTrue(actual);
        }
    }

    @Test
    void testIsValid_IdIsIncorrectNegativeDigit_ShouldReturnFalse() {
        List<String> incorrectIdsWithNegativeDigits = List.of("-1", "-100", "-54", "-1432", "-0932", "-3230234");
        for (String incorrectId : incorrectIdsWithNegativeDigits) {
            boolean actual = validator.isValid(incorrectId);
            assertFalse(actual);
        }
    }

    @Test
    void testIsValid_IdIsIncorrectPositiveDoubles_ShouldReturnFalse() {
        List<String> incorrectIdsWithNegativeDigits = List.of("1.2", "100.23", "54.3", "1432.0", "0932.3", "3230234.3", "0.1");
        for (String incorrectId : incorrectIdsWithNegativeDigits) {
            boolean actual = validator.isValid(incorrectId);
            assertFalse(actual);
        }
    }

    @Test
    void testIsValid_IdIsIncorrectNegativeDoubles_ShouldReturnFalse() {
        List<String> incorrectIdsWithNegativeDigits = List.of("-1.2", "-100.23", "-54.3", "-1432.0", "-0932.3", "-3230234.3", "-0.1");
        for (String incorrectId : incorrectIdsWithNegativeDigits) {
            boolean actual = validator.isValid(incorrectId);
            assertFalse(actual);
        }
    }

    @Test
    void testIsValid_IdWithWordsEn_ShouldReturnFalse() {
        List<String> incorrectIdsWithNegativeDigits = List.of("Hello", "all", "candy", "IdCart", "0book", "12book");
        for (String incorrectId : incorrectIdsWithNegativeDigits) {
            boolean actual = validator.isValid(incorrectId);
            assertFalse(actual);
        }
    }

    @Test
    void testIsValid_IdWithWordsUa_ShouldReturnFalse() {
        List<String> incorrectIdsWithNegativeDigits = List.of("привіт", "Все", "ноут", "Картка", "0книга", "12книга");
        for (String incorrectId : incorrectIdsWithNegativeDigits) {
            boolean actual = validator.isValid(incorrectId);
            assertFalse(actual);
        }
    }

    @Test
    void testIsValid_IdWithSymbol_ShouldReturnFalse() {
        List<String> incorrectIdsWithNegativeDigits = List.of("?", "/", "-", "_", "-");
        for (String incorrectId : incorrectIdsWithNegativeDigits) {
            boolean actual = validator.isValid(incorrectId);
            assertFalse(actual);
        }
    }

    @Test
    void testIsValid_IdNull_ShouldReturnFalse() {
        boolean actual = validator.isValid(ID_IS_NULL);
        assertFalse(actual);
    }

    @Test
    void testIsValid_IdIsEmpty_ShouldReturnFalse() {
        boolean actual = validator.isValid(EMPTY_ID);
        assertFalse(actual);
    }
}
