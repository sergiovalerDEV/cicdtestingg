package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
/*import java.sql.PreparedStatement;*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MotorSQLPostgre extends MotorSQL {
    private static final String URL = "jdbc:postgresql://intento.cnecsgksyas4.us-east-1.rds.amazonaws.com/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "12345678";
    private static final String SSL = "false";


    private static MotorSQLPostgre instance;
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    public MotorSQLPostgre() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized MotorSQLPostgre getInstance() {
        if (instance == null) {
            instance = new MotorSQLPostgre();
        }
        return instance;
    }

    @Override
    public void connect() {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conectado a la base de datos!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    @Override
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                conn.close();
                System.out.println("Desconectado de la base de datos!");
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