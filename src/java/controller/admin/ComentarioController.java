package controller.admin;

import entidade.Comentario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                int id = Integer.parseInt(request.getParameter("id"));
                meuComentario = meuComentarioDAO.get(id);

                request.setAttribute("comentario", meuComentario);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/comentarioUser/formComentario.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
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
        
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("Valor do id: " + id);

        String comentario = request.getParameter("comentario");
        System.out.println("Valor do comentario: " + comentario);
        
        String data = request.getParameter("data");
        System.out.println("Valor do data: " + data);
        
        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        System.out.println("Valor do idusuario: " + idusuario);

        int idcategoria = Integer.parseInt(request.getParameter("idcategoria"));
        System.out.println("Valor do idcategoria: " + idcategoria);
        
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (comentario.isEmpty() || data.isEmpty()) {

        } else {

            Comentario meuComentario = new Comentario();
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            meuComentario.setComentario(comentario);
            meuComentario.setData(data);
            meuComentario.setId(id);
            meuComentario.setIdusuario(1);
            meuComentario.setIdcategoria(idcategoria);

            try {
                switch (btEnviar) {
                    case "Incluir":
                        comentarioDAO.insert(meuComentario);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        comentarioDAO.update(meuComentario);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        comentarioDAO.delete(id);
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
