package models;

public class SqlQueries {
    public static final String SQL_BOOKS_SELECT_ALL = "";

    public static final String SQL_PRINTS_SELECT_ALL = "SELECT prints.id, prints.type_id, " +
            "books.isbn, books.title, books.writer, books.publication_date, book_genres.genre, " +
            "periodicals.issn, periodicals.title, periodicals.publication_date, periodicals_formats.format, periodicals_genres.genre " +
            "FROM prints " +
            "LEFT JOIN books ON prints.type_id = 0 AND prints.descr_id = books.id " +
            "LEFT JOIN book_genres ON books.genre_id = book_genres.id " +
            "LEFT JOIN periodicals ON prints.type_id = 1 AND prints.descr_id = periodicals.id " +
            "LEFT JOIN periodicals_genres ON periodicals.genre_id = periodicals_genres.id " +
            "LEFT JOIN periodicals_formats ON periodicals.format_id = periodicals_formats.id";

    public static final String SQL_BOOK_SELECT_BY_ID = "";
    public static final String SQL_BOOK_SELECT_BY_TITLE = "";
    public static final String SQL_BOOK_UPDATE = "";
    public static final String SQL_BOOK_DELETE = "";

    public static final String SQL_BOOK_ADD = "INSERT INTO books (isbn, title, writer, publication_date, genre_id) " +
                                               "VALUES (?, ?, ?, ?, (SELECT id FROM book_genres WHERE genre = ?))";

    public static final String SQL_BOOK_ADD_TO_PRINT = "INSERT INTO prints (type_id, descr_id, reciept_date, is_available, is_takeaway) " +
                                                       "VALUES ((SELECT id FROM print_types WHERE type = ?), ?, ?, ?, ?)";

}
