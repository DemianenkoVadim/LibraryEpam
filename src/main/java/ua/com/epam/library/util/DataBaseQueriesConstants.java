package ua.com.epam.library.util;

public abstract class DataBaseQueriesConstants {

    private DataBaseQueriesConstants() {
    }

    public static final String ADD_BOOK_AND_AUTHOR = """
            INSERT INTO `author_book`(`book_id`, `author_id`) VALUES (?,?)
                        """;

    public static final String FIND_ALL_GENRES = """
            SELECT * FROM `genre`
                        """;

    public static final String FIND_ALL_PUBLISHING_HOUSE = """
            SELECT * FROM `publishing_house`
                        """;

    public static final String FIND_ALL_ITEMS_BY_BOOK_ID = """
            SELECT *
                FROM `cart`
                    INNER JOIN `user` ON `user_id`=`user`.`id`
                    INNER JOIN `book` ON `book_id`=`book`.`id`
                    INNER JOIN `author` ON `author_id`=`author`.`id`
                    WHERE `book`.`id`=?
            """;
    public static final String UPDATE_BOOK = """
            UPDATE `book`       
            SET     `title`=?,
                    `published`=?,
                    `description`=?,
                    `photo_name`=?,
                    `genre_id`=?,
                    `author_id`=?,
                    `publishing_house_id`=?,
                    `quantity`=?
            WHERE `book`.`id`=?
                    """;

    public static final String FIND_BOOK_AUTHORS_BY_BOOK_ID = """
            SELECT*FROM `author_book`
            inner join author a on author_book.author_id = a.id
            where book_id=?
                      
                        """;

    public static final String FIND_BOOK_BY_TITLE = """
            SELECT *FROM `book` where `title`=?
                        """;

    public static final String FIND_AUTHOR_FIRST_NAME = """
            SELECT *FROM `author` where `first_name`=?
                        """;

    public static final String FIND_AUTHOR_LAST_NAME = """
            SELECT *FROM `author` where `last_name`=?
                        """;

    public static final String FIND_ROLE_BY_NAME_ROLE = """
            SELECT * FROM `role` WHERE `role`=?
            """;

    public static final String UPDATE_QUANTITY_BOOKS_WHEN_BOOK_IS_GIVEN_OR_RETURNED = """
            UPDATE `book`
                SET `quantity`=?
            WHERE `id`=?
            """;

    public static final String INSERT_BOOK_TO_CART = """
            INSERT
            INTO `cart`
            (
                `book_id`,
                `user_id`,
                `total`,
                `quantity`
            )
            VALUES (?,?,?,?)
            """;

    public static final String FIND_ALL_BOOK_FROM_CART = """
            SELECT *
                FROM `cart`
                    INNER JOIN `user` ON `user_id`=`user`.`id`
                    INNER JOIN `book` ON `book_id`=`book`.`id`
                    INNER JOIN `author` ON `author_id`=`author`.`id`
                    WHERE `user`.`id`=?
            """;

    public static final String FIND_ALL_RECEIPTS_BY_USER_ID = """
            SELECT *
                FROM `receipt`
                    INNER JOIN `user` ON `user_id`=`user`.`id`
                    INNER JOIN `book` ON `book_id`=`book`.`id`
                    INNER JOIN `author` ON `author_id`=`author`.`id`
            WHERE `user`.`id`=?
                ORDER BY `receiving_date` DESC
                """;

    public static final String FIND_ALL_USERS_DESCRIPTION_RECEIPTS = """
            SELECT *
                FROM `receipt`
                    INNER JOIN `user` ON `user_id` = `user`.`id`
                    INNER JOIN `book` ON `book_id` = `book`.`id`
                    INNER JOIN `author` ON `author_id` = `author`.`id`
            WHERE `rent`='SUBSCRIPTION'
                ORDER BY `receiving_date` DESC
               """;

    public static final String FIND_ALL_USERS_RECEIPTS = """
            SELECT *
            FROM `receipt`
                INNER JOIN `user` ON `user_id` = `user`.`id`
                INNER JOIN `book` ON `book_id` = `book`.`id`
                INNER JOIN `author` ON `author_id` = `author`.`id`
            ORDER BY `receiving_date` DESC
             """;

