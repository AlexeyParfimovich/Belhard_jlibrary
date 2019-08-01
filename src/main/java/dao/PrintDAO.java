package dao;

import entities.Print;
import java.util.List;
import java.sql.SQLException;

public interface PrintDAO {

    Print getPrintById (Long id) throws SQLException;

    Print getPrintByName (String name) throws SQLException;

    List<Print> getAllPrints () throws SQLException;

    void addPrint (Print print) throws SQLException;        // return prints id
    Print updatePrint (Print print) throws SQLException;    // return updated print
    boolean deletePrint (Print print) throws SQLException;  // return result of remove() method
}
