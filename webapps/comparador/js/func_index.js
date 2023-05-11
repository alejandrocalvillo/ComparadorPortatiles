var indicesSeleccionados = [];

let pagina = 0;

function loadOrdenadores() {
    fetch('/getOrdenadores', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
        },
        body: new URLSearchParams({pagina: pagina}).toString(),
    })
    .then(response => response.json())
    .then(ordenadores => {
        let tableHtml = `
        <table class="table table-striped">
        <thead>
            <tr>
                <th>Modelo</th>
				<th></th>
				<th></th>
            </tr>
        </thead>
        <tbody>`;
        ordenadores.forEach((ordenador, index) => {
            tableHtml += `
            <tr>
                <td>${ordenador.modelo}</td>
				<td><button onclick="detallesOrdenador(${index})" class="btn btn-info">Detalles</button></td>
				<td><button onclick="seleccionarOrdenador(${index})" class="btn btn-primary">Seleccionar</button></td>
            </tr>`;
        });
        tableHtml += `</tbody></table>`;
        document.getElementById('doceOrdenadores').innerHTML = tableHtml;
    });
}

document.getElementById('loadMoreButton').addEventListener('click', function() {
    page++;
    loadOrdenadores();
});


function searchOrdenadores() {
	// Send AJAX request
	fetch('buscar', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
		},
		body: new URLSearchParams(new FormData(document.getElementById("filtros"))).toString(),
	})
		.then(response => response.json())
		.then(ordenadores => {
			// Generate table HTML
			let tableHtml = `
	  <table id="tabla" class="table table-striped">
	  <thead>
		<tr>
		  <th>Marca</th>
		  <th>Modelo</th>
		  <th></th>
		  <th></th>
		</tr>
	  </thead>
	  <tbody>`;

			ordenadores.forEach((ordenador, index) => {
				tableHtml += `
		<tr>
		  <td>${ordenador.marca}</td>
		  <td>${ordenador.modelo}</td>
		  <td><button onclick="detallesOrdenador(${index})" class="btn btn-info">Detalles</button></td>
		  <td><button onclick="seleccionarOrdenador(${index})" class="btn btn-primary">Seleccionar</button></td>
		</tr>`;
			});

			tableHtml += '</tbody></table>';

			// Update results container
			document.getElementById('modalResultsContainer').innerHTML = tableHtml;

			// Save ordenadores array in a global variable
			window.ordenadoresArray = ordenadores;

			// Show modal
			const resultsModal = new bootstrap.Modal(document.getElementById('resultsModal'));
			resultsModal.show();
		});
}


function seleccionarOrdenador(index) {

	console.log('Seleccionaste ' + index);

	const ordenador = window.ordenadoresArray[index];

	if (indicesSeleccionados.indexOf(index) === -1) {

		indicesSeleccionados.push(index);

		const ordenadorSeleccionado = document.createElement('div');
		ordenadorSeleccionado.innerHTML = `<div class ="card-body"><h6 class="card-subtitle mb-2">Marca: ${ordenador.marca}</h6> <p class="card-text">Modelo: ${ordenador.modelo}</p></div>`;
		ordenadorSeleccionado.setAttribute('class', `card`);
		const btnEliminar = document.createElement('input');
		btnEliminar.id = 'eliminarOrdenador';
		btnEliminar.value = 'Eliminar';
		btnEliminar.type = 'button';
		btnEliminar.className = 'btn btn-danger';

		btnEliminar.addEventListener('click', () => {
			const indice = indicesSeleccionados.indexOf(index);
			if (indice > -1) {
				indicesSeleccionados.splice(indice, 1);
				ordenadorSeleccionado.remove();
			}
		});

		ordenadorSeleccionado.appendChild(btnEliminar);

		// Agregar el elemento div al div principal
		const ordenadoresSeleccionadosBox = document.getElementById('ordenadoresSeleccionadosBox');
		ordenadoresSeleccionadosBox.appendChild(ordenadorSeleccionado);

		var seleccion = new bootstrap.Modal(document.getElementById('ordenadoresSeleccionadosBox'));
		//seleccion.show();


	} else {
		console.log('Ya ha sido seleccionado');
	}



	document.getElementById('removeSelectionButton').addEventListener('click', function () {
		const ordenadoresSeleccionadosBox = document.getElementById('ordenadoresSeleccionadosBox');

		// Eliminar todos los hijos del div principal
		while (ordenadoresSeleccionadosBox.firstChild) {
			ordenadoresSeleccionadosBox.removeChild(ordenadoresSeleccionadosBox.firstChild);
		}

		// Vaciar el array de índices seleccionados
		indicesSeleccionados = [];
	});

}

