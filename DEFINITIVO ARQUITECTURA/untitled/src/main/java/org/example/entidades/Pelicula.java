package org.example.entidades;

public class Pelicula {
    public static final String ID ="ID";
    private int id;
    public static final String TITULO ="TITULO";
    private String titulo;
    public static final String SINOPSIS ="SINOPSIS";
    private String sinopsis;

    public Pelicula() {
    }

    public Pelicula(int id, String titulo, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                '}';
    }
}
