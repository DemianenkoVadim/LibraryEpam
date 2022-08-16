package ua.com.epam.library.service.validation.model;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult<T> {

    private final T entity;
    private final Map<String, String> errors;

    public ValidationResult(T entity, Map<String, String> errors) {
        this.entity = entity;
        this.errors = errors;
    }

    public ValidationResult(T entity) {
        this(entity, new HashMap<>());
    }

    public T getEntity() {
        return entity;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
