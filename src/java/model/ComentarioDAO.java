package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Comentario;

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
                    comentario.setData(resultado.getString("DATA"));
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO comentarios (cometario, data, idusuario) VALUES (?,?,?)");
            sql.setString(1, t.getComentario());
            sql.setString(2, t.getData());
            sql.setInt(3, t.getIdusuario());
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
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO comentarios (id, comentario, data, idcategoria, idusuario)"
                    + " VALUES (?,?,?,?,?)");
            sql.setInt(1, comentario.getId());
            sql.setString(2, comentario.getComentario());
            sql.setDate(3, comentario.getData());
            sql.setInt(4, comentario.getIdcategoria());
            sql.setInt(5, comentario.getIdusuario());
            sql.executeUpdate();

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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE comentarios SET cometario = ?, data = ?, idusuario = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, t.getComentario());
            sql.setString(2, t.getData());
            sql.setInt(3, t.getIdusuario());

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
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE comentarios SET cometario = ?, data = ?, idusuario = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, comentario.getComentario());
            sql.setString(2, comentario.getData());
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
                            resultado.getString("data"),
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
    
    public ArrayList<Comentario> getUser() {

        ArrayList<Comentario> meuUsuario = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM comentarios WHERE idusuario = ? ";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Comentario Comentario = new Comentario(
                            resultado.getInt("id"),
                            resultado.getString("comentario"),
                            resultado.getString("data"),
                            resultado.getInt("idcategoria"),
                            resultado.getInt("idusuario")                     
                    );
                    meuUsuario.add(Comentario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (GetUser) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meuUsuario;
    }
}
