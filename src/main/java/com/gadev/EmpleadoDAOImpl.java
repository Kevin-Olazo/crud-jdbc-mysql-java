package com.gadev;

import com.gadev.model.Empleado;

import java.sql.*;
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

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Empleado emp = mapearEmpleado(rs);
                empleados.add(emp);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return empleados;
    }

    @Override
    public Empleado porId(int id) {
        Empleado empleado = null;
        String sql = "SELECT * FROM empleados WHERE ID = ?"; // El '?' es un parametro seguro

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id); // Reemplaza el primer '?' con el ID

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    empleado = mapearEmpleado(rs);
                }
            }

        }  catch (SQLException e){
            e.printStackTrace();
        }
        return empleado;
    }

    @Override
    public void guardar(Empleado empleado) {

    }

    @Override
    public void eliminar(int id) {

    }

    // Metodo auxiliar
    private Empleado mapearEmpleado(ResultSet rs) throws SQLException{
        Empleado e = new Empleado();
        e.setId(rs.getInt("id"));
        e.setNombre(rs.getString("nombre"));
        e.setPuesto(rs.getString("puesto"));
        e.setSalario(rs.getDouble("salario"));
        e.setFechaContratacion(rs.getDate("fecha_contratacion").toLocalDate());
        return e;
    }
}
