
<%@ page language='java' contentType='text/html;charset=utf-8' %>
<%@ page import='comparador.Usuario' %>
<%@ page import='comparador.DBManager' %>
<!DOCTYPE html>
<html>
 	<head>
    	<title>Comparador de portátiles</title>
		<meta charset="UTF-8">
     	<link rel="stylesheet" href="css/inicio_sesion.css">
 		
  	</head>
	<body>
	
		<div id=texto>
		<form action="http://example.com/search">
			<p>
      		<label>
        	Usuario: <input type="text" name="usuario" value=<%= usuario %>>
      		</label>
			</p>
			<p>
      		<label>
        	Contraseña: <input type="password" name="contrasena"value=<%= contrasena %>>
      		</label>
			</p>
 			<p>
			<input type="submit" value="Iniciar sesion">
			<input type="submit" value="Registrarse" hr>
			</p>
      		
    	</form>
      


  <%	if (usuario != null && contrasena != null) { 
				
		Usuario usuarioBD = db.getUsuarioDB(usuario, contrasena); 

	 if (usuarioBD.getNombre() != null) { 
	session.setAttribute("usuario", usuarioBD); 
			response.sendRedirect("index.jsp"); 
		 } 
		
		 else { 
				out.println("<p>Error: Usuario o contraseña incorrectos.</p>"); 
		 } 
		 } else {
			out.println("<p>No hay usuario</p>"); 
		 }
		%>
		</div>
	</body>
</html>
