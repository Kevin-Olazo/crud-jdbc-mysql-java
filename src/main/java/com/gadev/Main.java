package com.gadev;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try{
           ConexionDB.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}