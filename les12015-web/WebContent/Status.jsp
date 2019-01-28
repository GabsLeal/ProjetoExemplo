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

		<form action="SalvarAluno" method="post" class="col s12">
			<%
				List<Aluno> alunos = (List<Aluno>) request.getAttribute("listaAluno");
			%>


			<div  class="input-field col s4">
				<input id="AlunoId" name="AlunoId" type="text"  style="display:none"
					value="<%out.print(alunos.get(0).getId());%>" >
			</div>

			<div class="input-field col s4">
				<input type="text" id="txtNome" name="txtNome"
					value="<%out.print(alunos.get(0).getNome());%>"><label
					for="txtNome">Nome</label>
			</div>
			<div class="input-field col s4">
				<input id="txtRA" name="txtRA" type="text"
					value="<%out.print(alunos.get(0).getRa());%>"> <label
					for="txtRA">RA</label>
			</div>
			<div class="input-field col s4">
				<input id="txtMotivo" name="txtMotivo" type="text"
					value=""> <label
					for="txtMotivo">Motivo</label>
			</div>
			<div class="input-field col s4">
				<select id="idstatus" name="idstatus" value="">
					<%
						if (alunos.get(0).getStatus() == true) {
							out.println("<option value='" + alunos.get(0).getStatus() + "'>true</option>");
							out.println("<option value='FALSE'>false</option>");

						} else if (alunos.get(0).getStatus() == false) {
							out.println("<option value='" + alunos.get(0).getStatus() + "'>false</option>");
							out.println("<option value='TRUE'>true</option>");

						}
					%>
				</select> <label>Status</label>
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