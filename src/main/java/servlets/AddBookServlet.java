package servlets;

import entities.Book;
import entities.BookGenre;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddBookServlet extends HttpServlet {

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
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Book book = new Book();

        try {
            book.setTitle(req.getParameter("title"));
            book.setWriter(req.getParameter("writer"));
            book.setIsbn(req.getParameter("isbn"));
            book.setDate(dateFormat.parse(req.getParameter("date")));
            book.setGenre(BookGenre.valueOf(req.getParameter("genre")));
        } catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            service.addPrint(book);
        } catch(SQLException ex){
            ex.printStackTrace();
            book = null;
        }

        req.setAttribute("Book", book);
        doGet(req, resp);
    }
}
