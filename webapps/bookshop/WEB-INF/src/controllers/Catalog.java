package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import bookshop.Book;
import bookshop.DBManager;


@WebServlet("/catalog")
public class Catalog extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Obtiene el carro de la compra desde la sesión. Lo crea si no existe.
        HttpSession session = request.getSession();
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<Book>();
            session.setAttribute("cart", cart);
        }

        try (DBManager db = new DBManager()) {
            // Obtiene el catálogo de libros desde la base de datos
            List<Book> books = db.listBooks();
            System.out.println("Catálogo: se han leído " + books.size() + " libros.");
            // Reenvía la petición a una plantilla JSP, pasando el catálogo como atributo
            request.setAttribute("books", books);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp");
            rd.forward(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}

