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

import bookshop.Book;
import bookshop.DBManager;

import javax.servlet.RequestDispatcher;

@WebServlet("/addToCart")
public class AddToCartController extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession();
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<Book>();
            session.setAttribute("cart", cart);
        }

        

        try (DBManager db = new DBManager()) {
            String[] selectedBookIds = request.getParameterValues("bookIds");        
            if (selectedBookIds !=null){
                for (String bookId : selectedBookIds) {
                    Book book = db.getBookbyId(Integer.parseInt(bookId));
                    if (book != null){
                        cart.add(book);
                    }
                }
            }
            response.sendRedirect("catalog");
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
    }
}
}
