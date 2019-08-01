package servlets;

import entities.Print;
import models.DBModel;
import services.PrintService;
import services.PrintServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class BookListServlet extends HttpServlet {

    DBModel db;
    PrintService service;

    @Override
    public void init() throws ServletException {
        db = DBModel.getInstance();
        service = new PrintServiceImpl();
        super.init();
    }

    @Override
    public void destroy() {
        db.closeConnections();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<String> names = null;

        try {
            names = service.findAllPrints().stream().map(Print::toString).collect(Collectors.toList());
        } catch(SQLException ex){
            System.out.println("Query error");
        }

        req.setAttribute("Books", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("bookList.jsp");
        requestDispatcher.forward(req, resp);
    }

}
