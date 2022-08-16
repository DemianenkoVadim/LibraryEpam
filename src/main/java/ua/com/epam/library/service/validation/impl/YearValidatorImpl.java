package ua.com.epam.library.service.validation.impl;

import ua.com.epam.library.service.validation.AbstractValidator;

public class YearValidatorImpl extends AbstractValidator {

    private static final String YEAR_REGEX = "^[0-9]{4}$";

    @Override
    protected String getRegex() {
        return YEAR_REGEX;
    }
}
