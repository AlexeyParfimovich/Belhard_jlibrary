package services;

import dao.PrintDAO;
import dao.PrintDAOImpl;
import entities.Print;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class PrintServiceImpl implements PrintService {

    private PrintDAO printDAO;

    public PrintServiceImpl() {
        this.printDAO = new PrintDAOImpl();
    }

    public Print findPrintById(Long id) {
        return printDAO.getPrintById(id);
    }

    public Print findPrintByName(String name) {
        return printDAO.getPrintByName(name);
    }

    public Collection<Print> findAllPrints() {
        return printDAO.getAllPrints();
    }

    static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));

        } catch (IOException | SAXException ex) {
            return false;
        }
        return true;
    }

    public Collection<Print> readPrintsFromXML(Collection<Print> prints, String xmlPath) {

        if(validateXMLSchema("Prints.xsd", xmlPath)) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                PrintXMLHandler handler = new PrintXMLHandler(prints);
                saxParser.parse(xmlPath, handler);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return prints;
        }
        return null;
    }

    public boolean exportFromPrintsToXML(String xmlPath) {
        return false;
    }

    public boolean importToPrintsFromXML(String xmlPath) {
        boolean result = false;
        if(validateXMLSchema("Prints.xsd", xmlPath)) {
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                PrintXMLHandler handler = new PrintXMLHandler(printDAO.getAllPrints());
                saxParser.parse(xmlPath, handler);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    public void addPrint(Print print) {
        printDAO.addPrint(print);
    }

    public void updatePrint(Print print) {
        printDAO.updatePrint(print);
    }

    public void deletePrint(Print print) {
        printDAO.deletePrint(print);
    }
}
