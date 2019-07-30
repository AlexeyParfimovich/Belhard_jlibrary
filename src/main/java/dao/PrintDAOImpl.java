package dao;

import entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrintDAOImpl implements PrintDAO {

    private static Logger log = Logger.getLogger(PrintDAOImpl.class.getName());
    private String dbDriver = "org.hsqldb.jdbcDriver";
    private String dbURL = "jdbc:hsqldb:file:./db/libdb";
    private String IfExists = ";ifexists=true";
    private String dbLogin = "SA";
    private String dbPass = "";

//    public PrintDAOImpl() {
//
////        Connection connection = null;
////
////        try {
////            log.info("Checking DB access:");
////
////            connection = DriverManager.getConnection(dbURL+IfExists, dbLogin, dbPass);
////            log.info("Сonnection established to " + dbURL);
////
////            connection.close();
////
////        } catch (SQLException ex) {
////
////            log.log(Level.WARNING,"Connecton not established:", ex);
////
////            try {
////                connection = DriverManager.getConnection(dbURL, dbLogin, dbPass);
////                createDefaultDB(connection);
////                log.info("Default DB created, connection established to " + dbURL);
////                connection.close()
////            } catch (SQLException exx) {
////                log.log(Level.SEVERE,"Default DB wasn't created:", exx);
////
////            } finally {
////                if (connection != null) connection.close();
////            }
////        }
//    }

    private void createDefaultDB(Connection connection) throws SQLException {

        String query;

        Statement statement = connection.createStatement();

        query = "CREATE TABLE IF NOT EXISTS book_genres (" +
                " id INTEGER IDENTITY PRIMARY KEY," +
                " genre VARCHAR(50) DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO book_genres (genre) VALUES " +
                "('CLASSIC'), ('CRIME'), ('FANTASY'), ('HISTORICAL'), ('HORROR'), ('THRILLER'), ('WESTERN')";
        statement.executeUpdate(query);

        log.info("Table Books_genres was created & filled with data");

        query = "CREATE TABLE IF NOT EXISTS books (" +
                " id BIGINT IDENTITY PRIMARY KEY," +
                " isbn VARCHAR(50) DEFAULT NULL," +
                " title VARCHAR(100) DEFAULT NULL," +
                " writer VARCHAR(50) DEFAULT NULL," +
                " publication_date DATE DEFAULT NULL," +
                " genre_id INTEGER DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO books (isbn, title, writer, publication_date, genre_id) VALUES " +
                "('ISBN 978-5-05-000013-2', 'New voices: A collection of Soviet short stories', 'Ivan Turgenev', '1985-01-01', 1)," +
                "('ISBN 978-5-05-001677-5', 'Fathers and Sons', 'Ivan Turgenev', '1996-01-01', 1)," +
                "('ISBN 978-5-05-002451-0', 'On the Eve', 'Ivan Turgenev', '1997-01-01', 1)," +
                "('ISBN 978-5-05-000014-9', 'Crime and Punishment', 'Fyodor Dostoyevsky', '1995-01-01', 2)," +
                "('ISBN 978-5-05-002441-1', 'Notes from the dead house', 'Fyodor Dostoyevsky', '1989-01-01', 5)," +
                "('ISBN 978-5-05-001170-1', 'Dubrovsky', 'Alexander Pushkin', '1987-01-01', 7)," +
                "('ISBN 978-5-05-003445-8', 'Tale of the Dead Princess and the Seven Knights', 'Alexander Pushkin', '1991-01-01', 3)," +
                "('ISBN 978-5-05-002493-0', 'Yellowbeak', 'Alexei Tolstoy', '1989-01-01', 1)," +
                "('ISBN 978-5-05-001176-3', 'Engineer Garin and His Death Ray', 'Alexei Tolstoy', '1987-01-01', 3)," +
                "('ISBN 978-5-05-002048-2', 'Father Sergius and other stories', 'Leo Tolstoy', '1988-01-01', 4)," +
                "('ISBN 978-5-05-001681-2', 'Quiet Flows the Don', 'Mikhail Sholokhov', '1993-01-01', 6)," +
                "('ISBN 978-5-05-000659-2', 'Amphibian', 'Alexander Belyaev', '1997-01-01', 3)," +
                "('ISBN 978-5-05-000549-6', 'Childhood', 'Makim Gorky', '1994-01-01', 1)";
        statement.executeUpdate(query);

        log.info("Table Books was created & filled with data");

        query = "CREATE TABLE IF NOT EXISTS periodicals_formats (" +
                " id INTEGER IDENTITY PRIMARY KEY," +
                " format VARCHAR(50) DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO periodicals_formats (format) VALUES " +
                "('NEWSPAPER'), ('MAGAZINE'), ('JOURNAL')";
        statement.executeUpdate(query);

        log.info("Table Periodicals_formats was created filled with data");

        query = "CREATE TABLE IF NOT EXISTS periodicals_genres (" +
                " id INTEGER IDENTITY PRIMARY KEY," +
                " genre VARCHAR(50) DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO periodicals_genres (genre) VALUES " +
                "('CELEBRITY'), ('COOKING'), ('FASHION'), ('GENERAL_NEWS'), ('HOME_GARDEN'), ('POPULAR'), ('WILDLIFE')";
        statement.executeUpdate(query);

        System.out.println("Table Periodicals_genres was created & filled with data");

        query = "CREATE TABLE IF NOT EXISTS periodicals (" +
                " id BIGINT IDENTITY PRIMARY KEY," +
                " issn VARCHAR(50) DEFAULT NULL," +
                " title VARCHAR(100) DEFAULT NULL," +
                " publication_date DATE DEFAULT NULL," +
                " format_id INTEGER DEFAULT NULL," +
                " genre_id INTEGER DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO periodicals (issn, title, publication_date, format_id, genre_id) VALUES " +
                "('ISSN 1817-9835', 'Кулинарная газета Вкуснятина №1(197)', '2019-01-01', 1, 2)," +
                "('ISSN 1817-9835', 'Кулинарная газета Вкуснятина №2(198)', '2019-02-01', 1, 2)," +
                "('ISSN 1818-247X', 'Вяжем, шьем и вышиваем №1(36)', '2019-01-01', 2, 3)," +
                "('ISSN 1818-247X', 'Вяжем, шьем и вышиваем №2(37)', '2019-02-01', 2, 3)," +
                "('ISSN 1560-5434', 'Лиза №35/2014', '2014-08-13', 2, 6)," +
                "('ISSN 1560-5434', 'Лиза №38/2018', '2018-09-15', 2, 6)," +
                "('ISSN 2222-3207', 'Домашний №12(541)', '2017-06-01', 2, 5)," +
                "('ISSN 2518-184X', 'Лукошко идей №9(89)', '2018-07-01', 2, 5)," +
                "('ISSN 1993-5390', 'Имена', '2009-03-01', 2, 1)";
        statement.executeUpdate(query);

        System.out.println("Table Periodicals was created & filled with data");

        query = "CREATE TABLE IF NOT EXISTS print_types (" +
                " id INTEGER IDENTITY PRIMARY KEY," +
                " type VARCHAR(50) DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO print_types (type) VALUES " +
                "('BOOK'), ('PERIODICAL')";
        statement.executeUpdate(query);

        System.out.println("Table Print_types was created & filled with data");

        query = "CREATE TABLE IF NOT EXISTS prints (" +
                " id BIGINT IDENTITY PRIMARY KEY," +
                " type_id INTEGER DEFAULT NULL," +
                " descr_id BIGINT DEFAULT NULL," +
                " reciept_date DATE DEFAULT NULL," +
                " is_available BOOLEAN DEFAULT NULL," +
                " is_takeaway BOOLEAN DEFAULT NULL" + ")";
        statement.executeUpdate(query);

        query = "INSERT INTO prints (type_id, descr_id, reciept_date, is_available, is_takeaway) VALUES " +
                "(1, 1, '2019-07-28', TRUE, TRUE)," +
                "(1, 2, '2019-07-28', TRUE, TRUE)," +
                "(1, 3, '2019-07-28', TRUE, TRUE)," +
                "(1, 4, '2019-07-28', TRUE, TRUE)," +
                "(1, 5, '2019-07-28', TRUE, TRUE)," +
                "(2, 1, '2019-07-28', 1, 0)," +
                "(2, 2, '2019-07-28', 1, 0)," +
                "(2, 3, '2019-07-28', 1, 0)," +
                "(2, 4, '2019-07-28', 1, 0)," +
                "(2, 5, '2019-07-28', 1, 0)";
        statement.executeUpdate(query);

        System.out.println("Table Prints was created & filled with data");

        query = "SHUTDOWN";
        statement.execute(query);
    }

    @Override
    public Print getPrintById(Long id) {
        Print result = null;

        return result;
    }

    @Override
    public Print getPrintByName(String name) {
        Print result = null;

        return result;
    }

    @Override
    public List<Print> getAllPrints() {
        List<Print> prints = null;

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(dbURL+IfExists, dbLogin, dbPass);
            //log.info("Сonnection established to " + dbURL);

            Statement statement = connection.createStatement();

            String query = "SELECT prints.id, prints.type_id, \n" +
                           "books.isbn, books.title, books.writer, books.publication_date, book_genres.genre, \n" +
                           "periodicals.issn, periodicals.title, periodicals.publication_date, periodicals_formats.format, periodicals_genres.genre \n" +
                           "FROM prints \n" +
                           "LEFT JOIN books ON prints.type_id = 1 AND prints.descr_id = books.id \n" +
                           "LEFT JOIN book_genres ON books.genre_id = book_genres.id \n" +
                           "LEFT JOIN periodicals ON prints.type_id = 2 AND prints.descr_id = periodicals.id \n" +
                           "LEFT JOIN periodicals_genres ON periodicals.genre_id = periodicals_genres.id \n" +
                           "LEFT JOIN periodicals_formats ON periodicals.format_id = periodicals_formats.id";
            ResultSet resultSet = statement.executeQuery(query);

//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(1) + " ; " +
//                                resultSet.getString(2) + " ; " +
//                                resultSet.getString(3) + " ; " +
//                                resultSet.getString(4) + " ; " +
//                                resultSet.getString(5) + " ; " +
//                                resultSet.getString(6) + " ; " +
//                                resultSet.getString(7) + " ; " +
//                                resultSet.getString(8) + " ; " +
//                                resultSet.getString(9) + " ; " +
//                                resultSet.getString(10) + " ; " +
//                                resultSet.getString(11) + " ; " +
//                                resultSet.getString(12));
//            }

            prints = new ArrayList<>();

            while (resultSet.next()) {
                Print print = null;
                switch(resultSet.getInt(2)){
                    case 1:
                        print = new Book();
                        print.setId(resultSet.getLong(1));
                        ((Book) print).setIsbn(resultSet.getString(3));
                        print.setTitle(resultSet.getString(4));
                        ((Book) print).setWriter(resultSet.getString(5));
                        print.setDate(resultSet.getDate(6));
                        ((Book) print).setGenre(BookGenre.valueOf(resultSet.getString(7)));
                        break;
                    case 2:
                        print = new Periodical();
                        print.setId(resultSet.getLong(1));
                        ((Periodical) print).setIssn(resultSet.getString(8));
                        print.setTitle(resultSet.getString(9));
                        print.setDate(resultSet.getDate(10));
                        ((Periodical) print).setFormat(PeriodicalFormat.valueOf(resultSet.getString(11)));
                        ((Periodical) print).setGenre(PeriodicalGenre.valueOf(resultSet.getString(12)));
                        break;
                }

                if(print != null) prints.add(print);
            }

            query = "SHUTDOWN";
            statement.execute(query);

            connection.close();

        } catch (SQLException ex) {
              log.log(Level.WARNING, "Connection not established:", ex);
          }

        return prints;
    }

    @Override
    public Long addPrint(Print print) {
        Long result = null;

        return result;
    }

    @Override
    public Print updatePrint(Print print) {
        Print pr = null;

//        if(prints.contains(print)){
//            pr = prints.get(prints.indexOf(print));
//            if (pr.getId().equals(print.getId())) {
//                switch (pr.getType()) {
//                    case BOOK:
//                        pr.setTitle(print.getTitle());
//                        pr.setDate(print.getDate());
//                        pr.setAvailable(print.getAvailable());
//                        pr.setTakeaway(print.getTakeaway());
//                        ((Book) pr).setIsbn(((Book) print).getIsbn());
//                        ((Book) pr).setGenre(((Book) print).getGenre());
//                        ((Book) pr).setWriter(((Book) print).getWriter());
//                        break;
//                    case PERIODICAL:
//                        pr.setTitle(print.getTitle());
//                        pr.setDate(print.getDate());
//                        pr.setAvailable(print.getAvailable());
//                        pr.setTakeaway(print.getTakeaway());
//                        ((Periodical) pr).setIssn(((Periodical) print).getIssn());
//                        ((Periodical) pr).setGenre(((Periodical) print).getGenre());
//                        ((Periodical) pr).setFormat(((Periodical) print).getFormat());
//                        break;
//                }
//            }
//        }
        return pr;
    }

    @Override
    public boolean deletePrint(Print print) {
        boolean result = false;


        return result;
    }

}
