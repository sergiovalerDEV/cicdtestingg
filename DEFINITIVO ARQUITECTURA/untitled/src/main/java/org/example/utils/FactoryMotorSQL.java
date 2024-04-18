package org.example.utils;

public class FactoryMotorSQL {
    public enum TipoMotor {
        MYSQL, POSTGRESQL
    }

    public static MotorSQL getMotorSQL(TipoMotor tipoMotor) {
        switch (tipoMotor) {
            case MYSQL:
                return MotorMySQL.getInstance();
            case POSTGRESQL:
                return MotorSQLPostgre.getInstance();
            default:
                throw new IllegalArgumentException("Error, elige un tipo de motor adecuado");
        }
    }
}