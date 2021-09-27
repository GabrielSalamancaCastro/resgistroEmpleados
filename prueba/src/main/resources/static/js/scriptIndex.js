
// METODO POST 
let botonRegistrar = document.querySelector('#registro-btn');

botonRegistrar.addEventListener("click", function(event){
    event.preventDefault(); 

let nombrePost = document.querySelector('#nombre');
let apellidoPost = document.querySelector('#apellido');
let documentoPost = document.querySelector('#documento');
let numeroDocumentoPost = document.querySelector('#numeroDocumento');
let nacimientoPost = document.querySelector('#nacimiento');
let vinculacionPost = document.querySelector('#vinculacion');
let cargoPost = document.querySelector('#cargo');
let salarioPost = document.querySelector('#salario');


let dataPost={
    nombre: nombrePost.value,
    apellidos: apellidoPost.value,
    tipoDocumento: documentoPost.value,
    numeroDocumento: numeroDocumentoPost.value,
    fechaNacimiento: nacimientoPost.value,
    fechaVinculacion: vinculacionPost.value,
    cargo: cargoPost.value,
    salario: salarioPost.value
}

console.log(dataPost);


    fetch('http://localhost:8080/empleados/save',{
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(dataPost),
    })
    .then(function(response){
        return response.json()
    })
    .then(function(data){
        console.log('Success:', data);
        /*confirmacionRegistro.innerHTML=`
        <h3>SE HA REGISTRADO A LA PERSONA CON ID: ${data.id}
        ` */
      })
      .catch((error) => {
        console.error('Error:', error);
      });
      
})








