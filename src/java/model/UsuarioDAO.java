package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Usuario;

/*
-- Estrutura da tabela `usuarios`

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `cpf` varchar(14) NOT NULL,
  `senha` varchar(8) NOT NULL,
  `endereco` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

 */
public class UsuarioDAO {

    public void Inserir(Usuario usuario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO usuarios (nome, cpf, endereco, senha)"
                    + " VALUES (?,?,?,?)");
            sql.setString(1, usuario.getNome());
            sql.setString(2, usuario.getCpf());
            sql.setString(3, usuario.getEndereco());
            sql.setString(4, usuario.getSenha());
            //sql.setString(5, usuario.getStatus());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    public Usuario getUsuario(int id) {
        Conexao conexao = new Conexao();
        try {
            Usuario usuario = new Usuario();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM usuarios WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    usuario.setId(Integer.parseInt(resultado.getString("id")));
                    usuario.setNome(resultado.getString("nome"));
                    usuario.setCpf(resultado.getString("cpf"));
                    usuario.setEndereco(resultado.getString("endereco"));
                    usuario.setSenha(resultado.getString("senha"));
                    usuario.setStatus(resultado.getString("aprovado"));
                }
            }
            
            return usuario;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public void Alterar(Usuario Usuario) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE usuarios SET nome = ?, cpf = ?, endereco = ?, senha = ?, aprovado = ? WHERE ID = ?");
            sql.setString(1, Usuario.getNome());
            sql.setString(2, Usuario.getCpf());
            sql.setString(3, Usuario.getEndereco());
            sql.setString(4, Usuario.getSenha());
            sql.setString(5, Usuario.getStatus());
            sql.setInt(6, Usuario.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }


    public void Excluir(Usuario Usuario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM usuarios WHERE ID = ? ");
            sql.setInt(1, Usuario.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Usuario> ListaDeUsuarios() {
        ArrayList<Usuario> meusUsuarios = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM usuarios order by nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Usuario usuario = new Usuario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("SENHA"));
                    usuario.setId(Integer.parseInt(resultado.getString("id")));
                    meusUsuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeUsuarios) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusUsuarios;
    }

    public Usuario Logar(Usuario usuario) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM usuarios WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, usuario.getCpf());
            sql.setString(2, usuario.getSenha());
            ResultSet resultado = sql.executeQuery();
            Usuario usuarioObtido = new Usuario();
            if (resultado != null) {
                while (resultado.next()) {
                    usuarioObtido.setId(Integer.parseInt(resultado.getString("ID")));
                    usuarioObtido.setNome(resultado.getString("NOME"));
                    usuarioObtido.setCpf(resultado.getString("CPF"));
                    usuarioObtido.setEndereco(resultado.getString("ENDERECO"));
                    usuarioObtido.setSenha(resultado.getString("SENHA"));
                    usuarioObtido.setStatus(resultado.getString("APROVADO"));
                }
            }
            return usuarioObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Usuario> getUsuarioNaoAprovado() {
        ArrayList<Usuario> meusUsuariosNaoAprovados = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM usuarios WHERE aprovado = 'N'";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {

                while (resultado.next()) {
                    Usuario usuario = new Usuario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("SENHA"));
                    usuario.setId(resultado.getInt("id"));
                    meusUsuariosNaoAprovados.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeUsuarios) incorreta", e);
        } finally {
            conexao.closeConexao();
        }
        return meusUsuariosNaoAprovados;
    }
    
    public ArrayList<Usuario> getUsuarioAprovado() {
        ArrayList<Usuario> meusUsuariosAprovados = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM usuarios WHERE aprovado = 'S'";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {

                while (resultado.next()) {
                    Usuario usuario = new Usuario(resultado.getString("NOME"),
                            resultado.getString("CPF"),
                            resultado.getString("ENDERECO"),
                            resultado.getString("SENHA"));
                    usuario.setId(resultado.getInt("id"));
                    meusUsuariosAprovados.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query de select (ListaDeUsuarios) incorreta", e);
        } finally {
            conexao.closeConexao();
        }
        return meusUsuariosAprovados;
    }


}