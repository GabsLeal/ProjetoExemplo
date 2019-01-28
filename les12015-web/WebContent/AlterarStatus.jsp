<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="les12015.dominio.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar de Livros</title>
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
			<li><a href="ConsultarCategoria?operacao=CONSULTAR&recebeLivro=cadastro">Livros</a></li>
			<li><a href="PequisarLivro.jsp">Pesquisar</a></li>
			
		</ul>
	</div>
	</nav>
	<div class="row" >

		<form action="SalvarLivro" method="post" class="col s12">
			<%
				List<Livro> livros = (List<Livro>) request.getAttribute("listaLivro");
			%>


			<div  class="input-field col s4">
				<input id="txtId" name="txtId" type="text"  style="display:none"
					value="<%out.print(livros.get(0).getId());%>" >
			</div>
			
						<div  class="input-field col s4">
				<input id="txtId" name="txtId" type="text"  style="display:none"
					value="<%out.print(livros.get(0).getId());%>" >
			</div>
			<div  class="input-field col s4">
				<input id="txtAutor" name="txtAutor" type="text"  style="display:none"
					value="<%out.print(livros.get(0).getAutor());%>" >
			</div>
			<div class="input-field col s4">
				<input type="text" id="dtAno" name="dtAno" style="display:none"
					value="<%out.print(livros.get(0).getAno());%>">
			</div>
			<div class="input-field col s4">
				<input id="txtTitulo" name="txtTitulo" type="text" style="display:none"
					value="<%out.print(livros.get(0).getTitulo());%>">
			</div>
			<div class="input-field col s4">
				<input id="txtEditora" name="txtEditora" type="text" style="display:none"
					value="<%out.print(livros.get(0).getEditora());%>"> 
			</div>
			<div class="input-field col s4">
				<input id="txtEdicao" name="txtEdicao" type="text" style="display:none"
					value="<%out.print(livros.get(0).getEdicao());%>"> 
			</div>
			<div class="input-field col s4">
				<input id="txtISBN" name="txtISBN" type="text" style="display:none"
					value="<%out.print(livros.get(0).getISBN());%>"> 
			</div>
			<div class="input-field col s4">
				<input id="txtPagina" name="txtPagina" type="text" style="display:none"
					value="<%out.print(livros.get(0).getnPaginas());%>"> 
			</div>
			<div class="input-field col s4">
				<input id="txtSinopse" name="txtSinopse" type="text" style="display:none"
					value="<%out.print(livros.get(0).getSinopse());%>">
			</div>
			<div class="input-field col s4">
				<input id="txtAltura" name="txtAltura" type="text" style="display:none"
					value="<%out.print(livros.get(0).getDimensao().getAltura());%>">

			</div>
			<div class="input-field col s4">
				<input id="txtLargura" name="txtLargura" type="text" style="display:none"
					value="<%out.print(livros.get(0).getDimensao().getLargura());%>">
			</div>
			<div class="input-field col s4">
				<input id="txtPeso" name="txtPeso" type="text" style="display:none"
					value="<%out.print(livros.get(0).getDimensao().getPeso());%>">
			</div>
			<div class="input-field col s4">
				<input id="txtProfundidade" name="txtProfundidade" type="text" style="display:none"
					value="<%out.print(livros.get(0).getDimensao().getProfundidade());%>">
			</div>
			<div class="input-field col s4">
				<input id="txtCodBarra" name="txtCodBarra" type="text" style="display:none"
					value="<%out.print(livros.get(0).getCod_barra());%>"> 
			</div>
			<div class="input-field col s4">
				<input id="txtResponsavel" name="txtResponsavel" type="text" style="display:none"
					value="<%out.print(livros.get(0).getResponsavel());%>"> 
			</div>
			<div class="input-field col s4">
				<select id="idstatus" name="idstatus" value="">
					<%
						if (livros.get(0).getStatus() == true) {
							out.println("<option value='" + livros.get(0).getStatus() + "'>true</option>");
							out.println("<option value='false'>false</option>");

						} else if (livros.get(0).getStatus() == false) {
							out.println("<option value='" + livros.get(0).getStatus() + "'>false</option>");
							out.println("<option value='true'>true</option>");

						}
					%>
				</select> <label>Status</label>
			</div>
			<div class="input-field col s4">
				<input id="txtMotivo" name="txtMotivo" type="text"
					value="">
					 <label
					for="txtMotivo">Motivo</label>
			</div>
						<input type="submit" id="operacao" name="operacao"
				class="waves-effect waves-light btn" value="SALVAR">
		</form>
	</div>
	<!--Import jQuery before materialize.js-->

	  <script>$('select').material_select();</script>
	
	  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
</body>
</html>