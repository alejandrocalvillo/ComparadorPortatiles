<!DOCTYPE html>
<html>

<head>
	<title>Comparador de portátiles</title>
	<meta charset="UTF-8">
	<!-- <link rel="stylesheet" href="css/index.css" type="text/css"> -->
	<!--Bootstrap 5 CSS CDN-->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/inicio_sesion.js" type="text/javascript"></script>

</head>

<body class="bg-info d-flex justify-content-center align-items-center vh-100">

	<div class="bg-white p-5 rounded-5 text-secondary shadow" style="width: 25rem">

		<div class="d-flex justify-content-center">
			<img src="img/login/Imagen_de_login.png" alt="login image" style="height: 7rem" />
		</div>
		<div class="text-center fs-1 fw-bold">Iniciar Sesion</div>
		<form action="${pageContext.request.contextPath}/login" method="POST" onsubmit="return validarFormulario()">
			<div class="input-group mt-4">
				<div class="input-group-text bg-info">
					<img src="img/login/icons/usuario.svg" alt="usuario icono" style="height: 20px; width: 20px;">
				</div>
				<div class="input-group-text bg-light">
					<input type="form-control bg-light" placeholder="Usuario" type="text" name="usuario" id="usuario">
				</div>
			</div>
			<div class="input-group mt-1">
				<div class="input-group-text bg-info">
					<img src="img/login/icons/password.svg" alt="contraseña icono" style="height: 20px; width: 20px;">
				</div>
				<div class="input-group-text bg-light">
					<input type="password" placeholder="Password" name="contrasena" id="contrasena">
				</div>
			</div>
			<div class="input-group mt-2">
				<input type="submit" class="btn btn-info text-white w-100 mt-4 fw-semibold shadow-sm"
					value="Iniciar sesion">
				<input id="Registro" type="button" class="btn btn-warning text-white w-100 mt-4 fw-semibold shadow-sm"
				value="Registrarse">
			</div>


		</form>
	</div>
</body>
<!-- Add Bootstrap 5 JS CDN -->
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

</html>