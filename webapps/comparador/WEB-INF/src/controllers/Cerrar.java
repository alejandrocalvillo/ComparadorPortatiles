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




@WebServlet("/cerrar")
public class Cerrar extends HttpServlet {
   
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession(false);
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    
    if (usuario!= null && session!=null) {
        // Invalidar la sesión del usuario
        session.invalidate();
    }
    response.sendRedirect(request.getContextPath()+"/index");
            
    }
}


