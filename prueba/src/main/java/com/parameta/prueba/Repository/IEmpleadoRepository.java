package com.parameta.prueba.Repository;

import com.parameta.prueba.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmpleadoRepository extends JpaRepository <Empleado, Long> {


    @Query("SELECT e FROM Empleado e WHERE e.numeroDocumento = ?1")
    Empleado findEmpleadoByDocumento(String documento);


}
