package dao;

import entities.Print;

import java.util.Collection;

public interface PrintDAO {

    Print getPrintById (Long id);

    Print getPrintByName (String name);

    Collection<Print> getAllPrints ();

    Long addPrint (Print print);        // return prints id
    Print updatePrint (Print print);    // return updated print
    boolean deletePrint (Print print);  // return result of remove() method
}
