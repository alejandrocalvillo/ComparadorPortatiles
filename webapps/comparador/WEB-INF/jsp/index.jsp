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
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
          <script src="js/func_index.js" type="text/javascript"></script>
          <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
        </head>

        <body>

          <header>
            <h1 id="title">Comparador de portátiles</h1>
            <div>
              <a href="/comparador/login">
                <input id="IniciarSesion" type="button" value="Iniciar Sesion"></input>
              </a>
            </div>

            <div class="lineaHorizontal"></div>
            <div class="lineaVertical"></div>
            </nav>
          </header>
          <main>
            <section>
              <!--Empieza primera linea de cajas--->
              <div id="contenedor1">
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
              </div>
              <!--Empieza segunda linea de cajas--->
              <div id="contenedor2">
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
              </div>
            </section>


            <!--Donde vemos los portatiles-->
            <section>
              <div id="PortatilesSeleccionadosTEXT">
                <p class="centered-text">Portatiles Seleccionados</p>
              </div>
              <div id="PortatilesSeleccionadosBOX"></div>
              <div><input id="comparar" type="button" value="Comparar"></input></div>
              <div><input id="eliminarCesta" type="button" value="Eliminar Selecion"></input></div>
            </section>

            <!--Filtros-->

            <section>
              <form id="filtros" onsubmit="event.preventDefault(); searchOrdenadores();">
                <div class="filtro">
                  <p class="centered-text-filtro">Marca:
                    <select class="seleccionador" name="marca">
                      <option value="ASUS">ASUS</option>
                      <option value="LG">LG</option>
                      <option value="DELL" selected>DELL</option>
                      <option value="TOSHIBA">TOSHIBA</option>
                      <option value="HP">HP</option>
                      <option value="LENOVO">LENOVO</option>
                    </select>
                  </p>
                </div>
                <div class="filtro">
                  <p class="centered-text-filtro">Tipo de Memoria:
                    <select class="seleccionador" name="tipoMemoria">
                      <option value="RAM">RAM</option>
                    </select>
                  </p>
                </div>
                <div class="filtro">
                  <p class="centered-text-filtro">Capacidad de Memoria:
                    <select class="seleccionador" name="capacidadMemoria">
                      <% List<Ordenador> memorias = (List<Ordenador>)request.getAttribute("tiposMemoria"); %>
                          <% for (Ordenador ordenador : memorias) { %>
                            <option value="<%= ordenador.getMemoriaCapacidad() %>">
                              <%= ordenador.getMemoriaCapacidad() %>
                            </option>
                            <% } %>
                    </select>
                  </p>
                </div>
                <div class="filtro">
                  <p class="centered-text-filtro">Procesador:
                    <select class="seleccionador" name="procesador">
                      <% List<Ordenador> procesadores = (List<Ordenador>)request.getAttribute("tiposProcesador"); %>
                          <% for (Ordenador ordenador : procesadores) { %>
                            <option value="<%= ordenador.getProcesador() %>">
                              <%= ordenador.getProcesador() %>
                            </option>
                            <% } %>
                    </select>
                  </p>
                </div>
                <div class="filtro">
                  <p class="centered-text-filtro">Tipo del Disco:
                    <select class="seleccionador" name="tipoDisco">
                      <% List<Ordenador> discos = (List<Ordenador>)request.getAttribute("tiposDisco"); %>
                          <% for (Ordenador ordenador : discos) { %>
                            <option value="<%= ordenador.getDiscoTipo() %>">
                              <%= ordenador.getDiscoTipo() %>
                            </option>
                            <% } %>
                    </select>
                  </p>
                </div>
                <div class="filtro">
                  <p class="centered-text-filtro">Capacidad del Disco:
                    <select class="seleccionador" name="capacidadDisco">
                      <% for (Ordenador ordenador : discos) { %>
                        <option value="<%= ordenador.getDiscoCapacidad() %>">
                          <%= ordenador.getDiscoCapacidad() %>
                        </option>
                        <% } %>
                    </select>
                  </p>
                </div id="buscar-box">
                <input id="buscar" type="submit" value="Buscar"></input>
              </form>
            </section>

            <section id="tabla-container">
              <div id="resultsContainer"></div>
            </section>

          </main>




          <!-- <footer>
      <p> Comparador de portátiles Lucia Mayoral, Camilo Nosetuapellido, Alejandro Calvillo © 2023</p>
    </footer> -->
        </body>
        <!--  <script src="main.js"></script>-->

        </html>