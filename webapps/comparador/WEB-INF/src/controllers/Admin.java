package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import comparador.*;



@WebServlet("/admin")
public class Admin extends HttpServlet {
 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
       if (usuario != null && usuario.getNombre().equals("admin") && usuario.getContrasena().equals("*00A51F3F48415C7D4E8908980D443C29C69B60C9")) {
            RequestDispatcher rd =request.getRequestDispatcher("WEB-INF/jsp/index_admin.jsp");
            rd.forward(request, response);
        } else {
             response.sendRedirect(request.getContextPath() + "/index");
        }      

        try (DBManager db = new DBManager()) {
            //Empezamos contando marcas en el index
  
	
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    } 
}
