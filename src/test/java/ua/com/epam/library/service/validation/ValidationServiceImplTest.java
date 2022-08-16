package ua.com.epam.library.service.validation;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import ua.com.epam.library.service.validation.validator.AddBookRequestValidator;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;

class ValidationServiceImplTest {

    @Test
    void validate() {
        Map<Class<?>, ModelValidator<?>> validators = new HashMap<>();
        validators.put(AddBookRequest.class, new AddBookRequestValidator());

        ValidationService v = new ValidationServiceImpl(validators);
        AddBookRequest request = new AddBookRequest();

        v.validate(request);
    }
}