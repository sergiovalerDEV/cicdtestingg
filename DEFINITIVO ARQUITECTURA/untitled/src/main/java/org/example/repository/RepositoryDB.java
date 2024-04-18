package org.example.repository;

import org.example.entidades.Pelicula;
import org.example.utils.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RepositoryDB implements Repository<Pelicula> {
    private static final String SQL_SELECT = "SELECT * FROM pelicula;";
    private MotorSQL motorSQL;

    public RepositoryDB(MotorSQL motorSQL) {
        this.motorSQL = motorSQL;
    }

    @Override
    public ArrayList<Pelicula> getMovies() {
        ArrayList<Pelicula> lstPeliculas = new ArrayList<>();
        try {
            motorSQL.connect();
            ResultSet rs = motorSQL.ejecutarQuery(SQL_SELECT);
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String sinopsis = rs.getString("sinopsis");

                Pelicula pelicula = new Pelicula(id, titulo, sinopsis);
                lstPeliculas.add(pelicula);
            }
            motorSQL.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lstPeliculas;
    }

    @Override
    public Pelicula find(int id) {
        try {
            motorSQL.connect();
            String sql = SQL_SELECT + " WHERE id = " + id;
            ResultSet rs = motorSQL.ejecutarQuery(sql);
            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String sinopsis = rs.getString("sinopsis");
                return new Pelicula(id, titulo, sinopsis);
            }
            motorSQL.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Pelicula> findAll() {
        return getMovies();
    }

    @Override
    public int delete(int id) {
        try {
            motorSQL.connect();

            // Eliminar la película con el ID dado
            String deleteSql = "DELETE FROM pelicula WHERE id = " + id;
            motorSQL.ejecutarSentencia(deleteSql);

            // Consultar todas las películas ordenadas por ID
            String querySql = "SELECT id FROM pelicula ORDER BY id";
            ResultSet resultSet = motorSQL.ejecutarQuery(querySql);

            int nuevoID = 1;

            // Actualizar los IDs de las películas restantes
            while (resultSet.next()) {
                int idActual = resultSet.getInt("id");
                String updateSql = "UPDATE pelicula SET id = " + nuevoID + " WHERE id = " + idActual;
                motorSQL.ejecutarSentencia(updateSql);
                nuevoID++;
            }

            motorSQL.disconnect();
            return 1; // Retorna 1 si se eliminó la película con éxito y se reestructuraron los IDs
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int update(int id, Pelicula pelicula) {
        try {
            motorSQL.connect();
            String sql = "UPDATE pelicula SET titulo = '" + pelicula.getTitulo() +
                    "', sinopsis = '" + pelicula.getSinopsis() + "' WHERE id = " + id;
            motorSQL.ejecutarSentencia(sql);
            motorSQL.disconnect();
            return 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int add(Pelicula pelicula) {
        try {
            motorSQL.connect();

            // Verificar si ya existe un ID de película igual en la base de datos
            String query = "SELECT * FROM pelicula WHERE id = " + pelicula.getId();
            ResultSet resultSet = motorSQL.ejecutarQuery(query);

            // Si se encontró una película con el mismo ID, mostrar un mensaje de error
            if (resultSet.next()) {
                System.out.println("Error: Ya existe una película con el mismo ID en la base de datos.");
                System.out.println();
                return 0; // Indica que no se pudo agregar la película
            }

            // Si el ID de la película es único, insertarla en la base de datos
            String sql = "INSERT INTO pelicula (id, titulo, sinopsis) VALUES (" +
                    pelicula.getId() + ", '" + pelicula.getTitulo() + "', '" + pelicula.getSinopsis() + "')";
            motorSQL.ejecutarSentencia(sql);

            motorSQL.disconnect();
            return 1; // Indica que se agregó la película correctamente
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}