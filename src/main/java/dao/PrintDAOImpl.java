package dao;

import entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class PrintDAOImpl implements PrintDAO {

    private List<Print> prints = new ArrayList<Print>();

    public PrintDAOImpl() {

            String query = null;
            Connection connection = null;

            // Загружаем драйвер
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                System.out.println("HSQLDB driver loaded successfully");

                // Установливаем соединение
                connection = DriverManager.getConnection("jdbc:hsqldb:file:./db/libdb", "SA", "");
                System.out.println("Сonnection established : " + connection);

                Statement statement = connection.createStatement();

                query = "CREATE TABLE IF NOT EXISTS book_genres (" +
                        " id INTEGER IDENTITY PRIMARY KEY," +
                        " genre VARCHAR(50) DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Book_genres was created");

                query = "INSERT INTO book_genres (genre) VALUES " +
                        "('CLASSIC'), ('CRIME'), ('FANTASY'), ('HISTORICAL'), ('HORROR'), ('THRILLER'), ('WESTERN')";
                statement.executeUpdate(query);
                System.out.println("Table Books_genres was filled with data");

                query = "CREATE TABLE IF NOT EXISTS books (" +
                        " id BIGINT IDENTITY PRIMARY KEY," +
                        " isbn VARCHAR(50) DEFAULT NULL," +
                        " title VARCHAR(100) DEFAULT NULL," +
                        " writer VARCHAR(50) DEFAULT NULL," +
                        " publication_date DATE DEFAULT NULL," +
                        " genre_id INTEGER DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Books was created");

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
                System.out.println("Table Books was filled with data");

                query = "CREATE TABLE IF NOT EXISTS periodicals_formats (" +
                        " id INTEGER IDENTITY PRIMARY KEY," +
                        " format VARCHAR(50) DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Periodicals_formats was created");

                query = "INSERT INTO periodicals_formats (format) VALUES " +
                        "('NEWSPAPER'), ('MAGAZINE'), ('JOURNAL')";
                statement.executeUpdate(query);
                System.out.println("Table Periodicals_formats was filled with data");

                query = "CREATE TABLE IF NOT EXISTS periodicals_genres (" +
                        " id INTEGER IDENTITY PRIMARY KEY," +
                        " genre VARCHAR(50) DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Periodicals_genres was created");

                query = "INSERT INTO periodicals_genres (genre) VALUES " +
                        "('CELEBRITY'), ('COOKING'), ('FASHION'), ('GENERAL_NEWS'), ('HOME_GARDEN'), ('POPULAR'), ('WILDLIFE')";
                statement.executeUpdate(query);
                System.out.println("Table Periodicals_genres was filled with data");

                query = "CREATE TABLE IF NOT EXISTS periodicals (" +
                        " id BIGINT IDENTITY PRIMARY KEY," +
                        " issn VARCHAR(50) DEFAULT NULL," +
                        " title VARCHAR(100) DEFAULT NULL," +
                        " publication_date DATE DEFAULT NULL," +
                        " format_id INTEGER DEFAULT NULL," +
                        " genre_id INTEGER DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Periodicals was created");

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
                System.out.println("Table Periodicals was filled with data");

                query = "CREATE TABLE IF NOT EXISTS print_types (" +
                        " id INTEGER IDENTITY PRIMARY KEY," +
                        " type VARCHAR(50) DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Print_types was created");

                query = "INSERT INTO print_types (type) VALUES " +
                        "('BOOK'), ('PERIODICAL')";
                statement.executeUpdate(query);
                System.out.println("Table Print_types was filled with data");

                query = "CREATE TABLE IF NOT EXISTS prints (" +
                        " id BIGINT IDENTITY PRIMARY KEY," +
                        " type_id INTEGER DEFAULT NULL," +
                        " descr_id BIGINT DEFAULT NULL," +
                        " reciept_date DATE DEFAULT NULL," +
                        " is_available BOOLEAN DEFAULT NULL," +
                        " is_takeaway BOOLEAN DEFAULT NULL" + ")";
                statement.executeUpdate(query);
                System.out.println("Table Prints was created");

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
                System.out.println("Table Prints was filled with data");

                query = "SHUTDOWN";
                statement.execute(query);

                connection.close();

            } catch (ClassNotFoundException ex) {
                System.out.println("HSQLDB driver not found:");
                ex.printStackTrace();
                System.exit(1);
            } catch (SQLException ex) {
                System.err.println("Connection failed:");
                ex.printStackTrace();
                System.exit(1);
            }
    }


    public Print getPrintById(Long id) {
        Print result = null;
        for(Print pr: prints){
            if(pr.getId().equals(id)){
                result = pr;
            }
        }
        return result;
    }

    public Print getPrintByName(String name) {
        Print result = null;
        for(Print pr: prints){
            if(pr.getTitle().equals(name)){
                result = pr;
            }
        }
        return result;
    }

    public Collection<Print> getAllPrints() {
        return prints;
    }

    public Long addPrint(Print print) {
        Long result = null;
        if(!prints.contains(print) && print != null){
            prints.add(print);
            result = print.getId(); // позиция книги в списке
        }
        return result;
    }

    public Print updatePrint(Print print) {
        Print pr = null;

        if(prints.contains(print)){
            pr = prints.get(prints.indexOf(print));
            if (pr.getId().equals(print.getId())) {
                switch (pr.getType()) {
                    case BOOK:
                        pr.setTitle(print.getTitle());
                        pr.setDate(print.getDate());
                        pr.setAvailable(print.getAvailable());
                        pr.setTakeaway(print.getTakeaway());
                        ((Book) pr).setIsbn(((Book) print).getIsbn());
                        ((Book) pr).setGenre(((Book) print).getGenre());
                        ((Book) pr).setWriter(((Book) print).getWriter());
                        break;
                    case PERIODICAL:
                        pr.setTitle(print.getTitle());
                        pr.setDate(print.getDate());
                        pr.setAvailable(print.getAvailable());
                        pr.setTakeaway(print.getTakeaway());
                        ((Periodical) pr).setIssn(((Periodical) print).getIssn());
                        ((Periodical) pr).setGenre(((Periodical) print).getGenre());
                        ((Periodical) pr).setFormat(((Periodical) print).getFormat());
                        break;
                }
            }
        }
        return pr;
    }

    public boolean deletePrint(Print print) {
        boolean result = false;

        if(prints.contains(print)){
            result = prints.remove(print);
        }
        return result;
    }

}
