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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Obtiene un unico ordenador.
        HttpSession session = request.getSession(false);
        Usuario usuario= new Usuario();

/* 
      	if (session != null && session.getAttribute("usuario") != null) {
            // El usuario ya inició sesión, redireccionar a la página index.jsp
            response.sendRedirect("/comparador/index");
            return;
        }
*/
        try (DBManager db = new DBManager()) {
            //Empezamos contando marcas en el index
  
		String usuario_str=request.getParameter("usuario");

		String contrasena_str=request.getParameter("contrasena");

        String email_str=request.getParameter("email");

		System.out.println(usuario_str+" "+contrasena_str+" "+email_str);

         if(usuario_str!=null && contrasena_str!=null && email_str!=null  )
            {
            usuario=db.insertUsuarioDB(usuario_str, contrasena_str, email_str);

            //comprobar que se ha creado bien el usuario y mandar al index
            System.out.println("usuario : "+usuario + "su nombre es "+usuario_str);
            //asociar la sesion al usuario creado
            // Guardar el objeto usuario en la sesión
            session = request.getSession();
            session.setAttribute("usuario", usuario);
            //ssesion y te manda al index
            response.sendRedirect(request.getContextPath()+"/index");
            
            }  
        else{

            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/registro.jsp");
            rd.forward(request, response);
            } 


	
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/registro.jsp");
       rd.forward(request, response);
    } 
}
