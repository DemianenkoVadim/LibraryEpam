package ua.com.epam.library.service.validation;

import ua.com.epam.library.exception.service.ServiceException;
import ua.com.epam.library.service.validation.model.ValidationResult;
import ua.com.epam.library.service.validation.validator.AddBookRequestValidator;
import ua.com.epam.library.service.validation.validator.CreateLibrarianValidator;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;
import ua.com.epam.library.servlet.admin.request.CreateLibrarianRequest;

import java.util.HashMap;
import java.util.Map;

public class ValidationServiceImpl implements ValidationService {

    private static ValidationServiceImpl instance;

    private Map<Class<?>, ModelValidator<?>> validators;

    public ValidationServiceImpl(Map<Class<?>, ModelValidator<?>> validators) {
        this.validators = validators;
    }

    public static ValidationServiceImpl getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    private static synchronized ValidationServiceImpl createInstance() {
        if (instance != null) {
            return instance;
        }
        Map<Class<?>, ModelValidator<?>> validators = new HashMap<>();
        validators.put(AddBookRequest.class, new AddBookRequestValidator());
        validators.put(CreateLibrarianRequest.class, new CreateLibrarianValidator());
        instance = new ValidationServiceImpl(validators);
        return instance;
    }

    @Override
    public <T> ValidationResult<T> validate(T request) {

        @SuppressWarnings("unchecked")
        ModelValidator<T> modelValidator = (ModelValidator<T>) validators.get(request.getClass());

        if (modelValidator == null) {
            throw new ServiceException(String.format("Validator for class [%s] not found", request.getClass().getName()));
        }
        return modelValidator.validate(request);
    }
}
