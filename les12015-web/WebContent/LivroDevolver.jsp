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
<form  action="ConsultarAluno" method="post">
<% List<Reserva> reservas =(List<Reserva>)request.getAttribute("listaDevolver");
out.print("<table class='striped'>");
out.println("<tr>");
out.println("<th>");
out.println("Nome");
out.println("</th>");
out.println("<th>");
out.println("Titulo");
out.println("</th>");
out.println("<th>");
out.println("Condicao");
out.println("</th>");
out.println("<th>");
out.println("Data");
out.println("</th>");
out.println(	"<th colspan='3' align='center'>");
out.println("Ações");
out.println("</th>");
for (Reserva reserva : reservas ){
	out.println("<tr>");
	out.println("<td>"+reserva.getResponsavel()+"</td>");
	out.println("<td>"+reserva.getTitulo()+"</td>");
	out.println("<td>"+reserva.getCondicao()+"</td>");
	out.println("<td>"+reserva.getDtCadastro()+"</td>");
	out.print("<td>");
	out.print("<a  href='AlterarReserva?operacao=ALTERAR&condicao=DEVOLVIDO&idEstoque="+reserva.getId_estoque()+"&qtEstoque="+reserva.getQtEstoque()+"&reservaId="+reserva.getId()+"'><i'>Aprovar Devolução</i></a>");
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