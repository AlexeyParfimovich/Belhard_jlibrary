import java.sql.*;

public class Runner {
    public static void main(String[] args) {

        Connection connection = null;

        // Загружаем драйвер
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            System.out.println("HSQLDB driver loaded successfully");
        } catch (ClassNotFoundException ex) {
            System.out.println("HSQLDB driver not found:");
            ex.printStackTrace();
            System.exit(1);
        }

        // Установливаем соединение
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:./db/libdb", "SA", "");
            System.out.println("Сonnection established : " + connection);

            //connection.close();
        } catch (SQLException ex) {
            System.err.println("Connection failed:");
            ex.printStackTrace();
            System.exit(1);
        }

        // Работа с тестовыми данными
        try {
            Statement statement = connection.createStatement();

            // Создание тестовой таблицы
            String query = "CREATE TABLE Prints (print_id IDENTITY, " +
                                                "book_id INTEGER," +
                                                "periodical_id INTEGER," +
                                                "print_type VARCHAR(50)," +
                                                "print_title VARCHAR(250)," +
                                                "publication_date DATE)";
            try {
                statement.executeUpdate(query);
                System.out.println("Table PRINTS was created");
            } catch (SQLException ex) {
                // игнорируем исключение если таблица уже существует.
              ex.printStackTrace();
            }

            // Добавление записей в таблицу.
            query = "INSERT INTO Prints (print_type, print_title, publication_date)" +
                               " VALUES('BOOK', 'Book 1', '2008-08-22')";
            statement.executeUpdate(query);
            query = "INSERT INTO Prints (print_type, print_title, publication_date)" +
                    " VALUES('BOOK', 'Book 2', '2008-08-23')";
            statement.executeUpdate(query);
            query = "INSERT INTO Prints (print_type, print_title, publication_date)" +
                    " VALUES('BOOK', 'Book 3', '2008-08-24')";
            statement.executeUpdate(query);

            System.out.println("Table PRINTS was filled with data");

            // Доступ к данным
            query = "SELECT * FROM Prints";
            ResultSet resultSet = statement.executeQuery(query);

            // Вывод данных
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt(1) + " " +
                                   "Book: " + resultSet.getInt(2) + " " +
                                   "Periodical: " + resultSet.getInt(3) + " " +
                                   "Type: " + resultSet.getString(4) + " " +
                                   "Title: " + resultSet.getString(5) + " " +
                                   "Date: " + resultSet.getDate(6));
            }

            //Отключение от БД
            query = "SHUTDOWN";
            statement.execute(query);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        PrintService ps = new PrintServiceImpl();
//
//        UserService us = new UserServiceImpl();
//
//        Collection<Print> prints = ps.findAllPrints();
//
//        ps.importToPrintsFromXML("Prints.xsd.xml");
//
//        if(prints != null){
//            for(Print print: prints) {
//                System.out.println(print);
//            }
//        }
    }

}
