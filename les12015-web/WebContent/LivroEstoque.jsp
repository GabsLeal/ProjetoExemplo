<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page
	import="les12015.dominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js">
	
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<nav>
	<div class="nav-wrapper blue-grey darken-3">
		<a href="#" class="brand-logo right">Logo</a>
		<ul id="nav-mobile" class="left hide-on-med-and-down">
			<li><a>Livros</a></li>
			<li><a href="Home.jsp">Home</a></li>
			
		</ul>
	</div>
	</nav>
<form  action=AlterarEstoque method="post">
<% List<Livro> livros =(List<Livro>)request.getAttribute("listaLivro");
List<Aluno> alunos = (List<Aluno>) session.getAttribute("listaAluno");
out.print("<table class='striped'>");
out.println("<tr>");
out.println("<th>");
out.println("Autor");
out.println("</th>");
out.println("<th>");
out.println("Titulo");
out.println("</th>");
out.println("<th>");
out.println("Editora");
out.println("</th>");
out.println("<th>");
out.println("Edição");
out.println("</th>");
out.println("<th>");
out.println("ISBN");
out.println("</th>");
out.println("<th>");
out.println("Páginas");
out.println("</th>");

out.println("<th>");
out.println("Ano");
out.println("</th>");
out.println("<th>");
out.println("Categoria");
out.println("</th>");
out.println("<th>");
out.println("Qt");
out.println("</th>");
out.println("<th>");
out.println("Biblioteca");
out.println("</th>");
out.println(	"<th>");
out.println("Ações");
out.println("</th>");
for ( Livro livro : livros ){
	out.println("<tr>");
	out.println("<td>"+livro.getAutor()+"</td>");
	out.println("<td>"+livro.getTitulo()+"</td>");
	out.println("<td>"+livro.getEditora()+"</td>");
	out.println("<td>"+livro.getEdicao()+"</td>");
	out.println("<td>"+livro.getISBN()+"</td>");
	out.println("<td>"+livro.getnPaginas()+"</td>");
	out.println("<td>"+livro.getAno()+"</td>");
	out.print("<td>");
	for( Categoria cat : livro.getCategoria() ){
		out.println(cat.getDescricao() +", ");
		}
	out.print("</td>");
	out.println("<td>"+livro.getQuantidade().getQuantidade()+"</td>");
	out.println("<td>"+livro.getQuantidade().getNome()+"</td>");
	out.print("<td>");
	out.print("<a  href='AlterarEstoque?operacao=ALTERAR&tipoUsuario=aluno&idLivro="+livro.getId()+"&idBiblioteca="+livro.getQuantidade().getId_biblioteca()+
			"&idUsuario="+alunos.get(0).getId()+"&idEstoque="+livro.getQuantidade().getId() +"&qtEstoque="+livro.getQuantidade().getQuantidade()+"'><i'>Reservar</i></a>");
	out.print("</td>");
	out.println("</tr>");


}
out.print("</table>");

%>
</form>
	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>