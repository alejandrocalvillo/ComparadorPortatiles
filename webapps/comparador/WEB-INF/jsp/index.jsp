<%@ page language='java' contentType='text/html;charset=utf-8' %>
  <%@ page import='comparador.Ordenador' %>
    <%@page import='comparador.Usuario' %>
      <%@ page import='java.util.ArrayList' %>
        <%@ page import='java.util.List' %>
          <!DOCTYPE html>
          <html>

          <head>
            <title>Comparador de portátiles</title>
            <meta charset="UTF-8">
            <link rel="stylesheet" href="css/index.css" type="text/css">
            <!--Bootstrap 5 CSS CDN-->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
              rel="stylesheet">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script src="js/func_index.js" type="text/javascript"></script>

          </head>
          <% Usuario usuario=(Usuario)request.getAttribute("usuario"); %>

            <body>
              <header class="bg-light mb-4">
                <div class="container d-flex justify-content-between py-3 align-items-start flex-column flex-md-row">
                  <h1>Comparador de portátiles</h1>
                  <div class="mt-2 mt-md-0 d-flex flex-column align-items-end">
                    <% if (usuario !=null){ %>
                      <div>
                        <p class="centered-text">Bienvenido <%= usuario.getNombre() %>
                        </p>
                        <% if (usuario.getAdmin()){ %>
                          <a href="${pageContext.request.contextPath}/admin" class="btn btn-primary"> Pantalla Admin</a>
                          <a href="${pageContext.request.contextPath}/cerrar" class="btn btn-danger">Cerrar Sesion</a>
                          <% } else {%>
                            <a href="${pageContext.request.contextPath}/cerrar" class="btn btn-danger">Cerrar Sesion</a>
                      </div>
                      <% } } else { %>
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary mb-2">Iniciar
                          Sesion</a>
                        <% } %>

                  </div>
                  <div class="lista-ordenadores">
                    <div id="ordenadoresSeleccionadosText">
                      <p class="centered-text">Portátiles Seleccionados</p>
                    </div>
                    <div id="ordenadoresSeleccionadosBox"></div>
                    <div><input id="compareButton" type="button" onclick="
                                        <% if (usuario != null) { %>
                                            compararLoged();" <% } else { %>
                      comparar();"
                      <% } %>
                        class="btn btn-primary mb-2" value="Comparar"></input>
                    </div>
                    <div><input id="removeSelectionButton" type="button" class="btn btn-danger"
                        value="Eliminar Selección"></input></div>
                  </div>

                </div>
              </header>

              <!--Empieza primeralinea de cajas--->
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

              <!-- Filtros de Busqueda -->
              <section class="mt-5">
                <form id="filtros" onsubmit="event.preventDefault(); 
            <% if (usuario != null) { %>
              searchOrdenadoresLoged();" <% } else { %>
                  searchOrdenadores();"
                  <% } %>

                    enctype="application/x-www-form-urlencoded">
                    <div class="row">
                      <div class="col-md-4 mb-3">
                        <label for="marca" class="form-label">Marca:</label>
                        <select class="form-select" id="marca" name="marca">
                          <option value="cualquiera">Cualquiera</option>
                          <option value="ASUS">ASUS</option>
                          <option value="LG">LG</option>
                          <option value="DELL" selected>DELL</option>
                          <option value="TOSHIBA">TOSHIBA</option>
                          <option value="HP">HP</option>
                          <option value="LENOVO">LENOVO</option>
                        </select>
                      </div>
                      <div class="col-md-4 mb-3">
                        <label for="tipoMemoria" class="form-label">Tipo de Memoria:</label>
                        <select class="form-select" id="tipoMemoria" name="tipoMemoria">
                          <option value="RAM">RAM</option>
                        </select>
                      </div>
                      <div class="col-md-4 mb-3">
                        <label for="capacidadMemoria" class="form-label">Capacidad de Memoria:</label>
                        <select class="form-select" id="capacidadMemoria" name="capacidadMemoria">
                          <% List<Ordenador> memorias = (List<Ordenador>)request.getAttribute("tiposMemoria"); %>
                              <option value="-1">Cualquiera</option>
                              <% for (Ordenador ordenador : memorias) { %>
                                <option value="<%= ordenador.getMemoriaCapacidad() %>">
                                  <%= ordenador.getMemoriaCapacidad() %>
                                </option>
                                <% } %>
                        </select>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-4 mb-3">
                        <label for="procesador" class="form-label">Procesador:</label>
                        <select class="form-select" id="procesador" name="procesador">
                          <% List<Ordenador> procesadores = (List<Ordenador>)request.getAttribute("tiposProcesador"); %>
                              <option value="cualquiera">Cualquiera</option>
                              <% for (Ordenador ordenador : procesadores) { %>
                                <option value="<%= ordenador.getProcesador() %>">
                                  <%= ordenador.getProcesador() %>
                                </option>
                                <% } %>
                        </select>
                      </div>
                      <div class="col-md-4 mb-3">
                        <label for="tipoDisco" class="form-label">Tipo del Disco:</label>
                        <select class="form-select" id="tipoDisco" name="tipoDisco">
                          <% List<Ordenador> discos = (List<Ordenador>)request.getAttribute("tiposDisco"); %>
                              <option value="cualquiera">Cualquiera</option>
                              <option value="HDD">HDD</option>
                              <option value="SSD">SSD</option>
                        </select>
                      </div>
                      <div class="col-md-4 mb-3">
                        <label for="capacidadDisco" class="form-label">Capacidad del Disco:</label>
                        <select class="form-select" id="capacidadDisco" name="capacidadDisco">
                          <option value="-1">Cualquiera</option>
                          <% for (Ordenador ordenador : discos) { %>
                            <option value="<%= ordenador.getDiscoCapacidad() %>">
                              <%= ordenador.getDiscoCapacidad() %>
                            </option>
                            <% } %>
                        </select>
                      </div>
                    </div>
                    <div class="d-grid gap-2">
                      <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                </form>
              </section>
              <!-- Los doce ultimos ordenadores -->
              <section>
                <div id="doceOrdenadores">
                  <input type="button" value="Cargar mas" class="btn btn-primary" id="loadMoreButton" onclick="loadOrdenadores();">
                </div>
              </section>
              <!-- Los resultados de la busqueda -->
              <section class="mb-4">
                <div id="resultsContainer"></div>
                <!-- Modal -->
                <div class="modal fade" id="resultsModal" tabindex="-1" aria-labelledby="resultsModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="resultsModalLabel">Ordenadores disponibles</h5>
                        <button type="button" class="btn-close close-button" data-bs-dismiss="modal"
                          aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        <div id="modalResultsContainer"></div>
                      </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                      </div>
                    </div>
                  </div>
                </div>
              </section>

              <!-- Modal para ver los detalles de un ordenador -->
              <section>
                <div class="modal fade" id="detallesModal" tabindex="-1" aria-labelledby="detallesModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="detallesModalLabel">Detalles del ordenador</h5>
                        <button type="button" class="btn-close close-button" data-bs-dismiss="modal"
                          aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        <div id="popUpDetalles"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </section>
              <!-- Modal para comparar -->


              <section>
                <div class="modal fade" id="compararModal" tabindex="-1" aria-labelledby="compararModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h5 class="modal-title" id="compararModalLabel">Comparativa de Ordenadores</h5>
                        <button type="button" class="btn-close close-button" data-bs-dismiss="modal"
                          aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        <div id="popUpComparar"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </section>



              <!-- Add Bootstrap 5 JS CDN -->
              <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
              <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
            </body>

          </html>