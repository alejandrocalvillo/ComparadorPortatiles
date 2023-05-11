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

        String paginaParameter = request.getParameter("pagina");
        int pagina;
        if (paginaParameter == null || paginaParameter.equals("0")) {
            pagina = 0;
        } else {
            pagina = Integer.parseInt(paginaParameter);
        }

        System.out.println("Pagina: " + pagina);
        System.out.println("Content Type: " + request.getContentType());


        try (DBManager dbManager = new DBManager()) {
            List<Ordenador> ordenadores = dbManager.getOrdenadores(pagina * 12, 12);
            JSONArray doceOrdenadoresjsonArray = new JSONArray();
            for (Ordenador ordenador : ordenadores) {
                JSONObject ordenadorJson = new JSONObject();
                ordenadorJson.put("id", ordenador.getId());
                System.out.println("id: " + ordenadorJson.get("id"));
                ordenadorJson.put("modelo", ordenador.getModelo());
                System.out.println("modelo: " + ordenadorJson.get("modelo"));
                ordenadorJson.put("marca", ordenador.getMarca());
                System.out.println("marca: " + ordenadorJson.get("marca"));
                ordenadorJson.put("procesador", ordenador.getProcesador());
                System.out.println("procesador: " + ordenadorJson.get("procesador"));
                ordenadorJson.put("memoriaTipo", ordenador.getMemoriaTipo());
                System.out.println("memoriaTipo: " + ordenadorJson.get("memoriaTipo"));
                ordenadorJson.put("memoriaCapacidad", ordenador.getMemoriaCapacidad());
                System.out.println("memoriaCapacidad: " + ordenadorJson.get("memoriaCapacidad"));
                ordenadorJson.put("discoTipo", ordenador.getDiscoTipo());
                System.out.println("discoTipo: " + ordenadorJson.get("discoTipo"));
                ordenadorJson.put("discoCapacidad", ordenador.getDiscoCapacidad());
                System.out.println("discoCapacidad: " + ordenadorJson.get("discoCapacidad"));
                ordenadorJson.put("tienda", ordenador.getTienda());
                System.out.println("tienda: " + ordenadorJson.get("tienda"));
                ordenadorJson.put("precio", ordenador.getPrecio());
                System.out.println("precio: " + ordenadorJson.get("precio"));
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
