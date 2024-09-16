package com.bcopstein.ex1biblioeca;

import java.util.List;

public interface IAcervoRepository {
    List<Livro> getAll();
    List<String> getTitulos();
    List<String> getAutores();
    List<Livro> getLivrosDoAutor(String autor);
    Livro getLivroTitulo(String titulo);
    boolean cadastraLivroNovo(Livro livro);
    boolean removeLivro(long codigo);
    boolean retiraLivro(long livroId, long userId);
    boolean devolveLivro(long livroId, long userId);
    List<Livro> getLivrosUsuario(long userId);
}
