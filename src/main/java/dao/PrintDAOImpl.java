package dao;

import entities.*;
import models.DBModel;
import models.SqlQueries;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class PrintDAOImpl implements PrintDAO {

    DBModel db;

    private static Logger log = Logger.getLogger(PrintDAOImpl.class.getName());


    public PrintDAOImpl() {
        db = DBModel.getInstance();
    }

    @Override
    public Print getPrintById(Long id) throws SQLException {

        Connection connection = db.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQueries.SQL_BOOK_SELECT_BY_ID);
        statement.setLong(1, id);

        ResultSet resultSet = statement.executeQuery();
        Print print = fillPrints(resultSet).get(0);

        db.returnConnection(connection);
        return print;
    }

    @Override
    public Print getPrintByName(String name) throws SQLException {
        Print result = null;

        return result;
    }

    @Override
    public List<Print> getAllPrints() throws SQLException {
        List<Print> prints = null;

        Connection connection = db.getConnection();

        PreparedStatement statement = connection.prepareStatement(SqlQueries.SQL_PRINTS_SELECT_ALL);
        ResultSet resultSet = statement.executeQuery();
        prints = fillPrints(resultSet);

        db.returnConnection(connection);
        return prints;
    }

    @Override
    public void addPrint(Print print) throws SQLException {
        Connection connection = db.getConnection();
        connection.setAutoCommit(false);

        ResultSet resaltSet = null;
        PreparedStatement statement = null;

        switch (print.getType()) {
            case BOOK:
//              "INSERT INTO books (isbn, title, writer, publication_date, genre_id) " +
//              "VALUES (?, ?, ?, ?, (SELECT id FROM book_genres WHERE genre = ?));";

                statement = connection.prepareStatement(SqlQueries.SQL_BOOK_ADD, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, ((Book)print).getIsbn());
                statement.setString(2, ((Book)print).getTitle());
                statement.setString(3, ((Book)print).getWriter());
                statement.setDate(4, new Date(((Book)print).getDate().getTime()));
                statement.setString(5, ((Book)print).getGenre().toString());

                statement.executeUpdate();

                Long recId = -1l;
                resaltSet = statement.getGeneratedKeys();
                if(resaltSet.next()) {
                    recId = resaltSet.getLong(1);
                }

//              "INSERT INTO prints (type_id, descr_id, reciept_date, is_available, is_takeaway) " +
//              "VALUES ((SELECT id FROM print_types WHERE type = ?), ?, ?, ?)";

                statement = connection.prepareStatement(SqlQueries.SQL_BOOK_ADD_TO_PRINT);

                statement.setString(1,print.getType().toString());
                statement.setLong(2, recId);
                statement.setDate(3, new Date( new java.util.Date().getTime()));
                statement.setBoolean(4, true);
                statement.setBoolean(5, false);
                statement.executeUpdate();

                connection.commit();

                break;
            case PERIODICAL:

                break;
        }

        db.returnConnection(connection);
    }

    @Override
    public Print updatePrint(Print print) throws SQLException {
        Print pr = null;
        return pr;
    }

    @Override
    public boolean deletePrint(Print print) throws SQLException {
        boolean result = false;
        return result;
    }

    private List<Print> fillPrints(ResultSet resultSet) throws SQLException {
        List<Print> prints = new ArrayList<>();

        String s;
        Long l;
        Date d;
        BookGenre b;

        while (resultSet.next()) {
            Print print = null;
            switch(resultSet.getInt(2)){
                case 0:
                    print = new Book();
                    l = resultSet.getLong(1);
                    print.setId(resultSet.getLong(1));
                    s = resultSet.getString(3);
                    ((Book) print).setIsbn(resultSet.getString(3));
                    s = resultSet.getString(4);
                    print.setTitle(resultSet.getString(4));
                    s = resultSet.getString(5);
                    ((Book) print).setWriter(resultSet.getString(5));
                    d = resultSet.getDate(6);
                    print.setDate(resultSet.getDate(6));
                    b = BookGenre.valueOf(resultSet.getString(7));
                    ((Book) print).setGenre(BookGenre.valueOf(resultSet.getString(7)));
                    break;
                case 1:
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
        return prints;
    }

}
