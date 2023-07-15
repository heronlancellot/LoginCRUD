package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioDAO;

@WebServlet(name = "DashboardController", urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       //String acao = (String) request.getParameter("acao");;
       //Usuario usuario = new Usuario();
       UsuarioDAO usuario = new UsuarioDAO();
        
       // switch (acao) {
       //     case "Aprovar":
       //         break;
       //     case "Alterar": 
       //         break;
        //    case "Excluir":
        //        break;
      //  }
        request.setAttribute("usuariosNaoAprovados", usuario.getUsuarioNaoAprovado());
        request.getRequestDispatcher("/views/admin/dashboard/areaRestrita.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
        
        UsuarioDAO usuario = new UsuarioDAO();
        
        request.setAttribute("usuariosNaoAprovados", usuario.getUsuarioNaoAprovado());
        request.getRequestDispatcher("/views/admin/dashboard/areaRestrita.jsp")
                .forward(request, response);
    }

}
