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



@WebServlet("/index")
public class Index extends HttpServlet {
    List<Ordenador> ordenadores;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Obtiene un unico ordenador.
        HttpSession session = request.getSession();

        try (DBManager db = new DBManager()) {
            //Empezamos contando marcas en el index

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.listOrdenadoresPorMarca("ASUS");
            request.setAttribute("ASUSCount", ordenadores.size());
            ordenadores.clear();

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.listOrdenadoresPorMarca("LG");
            request.setAttribute("LGCount", ordenadores.size());
            ordenadores.clear();
            
            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.listOrdenadoresPorMarca("DELL");
            request.setAttribute("DELLCount", ordenadores.size());
            ordenadores.clear();

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.listOrdenadoresPorMarca("TOSHIBA");
            request.setAttribute("TOSHIBACount", ordenadores.size());
            ordenadores.clear();

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.listOrdenadoresPorMarca("HP");
            request.setAttribute("HPCount", ordenadores.size());
            ordenadores.clear();

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.listOrdenadoresPorMarca("LENOVO");
            request.setAttribute("LENOVOCount", ordenadores.size());
            ordenadores.clear();
            // Reenvía la petición a una plantilla JSP, pasando el catálogo como atributo
            
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
            rd.forward(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
