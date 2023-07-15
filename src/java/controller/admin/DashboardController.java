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

        UsuarioDAO usuario = new UsuarioDAO();
        String acao = (String) request.getParameter("acao");
        
        switch (acao) {
            case "Listar":
                request.setAttribute("usuariosNaoAprovados", usuario.getUsuarioNaoAprovado());
                request.getRequestDispatcher("/views/admin/dashboard/areaRestrita.jsp")
                        .forward(request, response);
                out.println("<h2>xereca: "+ "</h2>");
                break;
            case "Alterar":
                break;
            case "Excluir": 
                break;
            case "Incluir":
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        request.setAttribute("usuariosNaoAprovados", usuarioDAO.getUsuarioNaoAprovado());
        request.getRequestDispatcher("/views/admin/dashboard/areaRestrita.jsp")
                .forward(request, response);
    }

}
