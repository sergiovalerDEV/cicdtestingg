package org.example.utils;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public abstract class MotorSQL {
    protected Connection conn;
    protected Statement st;
    protected ResultSet rs;

    public abstract void connect();
    public abstract void disconnect();
    public abstract ResultSet ejecutarQuery(String SQL);
    public abstract int ejecutarSentencia(String SQL);

    // Función para reiniciar el contador de autoincremento en PostgreSQL y solo en PostgreSQL
}
