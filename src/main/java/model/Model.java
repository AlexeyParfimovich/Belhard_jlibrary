package model;

import entities.Print;
import services.PrintService;
import services.PrintServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Model {

    private static Model instance = new Model();

    private List<Print> prints;

    private PrintService service;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        service = new PrintServiceImpl();
        prints = new ArrayList<>();
    }

    public void add(Print print) {

        //prints.add(print);
        //service.addPrint(print);
    }

    public List<String> list() {

        prints = service.findAllPrints();

        return prints.stream()
                .map(Print::toString)
                .collect(Collectors.toList());
    }
}
