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

@WebServlet("/puntosventa")
public class Puntos_de_venta extends HttpServlet {

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
                List<PuntosVenta> puntos = dbManager.getPuntosVentaDB();
    
                JSONArray puntosJsonArray = new JSONArray();
    
                System.out.println("Cree el JSONArray");
                for (PuntosVenta punto : puntos) {
                    JSONObject puntoJson = new JSONObject();
                    puntoJson.put("id", punto.getId());
                    puntoJson.put("tienda", punto.getTienda());
                    puntoJson.put("direccion", punto.getDireccion());
                    puntosJsonArray.put(puntoJson);
                }
    
                String puntosJson = puntosJsonArray.toString();
    
                // Set response content type and charset
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
    
                // Write JSON string to response
                response.getWriter().write(puntosJson);
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
                dbManager.deletePuntoDB(id);
    
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
      
            String tienda_str=request.getParameter("tienda");
            String direccion_str=request.getParameter("direccion");
            
    
             if(tienda_str!=null && direccion_str!=null  )
                {
                PuntosVenta punto=db.insertPuntoDB(tienda_str, direccion_str);
    
                
                }  
          
    
        
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
                response.sendError(500);
            }

        }   else if (accion.equals("actualizarTienda")) {

            try (DBManager db = new DBManager()) {
                String id=request.getParameter("id");
                String tienda_str=request.getParameter("tienda");


                if(id !=null && tienda_str !=null  )
                    {
                    db.changeNameShopDB(id, tienda_str);
                    }  
            
            } catch (SQLException | NamingException e) {
                    e.printStackTrace();
                    response.sendError(500);
            }
    
         }else if (accion.equals("actualizarDireccion")) {

            try (DBManager db = new DBManager()) {
                String id=request.getParameter("id");
                String direccion_str =request.getParameter("direccion");


                if(id !=null && direccion_str !=null  )
                    {
                    db.changeAddressShopDB(id, direccion_str);
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
