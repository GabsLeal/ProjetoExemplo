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
<%
				List<Aluno> alunos = (List<Aluno>) session.getAttribute("listaAluno");
			%>
	<nav>
	<div class="nav-wrapper blue-grey darken-3">
		<a href="#" class="brand-logo right">Logo</a>
		<ul id="nav-mobile" class="left hide-on-med-and-down">
			<li><a>Livros</a></li>
			<li><a href="Home.jsp">Home</a></li>
			<li><a href="FormLivro.jsp">Cadastrar</a></li>
			<li><a href="ConsultarCategoria?operacao=CONSULTAR&recebeLivro=cadastro">Livros</a></li>
			<li><a href="PequisarEstoqueLivro.jsp">Pesquisar</a></li>
			<%
			out.print("<li><a  href='ConsultarReserva?operacao=CONSULTAR&idUsuario="+alunos.get(0).getId()+"'><i'>Reserva</i></a></li>");
			%>
			
			<%
			out.print("<li><a  href='ConsultarReserva?operacao=CONSULTAR&tipoUsuario=aluno&idUsuario="+alunos.get(0).getId()+"&condicao=RETIRADOS'><i'>Retirados</i></a></li>");
			%>
						<%
			out.print("<li><a  href='ConsultarMultas?operacao=CONSULTAR&idUsuario="+alunos.get(0).getId()+"'><i'>Multas</i></a></li>");
			%>
			
			
			
		</ul>
	</div>
	</nav>
	<div class="row" >

		<form action="SalvarAluno" method="post" class="col s12">

			<div  class="input-field col s4">
				<input id="txtId" name="txtId" type="text"  style="display:none"
					value="<%out.print(alunos.get(0).getId());%>" >
			</div>
			
			<div class="input-field col s4">
			<label
					for="txtNome"><%out.print(alunos.get(0).getNome());%></label>
			</div>
			<div class="input-field col s4">
			 <label
					for="txtRA"><%out.print(alunos.get(0).getRa());%></label>
			</div>
			
		</form>
	</div>
	<!--Import jQuery before materialize.js-->

	  <script>$('select').material_select();</script>
	
	  <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
  <script src="js/materialize.js"></script>
  <script src="js/init.js"></script>
</body>
</html>