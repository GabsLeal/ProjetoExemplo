<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="les12015.dominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Livros</title>
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
	
		<ul id="nav-mobile" class="left hide-on-med-and-down">
			<li><a href="Home.jsp">Home</a></li>
	
			<li><a href="PequisarLivro.jsp">Pesquisar</a></li>
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=ESPERA&tipoUsuario=aluno">Reservados Alunos</a></li>
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=ESPERA&tipoUsuario=biblioteca">Reservados Bibliotecas</a></li>
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=ESPERA&tipoUsuario=professor">Reservados Professores</a></li>	
			<li><a href="PesquisarAnalise.jsp">Analise</a></li>		
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=DEVOLVER&tipoUsuario=aluno">Entregar Aluno</a></li>
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=DEVOLVER&tipoUsuario=biblioteca">Entregar Biblioteca</a></li>
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=DEVOLVER&tipoUsuario=professor">Entregar Professor</a></li>
			<li><a href="ConsultarReserva?operacao=CONSULTAR&condicao=LIVROENTREGAR">Livros Entregar</a></li>
			
			
			
			
			
		</ul>

	</div>
	</nav>
	<div class="row">
		<form action="SalvarLivro" method="post" class="col s12">
				<div class="input-field col s4">
					<input id="txtAutor" name="txtAutor" type="text" value="">
					<label for="txtAutor">Autor</label>
				</div>
				<div class="input-field col s4">
					<input type="text" id="dtAno" name="dtAno"><label
						for="dtAno" value="">Data</label>
				</div>
				<div class="input-field col s4">
					<input id="txtTitulo" type="text" name="txtTitulo" value="">
					<label for="txtTitulo">Titulo</label>
				</div>
				<div class="input-field col s4">
					<input id="txtEditora" name="txtEditora" type="text" value="">
					<label for="txtEditora">Editora</label>
				</div>
				<div class="input-field col s4">
					<input id="txtEdicao" name="txtEdicao" type="text" value="">
					<label for="txtEdicao">Edição</label>
				</div>
				<div class="input-field col s4">
					<input id="txtISBN" name="txtISBN" type="text" value=""> <label
						for="txtISBN">ISBN</label>
				</div>
				<div class="input-field col s4">
					<input id="txtPagina" name="txtPagina" type="text" value="">
					<label for="txtPagina">Número de Páginas</label>
				</div>
				<div class="input-field col s4">
					<input id="txtSinopse" name="txtSinopse" type="text" value="">
					<label for="txtSinopse">Sinopse</label>
				</div>
				<div class="input-field col s4">
					<input id="txtAltura" name="txtAltura" type="text" value="">
					<label for="txtAltura">Altura</label>
				</div>
				<div class="input-field col s4">
					<input id="txtLargura" name="txtLargura" type="text" value="">
					<label for="txtLargura">Largura</label>
				</div>
				<div class="input-field col s4">
					<input id="txtPeso" name="txtPeso" type="text"> <label
						for="txtPeso">Peso</label>
				</div>
				<div class="input-field col s4">
					<input id="txtProfundidade" name="txtProfundidade" type="text"
						value=""> <label for="txtProfundidade">Profundidade</label>
				</div>

				<div class="input-field col s4">
					<input id="txtCodBarra" name="txtCodBarra" type="text" value="">
					<label for="txtCodBarra">Codigo de Barras</label>
				</div>
				<div class="input-field col s4">
					<input id="txtResponsavel" name="txtResponsavel" type="text"
						value=""> <label for="txtResponsavel">Responsavel</label>
				</div>

				<div class="input-field col s12">
					<select multiple="multiple" id="idCat" name="idCat" >
						<option value="" disabled selected>Choose your option</option>
						<option  id="idCat" value="terror" >Terror</option>
						<option  id="idCat" value="romance">Romance</option>
						
			
					</select> <label>Categoria</label>
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
				
					<!-- <li><a href="Home.jsp" class="waves-effect waves-light btn" >Salvar</a></li> -->		
											<input type="submit" id="operacao" name="operacao"
					value="SALVAR">

		</form>
	</div>
	<!--Import jQuery before materialize.js-->
  <script>$('select').material_select();</script>
	
	  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
</body>
</html>