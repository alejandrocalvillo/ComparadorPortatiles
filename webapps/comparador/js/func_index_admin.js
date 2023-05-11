
let indexSeleccion ='1';

function searchUsuarios(accion) {
    // Send AJAX request
    fetch('usuarios', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      },
    body: new URLSearchParams({
      accion: accion // Agregar el parámetro de acción
    }).toString(),
     
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
            <td><button onclick="seleccionarUsuario(${index}, 'seleccionar')" class="btn btn-primary">Seleccionar</button></td>
            <td><button onclick="eliminarUsuario(${index}, 'eliminar')" class="btn btn-danger">Eliminar</button></td>
          </tr>`;
        });
    
        tableUsuariosHtml += '</tbody></table>';
    
        // Update results container
        document.getElementById('modalResultsContainer').innerHTML = tableUsuariosHtml;
    
        
        // Save usuarios array in a global variable
        window.usuariosArray = usuarios;

        console.log(`he rellenado los usuarios `+window.usuariosArray[1] );
        // Show modal
        const resultsModal = new bootstrap.Modal(document.getElementById('resultsModal'));
        resultsModal.show();
      });
  }


function seleccionarUsuario(index, accion) {
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
	
  detallesCambioHTML = `
              <form  onsubmit="cambiarDatos(event, ${usuario.id})" class="float-start">
									  <p>
										<label for="unittype">Selecciona el parametro a cambiar</label>
										<select id="unittype" name="unittype">
										  <option value="1" selected> Nombre </option>
										  <option value="2"> Contraseña </option>
										</select>
										<div id="editar-parametro"></div>
									</p>
									<button type="submit" class="btn btn-primary">Cambiar</button>
								  </form>`;


  document.getElementById('cambioDetalles').innerHTML = detallesCambioHTML;
  var detallesModal = new bootstrap.Modal(document.getElementById('detallesModal'));
  detallesModal.show();
  }

  function eliminarUsuario(index, accion) {
    // Obtener el usuario a eliminar
    const usuario = window.usuariosArray[index];
  
    // Mostrar confirmación de eliminación
    if (confirm(`¿Estás seguro de que deseas eliminar al usuario "${usuario.nombre}"?`)) {
      // Enviar solicitud AJAX para eliminar el usuario
      fetch('usuarios', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: new URLSearchParams({
          id: usuario.id, // Enviar el ID del usuario a eliminar
          accion:accion
        }).toString(),
      })
        //.then(response => response.json())
        .then(data => {
          // Actualizar la tabla de usuarios

          searchUsuarios('buscar');
          // Cerrar el modal
          const modal = document.querySelector('#resultsModal');
          const modalBootstrap = bootstrap.Modal.getInstance(modal);
          modalBootstrap.hide();
          
        })
        .catch(error => {
          // Mostrar mensaje de error
          console.error('Error al eliminar usuario:', error);
          alert('Error al eliminar usuario. Por favor, inténtelo de nuevo más tarde.');
          searchUsuarios('buscar');
        });
    }
  }

  function anadirUsuario(accion) {

    // Obtener los datos del formulario
    const formData = new FormData(document.getElementById("anadirUsuario"));
    formData.append('accion', accion);

    // Send AJAX request
    fetch('usuarios', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: new URLSearchParams(formData).toString(),
    })
    .then(data => {
      console.log('Usuario agregado:', data);
      // Actualizar la tabla de usuarios con el nuevo usuario
      searchUsuarios('buscar');
      // Cerrar el modal
      const modal = document.querySelector('#exampleModal');
      const modalBootstrap = bootstrap.Modal.getInstance(modal);
      modalBootstrap.hide();
 
      
    })
    .catch(error => {
      console.error('Error al agregar usuario:', error);
      alert('Error al agregar usuario');
    });
  }

  function cambiarDatos(event, index) {
    event.preventDefault(); // evitar que el formulario se envíe
    
     // Obtener el usuario a actualizar
    

    const parametro = document.getElementById("unittype").value;
    if (parametro === "1") {
      // mostrar cuadro de diálogo para cambiar el nombre
      const nuevoNombre = prompt("Ingrese su nuevo nombre:");
      console.log(`Nuevo nombre ingresado: ${nuevoNombre}`);


      fetch('usuarios', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: new URLSearchParams({
          id:index,
          nombre: nuevoNombre, // Enviar el nuevo nombre
          accion: 'actualizarNombre',
        }).toString(),
      })
        .then(data => {
          // Actualizar la tabla de usuarios
          searchUsuarios('buscar');
          alert('Los cambios se aplicaron correctamente.');
          const modal = document.querySelector('#detallesModal');
          const modalBootstrap = bootstrap.Modal.getInstance(modal);
          modalBootstrap.hide();
          const modal2 = document.querySelector('#resultsModal');
          const modalBootstrap2 = bootstrap.Modal.getInstance(modal2);
          modalBootstrap2.hide();
          // Mostrar mensaje de éxito
          
        })
        .catch(error => {
          // Mostrar mensaje de error
          console.error('Error al cambiar datos del usuario:', error);
          alert('Error al cambiar datos del usuario. Por favor, inténtelo de nuevo más tarde.');
        });


    } else if (parametro === "2") {
      // mostrar cuadro de diálogo para cambiar la contraseña
      const nuevaContrasena = prompt("Ingrese su nueva contraseña:");
      


      fetch('usuarios', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: new URLSearchParams({
          id:index,
          contrasena: nuevaContrasena, // Enviar el nuevo nombre
          accion: 'actualizarContrasena',
        }).toString(),
      })
        .then(data => {
          // Actualizar la tabla de usuarios
          searchUsuarios('buscar');
          alert('Los cambios se aplicaron correctamente.');
          const modal = document.querySelector('#detallesModal');
          const modalBootstrap = bootstrap.Modal.getInstance(modal);
          modalBootstrap.hide();
          const modal2 = document.querySelector('#resultsModal');
          const modalBootstrap2 = bootstrap.Modal.getInstance(modal2);
          modalBootstrap2.hide();
          // Mostrar mensaje de éxito
         
        })
        .catch(error => {
          // Mostrar mensaje de error
          console.error('Error al cambiar datos del usuario:', error);
          alert('Error al cambiar datos del usuario. Por favor, inténtelo de nuevo más tarde.');
          
        });


    } else {
      // manejar caso en el que se seleccione una opción no válida
      console.log("Opción no válida seleccionada");
    }
  }


  ///////////////////////////////////////////////////////////////Apartir de aqui va la parte de Ordenadores/////////////////////////////////////////////////////////////////

 function searchOrdenadores(accion) {
  // Send AJAX request
  fetch('ordenadores', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    },
  body: new URLSearchParams({
    accion: accion // Agregar el parámetro de acción
  }).toString(),
   
  })
    .then(response => response.json())
    .then(ordenadores => {
      // Generate table HTML
      let tableOrdenadoresHtml = `
      <table id="tabla" class="table table-striped">
      <thead>
        <tr>
          <th>Marca</th>
          <th>Modelo</th>
          <th>Procesador</th>
          <th>Tipo de memoria</th>
          <th>Capacidad de memoria</th>
          <th>Tipo de disco</th>
          <th>Capacidad de disco</th>
        </tr>
      </thead>
      <tbody>`;
  
      ordenadores.forEach((ordenador, index) => {
        tableOrdenadoresHtml += `
        <tr>
          <td>${ordenador.marca}</td>
          <td>${ordenador.modelo}</td>
          <td>${ordenador.procesador}</td>
          <td>${ordenador.memoriaTipo}</td>
          <td>${ordenador.memoriaCapacidad}</td>
          <td>${ordenador.discoTipo}</td>
          <td>${ordenador.discoCapacidad}</td>
          <td><button onclick="seleccionarOrdenador(${index}, 'seleccionar')" class="btn btn-primary">Seleccionar</button></td>
          <td><button onclick="eliminarOrdenador(${index}, 'eliminar')" class="btn btn-danger">Eliminar</button></td>
        </tr>`;
      });
  
      tableOrdenadoresHtml += '</tbody></table>';
  
      // Update results container
      document.getElementById('modalOrdenadoresContainer').innerHTML = tableOrdenadoresHtml;
  
      // Save ordenadores array in a global variable
      window.ordenadoresArray = ordenadores;
  
      // Show modal
      const ordenadoresModal = new bootstrap.Modal(document.getElementById('ordenadoresModal'));
      ordenadoresModal.show();
    });
}


function seleccionarOrdenador(index, accion) {
console.log('Detalles ' + index);

const ordenador = window.ordenadoresArray[index];

detallesHTML = `
  <table id="tabla" border="2">
  <tr>
      <th>Marca</th>
      <th>Modelo</th>
      <th>Procesador</th>
      <th>Tipo de memoria</th>
      <th>Capacidad de memoria</th>
      <th>Tipo de disco</th>
      <th>Capacidad de disco</th>    
  </tr>
  <tr>
    <td>${ordenador.marca}</td>
    <td>${ordenador.modelo}</td>
    <td>${ordenador.procesador}</td>
    <td>${ordenador.memoriaTipo}</td>
    <td>${ordenador.memoriaCapacidad}</td>
    <td>${ordenador.discoTipo}</td>
    <td>${ordenador.discoCapacidad}</td>
  </tr>
  </table>`;

document.getElementById('popUpDetallesOrdenador').innerHTML = detallesHTML;

// Muestra el modal de detalles
var detallesModal = new bootstrap.Modal(document.getElementById('detallesOrdenadorModal'));
detallesModal.show();
}

function eliminarOrdenador(index, accion) {
  // Obtener el ordenador a eliminar
  const ordenador = window.ordenadoresArray[index];

  // Mostrar confirmación de eliminación
  if (confirm(`¿Estás seguro de que deseas eliminar el ordenador "${ordenador.marca}" modelo "${ordenador.modelo}"?`)) {
    // Enviar solicitud AJAX para eliminar el ordenador
    fetch('ordenadores', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      },
      body: new URLSearchParams({
        id: index, // Enviar el ID del ordenador a eliminar
        accion:accion
      }).toString(),
    })
      //.then(response => response.json())
      .then(data => {
        // Actualizar la tabla de odenadores
        searchOrdenadores('buscar');
        
      })
      .catch(error => {
        // Mostrar mensaje de error
        console.error('Error al eliminar ordenador:', error);
        alert('Error al eliminar ordenador. Por favor, inténtelo de nuevo más tarde.');
        searchOrdenadores('buscar');
      });
  }
}

function anadirOrdenador(accion) {

  const formData = new FormData();
  formData.append('accion', accion);
  formData.append('modelo', modelo);
  formData.append('marca', marca);
  formData.append('procesador', procesador);
  formData.append('memoria', memoria);
  formData.append('disco', memoria);

  fetch('ordenadores', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: formData
  })
  .then(data => {
    console.log('Ordenador agregado:', data);
    // Actualizar la tabla de Ordenadores con el nuevo Ordenador
    searchOrdenadores('buscar');
    // Cerrar el modal
    const modal = document.querySelector('#ordenadorModal');
    const modalBootstrap = bootstrap.Modal.getInstance(modal);
    modalBootstrap.hide();
    
  })
  .catch(error => {
    console.error('Error al agregar ordenador:', error);
    alert('Error al agregar ordenador');
  });
}