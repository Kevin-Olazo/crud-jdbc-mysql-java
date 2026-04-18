package com.gadev.model;

import java.time.LocalDate;

public class Empleado {
    private int id;
    private String nombre;
    private String puesto;
    private double salario;
    private LocalDate fechaContratacion;

    public Empleado() {
    }

    public Empleado(int id, String nombre, String puesto, double salario, LocalDate fechaContratacion) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public Empleado(String nombre, String puesto, double salario, LocalDate fechaContratacion) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaContratacion = fechaContratacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    @Override
    public String toString() {
        return "Empleado[" +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", puesto: '" + puesto + '\'' +
                ", salario: " + salario +
                ", fecha de Contratacion: " + fechaContratacion +
                ']';
    }
}
