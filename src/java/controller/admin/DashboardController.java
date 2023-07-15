package controller.admin;

import entidade.Categoria;
import entidade.Usuario;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;
import model.UsuarioDAO;

@WebServlet(name = "DashboardController", urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();
        String acao = (String) request.getParameter("acao");

        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                request.setAttribute("usuariosNaoAprovados", usuarioDAO.getUsuarioNaoAprovado());
                request.getRequestDispatcher("/views/admin/dashboard/listaDashboard.jsp")
                        .forward(request, response);
                break;
            case "Alterar":
            case "Excluir":
                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    usuario = usuarioDAO.getUsuario(id);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException("Falhou");
                }

                request.setAttribute("usuario", usuario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/dashboard/formDashboard.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
       // Usuario usuario = new Usuario();
       // String acao = (String) request.getParameter("acao");
        String btEnviar = request.getParameter("btEnviar");

        //int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        RequestDispatcher rd;
        
        if (nome.isEmpty() || endereco.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {

        } 
        else {
            
            Usuario usuario = new Usuario(nome,cpf, endereco,senha);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        break;
                    case "Alterar":
                        usuarioDAO.Alterar(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");           
                        break;
                    case "Excluir":
                        usuarioDAO.Excluir(usuario);
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                    case "Aprovar":
                        usuario.setStatus("S");
                        usuarioDAO.Alterar(usuario);
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/dashboard?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }

    }

}
