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
    public void  doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);

        String usuario_str=request.getParameter("usuario");
		String contrasena_str=request.getParameter("contrasena");
		System.out.println(usuario_str+" "+contrasena_str);
		

        try (DBManager db = new DBManager()) {

            if(usuario_str!=null && contrasena_str!=null  )
                {
                usuario =db.getUsuarioDB(usuario_str, contrasena_str);
                System.out.println("antes de comprobar: "+usuario.getNombre()+" "+usuario.getContrasena());
                if(usuario.getNombre()!=null && usuario.getContrasena()!= null )
                    { 
                    if(usuario.getNombre().equals("admin") && usuario.getContrasena().equals("*00A51F3F48415C7D4E8908980D443C29C69B60C9"))
                        {
                        // Guardar el objeto usuario en la sesión
                        session = request.getSession();
                        session.setAttribute("usuario", usuario);
                        response.sendRedirect(request.getContextPath()+"/admin");
                         
                        }
                    else
                        {
                        // Guardar el objeto usuario en la sesión
                        session = request.getSession();
                        session.setAttribute("usuario", usuario);
                        response.sendRedirect(request.getContextPath()+"/index");
                        }    
                          
                    } 
                else
                    {
                    request.setAttribute("error", "Usuario o contraseña incorrectos.");
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inicio_sesion.jsp");
                    rd.forward(request, response);                 
                    //añadir parametro error y ponerlo enn jsp
                    }             
                }  
            else
                {
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inicio_sesion.jsp");
                rd.forward(request, response);
                } 

	
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    } 
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
   
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/inicio_sesion.jsp");
            rd.forward(request, response);
            
    }
}


