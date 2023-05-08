<%@ page language='java' contentType='text/html;charset=utf-8' %>
<%@ page import='comparador.Ordenador' %>
<%@ page import='java.util.ArrayList' %>
<%@ page import='java.util.List' %>
<!DOCTYPE html>
<html>
<head>
    <title>Comparador de portátiles</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <!-- Add Bootstrap 5 CSS CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/func_index.js" type="text/javascript"></script>

</head>
<body>
    <header class="bg-light mb-4">
        <div class="container d-flex justify-content-between py-3">
            <h1>Comparador de portátiles</h1>
            <a href="/comparador/login" class="btn btn-primary">Iniciar Sesion</a>
        </div>
    </header>
    <main class="container">
      <section class="row row-cols-1 row-cols-md-3 g-4 mb-4">
          <div class="rectangle">
              <p class="centered-text">ASUS</p>
              <% int ASUSCount=(Integer)request.getAttribute("ASUSCount"); %>
                <% if (ASUSCount !=0) { %>
                  <p class="centered-text">
                    <%= ASUSCount %> Portatiles
                  </p>
                  <% } else { %>
                    <p>No hay ordenadores Asus</p>
                    <% } %>
            </div>

            <div class="rectangle">
              <p class="centered-text">LG</p>
              <% int LGCount=(Integer)request.getAttribute("LGCount"); %>
                <% if (LGCount !=0) { %>
                  <p class="centered-text">
                    <%= LGCount %> Portatiles
                  </p>
                  <% } else { %>
                    <p>No hay ordenadores Asus</p>
                    <% } %>
            </div>

            <div class="rectangle">
              <p class="centered-text">DELL</p>
              <% int DELLCount=(Integer)request.getAttribute("DELLCount"); %>
                <% if (DELLCount !=0) { %>
                  <p class="centered-text">
                    <%= DELLCount %> Portatiles
                  </p>
                  <% } else { %>
                    <p>No hay ordenadores Asus</p>
                    <% } %>
            </div>

            <!--Empieza segunda linea de cajas--->

              <div class="rectangle">
                <p class="centered-text">TOSHIBA</p>
                <% int TOSHIBACount=(Integer)request.getAttribute("TOSHIBACount"); %>
                  <% if (TOSHIBACount !=0) { %>
                    <p class="centered-text">
                      <%= TOSHIBACount %> Portatiles
                    </p>
                    <% } else { %>
                      <p>No hay ordenadores Asus</p>
                      <% } %>
              </div>
              <div class="rectangle">
                <p class="centered-text">HP</p>
                <% int HPCount=(Integer)request.getAttribute("HPCount"); %>
                  <% if (HPCount !=0) { %>
                    <p class="centered-text">
                      <%= HPCount %> Portatiles
                    </p>
                    <% } else { %>
                      <p>No hay ordenadores Asus</p>
                      <% } %>
              </div>

              <div class="rectangle">
                <p class="centered-text">LENOVO</p>
                <% int LENOVOCount=(Integer)request.getAttribute("LENOVOCount"); %>
                  <% if (LENOVOCount !=0) { %>
                    <p class="centered-text">
                      <%= LENOVOCount %> Portatiles
                    </p>
                    <% } else { %>
                      <p>No hay ordenadores Asus</p>
                      <% } %>
              </div>
      </section>

      <!--Donde vemos los portatiles-->
      <div class="ordenadores-seleccionados">
        <div id="ordenadoresSeleccionadosText">
            <p class="centered-text">Portátiles Seleccionados</p>
        </div>
        <div id="ordenadoresSeleccionadosBox"></div>
        <div><input id="compareButton" type="button" class="btn btn-primary mb-2" value="Comparar"></input></div>
        <div><input id="removeSelectionButton" type="button" class="btn btn-danger" value="Eliminar Selección"></input></div>
    </div>
    <!-- Add Bootstrap 5 JS CDN -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</body>
</html>
