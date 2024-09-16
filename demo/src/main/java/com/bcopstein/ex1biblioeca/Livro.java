package com.bcopstein.ex1biblioeca;

public class Livro {
    private long id;
    private String titulo;
    private String autor;
    private int ano;
    private long userId;

    public Livro(long id, String titulo, String autor, int ano, long userId) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAno() {
        return ano;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        if(this.userId == -1)
            return "Livro [ano=" + ano + ", autor=" + autor + ", id=" + id + ", titulo=" + titulo + ", locação = Disponível]";
        else
            return "Livro [ano=" + ano + ", autor=" + autor + ", id=" + id + ", titulo=" + titulo + ", locação = Indisponível]";
    }
}