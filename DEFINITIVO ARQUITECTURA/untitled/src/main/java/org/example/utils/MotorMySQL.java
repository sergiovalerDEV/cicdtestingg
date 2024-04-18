package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorMySQL extends MotorSQL {
    private static final String URL = "jdbc:mysql://localhost:3306/intento";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static MotorMySQL instance;

    public MotorMySQL() {
        // Private constructor to prevent instantiation from outside
    }

    public static MotorMySQL getInstance() {
        if (instance == null) {
            synchronized (MotorMySQL.class) {
                if (instance == null) {
                    instance = new MotorMySQL();
                }
            }
        }
        return instance;
    }

    @Override
    public void connect() {
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al desconectar de la base de datos", e);
        }
    }

    @Override
    public ResultSet ejecutarQuery(String SQL) {
        try {
            st = conn.createStatement();
            rs = st.executeQuery(SQL);
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la consulta SQL", e);
        }
    }


    @Override
    public int ejecutarSentencia(String SQL) {
        try {
            st = conn.createStatement();
            return st.executeUpdate(SQL);
        } catch (SQLException e) {
            throw new RuntimeException("Error al ejecutar la sentencia SQL", e);
        }
    }

}