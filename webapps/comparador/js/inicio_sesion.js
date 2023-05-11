$(document).ready(function() {
    console.log("ready!");


	$("#Registro").click(function() {
 	 // Código a ejecutar cuando el usuario haga click
	 event.preventDefault();
	 var appPath = window.location.pathname;

	 // Extraer el nombre de la carpeta principal
	 var folderName = appPath.split('/')[1];
	 
	 // Construir la URL completa de la página de registro
	 var url = '/' + folderName + '/registro';
	 
	 // Redirigir a la página de registro
	 window.location.href = url;
  		
	});
	function validarFormulario() {
		var usuario = document.getElementById("usuario").value;
		var contrasena = document.getElementById("contrasena").value;
		if (usuario == "" || contrasena == "") {
		  alert("Por favor ingrese un usuario y contraseña válidos");
		  return false;
		}
		return true;
	  }
	
});

