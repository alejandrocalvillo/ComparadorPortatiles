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

@WebServlet("/buscar")
public class Buscador extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String marca = request.getParameter("marca");
        String procesador = request.getParameter("procesador");
        String memoriaTipo = request.getParameter("memoriaTipo");
        int memoriaCapacidad = Integer.parseInt(request.getParameter("memoriaCapacidad"));
        String discoTipo = request.getParameter("discoTipo");
        int discoCapacidad = Integer.parseInt(request.getParameter("discoCapacidad"));

        try (DBManager dbManager = new DBManager()) {

            List<Ordenador> ordenadores = dbManager.searchOrdenadores(marca, procesador, memoriaTipo, memoriaCapacidad,
                    discoTipo, discoCapacidad);
            request.setAttribute("ordenadores", ordenadores);
            request.getRequestDispatcher("index.jsp").forward(request, response);

            JSONArray ordenadoresJsonArray = new JSONArray();
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
                ordenadoresJsonArray.put(ordenadorJson);
            }

            String ordenadoresJson = ordenadoresJsonArray.toString();

            // Set response content type and charset
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Write JSON to response
            response.getWriter().write(ordenadoresJson);
        } catch (SQLException | NamingException ex) {
            ex.printStackTrace();
            response.sendError(500);
        }
    }
}
