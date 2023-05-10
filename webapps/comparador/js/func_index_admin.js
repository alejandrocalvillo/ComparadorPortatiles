
function searchUsuarios() {
    // Send AJAX request
    fetch('admin', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      },
     // body: new URLSearchParams(new FormData(document.getElementById("botonUsuarios"))).toString(),
    })
      .then(response => response.json())
      .then(usuarios => {
        // Generate table HTML
        let tableUsuariosHtml = `
        <table id="tabla" class="table table-striped">
        <thead>
          <tr>
            <th>Nombre</th>
            <th>Correo</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>`;
    
        usuarios.forEach((usuario, index) => {
          tableUsuariosHtml += `
          <tr>
            <td>${usuario.nombre}</td>
            <td>${usuario.correo}</td>
            <td><button onclick="seleccionarUsuario(${index})" class="btn btn-primary">Seleccionar</button></td>
            <td><button onclick="eliminarUsuario(${index})" class="btn btn-danger">Eliminar</button></td>
          </tr>`;
        });
    
        tableUsuariosHtml += '</tbody></table>';
    
        // Update results container
        document.getElementById('modalResultsContainer').innerHTML = tableUsuariosHtml;
    
        // Save usuarios array in a global variable
        window.usuariosArray = usuarios;
    
        // Show modal
        const resultsModal = new bootstrap.Modal(document.getElementById('resultsModal'));
        resultsModal.show();
      });
  }


function seleccionarUsuario(index) {
	console.log('Detalles ' + index);

	const usuario = window.usuariosArray[index];
  
	detallesHTML = `
	  <table id="tabla" border="2">
		<tr>
		  <th> Nombre   </th>
		  <th> Contraseña </th>
		  
		</tr>
		<tr>
		  <td>${usuario.nombre}</td>
		  <td>${usuario.correo}</td>
		</tr>
	  </table>`;
  
	document.getElementById('popUpDetalles').innerHTML = detallesHTML;
  
	// Muestra el modal de detalles
	var detallesModal = new bootstrap.Modal(document.getElementById('detallesModal'));
	detallesModal.show();
  }