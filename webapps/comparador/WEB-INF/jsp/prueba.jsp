<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='comparador.Ordenador' %>

<!DOCTYPE html>
<html>
    <head>
        <title>Catalog</title>
    </head>
    <body>
        <h1>Ordenador</h1>
<% int ASUSCount =  (Integer)request.getAttribute("ASUSCount"); %>
    <% if (ASUSCount != 0) { %>
            <p>
                <%= ASUSCount %> 
            </p>
    <% } else { %>
            <p>No hay ordenadores Asus</p>
    <% } %>

    <% int LGCount =  request.getAttribute("LGCount"); %>
    <% if (LGCount != 0) { %>
        <div>
            <p>
                <%= LGCount %>
            </p>
        </div>
    <% } else { %>
        <div>
            <p>No se encuentran ordenadores.</p>
        </div>
    <% } %>
    </body>
</html>