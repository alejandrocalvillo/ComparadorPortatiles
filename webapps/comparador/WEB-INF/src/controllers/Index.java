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
            Usuario usuario = (Usuario)session.getAttribute("usuario");
            request.setAttribute("usuario", usuario);

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
            
            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.tiposMemoria();
            request.setAttribute("tiposMemoria", ordenadores);
            //ordenadores.clear();

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.tiposProcesador();
            request.setAttribute("tiposProcesador", ordenadores);
            //ordenadores.clear();

            ordenadores = new ArrayList<Ordenador>();
            ordenadores = db.tiposDisco();
            request.setAttribute("tiposDisco", ordenadores);
            //ordenadores.clear();

            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/index.jsp");
            rd.forward(request, response);
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    // public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {       

    //     try (DBManager db = new DBManager()) {

    //         List<Ordenador> ordenadoresBuscados = new ArrayList<Ordenador>();

    //         String marca = request.getParameter("marca");
    //         String memoria = request.getParameter("memoria");
    //         String procesador = request.getParameter("procesador");
    //         String capacidadDisco = request.getParameter("capacidadDisco");
    //         String tipoMemoria = request.getParameter("tipoMemoria");
    //         String tipoDisco = request.getParameter("tipoDisco");

    //         ordenadoresBuscados = db.BuscarOrdenadores(marca, memoria, procesador, capacidadDisco, tipoMemoria, tipoDisco);
    //         // Redirigir al usuario a la página de inicio
    //         RequestDispatcher rd = request.getRequestDispatcher("/index");

    //         request.setAttribute("ordenadoresBuscados", ordenadoresBuscados);
    //         System.out.println("Paso por aqui");
    //         rd.forward(request, response);
    //         System.out.println("Por aqui?");

    //     } catch (SQLException | NamingException e) {
    //         e.printStackTrace();
    //         response.sendError(500);
    //     }

    // }

}