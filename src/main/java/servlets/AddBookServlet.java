package servlets;

import entities.Book;
import entities.BookGenre;
import entities.Print;
import entities.PrintType;
import model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addBook.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        BookGenre genre = BookGenre.valueOf(req.getParameter("genre"));

        Book book = new Book();
        book.setTitle(title);

        Model model = Model.getInstance();
        model.add(book);

        req.setAttribute("Book", book);
        doGet(req, resp);
    }
}
