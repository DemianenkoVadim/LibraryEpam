package ua.com.epam.library.service.validation.impl;

import ua.com.epam.library.service.validation.AbstractValidator;

public class PhoneValidatorImpl extends AbstractValidator {

    private static final String PHONE_REGEX = "^\\+?3?8?(0[5-9][0-9]\\d{7})$";

    @Override
    protected String getRegex() {
        return PHONE_REGEX;
    }
}
