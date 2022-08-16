package ua.com.epam.library.validation.impl;

import org.junit.jupiter.api.Test;
import ua.com.epam.library.service.validation.AbstractValidator;
import ua.com.epam.library.service.validation.impl.PhoneValidatorImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhoneValidatorImplTest {

    AbstractValidator validator = new PhoneValidatorImpl();

    List<String> correctPhoneNumber = List.of(
            "0671111111", "0961111111", "0971111111", "0981111111", "0681111111",
            "+380671111111", "+380961111111", "+380971111111", "+380981111111",
            "0501111111", "0661111111", "0951111111", "0991111111",
            "+380501111111", "+380661111111", "+380951111111", "+380991111111",
            "0631111111", "0731111111", "0931111111",
            "+380631111111", "+380731111111", "+380931111111"
    );

    List<String> incorrectPhoneNumber = List.of(
            "", " ", "Q", "1", "12343452", "2055550125",
            "202 555 0125", "(202) 555-0125", "+111 (202) 555-0125",
            "636 856 789", "+111 636 856 789", "636 85 67 89", "+111 636 85 67 89"
    );

    @Test
    void testPhoneValidator_CorrectPhoneNumber_ShouldReturnTrue() {
        for (String phoneNumber : correctPhoneNumber) {
            boolean isPhoneNumberValid = validator.isValid(phoneNumber);
            assertTrue(isPhoneNumberValid);
        }
    }

    @Test
    void testPhoneValidator_IncorrectPhoneNumber_ShouldReturnFalse() {
        for (String phoneNumber : incorrectPhoneNumber) {
            boolean isPhoneNumberValid = validator.isValid(phoneNumber);
            assertFalse(isPhoneNumberValid);
        }
    }

    @Test
    void testPhoneValidator_PhoneNumberIsNull_ShouldReturnFalse() {
        boolean isPhoneNumberValid = validator.isValid(null);
        assertFalse(isPhoneNumberValid);
    }
}