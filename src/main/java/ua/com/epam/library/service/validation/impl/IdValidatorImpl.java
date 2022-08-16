package ua.com.epam.library.service.validation.impl;

import ua.com.epam.library.service.validation.AbstractValidator;

public class IdValidatorImpl extends AbstractValidator {

    private static final String ID_REGEX = "(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])";

    @Override
    protected String getRegex() {
        return ID_REGEX;
    }
}
