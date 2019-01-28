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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

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
<% List<Analise> analises =(List<Analise>)request.getAttribute("listaGrafico");

List<String> quantidades =  new ArrayList<String>();

for (Analise analise : analises ){
	int qtAluno = 0, qtBiblioteca =0, qtProfessor =0;

	qtBiblioteca = Integer.valueOf(analise.getQtBiblioteca());
	qtProfessor = Integer.valueOf(analise.getQtProfessor());
	qtAluno = qtAluno + qtBiblioteca + qtProfessor;
	quantidades.add(Integer.toString(qtAluno));



}


%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
    	
      var data = google.visualization.arrayToDataTable([
        ["Usuario", "Quantidade", { role: "style" } ],
        ["Janerio", <% out.print(quantidades.get(0));%>, "silver"],
        ["Fevereiro", <% out.print(quantidades.get(1));%>, "gold"],
        ["Março", <% out.print(quantidades.get(2));%>, "gray"],
        ["Abril", <% out.print(quantidades.get(3));%>, "brown"],
        ["Maio", <% out.print(quantidades.get(4));%>, "color: #76A7FA"],
        ["Junho", <% out.print(quantidades.get(5));%>, "green"],
        ["Julho", <% out.print(quantidades.get(6));%>, "yellow"],
        ["Agosto", <% out.print(quantidades.get(7));%>, "red"],
        ["Setembro", <% out.print(quantidades.get(8));%>, "orange"],
        ["Outubro", <% out.print(quantidades.get(9));%>, "purple"],
        ["Novembro", <% out.print(quantidades.get(10));%>, "green"],
        ["Dezembro", <% out.print(quantidades.get(11));%>, "pink"],


      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "Livros Emprestados por categoria",
          vAxis: {
              title: "Quantidade de livros"
            },
        legend: { position: "none" },
      };
      var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
      chart.draw(view, options);
      
      
      
      
      
      
      
      
  }
  </script>
<div id="columnchart_values" style="width: 900px; height: 300px;"></div>

</form>
	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

</body>
</html>