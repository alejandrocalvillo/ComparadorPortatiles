package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.Console;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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

        System.out.println("Entro en el Post");
        HttpSession session = request.getSession();

        System.out.println("Content Type: " + session.getContentType());
        String marca = request.getParameter("marca");
        System.out.println("Marca: " + marca);

        String memoriaTipo = session.getParameter("tipoMemoria");
        System.out.println("Memoria Tipo: " + memoriaTipo);

        int memoriaCapacidad = Integer.parseInt(session.getParameter("capacidadMemoria"));
        System.out.println("Memoria Capacidad: " + memoriaCapacidad);

        String procesador = session.getParameter("procesador");
        System.out.println("Procesador: " + procesador);

        String discoTipo = session.getParameter("tipoDisco");
        System.out.println("Disco Tipo: " + discoTipo);

        int discoCapacidad = Integer.parseInt(session.getParameter("capacidadDisco"));
        System.out.println("Disco Capacidad: " + discoCapacidad);

        try (DBManager dbManager = new DBManager()) {
            System.out.println("Holita estoy aqui");
            List<Ordenador> ordenadores = dbManager.searchOrdenadores(marca, procesador, memoriaTipo, memoriaCapacidad,
                    discoTipo, discoCapacidad);

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
            String errorMessage = "Error: " + ex.getMessage();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(errorMessage);
        }
    }
}
