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
		<form action="ConsultarAnalise" method="post" class="col s12">
	
			 
		
						<div class="input-field col s12">
					<select  id="idAno" name="idAno" >
						<option value="" disabled selected>Choose your option</option>
						<option    value="2018" >2018</option>
						<option    value="2017">2017</option>
						<option    value="2016">2016</option>
						<option    value="2015">2015</option>
			
					</select> <label>Data</label>
				</div>
				
				<div class="input-field col s12">
					<select id="idCat" name="idCat" >
						<option value="" disabled selected>Choose your option</option>
						<option   value="terror" >Terror</option>
						<option  value="romance">Romance</option>
						
			
					</select> <label>Categoria</label>
				</div>
								
				<input type="submit" id="operacao" name="operacao"
					 value="CONSULTAR">
					
		</form>
	</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
 <script>$('select').material_select();</script>
</body>
</html>