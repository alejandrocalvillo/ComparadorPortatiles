<%@ page language='java' contentType='text/html;charset=utf-8' %>
	<%@ page import='comparador.Ordenador' %>

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
					<h2>PORTÁTILES</h2>
					<div class="buttons">
						<div class="buttons">
							<input type="button" class="btn btn-primary" onclick="searchOrdenadores('buscar');" value="Mostrar todos los ordenadores">
						</div>
					</div>
				</div>
				<div class="box">
					<h2>PORTATILES Y DONDE ENCONTRARLOS</h2>
					<div class="buttons">
						<div class="buttons">
							<input type="button" class="btn btn-primary" onclick="searchOrdenadoresVentas('buscar');" value="Mostrar todos los ordenadores">
						</div>
					</div>
				</div>
				<div class="box">
					<h2>USUARIOS</h2>
					<div class="buttons">
						<button id="botonUsuarios" onclick="searchUsuarios('buscar')">Mostrar</button>
					</div>
				</div>
				<div class="box">
					<h2>USUARIOS ADMINISTRADORES</h2>
					<div class="buttons">
						<button>Mostrar</button>
					</div>
				</div>
			</div>
			<div id="cajaEsquina">
				<button href="${pageContext.request.contextPath}/cerrar" class="btn btn-danger">Cerrar Sesión</button>
				<a href="<${pageContext.request.contextPath}/index" class="btn btn-primary">Pagina Principal</a>
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
								<button type="button"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Añadir</button>
								
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

			<!-- Los resultados de la busqueda ORDENADORES -->
			<section class="mb-4">
				<!-- Modal -->
				<div class="modal fade" id="ordenadoresModal" tabindex="-1" aria-labelledby="ordenadoresModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="ordenaoresModalLabel">Ordenadores</h5>
								<button type="button"  class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Añadir</button>
								
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


			<!-- Modal para cambiar detalles usuarios -->
			<section>
				<div class="modal fade" id="detallesModal" tabindex="-1" aria-labelledby="detallesModalLabel"
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
								<form action="http://example.com/search" class="float-start">
									<p>
									  <label for="unittype">Selecciona el parametro a cambiar</label>
									  <select id="unittype" name="unittype">
										<option value="1" selected> Nombre </option>
										<option value="2"> Contraseña </option>
									  </select>
									</p>
									<button type="submit" class="btn btn-primary">Cambiar</button>
								  </form>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Modal para añadir usuarios -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
				  <div class="modal-content">
					<div class="modal-header">
					  <h5 class="modal-title" id="exampleModalLabel">Agregar usuario</h5>
					  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
					  <form>
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
					  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
					  <button type="button" class="btn btn-primary" onclick="anadirUsuario('anadir')">Agregar</button>
					</div>
				  </div>
				</div>
			  </div>
			<!-- Add Bootstrap 5 JS CDN -->
			<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
			<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
		</body>

		</html>