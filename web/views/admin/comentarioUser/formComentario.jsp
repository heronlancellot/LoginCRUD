<%@page import="entidade.Comentario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Form Comentário</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-4 offset-3">
                    <%
                        Comentario comentario = (Comentario) request.getAttribute("comentario");
                        String acao = (String) request.getAttribute("acao");
                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Comentário</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Comentário</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Comentário</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>

                    <form action="/aplicacaoMVC/admin/ComentarioController" method="POST">
                        <input type="hidden" name="id" value="<%=comentario.getId()%>" class="form-control">
                        <input type="hidden" name="idusuario" value="<%=comentario.getIdusuario()%>" class="form-control">
                        <input type="hidden" name="idcategoria" value="<%=comentario.getIdcategoria()%>" class="form-control">                        
                        
                        <div class="mb-3">
                            <label for="comentario" class="form-label" >Comentario </label>
                            <input type="text" name="comentario" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=comentario.getComentario()%>" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="date" class="form-label" >Data </label>
                            <input type="date" name="data" <%= acao.equals("Excluir") ? "Readonly" : ""%> value="<%=comentario.getData()%>" class="form-control">
                        </div>     
                        <div>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/ComentarioController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>        
    </body>
</html>
