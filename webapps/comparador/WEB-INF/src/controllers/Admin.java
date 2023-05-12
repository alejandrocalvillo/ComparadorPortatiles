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

@WebServlet("/admin")
public class Admin extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
       

        try (DBManager db = new DBManager()) {
            if(usuario!=null && db.isAdmin(String.valueOf(usuario.getId()))) {

                List<String> listMarcas = new ArrayList<String>();
                List<String> listProcesadores = new ArrayList<String>();
                List<String> listMemorias = new ArrayList<String>();
                List<String> listDiscos = new ArrayList<String>();

                listMarcas = db.listMarcas();
                request.setAttribute("listMarcas", listMarcas);

                listProcesadores = db.listProcesadores();
                request.setAttribute("listProcesadores", listProcesadores);

                listMemorias = db.listMemorias();
                request.setAttribute("listMemorias", listMemorias);

                listDiscos = db.listDiscos();
                request.setAttribute("listDiscos", listDiscos);

                
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index_admin.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/index");
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion.equals("buscar")) {

            try (DBManager dbManager = new DBManager()) {
                System.out.println("Holita estoy aqui");
                List<Usuario> administradores = dbManager.getAdministradores();

                JSONArray administradoresJsonArray = new JSONArray();

                System.out.println("Cree el JSONArray");
                for (Usuario usuario : administradores) {
                    JSONObject usuarioJson = new JSONObject();
                    usuarioJson.put("id", usuario.getId());
                    usuarioJson.put("nombre", usuario.getNombre());
                    usuarioJson.put("correo", usuario.getCorreo());
                    administradoresJsonArray.put(usuarioJson);
                }

                String administradoresJson = administradoresJsonArray.toString();

                // Set response content type and charset
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                // Write JSON string to response
                response.getWriter().write(administradoresJson);
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
                dbManager.deleteAdmin(id);

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
        } else if (accion.equals("anadir")) {

            try (DBManager db = new DBManager()) {
                // Empezamos contando marcas en el index

                String id = request.getParameter("id");

                if (id != null ) {
                    db.declararAdmin(id);

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
