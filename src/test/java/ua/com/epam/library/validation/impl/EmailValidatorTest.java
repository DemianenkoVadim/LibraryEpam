package ua.com.epam.library.validation.impl;

import org.junit.jupiter.api.Test;
import ua.com.epam.library.service.validation.AbstractValidator;
import ua.com.epam.library.service.validation.impl.EmailValidatorImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {

    private final AbstractValidator validator = new EmailValidatorImpl();
    private static final String EMPTY_EMAIL = "";
    private static final String EMAIL_IS_NULL = null;

    List<String> correctEmails = List.of(
            "user@domain.com",
            "user@domain.co.in",
            "user1@domain.com",
            "user.name@domain.com",
            "user_name@domain.co.in",
            "user-name@domain.co.in",
            "mouse@gmail.com",
            "ban@gmail.com",
            "platosha@gmail.com"
    );

    List<String> incorrectEmails = List.of(
            "user#@domain.co.in",
            "user@domaincom",
            "user#domain.com",
            "user?name@domain.co.in",
            "gmail.com",
            "user'name@domain.co.in",
            ".username@yahoo.com"
    );

    @Test
    void testEmailValidator_EmailIsCorrect_ShouldReturnTrue() {
        for (String email : correctEmails) {
            boolean actual = validator.isValid(email);
            assertTrue(actual);
        }
    }

    @Test
    void testEmailValidator_EmailIsIncorrect_ShouldReturnFalse() {
        for (String email : incorrectEmails) {
            boolean actual = validator.isValid(email);
            assertFalse(actual);
        }
    }

    @Test
    void testEmailValidator_EmailIsEmpty_ShouldReturnFalse() {
        boolean actual = validator.isValid(EMPTY_EMAIL);
        assertFalse(actual);
    }

    @Test
    void testEmailValidator_EmailIsNull_ShouldReturnFalse() {
        boolean actual = validator.isValid(EMAIL_IS_NULL);
        assertFalse(actual);
    }
}
