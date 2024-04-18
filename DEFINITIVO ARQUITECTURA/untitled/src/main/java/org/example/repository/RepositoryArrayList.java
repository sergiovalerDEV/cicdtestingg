package org.example.repository;

import org.example.entidades.Pelicula;

import java.util.ArrayList;

public class RepositoryArrayList implements Repository<Pelicula> {

    private ArrayList<Pelicula> movies;

    public RepositoryArrayList(){
        this.movies = new ArrayList<>();
    }

    @Override
    public ArrayList<Pelicula> getMovies() {
        return movies;
    }

    @Override
    public Pelicula find(int id) {
        for (Pelicula movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null; // Retorna null si no se encuentra la película con el ID dado
    }

    @Override
    public ArrayList<Pelicula> findAll() {
        return new ArrayList<>(movies); // Retorna una copia de la lista de películas
    }

    @Override
    public int delete(int id) {
        int indexToRemove = -1;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == id) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            movies.remove(indexToRemove);
            // Reorganizar los IDs
            for (int i = indexToRemove; i < movies.size(); i++) {
                movies.get(i).setId(i + 1);
            }
            return 1; // Retorna 1 si se eliminó la película con éxito
        } else {
            return 0; // Retorna 0 si no se encontró la película con el ID dado
        }
    }


    @Override
    public int update(int id, Pelicula object) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == id) {
                movies.set(i, object);
                return 1; // Retorna 1 si se actualizó la película con éxito
            }
        }
        return 0; // Retorna 0 si no se encontró la película con el ID dado
    }

    @Override
    public int add(Pelicula pelicula) {
        // Verificar si ya existe un ID de película igual en la lista
        for (Pelicula existingPelicula : movies) {
            if (existingPelicula.getId() == pelicula.getId()) {
                System.out.println("Error: Ya existe una película con el mismo ID en el repositorio.");
                System.out.println();
                return 0; // Indica que ya existe una película con el mismo ID en el repositorio
            }
        }

        // Si el ID de la película es único, agregarla a la lista
        movies.add(pelicula);
        return 1; // Indica que se agregó la película correctamente
    }
}