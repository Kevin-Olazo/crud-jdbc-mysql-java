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
        String sql;
        // Si el empleado ya tiene un ID, significa que existe, entonces hacemos un UPDATE.
        // Si su ID es null (o 0), es un empleado nuevo, hacemos INSERT.
        if(empleado.getId() != null && empleado.getId() > 0){
            sql = "UPDATE empleados SET nombre=?, puesto=?, salario=?, fecha_contratacion=? WHERE id=?";
        } else {
            sql = "INSERT INTO empleados(nombre, puesto, salario, fecha_contratacion) VALUES (?,?,?,?)";
        }

        try (Connection conn = getConnection();
            PreparedStatement stmt =conn.prepareStatement(sql)){
            // Rellenar los '?' en orden
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2,empleado.getPuesto());
            stmt.setDouble(3, empleado.getSalario());
            // Convertir el LocalDate de Java al Date de SQL
            stmt.setDate(4, Date.valueOf(empleado.getFechaContratacion()));

            if (empleado.getId() != null && empleado.getId() > 0){
                stmt.setInt(5, empleado.getId());
            }

            stmt.executeUpdate(); // Ejecuta la accion (INSERT, UPDATE o DELETE)
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
