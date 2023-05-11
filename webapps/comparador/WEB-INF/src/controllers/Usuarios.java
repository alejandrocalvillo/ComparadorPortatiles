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

import org.json.JSONArray;
import org.json.JSONObject;

import comparador.*;

@WebServlet("/usuarios")
public class Usuarios extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        try (DBManager db = new DBManager()) {

            if(usuario!=null && db.isAdmin(String.valueOf(usuario.getId()))) {
                    RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index_admin.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/index");
                }
            }catch (SQLException | NamingException ex) {
                ex.printStackTrace();
                String errorMessage = "Error: " + ex.getMessage();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(errorMessage);
            }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("Entro en el Post");
        
        String accion = request.getParameter("accion");

        if (accion.equals("buscar")) {


            try (DBManager dbManager = new DBManager()) {
                System.out.println("Holita estoy aqui");
                List<Usuario> usuarios = dbManager.getUsuariosDB();
    
                JSONArray usuariosJsonArray = new JSONArray();
    
                System.out.println("Cree el JSONArray");
                for (Usuario usuario : usuarios) {
                    JSONObject usuarioJson = new JSONObject();
                    usuarioJson.put("id", usuario.getId());
                    usuarioJson.put("nombre", usuario.getNombre());
                    usuarioJson.put("correo", usuario.getCorreo());
                    usuariosJsonArray.put(usuarioJson);
                }
    
                String usuariosJson = usuariosJsonArray.toString();
    
                // Set response content type and charset
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
    
                // Write JSON string to response
                response.getWriter().write(usuariosJson);
            } catch (SQLException | NamingException ex) {
                ex.printStackTrace();
                String errorMessage = "Error: " + ex.getMessage();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(errorMessage);
            }


        } else if (accion.equals("eliminar")) {
            try (DBManager dbManager = new DBManager()) {
                String id = request.getParameter("id");
                dbManager.deleteUsuarioDB(id);
    
                // Set response content type and charset
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
    
        
            } catch (SQLException | NamingException ex) {
                ex.printStackTrace();
                String errorMessage = "Error: " + ex.getMessage();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(errorMessage);
            }
        }else if (accion.equals("anadir")) {

            try (DBManager db = new DBManager()) {
                //Empezamos contando marcas en el index
      
            String usuario_str=request.getParameter("nombre");
            String contrasena_str=request.getParameter("contrasena");
            String email_str=request.getParameter("correo");
    
            System.out.println(usuario_str+" "+contrasena_str+" "+email_str);
    
             if(usuario_str!=null && contrasena_str!=null && email_str!=null  )
                {
                Usuario usuario=db.insertUsuarioDB(usuario_str, contrasena_str, email_str);
    
                System.out.println("usuario : "+usuario + "su nombre es "+usuario_str);
                }  
          
    
        
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
                response.sendError(500);
            }

        }  else if (accion.equals("actualizarNombre")) {

            try (DBManager db = new DBManager()) {
                String id=request.getParameter("id");
                String usuario_str=request.getParameter("nombre");


                if(id !=null && usuario_str !=null  )
                    {
                    db.changeNameUserDB(id, usuario_str);
                    }  
            
            } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                    response.sendError(500);
            }
    
         }else if (accion.equals("actualizarContrasena")) {

            try (DBManager db = new DBManager()) {
                String id=request.getParameter("id");
                String contrasena_str =request.getParameter("contrasena");


                if(id !=null && contrasena_str !=null  )
                    {
                    db.changePasswordUserDB(id, contrasena_str);
                    }  
            
            } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                    response.sendError(500);
            }
    
         } else {
          // Acción desconocida
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción desconocida");
        }
    }
}
