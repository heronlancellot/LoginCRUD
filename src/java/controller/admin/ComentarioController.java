package controller.admin;

import entidade.Comentario;
import entidade.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ComentarioDAO;

@WebServlet(name = "ComentarioController", urlPatterns = {"/admin/ComentarioController"})
public class ComentarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        Comentario meuComentario = new Comentario();
        ComentarioDAO meuComentarioDAO = new ComentarioDAO();
        RequestDispatcher rd;
        int id=0;
        
        switch (acao) {
            case "Listar":
                ArrayList<Comentario> listaComentarios = meuComentarioDAO.getAll();
                request.setAttribute("listaComentarios", listaComentarios);

                rd = request.getRequestDispatcher("/views/admin/comentarioUser/listaComentarios.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":

                // get parametro ação indicando sobre qual categoria será a ação
                id = Integer.parseInt(request.getParameter("id"));
                meuComentario = meuComentarioDAO.get(id);

                request.setAttribute("comentario", meuComentario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/comentarioUser/formComentario.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                int idCategoria = Integer.parseInt(request.getParameter("id"));
                System.out.println("ssssssssssss: " + idCategoria);
                meuComentario.setIdcategoria(idCategoria);
                
                request.setAttribute("comentario", meuComentario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/comentarioUser/formComentario.jsp");
                rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Valor do id - Comentario: " + id);

        String comentario = request.getParameter("comentario");
        System.out.println("Valor do comentario: " + comentario);

        String dataString = request.getParameter("data");
        System.out.println("Valor do data: " + dataString);
        java.sql.Date data = java.sql.Date.valueOf(dataString);
        
        int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
        System.out.println("Valor do idcategoria: " + idcategoria);
        
        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        System.out.println("Valor do idusuario: " + idusuario);

        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (comentario.isEmpty() || dataString.isEmpty()) {
            request.setAttribute("msgError", "Usuário e/ou senha incorreto");
            rd = request.getRequestDispatcher("/views/admin/comentarioUser/formComentario.jsp");
            rd.forward(request, response);
        } else {
            
            System.out.println("Usuario GET ID = " + usuarioLogado.getId());
          
            Comentario meuComentario = new Comentario(0,comentario, data, usuarioLogado.getId(), idcategoria );
            System.out.println(" Comentario" + meuComentario);
    
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        
                        comentarioDAO.Inserir(meuComentario);
                        System.out.println("Incluir Comentario" + meuComentario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        comentarioDAO.update(meuComentario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        comentarioDAO.delete(usuarioLogado.getId());
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/ComentarioController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }
        }
    }
}
