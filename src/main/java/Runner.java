import dao.PrintDAO;
import dao.PrintDAOImpl;
import entities.Print;
import services.PrintService;
import services.PrintServiceImpl;

import java.sql.*;
import java.util.Collection;

public class Runner {
    public static void main(String[] args) {

//        PrintDAO ps = new PrintDAOImpl();

        PrintService ps = new PrintServiceImpl();

        //UserService us = new UserServiceImpl();

        Collection<Print> prints = ps.findAllPrints();

        //ps.importToPrintsFromXML("Prints.xsd.xml");

        if(prints != null){
            for(Print print: prints) {
                System.out.println(print);
            }
        }
    }

}
