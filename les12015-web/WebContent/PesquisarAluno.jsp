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
		<form action="ConsultarAluno" method="post" class="col s12">
			<div class="row">
	<div class="input-field col s4">
				<input id="txtNome" name="txtNome" type="text" value=""> <label
					for="txtNome">Nome</label>
			</div>

			<div class="input-field col s4">
				<input id="txtRA" type="text" name="txtRA" value=""> <label
					for="txtRA">RA</label>
			</div>
			<div class="input-field col s4">
				<input id="txtGenero" name="txtGenero" type="text" value="">
				<label for=txtGenero>Genero</label>
			</div>
								
				<input type="submit" id="operacao" name="operacao"
					 value="CONSULTAR">
					
		</form>
	</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

</body>
</html>