function detallesOrdenador(index) {
	console.log('Detalles ' + index);

	const ordenador = window.ordenadoresArray[index];

	detallesHTML = `
	  <table id="tabla" border="2">
		<tr>
		  <th>Marca</th>
		  <th>Modelo</th>
		  <th>Procesador</th>
		  <th>Memoria Tipo</th>
		  <th>Memoria Capacidad</th>
		  <th>Disco Tipo</th>
		  <th>Disco Capacidad</th>
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

	document.getElementById('popUpDetalles').innerHTML = detallesHTML;

	// Muestra el modal de detalles
	var detallesModal = new bootstrap.Modal(document.getElementById('detallesModal'));
	detallesModal.show();
}

function comparar() {

	console.log(indicesSeleccionados.length);

	compararHTML = `
		  <table id="tabla" border="2" class="table table-striped">
			<tr>
			  <th>Marca</th>
			  <th>Modelo</th>
			  <th>Procesador</th>
			  <th>Memoria Tipo</th>
			  <th>Memoria Capacidad</th>
			  <th>Disco Tipo</th>
			  <th>Disco Capacidad</th>
			</tr>`;

	var ordenadoresComparados = "";

	for (var i = 0; i < indicesSeleccionados.length; i++) {

		const ordenador = window.ordenadoresArray[indicesSeleccionados[i]];

		console.log(ordenador.modelo);

		var ordenadorAComparar = `
					<tr>
					  <td>${ordenador.marca}</td>
					  <td>${ordenador.modelo}</td>
					  <td>${ordenador.procesador}</td>
					  <td>${ordenador.memoriaTipo}</td>
					  <td>${ordenador.memoriaCapacidad}</td>
					  <td>${ordenador.discoTipo}</td>
					  <td>${ordenador.discoCapacidad}</td>
					</tr>`;

		ordenadoresComparados += ordenadorAComparar;
	}

	compararHTML += ordenadoresComparados;

	compararHTML += `</table>`;

	console.log(compararHTML);

	document.getElementById('popUpComparar').innerHTML = compararHTML;

	var detallesModal = new bootstrap.Modal(document.getElementById('compararModal'));
	detallesModal.show();

	compararHTML = '';

}


// Usuarios Logueados

function searchOrdenadoresLoged() {
	// Send AJAX request
	fetch('buscar', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
		},
		body: new URLSearchParams(new FormData(document.getElementById("filtros"))).toString(),
	})
		.then(response => response.json())
		.then(ordenadores => {
			// Generate table HTML
			let tableHtml = `
	  <table id="tabla" class="table table-striped">
	  <thead>
		<tr>
		  <th>Marca</th>
		  <th>Modelo</th>
		  <th></th>
		  <th></th>
		</tr>
	  </thead>
	  <tbody>`;

			ordenadores.forEach((ordenador, index) => {
				tableHtml += `
		<tr>
		  <td>${ordenador.marca}</td>
		  <td>${ordenador.modelo}</td>
		  <td><button onclick="detallesOrdenadorLoged(${index})" class="btn btn-info">Detalles</button></td>
		  <td><button onclick="seleccionarOrdenador(${index})" class="btn btn-primary">Seleccionar</button></td>
		</tr>`;
			});

			tableHtml += '</tbody></table>';

			// Update results container
			document.getElementById('modalResultsContainer').innerHTML = tableHtml;

			// Save ordenadores array in a global variable
			window.ordenadoresArray = ordenadores;

			// Show modal
			const resultsModal = new bootstrap.Modal(document.getElementById('resultsModal'));
			resultsModal.show();
		});
}

function detallesOrdenadorLoged(index) {
	console.log('Detalles ' + index);

	const ordenador = window.ordenadoresArray[index];

	detallesHTML = `
	  <table id="tabla" border="2">
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

		</tr>
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
		</tr>
	  </table>`;

	document.getElementById('popUpDetalles').innerHTML = detallesHTML;

	// Muestra el modal de detalles
	var detallesModal = new bootstrap.Modal(document.getElementById('detallesModal'));
	detallesModal.show();
}

function compararLoged() {

	console.log(indicesSeleccionados.length);

	compararHTML = `
		  <table id="tabla" border="2" class="table table-striped">
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
			</tr>`;

	var ordenadoresComparados = "";

	for (var i = 0; i < indicesSeleccionados.length; i++) {

		const ordenador = window.ordenadoresArray[indicesSeleccionados[i]];

		console.log(ordenador.modelo);

		var ordenadorAComparar = `
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
					</tr>`;

		ordenadoresComparados += ordenadorAComparar;
	}

	compararHTML += ordenadoresComparados;

	compararHTML += `</table>`;

	console.log(compararHTML);

	document.getElementById('popUpComparar').innerHTML = compararHTML;

	var detallesModal = new bootstrap.Modal(document.getElementById('compararModal'));
	detallesModal.show();

	compararHTML = '';

}





