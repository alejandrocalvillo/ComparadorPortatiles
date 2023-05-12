<%@ page language='java' contentType='text/html;charset=utf-8' %>
  <%@ page import='comparador.Ordenador' %>
    <%@page import='comparador.Usuario' %>
      <%@ page import='java.util.ArrayList' %>
        <%@ page import='java.util.List' %>

		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="UTF-8">
			<title>Mi Página Web</title>
			<meta charset="UTF-8">
			<link rel="stylesheet" href="css/index_admin.css" type="text/css">
			<!-- Add Bootstrap 5 CSS CDN -->
			<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
				rel="stylesheet">
			<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
			<script src="js/func_index_admin.js" type="text/javascript"></script>
		</head>

		<body>
			<div class="container">
				<div class="box">
					<h2>ORDENADORES</h2>
					<div class="buttons">
						<button id="botonOrdenadores" onclick="searchOrdenadores('buscar')">Mostrar</button>
					</div>
				</div>
				<div class="box">
					<h2>PUNTOS DE VENTA</h2>
					<div class="buttons">
						<button id="botonUsuarios" onclick="searchPuntosVacios('buscarNull')">Crear Punto de Venta</button>
						<button id="botonUsuarios" onclick="searchPuntos('buscar')">Mostrar</button>
					</div>
				</div>

				<div class="box">
					<h2>USUARIOS</h2>
					<div class="buttons">
						<button class="btn btn-primary" id="botonUsuarios"
							onclick="searchUsuarios('buscar')">Mostrar</button>
					</div>
				</div>
				<div class="box">
					<h2>USUARIOS ADMINISTRADORES</h2>
					<div class="buttons">
						<button class="btn btn-primary" id="botonAdministradores"
							onclick="searchAdministradores('buscar')">Mostrar</button>
					</div>
				</div>
			</div>
			<div id="cajaEsquina">
				<a href="${pageContext.request.contextPath}/cerrar" class="btn btn-danger">Cerrar Sesión</a>
				<a href="${pageContext.request.contextPath}/index" class="btn btn-primary">Pagina Principal</a>
			</div>

			<!-- Los resultados de la busqueda USUARIOS -->
			<section class="mb-4">
				<div id="resultsContainer"></div>
				<!-- Modal -->
				<div class="modal fade" id="resultsModal" tabindex="-1" aria-labelledby="resultsModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="resultsModalLabel">Usuarios </h5>
								<button type="button" class="btn btn-primary" data-bs-toggle="modal"
									data-bs-target="#exampleModal">Añadir</button>

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


			<!-- Modal para cambiar detalles usuarios -->
			<section>
				<div class="modal fade alFrente" id="detallesModal" tabindex="-1" aria-labelledby="detallesModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="detallesModalLabel">Detalles del usuario</h5>
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="popUpDetalles"></div>
							</div>
							<div class="modal-footer">
								<div id="cambioDetalles"></div>

							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Modal para añadir usuarios -->

			<section>
				<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
					aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Agregar usuario</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<form id="anadirUsuario">
									<div class="mb-3">
										<label for="nombre" class="form-label">Nombre</label>
										<input type="text" class="form-control" id="nombre" name="nombre">
									</div>
									<div class="mb-3">
										<label for="correo" class="form-label">Correo</label>
										<input type="email" class="form-control" id="correo" name="correo">
									</div>
									<div class="mb-3">
										<label for="contrasena" class="form-label">Contraseña</label>
										<input type="password" class="form-control" id="contrasena" name="contrasena">
									</div>
								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Cancelar</button>
								<button type="button" class="btn btn-primary"
									onclick="anadirUsuario('anadir')">Agregar</button>
							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Modal para ver los administradores -->
			<section class="mb-4">
				<div id="adminContainer"></div>
				<!-- Modal -->
				<div class="modal fade" id="adminModal" tabindex="-1" aria-labelledby="adminModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="adminModalLabel">Administradores </h5>
								<button type="button" class="btn btn-primary" data-bs-toggle="modal"
									data-bs-target="#adminAddModal" onclick="mostrarUsuariosParaAdmin('buscar')">Añadir</button>
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="modalAdminContainer"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Modal para agregar administradores -->
			<section>
				<div id="adminAddContainer"></div>
				<!-- Modal -->
				<div class="modal fade" id="adminAddModal" tabindex="-1" aria-labelledby="adminAddModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="adminAddModalLabel">Añadir Administradores</h5>
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="modalAdminAddContainer"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</section>



			<!-- Los resultados de la busqueda PUNTOS DE VENTA-->
			<section class="mb-4">
				<div id="resultsContainerPuntos"></div>
				<!-- Modal -->
				<div class="modal fade" id="resultsModalPuntos" tabindex="-1" aria-labelledby="resultsModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="resultsModalLabel">Puntos de venta </h5>
								<button type="button"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalPuntos">Añadir</button>
								
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="modalResultsContainerPuntos"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</section>


			<!-- Modal para cambiar detalles puntos de venta -->
			<section>
				<div class="modal fade" id="detallesModalPuntos" tabindex="-1" aria-labelledby="detallesModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="detallesModalLabel">Detalles del usuario</h5>
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="popUpDetallesPuntos"></div>
							</div>
							<div class="modal-footer">
								<div id="cambioDetallesPunto"></div>
								
							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Modal para añadir PUNTOS DE VENTA -->
			<div class="modal fade" id="exampleModalPuntos" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <h5 class="modal-title" id="exampleModalLabel">Agregar punto de venta</h5>
					  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
					  <form id="anadirPunto">
						<div class="mb-3">
						  <label for="tienda" class="form-label">Tienda</label>
						  <input type="text" class="form-control" id="tienda" name="tienda">
						</div>
						<div class="mb-3">
						  <label for="direccion" class="form-label">Direccion</label>
						  <input type="text" class="form-control" id="direccion" name="direccion">
						</div>
						
					  </form>
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
					  <button type="button" class="btn btn-primary" onclick="anadirPunto('anadir')">Agregar</button>
					</div>
				  </div>
				</div>
			  </div>

			<!-- Los resultados de la busqueda ORDENADORES -->
			<section class="mb-4">
				<div id="ordenadoresContainer"></div>
				<!-- Modal -->
				<div class="modal fade" id="ordenadoresModal" tabindex="-1" aria-labelledby="ordenadoresModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="ordenadoresModalLabel">Ordenadores </h5>
								<button type="button"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addOrdenadorModal">Añadir</button>
								
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="modalOrdenadoresContainer"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Modal para cambiar propiedades ordenador -->
			<section>
				<div class="modal fade" id="detallesOrdenadorModal" tabindex="-1" aria-labelledby="detallesOrdenadorModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="detallesOrdenadorModalLabel">Detalles del ordenador</h5>
								<button type="button" class="btn-close close-button" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<div id="popUpDetallesOrdenador"></div>
							</div>
							<div class="modal-footer">
								<div id="cambioDetallesOrdenador"></div>								
							</div>
						</div>
					</div>
				</div>
			</section>

			<!-- Modal para añadir ordenadores -->
			<div class="modal fade" id="addOrdenadorModal" tabindex="-1" aria-labelledby="addOrdenadorModalLabel" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <h5 class="modal-title" id="addOrdenadorModalLabel">Agregar ordenador</h5>
					  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
					  <form id="anadirOrdenador">
						<div class="mb-3">
						  <label for="modelo" class="form-label">Modelo</label>
							<input type="text" class="form-control" id="modelo" name="modelo" value="">
						</div>
						<div class="mb-3">
						  <label for="marca" class="form-label">marca</label>
						  <select class="form-select" id="marca" name="marca">
							<% List<String> marcas = (List<String>) request.getAttribute("listMarcas"); %>
							<% for (String marca : marcas) { %>
							  <option value="<%= marca %>">
								<%= marca %>
							  </option>
							<% } %>
						  </select>						  
						</div>
						<div class="mb-3">
						  <label for="procesador" class="form-label">Procesador</label>
							<select class="form-select" id="procesador" name="procesador">
								<% List<String> procesadores = (List<String>) request.getAttribute("listProcesadores"); %>
								<% for (String procesador : procesadores) { %>
								  <option value="<%= procesador %>">
									<%= procesador %>
								  </option>
								<% } %>
						  </select>
						</div>
						<div class="mb-3">
							<label for="memoria" class="form-label">Memoria</label>
							<select class="form-select" id="memoria" name="memoria">
								<% List<String> memorias = (List<String>) request.getAttribute("listMemorias"); %>
									<% for (String memoria : memorias) { %>
									  <option value="<%= memoria%>">
										<%= memoria %>
									  </option>
									<% } %>
							</select>
						</div>
						<div class="mb-3">
							<label for="disco" class="form-label">Disco</label>
							<select class="form-select" id="disco" name="disco">
								<% List<String> discos = (List<String>) request.getAttribute("listDiscos"); %>
									<% for (String disco : discos) { %>
									  <option value="<%= disco%>">
										<%= disco %>
									  </option>
									<% } %>
							</select>
						</div>
					  </form>
					</div>
					<div class="modal-footer">
					  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
					  <button type="button" class="btn btn-primary" onclick="anadirOrdenador('anadir')">Agregar</button>
					</div>
				  </div>
				</div>
			</div>
			<!-- Add Bootstrap 5 JS CDN -->
			<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
		</body>

		</html>