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



@WebServlet("/buscador")
public class Buscador extends HttpServlet {
    List<Ordenador> ordenadores;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {       

        try (DBManager db = new DBManager()) {

            String marca = request.getParameter("marca");
            String memoria = request.getParameter("memoria");
            String procesador = request.getParameter("procesador");
            String capacidadDisco = request.getParameter("capacidadDisco");
            String tipoMemoria = request.getParameter("tipoMemoria");
            String tipoDisco = request.getParameter("tipoDisco");

            ordenadores = db.BuscarOrdenadores(marca, memoria, procesador, capacidadDisco, tipoMemoria, tipoDisco);
            // Redirigir al usuario a la página de inicio
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");

            System.out.println("Paso por aqui");
            rd.forward(request, response);
            System.out.println("Por aqui?");

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }

    }

}
