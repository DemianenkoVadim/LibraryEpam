package ua.com.epam.library.service.validation;

import ua.com.epam.library.service.validation.impl.*;

public class ValidatorFactory {

    private final EmailValidatorImpl emailValidator = new EmailValidatorImpl();
    private final NameValidatorImpl nameValidator = new NameValidatorImpl();
    private final PhoneValidatorImpl phoneValidator = new PhoneValidatorImpl();
    private final YearValidatorImpl yearValidator = new YearValidatorImpl();
    private final IdValidatorImpl idValidator = new IdValidatorImpl();

    public static ValidatorFactory getInstance() {
        return Holder.INSTANCE;
    }

    public EmailValidatorImpl getEmailValidator() {
        return emailValidator;
    }

    public NameValidatorImpl getNameValidator() {
        return nameValidator;
    }

    public PhoneValidatorImpl getPhoneValidator() {
        return phoneValidator;
    }

    public YearValidatorImpl getYearValidator() {
        return yearValidator;
    }

    public IdValidatorImpl getIdValidator() {
        return idValidator;
    }

    private static class Holder {
        static final ValidatorFactory INSTANCE = new ValidatorFactory();
    }
}
