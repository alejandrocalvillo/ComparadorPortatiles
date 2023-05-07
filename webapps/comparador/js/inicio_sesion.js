$(document).ready(function() {
    console.log("ready!");


	$("#Registro").click(function() {
 	 // Código a ejecutar cuando el usuario haga click
	 event.preventDefault();
		window.location.href="/comparador/registro";
  		
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
	var errorMessage = document.getElementById("error-message");
	errorMessage.innerHTML = "Credenciales incorrectas";
	errorMessage.classList.add("show");
});

