package ua.com.epam.library.service.validation;

import ua.com.epam.library.service.validation.model.ValidationResult;

public interface ModelValidator<T> {

    ValidationResult<T> validate(T request);
}
