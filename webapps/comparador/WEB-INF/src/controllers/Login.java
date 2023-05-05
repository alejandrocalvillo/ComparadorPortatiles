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



@WebServlet("/login")
public class Login extends HttpServlet {
    Usuario usuario;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Obtiene un unico ordenador.
        HttpSession session = request.getSession(false);

        /* 
      	if (session != null && session.getAttribute("usuario") != null) {
            // El usuario ya inició sesión, redireccionar a la página index.jsp
            response.sendRedirect("/comparador/index");
            return;
        }
*/
        try (DBManager db = new DBManager()) {
            //Empezamos contando marcas en el index
       // String nextpage="/login";
		String usuario_str=request.getParameter("usuario");

		String contrasena_str=request.getParameter("contrasena");

		System.out.println(usuario_str+" "+contrasena_str);

        

         if(usuario_str!=null && contrasena_str!=null  )
            {
            usuario =db.getUsuarioDB(usuario_str, contrasena_str);
            System.out.println(usuario.getNombre()+" "+usuario.getContrasena());
            if(usuario.getNombre().equals(usuario_str))
                {
                //asociar la sesion al usuario creado
                // Guardar el objeto usuario en la sesión
                session = request.getSession();
                session.setAttribute("usuario", usuario);
                //ssesion y te manda al index
                RequestDispatcher rd = request.getRequestDispatcher("/index");
                rd.forward(request, response);
                } 
            
            
            }  else{

       RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inicio_sesion.jsp");
       rd.forward(request, response);
            } 

	
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
