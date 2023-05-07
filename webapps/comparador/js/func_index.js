

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
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Procesador</th>
                        <th>Memoria Tipo</th>
                        <th>Memoria Capacidad</th>
                        <th>Disco Tipo</th>
                        <th>Disco Capacidad</th>
                    </tr>`;

            ordenadores.forEach(ordenador => {
                tableHtml += `
                    <tr>
                        <td>${ordenador.id}</td>
                        <td>${ordenador.marca}</td>
                        <td>${ordenador.modelo}</td>
                        <td>${ordenador.procesador}</td>
                        <td>${ordenador.memoriaTipo}</td>
                        <td>${ordenador.memoriaCapacidad}</td>
                        <td>${ordenador.discoTipo}</td>
                        <td>${ordenador.discoCapacidad}</td>
                    </tr>`;
            });

            tableHtml += '</table>';

            // Update results container
            document.getElementById('resultsContainer').innerHTML = tableHtml;
        });
    }

	// console.log("ready!");

	// var tablaCreada = false; // variable para controlar si la tabla ya ha sido creada

	// var datos = [
	// 	{ marca: "LG", modelo: 1 },
	// 	{ marca: "MAX", modelo: 2 },
	// 	{ marca: "FIREBOT", modelo: 3 }
	// ];


	// $("#IniciarSesion").click(function () {
	// 	// Código a ejecutar cuando el usuario haga click
	// 	window.location.href = "/comparador/login";

	// });

	// $("#buscar").click('DOMContentLoaded', function () {
	// 	// Código a ejecutar cuando el usuario haga click
	// 	var marca = document.getElementById("marca").value;
	// 	var memoria = document.getElementById("memoria").value;
	// 	var procesador = document.getElementById("procesador").value;
	// 	var capacidadDisco = document.getElementById("capacidadDisco").value;
	// 	var tipoMemoria = document.getElementById("tipoMemoria").value;
	// 	var tipoDisco = document.getElementById("tipoDisco").value;

	// 	var data = {
	// 		marca: marca,
	// 		memoria: memoria,
	// 		procesador:procesador,
	// 		capacidadDisco:capacidadDisco,
	// 		tipoMemoria:tipoMemoria,
	// 		tipoDisco:tipoDisco
	// 	}  

	// 	$.ajax({
	// 		type: "POST",
	// 		url: "/comparador/buscador",
	// 		data: data,
	// 		processData: false,
	// 		contentType: false,
	// 		success: function (response) {
	// 			console.log(response);
	// 		},
	// 		error: function (error) {
	// 			console.log(error);
	// 		}
	// 	});

	// 	if (!tablaCreada) {
	// 		crearTabla();
	// 		tablaCreada = true;
	// 	}

	// });

	// function crearTabla() {
	// 	var tabla = document.createElement("table");
	// 	var encabezado = tabla.insertRow();
	// 	var encabezadoMarca = encabezado.insertCell(0);
	// 	var encabezadoModelo = encabezado.insertCell(1);
	// 	encabezadoMarca.innerHTML = "<b>Marca</b>";
	// 	encabezadoModelo.innerHTML = "<b>Modelo</b>";

	// 	for (var i = 0; i < datos.length; i++) {
	// 		var fila = tabla.insertRow();
	// 		fila.setAttribute('id', 'fila_' + i);
	// 		var celdaMarca = fila.insertCell(0);
	// 		var celdaModelo = fila.insertCell(1);
	// 		celdaMarca.innerHTML = datos[i].marca;
	// 		celdaModelo.innerHTML = datos[i].modelo;

	// 		// Agrega botones de Seleccionar y Modificar a la derecha de cada fila
	// 		var botones = '<button>Detalles</button><button>Seleccionar</button>';
	// 		fila.insertAdjacentHTML('beforeend', '<td>' + botones + '</td>');

	// 		fila.querySelector('button:nth-of-type(1)').addEventListener('click', function () {
	// 			var indice = this.parentNode.parentNode.rowIndex - 1;
	// 			console.log('Seleccionaste la fila ' + indice);

	// 			detallesOrdenador(indice);
	// 		});

	// 		fila.querySelector('button:nth-of-type(2)').addEventListener('click', function () {
	// 			var indice = this.parentNode.parentNode.rowIndex - 1;
	// 			var filaSeleccionada = this.parentNode.parentNode;
	// 			var marca = filaSeleccionada.cells[0].innerHTML;
	// 			var modelo = filaSeleccionada.cells[1].innerHTML;
	// 			console.log('Modificaste la fila ' + indice + "con nombre " + marca + "y edad " + modelo);

	// 			seleccionarOrdenador(nombre);
	// 		});
	// 	}
	// 	document.getElementById("tabla").appendChild(tabla);
	// }


	// function seleccionarOrdenador(ordenador) {
	// 	console.log('Modificaste ' + ordenador);
	// }

	// function detallesOrdenador(ordenador) {
	// 	console.log('Detalles ' + ordenador);
	// }


