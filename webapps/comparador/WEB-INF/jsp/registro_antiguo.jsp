<%@ page language='java' contentType='text/html;charset=utf-8' %>
<%@ page import='comparador.Usuario' %>
<%@ page import='comparador.DBManager' %>
<!DOCTYPE html>
<html>
 	<head>
    	<title>Registro</title>
		<meta charset="UTF-8">
     	<link rel="stylesheet" href="css/registro.css" type="text/css">
 		<!--<script src="func_index.js" type="text/javascript"></script> -->
    	<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
  	</head>
	<body>
		<div id=texto>
		<form action="${pageContext.request.contextPath}/registro" method="POST">
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
            <label>
            Email: <input type="email" name="email">
            </label>
            </p>
 			<p>
			<input type="submit" value="Registrarse" href="/prueba">
			</p>
      		
    	</form>
		<div id="cajaEsquina">
			
			<a href="${pageContext.request.contextPath}/index" class="btn btn-primary">Pagina Principal</a>
		</div>


		<%	
		DBManager db = new DBManager();
		Usuario usuario= new Usuario();
		String contrasena;
        String email;
		String usuarioForm = request.getParameter("usuario");
		String contrasenaForm = request.getParameter("contrasena");
		String emailForm=request.getParameter("email");
		if (usuarioForm != null && contrasenaForm != null && emailForm!= null) { 
		  Usuario usuarioBD = db.getUsuarioDB(usuarioForm, contrasenaForm); 
	
		  
		} else {%>
		  <p>No hay usuario</p> 
		<%}
		
		%>
		</div>
	</body>
</html>