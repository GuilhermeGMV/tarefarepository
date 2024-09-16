package com.bcopstein.ex1biblioeca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AcervoRepository implements IAcervoRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AcervoRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Livro> getAll(){
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros",
        (rs,rowNum)->
            new Livro(rs.getInt("id"),rs.getString("titulo"),
            rs.getString("autor"),rs.getInt("ano"), rs.getInt("userId")));
        return resp;
    }

    @Override
    public List<String> getTitulos(){
        List<String> resp = this.jdbcTemplate.query("SELECT titulo from livros",
        (rs,rowNum)->
            rs.getString("titulo"));
        return resp;
    }

    @Override
    public List<String> getAutores(){
        List<String> resp = this.jdbcTemplate.query("SELECT autor from livros",
        (rs,rowNum)->
            rs.getString("autor"));
        return resp;
    }

    @Override
    public List<Livro> getLivrosDoAutor(String autor) {
        String sql = "SELECT * FROM livros WHERE autor = :autor";
        Map<String, Object> params = new HashMap<>();
        params.put("autor", autor);

        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) ->
            new Livro(rs.getInt("id"), rs.getString("titulo"),
            rs.getString("autor"), rs.getInt("ano"), rs.getInt("userId")));
    }

    @Override
    public Livro getLivroTitulo(String titulo) {
        String sql = "SELECT * FROM livros WHERE titulo = :titulo";
        Map<String, Object> params = new HashMap<>();
        params.put("titulo", titulo);

        return namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) ->
            new Livro(rs.getInt("id"), rs.getString("titulo"),
            rs.getString("autor"), rs.getInt("ano"), rs.getInt("userId")));
    }

    @Override
    public boolean removeLivro(long id){
        this.jdbcTemplate.update("DELETE FROM livros WHERE id = ?", id);
        return true;
    }

    @Override
    public boolean cadastraLivroNovo(Livro livro){
        this.jdbcTemplate.update(
            "INSERT INTO livros(id,titulo,autor,ano) VALUES (?,?,?,?)",
            livro.getId(),livro.getTitulo(),livro.getAutor(),livro.getAno());
            return true;
    }

    @Override
    public boolean retiraLivro(long livroId, long userId){
        this.jdbcTemplate.update(
            "UPDATE livros SET userId = ? WHERE id = ?",
            userId,livroId);
            return true;
    }

    @Override
    public boolean devolveLivro(long livroId, long userId){
        this.jdbcTemplate.update(
            "UPDATE livros SET userId = -1 WHERE id = ?",
            livroId);
            return true;
    }

    @Override
    public List<Livro> getLivrosUsuario(long userId){
        String sql = "SELECT * FROM livros WHERE userId = :userId";
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);

        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) ->
            new Livro(rs.getInt("id"), rs.getString("titulo"),
            rs.getString("autor"), rs.getInt("ano"), rs.getInt("userId")));
    }
}