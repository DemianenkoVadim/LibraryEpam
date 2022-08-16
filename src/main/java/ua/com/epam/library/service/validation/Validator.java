package ua.com.epam.library.service.validation;

public interface Validator {

    /**
     * Method to validate information
     *
     * @param expression information as a string for validation
     * @return result of validation
     */
    boolean isValid(String expression);
}
