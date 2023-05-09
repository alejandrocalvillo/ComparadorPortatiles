
<%@ page language='java' contentType='text/html;charset=utf-8' %>
<%@ page import='comparador.Usuario' %>
<%@ page import='comparador.DBManager' %>
<!DOCTYPE html>
<html>
 	<head>
    	<title>Inicio sesion</title>
		<meta charset="UTF-8">
     	<link rel="stylesheet" href="css/inicio_sesion.css">
			<!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">-->
		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		 <script src="js/inicio_sesion.js" type="text/javascript"></script>
 		<!--<script src="func_index.js" type="text/javascript"></script> -->
    	<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
  	</head>
	<body>
		<div id=texto>
		<form action="${pageContext.request.contextPath}/login" method="POST" onsubmit="return validarFormulario()">
			<p>
      		<label>
        	Usuario: <input type="text" name="usuario">
      		</label>
			</p>
			<p>
      		<label>
        	Contraseña: <input type="password" name="contrasena">
      		</label>
			</p>
 			<p>
			<input id="Inicio" type="submit" value="Iniciar sesion">
			<input id="Registro" type="button" value="Registrarse" >
			</p>
      		
    	</form>
		<div id="cajaEsquina">
			
			<a href="/comparador/index" class="btn btn-primary">Pagina Principal</a>
		</div>

	
		<%	
		DBManager db = new DBManager();
		Usuario usuario= new Usuario();
		String contrasena;
		String usuarioForm = request.getParameter("usuario");
		String contrasenaForm = request.getParameter("contrasena");
		
		if (usuarioForm != null && contrasenaForm != null) { 
		  Usuario usuarioBD = db.getUsuarioDB(usuarioForm, contrasenaForm); 
	
		  if (usuarioBD.getNombre() != null) { 
			session.setAttribute("usuario", usuarioBD); 
			response.sendRedirect("index.jsp"); 
		  } 
		  else { %> 
			<p> Error: Usuario o contraseña incorrectos.  </p>
			
			  <%} 
		} else {
		  out.println("<p>No hay usuario</p>"); 
		}
		
		%>

		<%
    		String error = (String) request.getAttribute("error");
    		if (error != null) {
			%>
        		<p id="error-message"><%= error %></p>
			<%
    		}
		%>
		
		</div>
		<div id="error-message"></div>
	</body>
</html>
