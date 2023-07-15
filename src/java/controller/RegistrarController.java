package controller;

import entidade.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UsuarioDAO;

/**
 *
 * @author Leonardo
 */
@WebServlet(name = "RegistrarController", urlPatterns = {"/RegistrarController"})
public class RegistrarController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException {

    	RequestDispatcher rd;
    	rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
    	rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        	throws ServletException, IOException {

    	String nome = request.getParameter("nome");
    	String endereco = request.getParameter("endereco");
    	String cpf = request.getParameter("cpf");
    	String senha = request.getParameter("senha");
    	String btEnviar = request.getParameter("btEnviar");
    	//String status = request.getParameter("aprovado");

    	Usuario usuario = new Usuario(nome, cpf, endereco, senha);
    	UsuarioDAO usuariodao = new UsuarioDAO();
   	 
        RequestDispatcher rd;

        if (nome.isEmpty() && endereco.isEmpty() && cpf.isEmpty() && senha.isEmpty()) {
            
            request.setAttribute("msgError", "É necessário preencher todos os campos");
            rd = request.getRequestDispatcher("/views/registro/formRegistro.jsp");
            rd.forward(request, response);
        
        
        
        }else {
            try 
            {
                    usuariodao.Inserir(usuario);
            }
            catch (Exception ex)
            {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de usuario");
            }


            request.setAttribute("msgOperacaoRealizada", "Usuário Registrado com Sucessos");
            request.setAttribute("link", "AutenticaController?acao=Login");
            rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
            rd.forward(request, response);

            }
}
}
