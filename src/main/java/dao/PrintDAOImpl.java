package dao;

import entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;


public class PrintDAOImpl implements PrintDAO {

    private List<Print> prints = new ArrayList<Print>();

    public PrintDAOImpl() {
        Random rnd = new Random();
        for(int i=0; i<5; i++) {
            Book print = new Book();
            print.setTitle("Книга " + (i+1));
            print.setGenre(BookGenre.CLASSIC);
            print.setIsbn("ISBN " + i);
            print.setId(rnd.nextLong()+i);
            print.setWriter("Аффтар "+ i);
            prints.add(print);
        }

        for(int i=0; i<5; i++) {
            Periodical print = new Periodical();
            print.setTitle("Журнал " + (i+1));
            print.setFormat(PeriodicalFormat.JOURNAL);
            print.setGenre(PeriodicalGenre.FASHION);
            print.setIssn("ISSN " + i);
            print.setId(rnd.nextLong()+i);
            prints.add(print);
        }

        for(int i=0; i<5; i++) {
            Periodical print = new Periodical();
            print.setTitle("Газета " + (i+1));
            print.setFormat(PeriodicalFormat.NEWSPAPER);
            print.setGenre(PeriodicalGenre.GENERAL_NEWS);
            print.setIssn("ISSN " + i);
            print.setId(rnd.nextLong()+i);
            prints.add(print);
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
