<%@ page language='java' contentType='text/html;charset=utf-8'%>
<%@ page import='comparador.Ordenador' %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Mi Página Web</title>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/index_admin.css" type="text/css">
  <!-- Add Bootstrap 5 CSS CDN -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="js/func_index_admin.js" type="text/javascript"></script>
</head>
<body>
	<div class="container">
		<div class="box">
			<h2>PORTÁTILES</h2>
			<div class="buttons">
				<button>Añadir</button>
				<button>Modificar</button>
				<button>Eliminar</button>
			</div>
		</div>
		<div class="box">
			<h2>PUNTOS DE VENTA</h2>
			<div class="buttons">
				<button>Añadir</button>
				<button>Modificar</button>
				<button>Eliminar</button>
			</div>
		</div>
		<div class="box">
			<h2>USUARIOS</h2>
			<div class="buttons">
				<button>Añadir</button>
				<button>Modificar</button>
				<button>Eliminar</button>
			</div>
		</div>
		<div class="box">
			<h2>USUARIOS ADMINISTRADORES</h2>
			<div class="buttons">
				<button>Añadir</button>
				<button>Modificar</button>
				<button>Eliminar</button>
			</div>
		</div>
	</div>
  <div id="cajaEsquina">
	<button class="btn btn-danger">Cerrar Sesión</button>
	<a href="/comparador/index" class="btn btn-primary">Pagina Principal</a>
</div>
</body>
</html>