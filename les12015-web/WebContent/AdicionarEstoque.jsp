<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="les12015.dominio.*, java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estoque</title>


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
		<form action="SalvarEstoque" method="post" class="col s12">
			<div class="input-field col s4">
				<input id="txtQt" name="txtQt" type="text" value=""> <label
					for="txtQt">Quantidade</label>
			</div>
				
				<div class="input-field col s12">
					<select id="idBiblioteca" name="idBiblioteca" value="">
						<option value="" disabled selected>Choose your option</option>
						<%
							List<Biblioteca> bibliotecas = (List<Biblioteca>) request.getAttribute("listaBiblioteca");
							
							for (Biblioteca biblioteca : bibliotecas) {
								out.println("<option value='" + biblioteca.getId() + "'>" + biblioteca.getNome() + "</option>");

							}
						%>

					</select> <label>Bibliotecas</label> 

				</div>
			<div class="input-field col s4">
				<input id="txtIdLivro" name="txtIdLivro" style="display:none"  type="text" value="<%out.print(bibliotecas.get(0).getLivro().getId());%>"> <label
					for="txtIdLivro">Livro</label>
			</div>
		
					<input type="submit" id="operacao" name="operacao"
				class="waves-effect waves-light btn" value="SALVAR">
		</form>
	</div>
	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>
	<script>
		function duplicarCampos() {
			var clone = document.getElementById('origem').cloneNode(true);
			var destino = document.getElementById('destino');
			destino.appendChild(clone);
			var camposClonados = clone.getElementsByTagName('input');
			for (i = 0; i < camposClonados.length; i++) {
				camposClonados[i].value = '';
			}
		}
		function removerCampos(id) {
			var node1 = document.getElementById('destino');
			node1.removeChild(node1.childNodes[0]);
		}
		function duplicarCamposCartao() {
			var clone = document.getElementById('origemcartao').cloneNode(true);
			var destino = document.getElementById('destinocartao');
			destino.appendChild(clone);
			var camposClonados = clone.getElementsByTagName('input');
			var selectClonados = clone.getElementsByTagName('select');
			for (i = 0; i < camposClonados.length; i++) {
				camposClonados[i].value = '';
			}
			for (i = 0; i < selectClonados.options.length; i++) {
				var option = selectClonados.options[i];
				selectClonados[i].value = '';
				camposClonados[i].value = '';
			}
		}
		function removerCamposCartao(id) {
			var node1 = document.getElementById('destinocartao');
			node1.removeChild(node1.childNodes[0]);
		}
	</script>

</body>
</html>