    public static final String FIND_ALL_SUBSCRIPTION_RECEIPTS_BY_USER_ID = """
            SELECT *FROM `receipt`
                INNER JOIN `user` ON `user_id` = `user`.`id`
                INNER JOIN `book` ON `book_id` = `book`.`id`
                INNER JOIN `author` ON `author_id` = `author`.`id`
            WHERE `user`.`id` = ? AND `stage` = 'READING' AND `rent` = 'SUBSCRIPTION'
            ORDER BY `receiving_date` DESC
               """;

    public static final String FIND_AUTHOR_BY_FIRST_AND_LAST_NAME = """
            SELECT *
                FROM `author`
            WHERE first_name=?
                AND last_name=?
            """;

    public static final String FIND_ALL_LIBRARIANS = """
            SELECT *
                FROM `user`
            INNER JOIN `role` ON `role_id` = `role`.`id`
                WHERE `role_id` = 2
            """;

    public static final String FIND_GENRE = """ 
            SELECT *
                FROM `genre`
            WHERE genre = ?
            """;
    public static final String FIND_PUBLISHING_HOUSE_BY_COMPANY_NAME = """
            SELECT *
                FROM `publishing_house`
            WHERE company = ?
            """;

    public static final String FIND_ALL_BLOCKED_USERS = """
            SELECT *
            FROM `user`
                 INNER JOIN `role` ON `role_id` = `role`.`id`
            WHERE `role_id` = 3
                 AND `status` = 'BLOCKED'
            """;

    public static final String DELETE_USER = """
            DELETE
              FROM `user`
              WHERE id = ?
            """;

    public static final String FIND_USER_BY_EMAIL = """
                    SELECT *
                    FROM `user`
                          INNER JOIN `role` ON `role_id` = `role`.`id`
                    WHERE `user`.`email` = ?
            """;

    public static final String FIND_USER_BY_MOBILE_PHONE = """
            SELECT *
             FROM `user`
                INNER JOIN `role` ON `role_id` = `role`.`id`
              WHERE `user`.`phone` = ?
             """;

    public static final String FIND_USER_BY_EMAIL_AND_PASSWORD = """
                   SELECT *
                      FROM `user`
                               INNER JOIN `role` ON `role_id` = `role`.`id`
                      WHERE `email` = ?
                        AND `password` = ?
            """;

    public static final String FIND_USER_BY_ID = """
                    SELECT *
                      FROM `user`
                               INNER JOIN `role` ON `role_id` = `role`.`id`
                      WHERE `user`.`id` = ?
            """;

    public static final String FIND_ALL_ACTIVE_USERS = """
            SELECT *
                 FROM `user`
                       INNER JOIN `role` ON `role_id`=`role`.`id`
                 WHERE
                   `role_id`=3
                 AND `status`='ACTIVE'
               """;

    public static final String CHANGE_USER_STATUS_TO_BLOCKED = """
            UPDATE `user`
                SET `status`='BLOCKED'
            WHERE `id`=?
            """;

    public static final String CHANGE_USER_STATUS_TO_UNBLOCK = """
            UPDATE `user`
                SET `status`='ACTIVE'
            WHERE `id`=?
            """;

    public static final String CHANGE_STAGE_READY_FOR_ISSUANCE = """
            UPDATE `receipt`
            SET `stage`= 'READY_FOR_ISSUANCE'
            WHERE `book_id` = ?
              AND `user_id` = ?
              AND `receipt`.`id` = ?
             """;

    public static final String CHANGE_STAGE_READING = """
            UPDATE `receipt`
                INNER JOIN `user`ON `user_id` = `user`.`id`
                INNER JOIN `book`ON `book_id` = `book`.`id`
                INNER JOIN `author`ON `author_id` = `author`.`id`
            SET `stage`='READING',
                   `receiving_date`=?,
                   `estimate_return_date`=?
            WHERE `book_id`=?
            AND `user_id`=?
            AND `receipt`.`id`=?
                    """;

    public static final String CHANGE_STAGE_RETURNED = """
            UPDATE `receipt`
                INNER JOIN `user` ON `user_id` = `user`.`id`
                INNER JOIN `book` ON `book_id` = `book`.`id`
                INNER JOIN `author` ON `author_id` = `author`.`id`
            SET `stage`='RETURNED',
                `real_return_date`=?,
                `penalty`=?
            WHERE `book_id` = ?
              AND `user_id` = ?
              AND `receipt`.`id` = ?
            """;

