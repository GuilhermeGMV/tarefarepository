package com.bcopstein.ex1biblioeca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioRepository implements IUsuarioRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Usuario> getAll(){
        List<Usuario> resp = this.jdbcTemplate.query("SELECT * from usuarios",
        (rs,rowNum)->
            new Usuario(rs.getInt("id"),rs.getString("nome"),
            rs.getString("data_nascimento")));
        return resp;
    }

}