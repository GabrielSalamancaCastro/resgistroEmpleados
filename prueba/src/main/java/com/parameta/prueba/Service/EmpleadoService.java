package com.parameta.prueba.Service;

import com.parameta.prueba.Model.Empleado;
import com.parameta.prueba.Repository.IEmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements IEmpleadoService <Empleado>{

    /* ==================== ATRIBUTOS ==========================*/

    private IEmpleadoRepository empleadoRepository;

    /* ==================== CONSTRUCTOR ==========================*/

    public EmpleadoService(IEmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }


    /* ==================== METODOS DE LA INTERFACE ==========================*/
    @Override
    public List<Empleado> findAll() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    public Empleado save(Empleado empleado) {
        empleado.encontrarEdad();
        empleado.encontrarTiempoVinculacion();
        return empleadoRepository.save(empleado);
    }

    @Override
    public String delete(Long id) {
        if(findById(id).isPresent()){
            empleadoRepository.deleteById(id);
            return "Empleado con el id: " +id + "ha sido eliminado";
        }else{
            return "Empleado no existe";
        }
    }

    @Override
    public String update(Empleado empleado) {
        Empleado empleadoUpdate = empleadoRepository.findById(empleado.getId()).get();
        empleadoUpdate.setNombre(empleado.getNombre());
        empleadoUpdate.setApellidos(empleado.getApellidos());
        empleadoUpdate.setTipoDocumento(empleado.getTipoDocumento());
        empleadoUpdate.setNumeroDocumento(empleado.getNumeroDocumento());
        empleadoUpdate.setFechaNacimiento(empleado.getFechaNacimiento());
        empleadoUpdate.setFechaVinculacion(empleado.getFechaVinculacion());
        empleadoUpdate.setCargo(empleado.getCargo());
        empleadoUpdate.setSalario(empleado.getSalario());

        empleadoRepository.save(empleadoUpdate);

        return "El paciente con id: " + empleado.getId() + "se ha actualizado";
    }

    /* ==================== OTROS METODOS ==========================*/

    public Empleado findEmpleadoByDocumento(String documento){
        return empleadoRepository.findEmpleadoByDocumento(documento);
    }





}
