
//METODO GET (LISTAR TODOS)
let listarTodos = document.querySelector('#botonListado');
let listaCompleta = document.querySelector('.listaPersonas');

listarTodos.addEventListener("click",function(event){
    event.preventDefault();
    fetch('http://localhost:8080/empleados/all')
    .then(function(response){
        return response.json();
    })
    .then(function(personas){
        personas.forEach((element) => {
            listaCompleta.innerHTML+=`
            <div id='personaRegistrada'>
            <p>ID: ${element.id}<p>
            <p>NOMBRE: ${element.nombre}<p>
            <p>APELLIDO: ${element.apellidos}</p>
            <p>TIPO DE DOCUMENTO: ${element.tipoDocumento}</p>
            <p>FECHA DE NACIMIENTO: ${element.fechaNacimiento}<p>
            <p>EDAD: ${element.edad}<p>
            <p>FECHA DE VINCULACION: ${element.fechaVinculacion}</p>
            <p>TIEMPO VINCULADO: ${element.tiempoVinculado}</p>
            <p>CARGO: ${element.cargo}</p>
            <p>SALARIO: ${element.salario}</p>
            ` 
        }); 
    })
    .catch(function(err){
        console.error(err);
    })
})


//METODO GET (GET BY ID)


let getPersonaByID = document.querySelector('#getByID');


getPersonaByID.addEventListener('click', function(event){
    event.preventDefault();

    let documentoEmpleado= document.querySelector('#id-persona').value;

    fetch(`http://localhost:8080/empleados/documento/${documentoEmpleado}`)
    .then(function(response){
       return response.json();
    }).then(function(persona){
        listaCompleta.innerHTML+=`
        <div id='personaRegistrada'>
        <p>ID: ${persona.id}<p>
        <p>NOMBRE: ${persona.nombre}<p>
        <p>APELLIDO: ${persona.apellidos}</p>
        <p>TIPO DE DOCUMENTO: ${persona.tipoDocumento}</p>
        <p>FECHA DE NACIMIENTO: ${persona.fechaNacimiento}<p>
        <p>EDAD: ${persona.edad}<p>
        <p>FECHA DE VINCULACION: ${persona.fechaVinculacion}</p>
        <p>TIEMPO VINCULADO: ${persona.tiempoVinculado}</p>
        <p>CARGO: ${persona.cargo}</p>
        <p>SALARIO: ${persona.salario}</p>
            <div id='botones'>
            <button id="eliminarPersona">ELIMINAR</button>
            <button id="procesarPersona">ACTUALIZAR</button>
            </div>
            </div>
            ` 
        
            //METODO DELETE
            let eliminar = document.querySelector("#eliminarPersona");

            eliminar.addEventListener("click", function(event){
            event.preventDefault();
                let confirmacionDelete=confirm("¿ESTAS SEGURO QUE QUIERES ELIMINAR ESTA PERSONA DEL SISTEMA?")
                if(confirmacionDelete){
                console.log("CLICKE EN ACEPTAR");
                fetch(`http://localhost:8080/empleados/delete/${persona.id}`,{
                    method: 'DELETE',
                })
                .then(function(response){
                    return response.json();
                }).then(function(data){
                    console.log('Success',data);
                }).catch(function(err){
                    console.error(err);
                })
                }
                });

                //METODO PUT
                let procesar=document.querySelector('#procesarPersona');

                procesar.addEventListener("click", function(event){
                    event.preventDefault();
                    let modal= document.querySelector('#myModal');
                    modal.style.display="block";
                    let actualizar = document.querySelector('#update')
                    actualizar.addEventListener("click",function(event){
                        event.preventDefault();
                        let nuevoCargo= document.querySelector('#nuevoCargo');
                        let nuevoSalario= document.querySelector('#nuevoSalario');
                        let confircionProcesar=confirm("¿DESEAS PROCESAR ESTA PERSONA?")
                        let procesarData={
                            id: persona.id,
                            nombre: persona.nombre,
                            apellidos: persona.apellidos,
                            tipoDocumento: persona.tipoDocumento,
                            numeroDocumento: persona.numeroDocumento,
                            fechaNacimiento: persona.fechaNacimiento,
                            edad: persona.edad,
                            fechaVinculacion: persona.fechaVinculacion,
                            tiempoVinculado: persona.tiempoVinculado,
                            cargo: nuevoCargo.value,
                            salario: nuevoSalario.value
                        }
                        if(confircionProcesar){
                            fetch('http://localhost:8080/empleados/update',{
                                method: 'PUT',
                                headers: {
                                    'Content-Type': 'application/json',
                                  },
                                  body: JSON.stringify(procesarData),
                                })
                                .then(function(response){
                                    return response.json()
                                })
                                .then(function(respuesta){
                                    console.log('Success:', respuesta);
                                  })
                                  .catch((error) => {
                                    console.error('Error:', error);
                                  });
                        }
                    })
                    

                })



    }).catch(function(err){
        console.error(err);
    })

})





