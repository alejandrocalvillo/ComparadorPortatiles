<%@ page language='java' contentType='text/html;charset=utf-8' %>
	<%@ page import='comparador.Usuario' %>
		<%@ page import='comparador.DBManager' %>
			<!DOCTYPE html>
			<html>

			<head>
				<title>Inicio sesion</title>
				<meta charset="UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
					rel="stylesheet">
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
				<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
				<script src="js/inicio_sesion.js" type="text/javascript"></script>
			</head>

			<body>
				<div class="container">
					<div class="row justify-content-center">
						<div class="col-md-6">
							<div class="card mt-5">
								<div class="card-body">
									<form action="${pageContext.request.contextPath}/login" method="POST"
										onsubmit="return validarFormulario()">
										<div class="mb-3">
											<label for="usuario" class="form-label">Usuario</label>
											<input type="text" class="form-control" name="usuario" id="usuario">
										</div>
										<div class="mb-3">
											<label for="contrasena" class="form-label">Contraseña</label>
											<input type="password" class="form-control" name="contrasena"
												id="contrasena">
										</div>
										<div class="d-grid">
											<input type="submit" class="btn btn-primary" value="Iniciar sesión">
										</div>
									</form>
								</div>
							</div>
							<div class="text-center mt-3">
								<input type="button" class="btn btn-link" value="Registrarse">
							</div>
						</div>
						<div class="col-md-6 d-none d-md-block">
							<img src="https://us.123rf.com/450wm/koblizeek/koblizeek2001/koblizeek200100050/138262629-usuario-miembro-de-perfil-de-icono-de-hombre-vector-de-s%C3%ADmbolo-perconal-sobre-fondo-blanco-aislado.jpg?ver=6"
								class="img-fluid" alt="Imagen de perfil">
						</div>
					</div>
					<div class="text-center mt-3">
						<a href="/comparador/index" class="btn btn-primary">Página Principal</a>
					</div>
				</div>

				<% DBManager db=new DBManager(); Usuario usuario=new Usuario(); String contrasena; String
					usuarioForm=request.getParameter("usuario"); String
					contrasenaForm=request.getParameter("contrasena"); if (usuarioForm !=null && contrasenaForm !=null)
					{ Usuario usuarioBD=db.getUsuarioDB(usuarioForm, contrasenaForm); if (usuarioBD.getNombre() !=null)
					{ session.setAttribute("usuario", usuarioBD); response.sendRedirect("index.jsp"); } else { %>
					<div class="alert alert-danger text-center mt-3" role="alert">
						Error: Usuario o contraseña incorrectos.
					</div>
					<% } } else { out.println("<p>No hay usuario</p>");
						}
						%>

						<% String error=(String) request.getAttribute("error"); if (error !=null) { %>
							<div class="alert alert-danger text-center mt-3" role="alert">
								<%= error %>
							</div>
							<% } %>


			</body>

			</html>