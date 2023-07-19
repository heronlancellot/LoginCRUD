package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Comentario;
import java.sql.Types;

/*
--
-- Estrutura da tabela `comentarios`
--

CREATE TABLE IF NOT EXISTS `comentarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comentario` varchar(255) NOT NULL,
  `data` date DEFAULT NULL,
  `idusuario` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario` (`idusuario`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

 */
public class ComentarioDAO implements Dao<Comentario> {

    @Override
    public Comentario get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM comentarios WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Comentario comentario = new Comentario();

            if (resultado != null) {
                while (resultado.next()) {
                    comentario.setId(Integer.parseInt(resultado.getString("ID")));
                    comentario.setComentario(resultado.getString("COMENTARIO"));
                    comentario.setData(resultado.getDate("DATA"));
                    comentario.setId(Integer.parseInt(resultado.getString("IDUSUARIO")));
                }
            }
            return comentario;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void insert(Comentario t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO comentarios (comentario, data, , idcategoria, idusuario) VALUES (?,?,?,?)");
           
            System.out.println(t.getComentario());
            System.out.println(t.getData().toString());
            System.out.println(t.getIdcategoria());
            System.out.println(t.getIdusuario());
            
            
            
            
            sql.setString(1, t.getComentario());
            sql.setDate(2, t.getData());
            sql.setInt(3, t.getIdcategoria());
            sql.setInt(4, t.getIdusuario());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de insert (comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
 public void Inserir(Comentario comentario) throws Exception {
    Conexao conexao = new Conexao();
    try {
      
        final String SQL = "INSERT INTO comentarios (id, comentario, data, idcategoria, idusuario) VALUES (?,?,?,?,?)";
        
        try (PreparedStatement sql = conexao.getConexao().prepareStatement(SQL)) {
            
            
            sql.setNull(1, Types.INTEGER);
            
           
            sql.setString(2, comentario.getComentario());
            
            java.util.Date utilDate = comentario.getData();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            sql.setDate(3, sqlDate);

            sql.setInt(4, comentario.getIdcategoria());
            sql.setInt(5, comentario.getIdusuario());
            
            sql.executeUpdate();
        }

    } catch (SQLException e) {
        throw new RuntimeException();
    } finally {
        conexao.closeConexao();
    }
}
    @Override
    public void update(Comentario t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE comentarios SET comentario = ?, data = ?, idcategoria = ?, idusuario = ?  WHERE ID = ? ");
            
            sql.setString(1, t.getComentario());
            sql.setDate(2, t.getData());
            sql.setInt(3, t.getIdcategoria());
            sql.setInt(4, t.getIdusuario());
            sql.setInt(5, t.getId());
          
            

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Comentario comentario) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE comentarios SET comentario = ?, data = ?, idusuario = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, comentario.getComentario());
            sql.setDate(2, comentario.getData());
            sql.setInt(3, comentario.getIdusuario());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Comentarios WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir comentario) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Comentario> getAll() {

        ArrayList<Comentario> meusComentarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM comentarios";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Comentario Comentario = new Comentario(resultado.getInt("id"),resultado.getString("comentario"),
                            resultado.getDate("data"),
                            resultado.getInt("idusuario"),
                            resultado.getInt("idcategoria")
                    );
                    meusComentarios.add(Comentario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (GetAll) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusComentarios;
    }
    
       public ArrayList<Comentario> getAll(int idusuario) {

        ArrayList<Comentario> meuUsuario = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            // Use um parâmetro na cláusula WHERE para filtrar pelo idusuario
            String selectSQL = "SELECT * FROM comentarios WHERE idusuario = ?";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            // Defina o valor do parâmetro com o idusuario que você quer filtrar
            preparedStatement.setInt(1, idusuario);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Comentario Comentario = new Comentario(
                            resultado.getInt("id"),
                            resultado.getString("comentario"),
                            resultado.getDate("data"),
                            resultado.getInt("idusuario"), 
                            resultado.getInt("idcategoria")

                    );
                    meuUsuario.add(Comentario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (GetAll) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meuUsuario;
    }
    
}
