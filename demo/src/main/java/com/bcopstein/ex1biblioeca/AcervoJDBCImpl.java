import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AcervoJDBCImpl implements IAcervoRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AcervoJDBCImpl(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}

    @Override
    public List<Livro> getAll(){
        List<Livro> resp = this.jdbcTemplate.query("SELECT * from livros",
        (rs,rowNum)->
            new Livro(rs.getInt("codigo"),rs.getString("titulo"),rs.getString("autor"),rs.getInt("ano")));
        return resp;
    }

    @Override
    public boolean removeLivro(long codigo){
        this.jdbcTemplate.update("DELETE FROM livros WHERE codigo = ?", codigo);
        return true;
    }

    @Override
    public boolean cadastraLivroNovo(Livro livro){
        this.jdbcTemplate.update(
            "INSERT INTO livros(codigo,titulo,autor,ano) VALUES (?,?,?,?)",
            livro.codigo(),livro.titulo(),livro.autor(),livro.ano());
            return true;
    }