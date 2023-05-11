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

@WebServlet("/getOrdenadores")
public class GetTwelveOrdenadores extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Entro en el Post");

        System.out.println("Content Type: " + request.getContentType());
        String marca = request.getParameter("marca");
        System.out.println("Marca: " + marca);

        String memoriaTipo = request.getParameter("tipoMemoria");
        System.out.println("Memoria Tipo: " + memoriaTipo);

        int memoriaCapacidad = Integer.parseInt(request.getParameter("capacidadMemoria"));
        System.out.println("Memoria Capacidad: " + memoriaCapacidad);

        String procesador = request.getParameter("procesador");
        System.out.println("Procesador: " + procesador);

        String discoTipo = request.getParameter("tipoDisco");
        System.out.println("Disco Tipo: " + discoTipo);

        int discoCapacidad = Integer.parseInt(request.getParameter("capacidadDisco"));
        System.out.println("Disco Capacidad: " + discoCapacidad);

        String paginaParameter = request.getParameter("pagina");
        int pagina;
        if (paginaParameter == null || paginaParameter.equals("0")) {
            pagina = 0;
        } else {
            pagina = Integer.parseInt(paginaParameter);
        }

        try (DBManager dbManager = new DBManager()) {
            List<Ordenador> ordenadores = dbManager.getOrdenadores(pagina * 12, 12);
            JSONArray doceOrdenadoresjsonArray = new JSONArray();
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
                ordenadorJson.put("tienda", ordenador.getTienda());
                ordenadorJson.put("precio", ordenador.getPrecio());
                doceOrdenadoresjsonArray.put(ordenadorJson);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(doceOrdenadoresjsonArray.toString());
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

}
