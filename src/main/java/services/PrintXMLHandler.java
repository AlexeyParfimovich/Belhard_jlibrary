package services;

import entities.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

public class PrintXMLHandler extends DefaultHandler {

    private Collection prints;
    private Print print;

    private boolean isID = false;
    private boolean isDate = false;
    private boolean isISBN = false;
    private boolean isISSN = false;
    private boolean isTitle = false;
    private boolean isWriter = false;
    private boolean isFormat = false;
    private boolean isBookgenre = false;
    private boolean isPeriodicalgenre = false;

    PrintXMLHandler(Collection prints) throws SAXException{
        if(prints == null) {
            throw new SAXException("Collection is not defined");
        }
        this.prints = prints;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        switch (qName) {
            case "book":
                print = new Book();
                for (int i=0; i<attributes.getLength(); i++) {
                    if(attributes.getQName(i).equals("id")){
                        print.setId(Long.parseLong(attributes.getValue(i)));
                    }
                }
                break;
            case "periodical": print = new Periodical(); break;
            case "date": isDate = true; break;
            case "isbn": isISBN = true; break;
            case "issn": isISSN = true; break;
            case "title": isTitle = true; break;
            case "writer": isWriter = true; break;
            case "format": isFormat = true; break;
            case "bookgenre": isBookgenre = true; break;
            case "periodicalgenre": isPeriodicalgenre = true; break;
        }
    }


    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (print != null) {

            String str = new String(ch, start, length);

            if(isTitle){

                print.setTitle(new String(ch, start, length));

            } else if(isDate) {

                try {
                    print.setDate(new SimpleDateFormat("YYYY-MM-DD").parse(new String(ch, start, length)));
                } catch(ParseException ex){}

            } else if(isISBN){

                ((Book)print).setIsbn(new String(ch, start, length));

            } else if(isWriter){

                ((Book)print).setWriter(new String(ch, start, length));

            } else if(isBookgenre){

                ((Book)print).setGenre(BookGenre.valueOf(new String(ch, start, length)));

            } else if(isISSN){

                ((Periodical)print).setIssn(new String(ch, start, length));

            } else if(isFormat){

                ((Periodical)print).setFormat(PeriodicalFormat.valueOf(new String(ch, start, length)));

            } else if(isPeriodicalgenre){

                ((Periodical)print).setGenre(PeriodicalGenre.valueOf(new String(ch, start, length)));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        switch (qName) {
            case "book":
            case "periodical":
                if (print != null && !prints.contains(print)) { prints.add(print); }
                print = null;
                break;
            case "date": isDate = false; break;
            case "isbn": isISBN = false; break;
            case "issn": isISSN = false; break;
            case "title": isTitle = false; break;
            case "writer": isWriter = false; break;
            case "format": isFormat = false; break;
            case "bookgenre": isBookgenre = false; break;
            case "periodicalgenre": isPeriodicalgenre = false; break;
        }

    }

}
