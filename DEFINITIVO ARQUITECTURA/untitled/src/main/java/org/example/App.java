package org.example;

import org.example.entidades.Pelicula;
import org.example.repository.*;
import org.example.services.ServicePeliculas;
import org.example.utils.MotorMySQL;
import org.example.utils.MotorSQL;
import org.example.utils.MotorSQLPostgre;

import java.util.List;

public class App {
    //ESTO DEBE EJECUTARSE UNA SOLA VEZ Y LUEGO BORRAR TODOS LOS DREGISTROS CREADOS Y ALTERAR LAS TABLAS DE BASES DE DATOS DE DIFERENTE MANERA
    //SINO LOS REGISTROS SE COLAPSARÁN Y NO FUNCIONARÁ
    //DEBEMOS INPLEMENTAR UNA FORMA DE, EN CADA CASO, UPDATEAR TDO PARA QUE VUELVA A SU ESTADO ORIGINAL, SI ES QUE SE PUEDE

    public static void main(String[] args) {
        // Caso 1: Acceso a datos desde ArrayList
        Repository<Pelicula> repArrayList = new RepositoryArrayList();
        ServicePeliculas myServiceArrayList = new ServicePeliculas(repArrayList);

        // Añadir 5 películas al ArrayList
        for (int i = 1; i <= 5; i++) {
            myServiceArrayList.agregarPelicula(new Pelicula(i, "Pulp Fiction", "Descripción" + i));
        }

        System.out.println("Películas desde ArrayList añadiendo 5 películas genéricas:");
        for (Pelicula pelicula : myServiceArrayList.leerPeliculas()) {
            System.out.println("\u001B[33m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Actualizar películas en el ArrayList
        myServiceArrayList.actualizarPelicula(1, new Pelicula(1, "Titanic", "Un romance épico en el trágico hundimiento del Titanic"));
        myServiceArrayList.actualizarPelicula(2, new Pelicula(2, "Inception", "Un thriller de ciencia ficción sobre los sueños"));
        myServiceArrayList.actualizarPelicula(3, new Pelicula(3, "The Godfather", "Una épica de crimen y drama familiar"));
        myServiceArrayList.actualizarPelicula(4, new Pelicula(4, "The Shawshank Redemption", "Un drama carcelario basado en una novela de Stephen King"));
        myServiceArrayList.actualizarPelicula(5, new Pelicula(5, "Interstellar", "Una odisea espacial sobre amor y humanidad"));

        System.out.println("Películas desde ArrayList actualizando 5 películas reales:");
        for (Pelicula pelicula : myServiceArrayList.leerPeliculas()) {
            System.out.println("\u001B[33m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Eliminar una película del ArrayList
        myServiceArrayList.eliminarPelicula(3);

        System.out.println("Películas desde ArrayList tras haber eliminado una de ellas:");
        for (Pelicula pelicula : myServiceArrayList.leerPeliculas()) {
            System.out.println("\u001B[33m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();
        myServiceArrayList.agregarPelicula(new Pelicula(1, "Pelicula 1", "Descripción 1"));//Añadimos película para forzar el mensaje de error del ArrayList
        //Forzamos el mensaje de error en este caso



        //Caso 2: Acceso a datos desde HashMap
        Repository<Pelicula> repHashMap = new RepositoryHashMap();
        ServicePeliculas myServiceHashMap = new ServicePeliculas(repHashMap);

        // Añadir películas al HashMap
        for (int i = 1; i <= 5; i++) {
            myServiceHashMap.agregarPelicula(new Pelicula(i, "The Matrix", "Descripción" + i));
        }

        System.out.println("Películas desde HashMap añadiendo 5 películas de acción:");
        for (Pelicula pelicula : myServiceHashMap.leerPeliculas()) {
            System.out.println("\u001B[34m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Actualizar películas en el HashMap
        myServiceHashMap.actualizarPelicula(1, new Pelicula(1, "Titanic", "Drama"));
        myServiceHashMap.actualizarPelicula(2, new Pelicula(2, "The Notebook", "Romance"));
        myServiceHashMap.actualizarPelicula(3, new Pelicula(3, "La La Land", "Musical"));
        myServiceHashMap.actualizarPelicula(4, new Pelicula(4, "Forrest Gump", "Drama"));
        myServiceHashMap.actualizarPelicula(5, new Pelicula(5, "Die Hard", "Acción"));

        System.out.println("Películas desde HashMap actualizando géneros de 5 películas:");
        for (Pelicula pelicula : myServiceHashMap.leerPeliculas()) {
            System.out.println("\u001B[34m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Eliminar una película del HashMap
        myServiceHashMap.eliminarPelicula(300);
        System.out.println("Películas desde HashMap tras haber eliminado una película:");
        for (Pelicula pelicula : myServiceHashMap.leerPeliculas()) {
            System.out.println("\u001B[34m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        myServiceHashMap.agregarPelicula(new Pelicula(1, "Pelicula 1", "Descripción 1"));//Añadimos película para forzar el mensaje de error del HashMap*/
        //Forzamos el error en el HashMap

        // Caso 3: Acceso a datos desde Base de Datos PostgreSQL
        /*MotorSQL motorSQLPostgre = new MotorSQLPostgre();
        Repository<Pelicula> repPostgre = new RepositoryDB(motorSQLPostgre);
        ServicePeliculas myServicePostgre = new ServicePeliculas(repPostgre);


        // Añadir 5 películas a la base de datos PostgreSQL
        for (int i = 1; i <= 5; i++) {
            myServicePostgre.agregarPelicula(new Pelicula(i, "Pulp Fiction", "Descripción" + i));
        }

        System.out.println("Películas desde PostgreSQL añadiendo 5 películas genéricas:");
        for (Pelicula pelicula : myServicePostgre.leerPeliculas()) {
            System.out.println("\u001B[35m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Actualizar películas en la base de datos PostgreSQL
        myServicePostgre.actualizarPelicula(1, new Pelicula(1, "Titanic", "Un romance épico en el trágico hundimiento del Titanic"));
        myServicePostgre.actualizarPelicula(2, new Pelicula(2, "Inception", "Un thriller de ciencia ficción sobre los sueños"));
        myServicePostgre.actualizarPelicula(3, new Pelicula(3, "The Godfather", "Una épica de crimen y drama familiar"));
        myServicePostgre.actualizarPelicula(4, new Pelicula(4, "The Shawshank Redemption", "Un drama carcelario basado en una novela de Stephen King"));
        myServicePostgre.actualizarPelicula(5, new Pelicula(5, "Interstellar", "Una odisea espacial sobre amor y humanidad"));

        System.out.println("Películas desde PostgreSQL actualizando 5 películas reales:");
        for (Pelicula pelicula : myServicePostgre.leerPeliculas()) {
            System.out.println("\u001B[35m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Eliminar una película de la base de datos PostgreSQL
        myServicePostgre.eliminarPelicula(3);

        System.out.println("Películas desde PostgreSQL tras haber eliminado una de ellas:");
        for (Pelicula pelicula : myServicePostgre.leerPeliculas()) {
            System.out.println("\u001B[35m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();/* */

        // Caso 4: Acceso a datos desde Base de Datos Local (MySQL)
        MotorSQL motorSQLLocal = new MotorMySQL(); // Debes proporcionar un objeto MotorSQL válido
        Repository<Pelicula> repLocal = new RepositoryDB(motorSQLLocal);
        ServicePeliculas myServiceLocal = new ServicePeliculas(repLocal);


        try {
            // Añadir 5 películas a la base de datos local MySQL
            for (int i = 1; i <= 5; i++) {
                myServiceLocal.agregarPelicula(new Pelicula(i, "Pulp Fiction", "Descripción" + i));
            }

            System.out.println("Películas desde Base de Datos Local (MySQL) añadiendo 5 películas genéricas:");
            for (Pelicula pelicula : myServiceLocal.leerPeliculas()) {
                System.out.println("\u001B[36m" + pelicula.toString() + "\u001B[0m");
            }
            System.out.println();

            // Actualizar películas en la base de datos local MySQL
            myServiceLocal.actualizarPelicula(1, new Pelicula(1, "Titanic", "Un romance épico en el trágico hundimiento del Titanic"));
            myServiceLocal.actualizarPelicula(2, new Pelicula(2, "Inception", "Un thriller de ciencia ficción sobre los sueños"));
            myServiceLocal.actualizarPelicula(3, new Pelicula(3, "The Godfather", "Una épica de crimen y drama familiar"));
            myServiceLocal.actualizarPelicula(4, new Pelicula(4, "The Shawshank Redemption", "Un drama carcelario basado en una novela de Stephen King"));
            myServiceLocal.actualizarPelicula(5, new Pelicula(5, "Interstellar", "Una odisea espacial sobre amor y humanidad"));

            System.out.println("Películas desde Base de Datos Local (MySQL) actualizando 5 películas reales:");
            for (Pelicula pelicula : myServiceLocal.leerPeliculas()) {
                System.out.println("\u001B[36m" + pelicula.toString() + "\u001B[0m");
            }
            System.out.println();

            // Eliminar una película de la base de datos local MySQL
            myServiceLocal.eliminarPelicula(3);

            System.out.println("Películas desde Base de Datos Local (MySQL) tras haber eliminado una de ellas:");
            for (Pelicula pelicula : myServiceLocal.leerPeliculas()) {
                System.out.println("\u001B[36m" + pelicula.toString() + "\u001B[0m");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return; // Salir del método o función actual si ocurre un error
        }


        // Caso 5: Acceso a datos desde Fichero Local
        Repository<Pelicula> repFichero = new RepositoryFichero("Peliculas.txt");
        ServicePeliculas myServiceFichero = new ServicePeliculas(repFichero);

        // Añadir 5 películas al fichero local
        for (int i = 1; i <= 5; i++) {
            Pelicula nuevaPelicula = new Pelicula(i, "Película genérica", "Descripción" + i);
            myServiceFichero.agregarPelicula(nuevaPelicula);
        }

        System.out.println("Películas desde Fichero Local añadiendo 5 películas genéricas:");
        for (Pelicula pelicula : myServiceFichero.leerPeliculas()) {
            System.out.println("\u001B[37m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Actualizar películas en el fichero local
        myServiceFichero.actualizarPelicula(1, new Pelicula(1, "Titanic", "Un romance épico en el trágico hundimiento del Titanic"));
        myServiceFichero.actualizarPelicula(2, new Pelicula(2, "Inception", "Un thriller de ciencia ficción sobre los sueños"));
        myServiceFichero.actualizarPelicula(3, new Pelicula(3, "The Godfather", "Una épica de crimen y drama familiar"));
        myServiceFichero.actualizarPelicula(4, new Pelicula(4, "The Shawshank Redemption", "Un drama carcelario basado en una novela de Stephen King"));
        myServiceFichero.actualizarPelicula(5, new Pelicula(5, "Interstellar", "Una odisea espacial sobre amor y humanidad"));

        System.out.println("Películas desde Fichero Local actualizando 5 películas reales:");
        for (Pelicula pelicula : myServiceFichero.leerPeliculas()) {
            System.out.println("\u001B[37m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();

        // Eliminar una película del fichero local
        myServiceFichero.eliminarPelicula(3);

        System.out.println("Películas desde Fichero Local tras haber eliminado una de ellas:");
        for (Pelicula pelicula : myServiceFichero.leerPeliculas()) {
            System.out.println("\u001B[37m" + pelicula.toString() + "\u001B[0m");
        }
        System.out.println();
    }
}