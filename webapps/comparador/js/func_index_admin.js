//Usuarios
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
        accion: accion
      }).toString(),
    })
      //.then(response => response.json())
      .then(data => {
        // Actualizar la tabla de usuarios
        searchUsuarios('buscar');

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

  const formData = new FormData();
  formData.append('accion', accion);
  formData.append('nombre', nombre);
  formData.append('correo', correo);
  formData.append('contrasena', contrasena);

  fetch('usuarios', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: formData
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

//Ordenadores

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
            <th>Memoria Tipo</th>
            <th>Memoria Capacidad</th>
            <th>Disco Tipo</th>
            <th>Disco Capacidad</th>
            <th></th>
            <th></th>
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
      const resultsModal = new bootstrap.Modal(document.getElementById('ordenadoresModal'));
      resultsModal.show();
    });
}


function searchOrdenadoresVentas(accion) {
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
            <th>Memoria Tipo</th>
            <th>Memoria Capacidad</th>
            <th>Disco Tipo</th>
            <th>Disco Capacidad</th>
            <th>Tienda</th>
            <th>Precio</th>
            <th></th>
            <th></th>
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
            <td>${ordenador.tienda}</td>
            <td>${ordenador.precio}</td>
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
      const resultsModal = new bootstrap.Modal(document.getElementById('ordenadoresModal'));
      resultsModal.show();
    });
}

function eliminarOrdenador(index, accion) {
  // Obtener el ordenador a eliminar
  const ordenador = window.ordenadoresArray[index];

  // Mostrar confirmación de eliminación
  if (confirm(`¿Estás seguro de que deseas eliminar el ordenador "${ordenador.model}"?`)) {
    // Enviar solicitud AJAX para eliminar el ordenador
    fetch('ordenadores', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
      },
      body: new URLSearchParams({
        id: ordenador.id, // Enviar el ID del usuario a eliminar
        accion: accion
      }).toString(),
    })
      //.then(response => response.json())
      .then(data => {
        // Actualizar la tabla de Ordenadores
        searchOrdenadores('buscar');

      })
      .catch(error => {
        // Mostrar mensaje de error
        console.error('Error al eliminar el ordenador:', error);
        alert('Error al eliminar usuario. Por favor, inténtelo de nuevo más tarde.');
        searchOrdenadores('buscar');
      });
  }
}