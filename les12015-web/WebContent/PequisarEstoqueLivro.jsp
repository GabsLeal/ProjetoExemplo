<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="les12015.dominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" 	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js">
	
</script>
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
	<div class="row">
		<form action="ConsultarLivro" method="post" class="col s12">
			<div class="row">
			
			<%
				List<Aluno> alunos = (List<Aluno>) session.getAttribute("listaAluno");
			%>
						<div  class="input-field col s4">
				<input id="txtIdUsuario" name="txtIdUsuario" type="text"  style="display:none"
					value="<%out.print(alunos.get(0).getId());%>" >
			</div>
			
			<div class="input-field col s4">
			<label
					for="txtNome"><%out.print(alunos.get(0).getNome());%></label>
			</div>
				<div class="input-field col s4">
					<input id="txtAutor" name="txtAutor" type="text" value="">
					<label for="txtAutor">Autor</label>
				</div>

				<div class="input-field col s4">
					<input id="txtTitulo" type="text" name="txtTitulo"  value="">
					<label for="txtTitulo">Titulo</label>
				</div>
				<div class="input-field col s4">
					<input id="txtEditora" name="txtEditora" type="text" value=""
					> <label for="txtEditora">Editora</label>
				</div>
				<div class="input-field col s4">
					<input id="txtEdicao" name="txtEdicao" type="text" value="">
					<label for="txtEdicao">Edição</label>
				</div>
				<div class="input-field col s4">
					<input id="txtISBN" name="txtISBN" type="text" value="">
					<label for="txtISBN">ISBN</label>
				</div>
				<div class="input-field col s4">
					<input id="txtPrecificacao" name="txtPrecificacao" type="text" value=""
						> <label for="txtPrecificacao">Precificação</label>
				</div>
				<div class="input-field col s4">
					<input id="txtCodBarra" name="txtCodBarra" type="text" value=""
						> <label for="txtCodBarra">Codigo de Barras</label>
				</div>
					<div class="input-field col s4">
					<input id="situacao" name="situacao" type="text" value="pesquisarEstoque" style="display:none"
						> 
				</div>

				
				<input type="submit" id="operacao" name="operacao"
					 value="CONSULTAR">
					
		</form>
	</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

</body>
</html>