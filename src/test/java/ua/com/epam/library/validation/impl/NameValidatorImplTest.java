package ua.com.epam.library.validation.impl;

import org.junit.jupiter.api.Test;
import ua.com.epam.library.service.validation.AbstractValidator;
import ua.com.epam.library.service.validation.impl.NameValidatorImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NameValidatorImplTest {

    AbstractValidator validator = new NameValidatorImpl();

    List<String> correctName = List.of("Name", "Tom", "Олександр", "name");

    List<String> incorrectName = List.of("", " ", " name");

    @Test
    void testNameValidator_CorrectName_ShouldReturnTrue() {
        for (String name : correctName) {
            boolean isNameValid = validator.isValid(name);
            assertTrue(isNameValid);
        }
    }

    @Test
    void testNameValidator_IncorrectName_ShouldReturnFalse() {
        for (String name : incorrectName) {
            boolean isNameValid = validator.isValid(name);
            assertFalse(isNameValid);
        }
    }

    @Test
    void testNameValidator_NameIsNull_ShouldReturnFalse() {
        boolean isNameValid = validator.isValid(null);
        assertFalse(isNameValid);
    }
}