package ua.com.epam.library.service.validation.impl;

import ua.com.epam.library.service.validation.AbstractValidator;

public class NameValidatorImpl extends AbstractValidator {

    private static final String NAME_REGEX = "^[^\s]+,?(\s[^\s]+)*$";

    @Override
    protected String getRegex() {
        return NAME_REGEX;
    }
}
