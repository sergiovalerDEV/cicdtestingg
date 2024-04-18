package org.example.services;

import org.example.entidades.Pelicula;
import org.example.repository.Repository;
import org.example.repository.RepositoryFichero;

import java.util.ArrayList;

public class ServicePeliculas {
    private Repository<Pelicula> myRepo;

    public ServicePeliculas(Repository<Pelicula> myRepo) {
        this.myRepo = myRepo;
    }

    public ArrayList<Pelicula> leerPeliculas() {
        return (ArrayList<Pelicula>) myRepo.getMovies();
    }

    public void agregarPelicula(Pelicula pelicula) {
        myRepo.add(pelicula);
    }

    public void actualizarPelicula(int id, Pelicula pelicula) {
        myRepo.update(id, pelicula);
    }

    public void eliminarPelicula(int id) {
        myRepo.delete(id);
    }



}
