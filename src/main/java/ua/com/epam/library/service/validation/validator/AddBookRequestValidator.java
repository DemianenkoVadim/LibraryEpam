package ua.com.epam.library.service.validation.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import ua.com.epam.library.service.*;
import ua.com.epam.library.service.validation.ModelValidator;
import ua.com.epam.library.service.validation.Validator;
import ua.com.epam.library.service.validation.ValidatorFactory;
import ua.com.epam.library.service.validation.model.ValidationResult;
import ua.com.epam.library.servlet.admin.request.AddBookRequest;

import static ua.com.epam.library.util.Constant.COPIES_LIMIT;
import static ua.com.epam.library.util.Constant.ERROR_MESSAGE_EMPTY_REQUEST;

public class AddBookRequestValidator implements ModelValidator<AddBookRequest> {

    Logger log = (Logger) LogManager.getLogger(AddBookRequestValidator.class.getName());

    private final BookService bookService;
    private final GenreService genreService;
    private final PublishingHouseService publishingHouseService;
    private final AuthorService authorService;
    private final Validator nameValidator;

    public AddBookRequestValidator(
            BookService bookService,
            GenreService genreService,
            PublishingHouseService publishingHouseService,
            AuthorService authorService,
            Validator nameValidator) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.publishingHouseService = publishingHouseService;
        this.authorService = authorService;
        this.nameValidator = nameValidator;
    }

    public AddBookRequestValidator() {
        this(
                ServiceFactory.getInstance().getBookService(),
                ServiceFactory.getInstance().getGenreService(),
                ServiceFactory.getInstance().getPublishingHouseService(),
                ServiceFactory.getInstance().getAuthorService(),
                ValidatorFactory.getInstance().getNameValidator()
        );
    }

    @Override
    public ValidationResult<AddBookRequest> validate(AddBookRequest request) {
        ValidationResult<AddBookRequest> validationResult = new ValidationResult<>(request);
        checksTitleFieldForValidity(request, validationResult);
        checksPublishingHouseFieldRequestForValidity(request, validationResult);
        checksPublishedFieldForValidity(request, validationResult);
        checksQuantityFieldRequestForValidity(request, validationResult);
        checksDescriptionFieldForValidity(request, validationResult);
        checksGenreFieldRequestForValidity(request, validationResult);
        checksAuthorFirstNameFieldForValidity(request, validationResult);
        checksAuthorLastNameFieldForValidity(request, validationResult);
        checksBookImageFieldForValidity(request, validationResult);
        return validationResult;
    }

    private void checksTitleFieldForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `title` for validity");
        if (request.getTitle() == null || request.getTitle().isEmpty()) {
            log.warn("Field `title` is NULL or Empty");
            validationResult.getErrors().put("title", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (!nameValidator.isValid(request.getTitle())) {
            log.warn("Input value in the field `title` is not valid");
            validationResult.getErrors().put("title", "Book title is not valid");
        }
    }

    private void checksPublishingHouseFieldRequestForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `publishing house` for validity");
        if (request.getPublishingHouse() == null || request.getPublishingHouse().isEmpty()) {
            log.warn("Field `publishing house in NULL or Empty");
            validationResult.getErrors().put("publishingHouse", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (publishingHouseService.findByName(request.getPublishingHouse()).isEmpty()) {
            log.warn("Input value in the field `publishing house` is not valid");
            validationResult.getErrors().put("publishingHouse", "This publishing house does not exist in the database");
        }
    }

    private void checksBookImageFieldForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `Book image` for validity");
        if (request.getBookImage() == null || request.getBookImage().isEmpty()) {
            log.warn("Field `Book image` is NULL or Empty");
            validationResult.getErrors().put("bookImage", ERROR_MESSAGE_EMPTY_REQUEST);
        }
    }

    private void checksPublishedFieldForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `published` for validity");
        if (request.getPublished() == null || request.getPublished().isEmpty()) {
            log.warn("Field `published` is NULL or Empty");
            validationResult.getErrors().put("published", ERROR_MESSAGE_EMPTY_REQUEST);
        }
    }

    private void checksDescriptionFieldForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `Description` for validity");
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            log.warn("Field `Description` is NULL or Empty");
            validationResult.getErrors().put("description", ERROR_MESSAGE_EMPTY_REQUEST);
        }
    }

    private void checksAuthorLastNameFieldForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `Author Last name` for validity");
        if (request.getAuthorLastName() == null || request.getAuthorLastName().isEmpty()) {
            log.warn("Field `Author Last name` is NULL or Empty");
            validationResult.getErrors().put("authorLastName", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (!nameValidator.isValid(request.getAuthorLastName())) {
            log.warn("Input value in the field `Author Last name` is not valid");
            validationResult.getErrors().put("authorLastName", "Last name is not valid");
            return;
        }
        if (authorService.findAuthorLastName(request.getAuthorLastName()).isEmpty()) {
            log.warn("Input value in the field `Author Last name` is does not exist in the database");
            validationResult.getErrors().put("authorLastName", "Last name is not found in the database");
        }
    }

    private void checksAuthorFirstNameFieldForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `Author First name` for validity");
        if (request.getAuthorFirstName() == null || request.getAuthorFirstName().isEmpty()) {
            log.warn("Field `Author First name` is NULL or Empty");
            validationResult.getErrors().put("authorFirstName", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (!nameValidator.isValid(request.getAuthorFirstName())) {
            log.warn("Input value in the field `Author First name` is not valid");
            validationResult.getErrors().put("authorFirstName", "First name is not valid");
            return;
        }
        if (authorService.findAuthorFirstName(request.getAuthorFirstName()).isEmpty()) {
            log.warn("Input value in the field `Author first name` is does not exist in the database");
            validationResult.getErrors().put("authorFirstName", "First name is not found in the database");
        }
    }

    private void checksGenreFieldRequestForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `Genre` for validity");
        if (request.getGenre() == null || request.getGenre().isEmpty()) {
            log.warn("Field `genre` is NULL or Empty");
            validationResult.getErrors().put("genre", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (genreService.findByName(request.getGenre()).isEmpty()) {
            log.warn("Input value in the field `Genre` is does not exist in the database");
            validationResult.getErrors().put("genre", "Genre does not exist in the database");
        }
    }

    private void checksQuantityFieldRequestForValidity(AddBookRequest request, ValidationResult<AddBookRequest> validationResult) {
        log.info("Checking input value in the field `quantity` for validity");
        if (request.getQuantity() == 0) {
            log.warn("Field `quantity` is NULL or Empty");
            validationResult.getErrors().put("quantity", ERROR_MESSAGE_EMPTY_REQUEST);
            return;
        }
        if (request.getQuantity() < 0) {
            log.warn("Input value in the field `Quantity` is not valid");
            validationResult.getErrors().put("quantity", "Can not be negative");
            return;
        }
        if (request.getQuantity() > COPIES_LIMIT) {
            log.warn("Input value in the field `Quantity` is more than limit copies prepared");
            validationResult.getErrors().put("quantity", "Can not be too many copies");
        }
    }
}
