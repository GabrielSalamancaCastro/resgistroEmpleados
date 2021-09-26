package com.parameta.prueba.Model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;


@Entity
@Table(name = "empleados")
public class Empleado {

    /* ==================== ATRIBUTOS ==========================*/

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "empleado_sequence")
    @SequenceGenerator(name = "empleado_sequence", sequenceName = "empleado_sequence")
    private Long id;

    private String nombre;
    private String apellidos;
    private String tipoDocumento;
    private String numeroDocumento;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private  LocalDate fechaVinculacion;
    private Integer tiempoVinculado;
    private String cargo;
    private Double salario;


    /* ==================== GETTERS AND SETTERS ==========================*/

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(LocalDate fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getTiempoVinculado() {
        return tiempoVinculado;
    }

    public void setTiempoVinculado(Integer tiempoVinculado) {
        this.tiempoVinculado = tiempoVinculado;
    }


    /* ==================== METODOS ==========================*/

    public void encontrarEdad(){
        setEdad(Period.between(getFechaNacimiento(),LocalDate.now()).getYears());
    }

    public  void encontrarTiempoVinculacion(){
        setTiempoVinculado(Period.between(getFechaVinculacion(),LocalDate.now()).getYears());
    }



    /* ==================== CONSTRUCTOR ==========================*/

    public Empleado(String nombre, String apellidos, String tipoDocumento, String numeroDocumento, LocalDate fechaNacimiento, LocalDate fechaVinculacion, String cargo, Double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaVinculacion = fechaVinculacion;
        this.cargo = cargo;
        this.salario = salario;
    }


    public Empleado() {}

}
