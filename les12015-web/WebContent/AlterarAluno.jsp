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
				<input id="txtId" name="txtId" type="text"  style="display:none"
					value="<%out.print(alunos.get(0).getId());%>" >
			</div>
			
			
			<div class="input-field col s4">
				<input id="txtGenero" name="txtGenero" type="text"
					value="<%out.print(alunos.get(0).getGenero());%>"> <label
					for="txtGenero">Genero</label>
			</div>

			<div class="input-field col s4">
				<input type="text" id="txtNome" name="txtNome"
					value="<%out.print(alunos.get(0).getNome());%>"><label
					for="txtNome">Nome</label>
			</div>
			<div class="input-field col s4">
				<input type="text" id="txtNascimento" name="txtNascimento"
					value="<%out.print(alunos.get(0).getData_nascimento());%>"><label
					for="txtNascimento">Nascimento</label>
			</div>
			<div class="input-field col s4">
				<input id="txtRA" name="txtRA" type="text"
					value="<%out.print(alunos.get(0).getRa());%>"> <label
					for="txtRA">RA</label>
			</div>
			<div class="input-field col s4">
				<input id="txtTipoTelefone" name="txtTipoTelefone" type="text"
					value="<%out.print(alunos.get(0).getTelefone().getTipo());%>"> <label
					for="txtTipoTelefone">TipoTelefone</label>
			</div>
			<div class="input-field col s4">
				<input id="txtDDD" name="txtDDD" type="text"
					value="<%out.print(alunos.get(0).getTelefone().getDdd());%>"> <label
					for="txtDDD">DDD</label>
			</div>
			<div class="input-field col s4">
				<input id="txtNumeroTelefone" name="txtNumeroTelefone" type="text"
					value="<%out.print(alunos.get(0).getTelefone().getNumero());%>"> <label
					for="txtNumeroTelefone">Numero</label>
			</div>
			<div class="input-field col s4">
				<input id="txtEmail" name="txtEmail" type="text"
					value="<%out.print(alunos.get(0).getEmail());%>"> <label
					for="txtEmail">Email</label>
			</div>
			<div class="input-field col s4">
				<input id="txtSenha" name="txtSenha" type="text" 
				value="<%out.print(alunos.get(0).getSenha());%>"> <label
					for="txtSenha">Senha</label>
			</div>
			<div class="input-field col s4">
				<input id="txtSenhaConfirm" name="txtSenhaConfirm" type="text"
					value="<%out.print(alunos.get(0).getSenhaconfirma());%>"> <label 
					for="txtSenhaConfirm">Confirmar
					Senha</label>
			</div>
						<div class="input-field col s4">
					<input id="txtMatricula" name="txtMatricula" type="text"
					 value="<%out.print(alunos.get(0).getMatricula());%>">
					<label for="txtMatricula">Data Matricula</label>
				</div>
				<div class="input-field col s4">
					<input id="txtTipoResidencia" name="txtTipoResidencia" type="text"
						value="<%out.print(alunos.get(0).getEndereco().getTipo());%>"> <label 
						for="txtTipoResidencia">Tipo
						Residencia</label>
				</div>
				<div class="input-field col s4">
					<input id="txtTipoLogradouro" name="txtTipoLogradouro" type="text"
						value="<%out.print(alunos.get(0).getEndereco().getLogradouro().getTipo());%>"> <label
						 for="txtTipoLogradouro">Tipo
						Logradouro</label>
				</div>
				<div class="input-field col s4">
					<input id="txtLogradouro" name="txtLogradouro" type="text" 
					value="<%out.print(alunos.get(0).getEndereco().getLogradouro().getNome());%>">
					<label for="txtLogradouro">"Logradouro"</label>
				</div>
				<div class="input-field col s4">
					<input id="txtNumeroEnd" name="txtNumeroEnd" type="text" 
					value="<%out.print(alunos.get(0).getEndereco().getNumero());%>">
					<label for="txtNumeroEnd">Numero de Endereço</label>
				</div>
				<div class="input-field col s4">
					<input id="txtBairro" name="txtBairro" type="text" 
					value="<%out.print(alunos.get(0).getEndereco().getBairro());%>">
					<label for="txtBairro">Bairro</label>
				</div>
				<div class="input-field col s4">
					<input id="txtCep" name="txtCep" type="text"
					 value="<%out.print(alunos.get(0).getEndereco().getCep());%>"> <label
						for="txtCep">Cep</label>
				</div>
				<div class="input-field col s4">
					<input id="txtCidade" name="txtCidade" type="text" 
					value="<%out.print(alunos.get(0).getEndereco().getCidade().getNome());%>">
					<label for="txtCidade">Cidade</label>
				</div>
							<div class="input-field col s4">
					<select id="txtEstado" name="txtEstado" value="">
						<option value="" disabled selected>Choose your option</option>
						<option value="ac">Acre</option>
						<option value="al">Alagoas</option>
						<option value="am">Amazonas</option>
						<option value="ap">Amapá</option>
						<option value="ba">Bahia</option>
						<option value="ce">Ceará</option>
						<option value="df">Distrito Federal</option>
						<option value="es">Espírito Santo</option>
						<option value="go">Goiás</option>
						<option value="ma">Maranhão</option>
						<option value="mt">Mato Grosso</option>
						<option value="ms">Mato Grosso do Sul</option>
						<option value="mg">Minas Gerais</option>
						<option value="pa">Pará</option>
						<option value="pb">Paraíba</option>
						<option value="pr">Paraná</option>
						<option value="pe">Pernambuco</option>
						<option value="pi">Piauí</option>
						<option value="rj">Rio de Janeiro</option>
						<option value="rn">Rio Grande do Norte</option>
						<option value="ro">Rondônia</option>
						<option value="rs">Rio Grande do Sul</option>
						<option value="rr">Roraima</option>
						<option value="sc">Santa Catarina</option>
						<option value="se">Sergipe</option>
						<option value="sp">São Paulo</option>
						<option value="to">Tocantins</option>
					</select> <label for="txtEstado">Estado</label>
				</div>
				<div class="input-field col s4">
					<select id="txtPais" name="txtPais" value="">
						<option value="" disabled selected>Choose your option</option>
						<option value="Argentina">Argentina</option>
						<option value="Armênia">Armênia</option>
						<option value="Aruba">Aruba</option>
						<option value="Austrália">Austrália</option>
						<option value="Áustria">Áustria</option>
						<option value="Azerbaijão">Azerbaijão</option>
						<option value="Bahamas">Bahamas</option>
						<option value="Bahrein">Bahrein</option>
						<option value="Brasil">Brasil</option>
						<option value="EUA">EUA</option>
						</select> <label for="txtPais">Pais</label>
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