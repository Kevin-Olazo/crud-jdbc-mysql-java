package com.gadev;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDB {

    private static Connection conexion; // Guarda la conexion unica
    private static final Properties properties = new Properties(); // Guarda la config

    // 1. Leer el archivo UNA sola vez al arrancar el programa
    static {
        try (InputStream input = ConexionDB.class.getClassLoader().getResourceAsStream("database.properties")){
            if (input == null){
                throw new RuntimeException("No se encontro el archivo database.properties");
            }
            properties.load(input);
        } catch (IOException e){
            throw new RuntimeException("Error al cargar database.properties: " + e.getMessage(), e);
        }
    }

    private ConexionDB(){}; // Constructor privado para evitar instancias

    // 2. Entrega la conexion (Reutilizando la misma siempre que sea posible)
    public static Connection getInstance() throws SQLException {
        // Solo abrimos una nueva si no existe, o si se cerro por error de red
        if(conexion == null || conexion.isClosed()){
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("¡Error! El driver de MySQL no está en el Classpath.", e);
            }

            conexion = DriverManager.getConnection(url, user, password);
            System.out.println("Conexion a la base de datos establecida con exito!");
        }
        return conexion;
    }

}
