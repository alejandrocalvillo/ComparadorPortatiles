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



@WebServlet("/registro")
public class Registro extends HttpServlet {
    Usuario usuario;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Obtiene un unico ordenador.
        HttpSession session = request.getSession(false);


      	if (session != null && session.getAttribute("usuario") != null) {
            // El usuario ya inició sesión, redireccionar a la página index.jsp
            response.sendRedirect("WEB-INF/jsp/index.jsp");
            return;
        }

        try (DBManager db = new DBManager()) {
            //Empezamos contando marcas en el index
       // String nextpage="/login";
		String usuario_str=request.getParameter("usuario");

		String contrasena_str=request.getParameter("contrasena");
        String email_str=request.getParameter("email");

		System.out.println(usuario_str+" "+contrasena_str+""+email_str);

        db.insertUsuarioDB(usuario_str, contrasena_str, email_str);

       /* if(usuario==null)
            {
            // Guardar el objeto usuario en la sesión
            session = request.getSession();
            session.setAttribute("usuario", usuario);
            //ssesion
            }  */

		if(session==null) {
            //nextpage="jsp/index.jsp";
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/index");
       rd.forward(request, response);
            

		} 
  
       RequestDispatcher rd = request.getRequestDispatcher("html/registro.html");
       rd.forward(request, response);
       

	
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
