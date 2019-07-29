package services;

import entities.Print;
import java.util.List;

public interface PrintService {

    Print findPrintById (Long id);

    Print findPrintByName (String name);

    List<Print> findAllPrints ();

    boolean importToPrintsFromXML (String fileName);
    boolean exportFromPrintsToXML (String fileName);

    void addPrint (Print print);        // return ArrayList index of element
    void updatePrint (Print print);     // return ArrayList index of element
    void deletePrint (Print print);     // return number of elements in ArrayList
}
