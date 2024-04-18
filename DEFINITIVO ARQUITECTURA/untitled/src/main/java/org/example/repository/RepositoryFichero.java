package org.example.repository;

import org.example.entidades.Pelicula;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryFichero implements Repository<Pelicula> {
    private String filePath;

    public RepositoryFichero(String path) {
        try {
            String project = System.getProperty("user.dir");
            this.filePath = project + "\\src\\main\\java\\org\\example\\" + path;
        } catch (Exception e) {
            throw new RuntimeException("Error al construir la ruta del archivo", e);
        }
    }

    @Override
    public List<Pelicula> getMovies() {
        return findAll();
    }

    @Override
    public Pelicula find(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pelicula pelicula = parsePeliculaFromLine(line);
                if (pelicula != null && pelicula.getId() == id) {
                    return pelicula;
                }
            }
        } catch (IOException e) {
            handleIOException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Pelicula> findAll() {
        List<Pelicula> peliculas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Pelicula pelicula = parsePeliculaFromLine(line);
                if (pelicula != null) {
                    peliculas.add(pelicula);
                }
            }
        } catch (IOException e) {
            handleIOException(e);
        }
        return (ArrayList<Pelicula>) peliculas;
    }

    @Override
    public int delete(int id) {
        List<Pelicula> peliculas = findAll();
        boolean encontrada = false;

        // Buscar y eliminar la película con el ID dado
        for (int i = 0; i < peliculas.size(); i++) {
            if (peliculas.get(i).getId() == id) {
                peliculas.remove(i);
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            return 0; // No se encontró la película con el ID dado
        }

        // Reorganizar los IDs restantes de manera consecutiva
        for (int i = 0; i < peliculas.size(); i++) {
            peliculas.get(i).setId(i + 1);
        }

        // Guardar las películas actualizadas en el archivo
        if (saveToFile(peliculas)) {
            return 1; // Eliminación exitosa
        } else {
            return -1; // Error al guardar en el archivo
        }
    }


    @Override
    public int update(int id, Pelicula pelicula) {
        List<Pelicula> peliculas = findAll();
        for (int i = 0; i < peliculas.size(); i++) {
            if (peliculas.get(i).getId() == id) {
                peliculas.set(i, pelicula);
                if (saveToFile(peliculas)) {
                    return 1; // Actualización exitosa
                } else {
                    return -1; // Error al guardar en el archivo
                }
            }
        }
        return 0; // No se encontró la película con el ID dado
    }

    @Override
    public int add(Pelicula pelicula) {
        List<Pelicula> peliculas = findAll();

        // Verificar si la película ya existe en la lista
        for (Pelicula p : peliculas) {
            if (p.getId() == pelicula.getId()) {
                System.out.println("Ya existe una película con el mismo ID en el repositorio");
                System.out.println();
                return 0; // Indica que ya existe una película con el mismo ID en el repositorio
            }
        }

        peliculas.add(pelicula);
        if (saveToFile(peliculas)) {
            return 1; // Adición exitosa
        } else {
            return -1; // Error al guardar en el archivo
        }
    }


    private boolean saveToFile(List<Pelicula> peliculas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Pelicula pelicula : peliculas) {
                writer.write(pelicula.getId() + ",\"" + pelicula.getTitulo() + "\",\"" + pelicula.getSinopsis() + "\"");
                writer.newLine();
            }
            return true; // Guardado exitoso
        } catch (IOException e) {
            handleIOException(e);
            return false; // Error al guardar en el archivo
        }
    }

    private Pelicula parsePeliculaFromLine(String line) {
        try {
            line = line.trim();
            String[] parts = line.split(",");
            if (parts.length <3) {
                throw new IllegalArgumentException("La línea no tiene el formato esperado: " + line);
            }
            int id = Integer.parseInt(parts[0].trim());
            String titulo = parts[1].trim();
            String sinopsis = parts[2].trim();
            titulo = titulo.replaceAll("^\"|\"$", "");
            sinopsis = sinopsis.replaceAll("^\"|\"$", "");
            return new Pelicula(id, titulo, sinopsis);
        } catch (NumberFormatException e) {
            handleNumberFormatException(line, e);
            return null;
        } catch (IllegalArgumentException e) {
            handleIllegalArgumentException(e);
            return null;
        }
    }

    private void handleIOException(IOException e) {
        System.err.println("Error de E/S al acceder al archivo: " + e.getMessage());
        e.printStackTrace();
    }

    private void handleNumberFormatException(String line, NumberFormatException e) {
        System.err.println("Error al convertir el ID de la película. Línea no válida: " + line);
        e.printStackTrace();
    }

    private void handleIllegalArgumentException(IllegalArgumentException e) {
        System.err.println(e.getMessage());
    }
}
