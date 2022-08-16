package ua.com.epam.library.util;

public final class Constant {

    private Constant() {
    }

    public static final String USER = "user";
    public static final String ERROR_MESSAGE_EMPTY_REQUEST = "Can not be empty";

    public static final int COPIES_LIMIT = 100;

    public static final int PAGE_SIZE = 8;
    public static final String TABLE_SIZE_COUNT = "SELECT count(*) FROM `book`";

    public static final int ONE_BOOK = 1;
    public static final double ZERO_BOOK_COPIES = 0;

    public static final String LIBRARIAN = "librarian";
    public static final double PENALTY_FOR_DAY = 10;

    public static final double NO_PENALTY = 0;

    public final static int ROLE_USER = 3;
    public final static int ROLE_LIBRARIAN = 2;
}
