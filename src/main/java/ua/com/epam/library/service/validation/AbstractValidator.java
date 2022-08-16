package ua.com.epam.library.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractValidator implements Validator {

    protected abstract String getRegex();

    @Override
    public boolean isValid(String expression) {
        if (expression == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(getRegex());
        Matcher matcher = pattern.matcher(expression);
        return matcher.find();
    }
}
