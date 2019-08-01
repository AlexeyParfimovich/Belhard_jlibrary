package services;

import entities.Print;

import java.sql.SQLException;
import java.util.List;

public interface PrintService {

    Print findPrintById (Long id) throws SQLException;

    Print findPrintByName (String name) throws SQLException;

    List<Print> findAllPrints () throws SQLException;

    boolean importToPrintsFromXML (String fileName);
    boolean exportFromPrintsToXML (String fileName);

    void addPrint (Print print) throws SQLException;        // return ArrayList index of element
    void updatePrint (Print print) throws SQLException;     // return ArrayList index of element
    void deletePrint (Print print) throws SQLException;     // return number of elements in ArrayList
}
