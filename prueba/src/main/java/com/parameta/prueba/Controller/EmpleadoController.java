package com.parameta.prueba.Controller;


import com.parameta.prueba.Model.Empleado;
import com.parameta.prueba.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("empleados")
public class EmpleadoController {

    /* ==================== ATRIBUTOS ==========================*/
    private EmpleadoService empleadoService;

    /* ==================== CONSTRUCTOR ==========================*/

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    /* ==================== METODOS GET ==========================*/
    @GetMapping("/all")
    public List<Empleado> getAllEmpleados(){
        return empleadoService.findAll();
    }

    @GetMapping("/{id}")
    public Empleado getEmpleadoByID(@PathVariable Long id){
        return empleadoService.findById(id).orElse(null);
    }

    @GetMapping("/documento/{documento}")
    public Empleado getEmpleadoByDocumento(@PathVariable String documento){
        return empleadoService.findEmpleadoByDocumento(documento);
    }

    /* ==================== METODOS POST ==========================*/

    @PostMapping("/save")
    public ResponseEntity<?> saveEmpleado(@RequestBody Empleado empleado){

        ResponseEntity response;

        if (empleadoService.findEmpleadoByDocumento(empleado.getNumeroDocumento()) != null){
            response = new ResponseEntity("El empleado ya existe, cambielo por favor", HttpStatus.CONFLICT);
        }
        else if (Period.between(empleado.getFechaNacimiento(),LocalDate.now()).getYears()<18){
            response = new ResponseEntity("El empleado no puede ser menor de edad, verifique la edad suministrada", HttpStatus.NOT_ACCEPTABLE);
        }
        else if (empleado.getNombre().isEmpty() || empleado.getApellidos().isEmpty() || empleado.getNumeroDocumento().isEmpty() || empleado.getCargo().isEmpty()){
            response = new ResponseEntity( "Los campos no pueden estar vacios", HttpStatus.NOT_ACCEPTABLE);
        }
        else{
            //empleado.encontrarEdad();
            //empleado.encontrarTiempoVinculacion();
            response = new ResponseEntity(empleadoService.save(empleado),HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/test")
    public List<Empleado> test(){
        Empleado empleado1 = new Empleado("Juan","Gonzales","CC","1234567", LocalDate.of(1994,3,23),LocalDate.of(2015,2,1),"Desarrollador Back End Jr", 2500.00);
        Empleado empleado2 = new Empleado("Sergio","Rueda","CC","1234568", LocalDate.of(2000,9,15),LocalDate.of(2019,2,1),"DevOps", 5000.00);
        Empleado empleado3 = new Empleado("Kevin","Martinez","CC","1234569", LocalDate.of(1997,8,10),LocalDate.of(2018,2,1),"QA tester", 3500.00);
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(empleadoService.save(empleado1));
        empleados.add(empleadoService.save(empleado2));
        empleados.add(empleadoService.save(empleado3));
        return empleados;
    }

    /* ==================== METODO PUT ==========================*/

    @PutMapping("/update")
    public  ResponseEntity<?> updateEmpleado(@RequestBody Empleado empleado){
        ResponseEntity response;
        if(empleadoService.findById(empleado.getId()).isPresent()){
            response= new ResponseEntity(empleadoService.update(empleado), HttpStatus.OK);
        }else{
            response=new ResponseEntity("El empleado no existe",HttpStatus.NOT_FOUND);
        }
        return response;
    }

    /* ==================== METODO DELETE ==========================*/

    @DeleteMapping("/delete/{id}")
    public String deleteEmpleado(@PathVariable Long id){
        return empleadoService.delete(id);
    }


}
