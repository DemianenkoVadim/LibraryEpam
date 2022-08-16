package ua.com.epam.library.service.validation.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.service.ServiceFactory;
import ua.com.epam.library.service.UserService;
import ua.com.epam.library.service.validation.ModelValidator;
import ua.com.epam.library.service.validation.Validator;
import ua.com.epam.library.service.validation.ValidatorFactory;
import ua.com.epam.library.service.validation.model.ValidationResult;
import ua.com.epam.library.servlet.admin.request.CreateLibrarianRequest;

import static ua.com.epam.library.util.Constant.ERROR_MESSAGE_EMPTY_REQUEST;

public class CreateLibrarianValidator implements ModelValidator<CreateLibrarianRequest> {

    Logger log = (Logger) LogManager.getLogger(CreateLibrarianValidator.class.getName());

    private final UserService userService;
    private final Validator nameValidator;
    private final Validator emailValidator;
    private final Validator phoneValidator;

    public CreateLibrarianValidator(UserService userService,
                                    Validator nameValidator,
                                    Validator emailValidator,
                                    Validator phoneValidator) {
        this.nameValidator = nameValidator;
        this.emailValidator = emailValidator;
        this.phoneValidator = phoneValidator;
        this.userService = userService;
    }

    public CreateLibrarianValidator() {
        this(
                ServiceFactory.getInstance().getUserService(),
                ValidatorFactory.getInstance().getNameValidator(),
                ValidatorFactory.getInstance().getEmailValidator(),
                ValidatorFactory.getInstance().getPhoneValidator()
        );
    }

    @Override
    public ValidationResult<CreateLibrarianRequest> validate(CreateLibrarianRequest request) {
        ValidationResult<CreateLibrarianRequest> validationResult = new ValidationResult<>(request);
        checksNameFieldForValidity(request, validationResult);
        checksEmailFieldForValidity(request, validationResult);
        checksPhoneFieldForValidity(request, validationResult);
        checksPasswordFieldForValidity(request, validationResult);
        checksCheckInFieldForValidity(request, validationResult);
        return validationResult;
    }

    private void checksCheckInFieldForValidity(CreateLibrarianRequest request, ValidationResult<CreateLibrarianRequest> validationResult) {
        log.info("Checking input value in the field `check` for validity");
        if (request.getCheck() == null || request.getCheck().isEmpty()) {
            log.warn("Field `check` is NULL or Empty");
            validationResult.getErrors().put("check", ERROR_MESSAGE_EMPTY_REQUEST);
        }
    }

    private void checksPasswordFieldForValidity(CreateLibrarianRequest request, ValidationResult<CreateLibrarianRequest> validationResult) {
        log.info("Checking input value in the field `password` for validity");
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            log.warn("Field `password` is NULL or Empty");
            validationResult.getErrors().put("password", ERROR_MESSAGE_EMPTY_REQUEST);
        }
    }

    private void checksPhoneFieldForValidity(CreateLibrarianRequest request, ValidationResult<CreateLibrarianRequest> validationResult) {
        log.info("Checking input value in the field `phone` for validity");
        if (request.getPhone() == null || request.getPhone().isEmpty()) {
            log.warn("Field `phone` is NULL or Empty");
            validationResult.getErrors().put("phone", ERROR_MESSAGE_EMPTY_REQUEST);
        }
        if (!phoneValidator.isValid(request.getPhone())) {
            log.warn("Input value in the `phone` is not valid");
            validationResult.getErrors().put("phone", "Phone is not valid");
            return;
        }
        if (userService.findUserByPhone(request.getPhone()).isPresent()) {
            log.warn("User with phone number: {} already exist", request.getPhone());
            validationResult.getErrors().put("phone", "Phone is already exists");
        }
    }

    private void checksEmailFieldForValidity(CreateLibrarianRequest request, ValidationResult<CreateLibrarianRequest> validationResult) {
        log.info("Checking input value in the field `email` for validity");
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            log.warn("Field `email` is NULL or Empty");
            validationResult.getErrors().put("email", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (!emailValidator.isValid(request.getEmail())) {
            log.warn("Input value in the field `email` is not valid");
            validationResult.getErrors().put("email", "Email is not valid");
            return;
        }
        if (userService.findUserByEmail(request.getEmail()).isPresent()) {
            log.warn("User with email: {} already exists", request.getEmail());
            validationResult.getErrors().put("email", "Email is already exists");
        }
    }

    private void checksNameFieldForValidity(CreateLibrarianRequest request, ValidationResult<CreateLibrarianRequest> validationResult) {
        log.info("Checking input value in the field `name` for validity");
        if (request.getName() == null || request.getName().isEmpty()) {
            log.warn("Field `name` is NULL or Empty");
            validationResult.getErrors().put("name", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (!nameValidator.isValid(request.getName())) {
            log.warn("Input value in the field `name` is not valid");
            validationResult.getErrors().put("name", "Name is not valid");
        }
    }
}
