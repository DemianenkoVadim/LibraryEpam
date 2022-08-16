package ua.com.epam.library.exception.myexception;

public class PublishingHouseNotFoundException extends RuntimeException {

    public PublishingHouseNotFoundException(String message) {
        super(message);
    }
}
