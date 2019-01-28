<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="les12015.dominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Livros</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">
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
			<li><a href="FormLivro.jsp">Cadastrar</a></li>
	
			<li><a href="PequisarLivro.jsp">Pesquisar</a></li>
			<li><a href="Reservados.jsp">Reservados</a></li>
			<li><a href="Entregar.jsp">Entregar</a></li>
			<li><a href="Situacao.jsp">Situacao</a></li>
	</div>
	</nav>
	<div class="row">
		<form action="SalvarLivro" method="post" class="col s12">
								<div class="input-field col s4">
					<input id="txtAutor" name="txtAutor" type="text" value="Teste">
					<label for="txtAutor">Nome</label>
				</div>
				<div class="input-field col s4">
					<input id="txtAutor" name="txtAutor" type="text" value="Teste">
					<label for="txtAutor">Autor</label>
				</div>
				<div class="input-field col s4">
					<input id="txtTitulo" type="text" name="txtTitulo" value="Teste">
					<label for="txtTitulo">Data</label>
				</div>
				<div class="input-field col s4">
					<input id="txtTitulo" type="text" name="txtTitulo" value="Teste">
					<label for="txtTitulo">Titulo</label>
				</div>
				<div class="input-field col s4">
					<input id="txtEditora" name="txtEditora" type="text" value="Teste">
					<label for="txtEditora">Editora</label>
				</div>
				<div class="input-field col s4">
					<input id="txtEdicao" name="txtEdicao" type="text" value="Teste">
					<label for="txtEdicao">Edição</label>
				</div>
				<div class="input-field col s4">
					<input id="txtISBN" name="txtISBN" type="text" value="Teste"> <label
						for="txtISBN">Regristro</label>
				</div>
				<div class="input-field col s4">
					<input id="txtCategoria" name="txtCategoria" type="text" value="Teste">
					<label for="txtCategoria">Categoria</label>
				</div>
								<div class="input-field col s4">
					<input id="txtData" name="txtData" type="text" value="TESTE">
					<label for="txtData">Data para entregaar</label>
				</div>


<!--								<div class="input-field col s12">
					<select multiple id="idSubCat" name="idSubCat" value="">
						<option value="" disabled selected>Choose your option</option>
						<%
		//				List<Categoria> subcategorias = (List<Categoria>) request.getAttribute("listaCategoria");	
		//					for (Categoria categoria : categorias) {
		//						out.print(categoria.getDescricao());
		//						out.println("<option value='"+categoria.getId()+"'>"+categoria.getDescricao()+"</option>");//

//								out.println("</div>");
//							}
					%>
			
					</select> <label>SubCategoria</label>
				</div>-->
				
							<li><a href="Home.jsp" class="waves-effect waves-light btn" >Entregue</a></li>

		</form>
	</div>
	<!--Import jQuery before materialize.js-->
	  <script>$('select').material_select();</script>
	
	  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
</body>
</html>