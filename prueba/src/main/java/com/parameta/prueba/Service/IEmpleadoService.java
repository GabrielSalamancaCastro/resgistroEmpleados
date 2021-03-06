package com.parameta.prueba.Service;

import java.util.List;
import java.util.Optional;

public interface IEmpleadoService <T>{

    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T t);
    String delete(Long id);
    String update(T t);



}
