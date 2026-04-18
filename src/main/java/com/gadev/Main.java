package com.gadev;

import com.gadev.model.Empleado;

import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Repositorio<Empleado> repo = new EmpleadoDAOImpl();

        System.out.println("=== 1. CREAR UN EMPLEADO ===");
        Empleado nuevoEmpleado = new Empleado("Ana Gomez", "Desarrolladora Java", 3500.00, LocalDate.now());
        repo.guardar(nuevoEmpleado);
        System.out.println("Empleado guardado en la base de datos.\n");

        System.out.println("=== 2. LISTAR TODOS LOS EMPLEADOS");
        repo.listarTodos().forEach(System.out::println);
        System.out.println();

        System.out.println("=== 3. ACTUALIZAR EMPLEADO con ID 1 ===");
        Empleado empleadoParaActualizar = repo.porId(1);
        if (empleadoParaActualizar != null){
            empleadoParaActualizar.setSalario(4200.00);
            empleadoParaActualizar.setPuesto("Senior Backend Developer");
            repo.guardar(empleadoParaActualizar);
            System.out.println("Empleado actualizado.");
            System.out.println(repo.porId(1));
        } else {
            System.out.println("No se encontro el empleado con ID 1");
        }
        System.out.println();

        System.out.println("=== 4. ELIMINAR EMPLEADO");

        System.out.println("Empleado eliminado");
        System.out.println("Total de empleados en BD: " + repo.listarTodos().size());



    }
}