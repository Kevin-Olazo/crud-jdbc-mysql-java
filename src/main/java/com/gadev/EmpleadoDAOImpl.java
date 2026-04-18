package com.gadev;

import com.gadev.model.Empleado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAOImpl implements Repositorio<Empleado>{

    private Connection getConnection() throws SQLException{
        return ConexionDB.getInstance();
    }

    @Override
    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";


        return empleados;
    }

    @Override
    public Empleado porId(int id) {
        return null;
    }

    @Override
    public void guardar(Empleado empleado) {

    }

    @Override
    public void eliminar(int id) {

    }
}
