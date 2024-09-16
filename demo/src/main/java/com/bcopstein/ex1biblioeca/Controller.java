package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @Autowired
    private IAcervoRepository livros;

    @Autowired
    private IUsuarioRepository usuarios;



    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return livros.getAll();
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return livros.getAutores();
    }

    @GetMapping("livrosAutor")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@RequestParam(value = "autor") String autor) {
        return livros.getLivrosDoAutor(autor);
    }

    @GetMapping("/livrosautor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoAutor(@PathVariable(value="autor") String autor, @PathVariable(value="ano")int ano) {
        return livros.getLivrosDoAutor(autor)
                .stream()
                .filter(l->l.getAno() == ano)
                .toList();
    }

    @PostMapping("/novolivro")
    @CrossOrigin(origins = "*")
    public boolean cadastraLivroNovo(@RequestBody final Livro livro) {
        return livros.cadastraLivroNovo(livro);
    }

    @GetMapping("usuarios")
    @CrossOrigin(origins = "*")
    public List<Usuario> getListaUsuarios() {
        return usuarios.getAll();
    }

    @PostMapping("/retirarlivro")
    @CrossOrigin(origins = "*")
    public boolean retiraLivro(@RequestParam(value = "livroId") long livroId, @RequestParam(value = "usuarioId") long usuarioId) {
        return livros.retiraLivro(livroId, usuarioId);
    }

    @PostMapping("/devolverlivro")
    @CrossOrigin(origins = "*")
    public boolean devolveLivro(@RequestParam(value = "livroId") long livroId, @RequestParam(value = "usuarioId") long usuarioId) {
        return livros.devolveLivro(livroId, usuarioId);
    }

    @GetMapping("/livros/user/{userId}")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDoUsuario(@PathVariable(value = "userId") long userId) {
        return livros.getLivrosUsuario(userId);
    }

    @GetMapping("/livros/disponiveis")
    @CrossOrigin(origins = "*")
    public List<Livro> getLivrosDisponiveis() {
        return livros.getAll()
                .stream()
                .filter(l -> l.getUserId() == -1)
                .toList();
    }
}