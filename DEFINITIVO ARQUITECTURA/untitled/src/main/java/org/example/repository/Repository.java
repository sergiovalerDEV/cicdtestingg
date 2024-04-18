package org.example.repository;

import org.example.entidades.Pelicula;

import java.util.ArrayList;
import java.util.List;

public interface Repository<T> { // GENÉRICOS
    public List<Pelicula> getMovies();
    // Método para buscar un objeto por su ID
    public T find(int id);
    // Método para obtener todos los objetos en el repositorio
    public ArrayList<T> findAll();
    // Método para eliminar un objeto por su ID
    public int delete(int id);
    // Método para actualizar un objeto por su ID
    public int update(int id, T object);
    // Método para agregar un nuevo objeto al repositorio
    public int add(T object);
}