    public static final String FIND_REAL_RETURNED_DATE = """
            SELECT *
            FROM `receipt`
            INNER JOIN `user`ON `user_id` = `user`.`id`
            INNER JOIN `book`ON `book_id` = `book`.`id`
            INNER JOIN `author`ON `author_id` = `author`.`id`
            WHERE `book_id` =?
            AND `user_id` =?
            AND `receipt`.`id` =?
                    """;

    public static final String FIND_ESTIMATE_DATE = """
            SELECT *
            FROM `receipt`
            INNER JOIN `user`ON `user_id` = `user`.`id`
            INNER JOIN `book`ON `book_id` = `book`.`id`
            INNER JOIN `author`ON `author_id` = `author`.`id`
            WHERE `book_id` =?
            AND `user_id` =?
            AND `receipt`.`id` =?
                    """;

    public static final String ADD_BOOK_TO_ORDER = """
            INSERT INTO `receipt`
                    (
                    `user_id`,
                    `book_id`,
                    `receipt_number`,
                    `receiving_date`,
                    `estimate_return_date`,
                    `real_return_date`,
                    `stage`,
                    `penalty`,
                    `rent`
                    )

            VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

    public static final String CREATE_USER = """
            INSERT INTO `user` (
                    `name`,
                    `email`,
                    `phone`,
                    `password`,
                    `role_id`,
                    `created`,
                    `updated`,
                    `status`
                    )

            VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                    """;
    public static final String USER_REGISTRATION = """
            INSERT INTO `user` (
                    `name`,
                    `email`,
                    `phone`,
                    `password`,
                    `role_id`,
                    `created`,
                    `updated`,
                    `status`
                    )

            VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                    """;

    public static final String FIND_BOOK_BY_ID = """
            SELECT *
            FROM `book`
            INNER JOIN `genre` ON `genre_id` = `genre`.`id`
             INNER JOIN `author` ON `author_id` = `author`.`id`           
            INNER JOIN `publishing_house` ON `publishing_house_id` = `publishing_house`.`id`
            WHERE `book`.`id` =?
                    """;

    public static final String FIND_ALL_BOOKS_BY_GENRE = """
            SELECT *
            FROM `book`
            INNER JOIN `genre`ON `genre_id` = `genre`.`id`
            INNER JOIN `author`ON `author_id` = `author`.`id`
            INNER JOIN `publishing_house`ON `publishing_house_id` = `publishing_house`.`id`
            WHERE `genre`.`genre` =?
            ORDER BY `book`.`id`ASC
                    """;
    //    public static final String FIND_ALL_BOOKS_BY_GENRE = "SELECT * FROM `book` INNER JOIN `genre` ON `genre_id`=`genre`.`id` INNER JOIN `author` ON `author_id`=`author`.`id` INNER JOIN `publishing_house` ON `publishing_house_id`=`publishing_house`.`id` WHERE `genre`.`genre`=? ORDER BY `book`.`id` ASC LIMIT ? OFFSET ?";

    public static final String FIND_BOOKS_BY_TITLE_AND_AUTHOR = """
            SELECT *
            FROM `book`
            INNER JOIN `genre` ON `genre_id` = `genre`.`id`
            INNER JOIN `author`ON `author_id` = `author`.`id`
            INNER JOIN `publishing_house`ON `publishing_house_id` = `publishing_house`.`id`
            WHERE `book`.`title`like ?
            OR `first_name`like ?
            OR `last_name`like ?
                    """;

    public static final String ADD_BOOK_TO_THE_LIBRARY = """
            INSERT INTO `book`
                    (
                    `title`,
                    `published`,
                    `quantity`,
                    `description`,
                    `photo_name`,
                    `genre_id`,
                    `author_id`,
                    `publishing_house_id`,
                    `created`
                    )
            VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)
                    """;

    public static final String FIND_USER_BY_PASSWORD = """
            SELECT *
            FROM `user`
            INNER JOIN `role` ON `role_id`=`role`.`id`
            WHERE `user`.`id`=?
            AND `password`=?
                    """;

    public static final String UPDATE_USER = """
            UPDATE `user`
            SET `name`=?,
                    `email`=?,
                    `phone`=?,
                    `updated`=?
            WHERE `id`=?
                    """;

    public static final String FIND_CART_WITH_RECEIPT_BY_USER_ID_AND_BOOK_ID = """
            SELECT *
            FROM `cart`
                INNER JOIN `user` ON `user_id`=`user`.`id`
                INNER JOIN `book` ON `book_id`=`book`.`id`
                INNER JOIN `author` ON `author_id`=`author`.`id`
            WHERE `user_id`=?
                AND `book_id`=?
                AND `cart`.`id`=?;
            """;

    public static final String SORT_BY_TITLE_ASC = """
            SELECT *
            FROM `book`
                INNER JOIN `genre`ON `genre_id`=`genre`.`id`
                INNER JOIN `author`ON `author_id`=`author`.`id`
                INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `title`ASC
                LIMIT ? OFFSET ?
            """;

    public static final String SORT_BY_TITLE_DESC = """
            SELECT *
            FROM `book`
                INNER JOIN `genre`ON `genre_id`=`genre`.`id`
                INNER JOIN `author`ON `author_id`=`author`.`id`
                INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `title` DESC
             LIMIT ? OFFSET ?
            """;

    public static final String SORT_BY_AUTHOR_ASC = """
            SELECT *
            FROM `book`
                INNER JOIN `genre`ON `genre_id`=`genre`.`id`
                INNER JOIN `author`ON `author_id`=`author`.`id`
                INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `first_name` ASC 
            LIMIT ? OFFSET ?
                    """;

    public static final String SORT_BY_AUTHOR_DESC = """
            SELECT *FROM `book`
            INNER JOIN `genre`ON `genre_id`=`genre`.`id`
            INNER JOIN `author`ON `author_id`=`author`.`id`
            INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `first_name`
            DESC LIMIT ? OFFSET ?
                    """;

    public static final String SORT_BY_PUBLISHED_YEAR_ASC = """
            SELECT *FROM `book`
            INNER JOIN `genre`ON `genre_id`=`genre`.`id`
            INNER JOIN `author`ON `author_id`=`author`.`id`
            INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `published`
            ASC LIMIT ? OFFSET ?
                    """;

    public static final String SORT_BY_PUBLISHED_YEAR_DESC = """
            SELECT *FROM `book`
            INNER JOIN `genre`ON `genre_id`=`genre`.`id`
            INNER JOIN `author`ON `author_id`=`author`.`id`
            INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `published`
            DESC LIMIT ? OFFSET ?
                    """;

    public static final String SORT_BY_PUBLISHING_HOUSE_ASC = """
            SELECT *
                FROM `book`
                    INNER JOIN `genre`ON `genre_id`=`genre`.`id`
                    INNER JOIN `author`ON `author_id`=`author`.`id`
                    INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `company`
                ASC LIMIT ? OFFSET ?
                    """;

    public static final String SORT_BY_PUBLISHING_HOUSE_DESC = """
            SELECT *
                FROM `book`
                    INNER JOIN `genre`ON `genre_id`=`genre`.`id`
                    INNER JOIN `author`ON `author_id`=`author`.`id`
                    INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `company`
                DESC LIMIT ? OFFSET ?
                    """;

    public static final String FIND_ALL_BOOKS = """
            SELECT * FROM `book`
                    INNER JOIN `genre` ON `genre_id`=`genre`.`id`
                    INNER JOIN `author` ON `author_id`=`author`.`id`
                    INNER JOIN `publishing_house` ON `publishing_house_id`=`publishing_house`.`id`
            """;

    public static final String FIND_ALL_BOOKS_LIMIT = """
            SELECT *
            FROM `book`
            INNER JOIN `genre`ON `genre_id`=`genre`.`id`
            INNER JOIN `author`ON `author_id`=`author`.`id`
            INNER JOIN  `publishing_house`ON `publishing_house_id`=`publishing_house`.`id`
            ORDER BY `book`.`id`
            ASC LIMIT ? OFFSET ?
                    """;

    public static final String DELETE_BOOK = """ 
            DELETE 
            FROM `book` 
                 WHERE id=?
            """;

    public static final String DELETE_BOOK_FROM_CART = """
            DELETE
            FROM `cart`
               WHERE `book_id`=?
                 AND `user_id`=?
                 AND `cart`.`id`=?
                    """;
}
