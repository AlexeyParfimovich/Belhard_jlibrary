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
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class PrintServiceImpl implements PrintService {
    private static Logger log = Logger.getLogger(PrintServiceImpl.class.getName());

    private PrintDAO printDAO;

    public PrintServiceImpl() {
        this.printDAO = new PrintDAOImpl();
    }

    @Override
    public Print findPrintById(Long id) throws SQLException {
        return printDAO.getPrintById(id);
    }

    @Override
    public Print findPrintByName(String name) throws SQLException {
        return printDAO.getPrintByName(name);
    }

    @Override
    public List<Print> findAllPrints() throws SQLException {
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

    public List<Print> readPrintsFromXML(List<Print> prints, String xmlPath) {

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

    @Override
    public boolean exportFromPrintsToXML(String xmlPath) {
        return false;
    }

    @Override
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

    @Override
    public void addPrint(Print print) throws SQLException {
        printDAO.addPrint(print);
    }

    @Override
    public void updatePrint(Print print) throws SQLException {
        printDAO.updatePrint(print);
    }

    @Override
    public void deletePrint(Print print) throws SQLException {
        printDAO.deletePrint(print);
    }
}
