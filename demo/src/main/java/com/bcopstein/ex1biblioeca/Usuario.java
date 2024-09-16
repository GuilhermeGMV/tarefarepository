package com.bcopstein.ex1biblioeca;

public class Usuario {
    
    private long id;
    private String nome;
    private String dataNascimento;

    public Usuario(long id, String nome, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public long getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + "]";
    }
}
