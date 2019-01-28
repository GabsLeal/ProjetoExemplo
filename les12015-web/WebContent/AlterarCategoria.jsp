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
<title>Enderecos</title>
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
			
		</ul>
	</div>
	</nav>
<form  action="ConsultarLivro" method="post">

<select multiple id="idCat" name="idCat" value="">
	<option value="" disabled selected>Choose your option</option>
	<%
		List<Categoria> categorialista = (List<Categoria>) session.getAttribute("listaCategoria");
	
	for (Categoria categoria : categorialista) {
		out.print(categoria.getDescricao());
		out.println("<option value='"+categoria.getId()+"'>"+categoria.getDescricao()+"</option>");

		out.println("</div>");
	}
		
	%>
</select> <label>Categoria</label>
</div>

</form>
	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>