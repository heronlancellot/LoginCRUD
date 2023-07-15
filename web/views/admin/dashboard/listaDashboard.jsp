<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Usuario" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista DashBoard</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Lista DashBoard</h1>
                <%
                    Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");
                    ArrayList<Usuario> usuariosNaoAprovados = (ArrayList<Usuario>) request.getAttribute("usuariosNaoAprovados");
                    out.println("<h3>Usuário logado com sucesso</h3>");
                    out.println("<h2>Nome: " + usuarioLogado.getNome() + "</h2>");


                %>
             
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                          
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%                                
                                

                                for (Usuario usuario : usuariosNaoAprovados) {
                                    out.println("<tr>");
                                    out.println("<th>" + usuario.getId() + "</th>");
                                    out.println("<td>" + usuario.getNome()+ "</td>");
                                    
                            %>
                        <td>
                            <a href="/aplicacaoMVC/admin/dashboard?acao=Alterar&id=<%=usuario.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/dashboard?acao=Excluir&usuario=<%=usuario.getId()%>" class="btn btn-danger">Excluir</a>
                            <a href="/aplicacaoMVC/admin/dashboard?acao=Aprovar&id=<%=usuario.getId()%>" class="btn btn-success">Aprovar</a></td>
                        <%   out.println("</tr>");
                            }
                        %>

                        </tbody>
                    </table>
                </div>

            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
