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

@WebServlet("/ordenadores")
public class Ordenadores extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null && usuario.getNombre().equals("admin")
                && usuario.getContrasena().equals("*00A51F3F48415C7D4E8908980D443C29C69B60C9")) {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index_admin.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
/* 
        System.out.println("Entro en el Post");
        
        String accion = request.getParameter("accion");

        if (accion.equals("buscar")) {


            try (DBManager dbManager = new DBManager()) {
                System.out.println("Holita estoy aqui");
                List<Ordenador> ordenadores = dbManager.getOrdenadoresDB();
    
                JSONArray ordenadoresJsonArray = new JSONArray();
    
                System.out.println("Cree el JSONArray");
                for (Ordenador ordenador : ordenadores) {
                    JSONObject ordenadorJson = new JSONObject();
                    ordenadorJson.put("id", ordenador.getId());
                    ordenadorJson.put("modelo", ordenador.getModelo());
                    ordenadorJson.put("marca", ordenador.getMarca());
                    ordenadorJson.put("procesador", ordenador.getProcesador());
                    ordenadorJson.put("memoriaTipo", ordenador.getMemoriaTipo());
                    ordenadorJson.put("memoriaCapacidad", ordenador.getMemoriaCapacidad());
                    ordenadorJson.put("discoTipo", ordenador.getDiscoTipo());
                    ordenadorJson.put("discoCapacidad", ordenador.getDiscoCapacidad());
                    ordenadorJson.put("precio", ordenador.getPrecio());
                    ordenadoresJsonArray.put(ordenadorJson);
                }
    
                String ordenadoresJson  = ordenadoresJsonArray.toString();
    
                // Set response content type and charset
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
    
                // Write JSON string to response
                response.getWriter().write(ordenadoresJson );
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
                dbManager.deleteOrdenadorDB(id);
    
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

            String modelo_str = request.getParameter("modelo");    

            String marca_str = request.getParameter("marca");
            String procesador_str = request.getParameter("procesador");
            String memoria_str = request.getParameter("memoria");
            String disco_str = request.getParameter("disco");
            //String puntoVenta_str = request.getParameter("puntoVenta");
            
            String marcaId = db.getMarcaId(marca_str);
            String procesadorId = db.getMarcaId(procesador_str);
            String memoriaId = db.getMarcaId(memoria_str);
            String discoId = db.getMarcaId(disco_str);

             if(modelo_str!=null && marcaId!=null && procesadorId!=null && memoriaId!=null && discoId!=null)
                {

                db.insertOrdenadorDB(modelo_str, marcaId, procesadorId , memoriaId , discoId );
    
                System.out.println("modelo : "+modelo_str  + ", su marcaiD es "+marcaId + ", su procesadorId es "+procesadorId + ", su memoriaId es "+memoriaId + ", su discoId es " + discoId);
                }  
          
        
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
                response.sendError(500);
            }

        }else if(accion.equals("editar")){ 


            try (DBManager db = new DBManager()) {
            //Empezamos contando marcas en el index

            String id = request.getParameter("id");    
      
            String modelo_str = request.getParameter("modelo");    

            String marca_str = request.getParameter("marca");
            String procesador_str = request.getParameter("procesador");
            String memoria_str = request.getParameter("memoria");
            String disco_str = request.getParameter("disco");
            //String puntoVenta_str = request.getParameter("puntoVenta");
            
            String marcaId = db.getMarcaId(marca_str);
            String procesadorId = db.getMarcaId(procesador_str);
            String memoriaId = db.getMarcaId(memoria_str);
            String discoId = db.getMarcaId(disco_str);

            if(modelo_str!=null && marcaId!=null && procesadorId!=null && memoriaId!=null && discoId!=null)
            {
                db.updateOrdenadorModelo(id, modelo_str);
                db.updateOrdenadorMarca(id, marcaId);
                db.updateOrdenadorProcesador(id, procesadorId);
                db.updateOrdenadorMemoria(id ,memoriaId);
                db.updateOrdenadorDisco(id,discoId);

                System.out.println("modelo : "+modelo_str  + ", su marcaiD es "+marcaId + ", su procesadorId es "+procesadorId + ", su memoriaId es "+memoriaId + ", su discoId es " + discoId);
            }   
           
        
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
                response.sendError(500);
            }

        }  else {
          // Acción desconocida
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción desconocida");
        }*/
    }
}
