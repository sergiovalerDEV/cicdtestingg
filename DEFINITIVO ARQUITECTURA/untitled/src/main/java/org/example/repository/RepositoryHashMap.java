package org.example.repository;

import org.example.entidades.Pelicula;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RepositoryHashMap implements Repository<Pelicula> {
    private HashMap<Integer, Pelicula> peliculasMap;

    public RepositoryHashMap() {
        this.peliculasMap = new HashMap<>();
    }

    @Override
    public ArrayList<Pelicula> getMovies() {
        return new ArrayList<>(peliculasMap.values());
    }

    @Override
    public Pelicula find(int id) {
        return peliculasMap.get(id);
    }

    @Override
    public ArrayList<Pelicula> findAll() {
        return getMovies();
    }

    @Override
    public int delete(int id) {
        if (peliculasMap.containsKey(id)) {
            peliculasMap.remove(id);

            // Reorganizar los IDs restantes de manera consecutiva
            int newId = 1;
            for (Pelicula pelicula : peliculasMap.values()) {
                pelicula.setId(newId++);
            }

            return 1; // Indica que se eliminó la película correctamente
        } else {
            return 0; // Indica que la película no se encontraba en el repositorio
        }
    }


    @Override
    public int update(int id, Pelicula pelicula) {
        if (peliculasMap.containsKey(id)) {
            peliculasMap.put(id, pelicula);
            return 1; // Indica que se actualizó la película correctamente
        } else {
            return 0; // Indica que la película no se encontraba en el repositorio

        }
    }

    @Override
    public int add(Pelicula pelicula) {
        if (!peliculasMap.containsKey(pelicula.getId())) {
            peliculasMap.put(pelicula.getId(), pelicula);
            return 1; // Indica que se agregó la película correctamente
        } else {
            System.out.println("Este ID de película no es válido");
            System.out.println();
            return 0; // Indica que ya existe una película con el mismo ID en el repositorio
        }
    }
}