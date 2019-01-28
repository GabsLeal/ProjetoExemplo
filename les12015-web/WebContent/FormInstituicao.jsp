<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="les12015.dominio.*, java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Aluno</title>

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
	
		
		
		</ul>
	</div>
	</nav>
	<div class="row">
		<form action="SalvarCliente" method="get" class="col s12">
			<div class="input-field col s4">
				<input id="txtNome" name="txtNome" type="text" value=""> <label
					for="txtNome">Nome</label>
			</div>

			<div class="input-field col s4">
				<input id="txtFundacao" name="txtFundacao" type="text" value="">
				<label for="txtFundacao">Data Fundação</label>
			</div>
			<div class="input-field col s4">
				<input id="txtRI" type="text" name="txtRI" value=""> <label
					for="txtRI">Regristo de Instituição</label>
			</div>
			<div class="input-field col s4">
				<input id="txtTipoTelefone" name="txtTipoTelefone" type="text"
					value=""> <label for="txtTipoTelefone">Tipo</label>
			</div>
			<div class="input-field col s4">
				<input id="txtDDD" name="txtDDD" type="text" value=""> <label
					for="txtDDD">DDD</label>
			</div>
			<div class="input-field col s4">
				<input id="txtNumeroTelefone" name="txtNumeroTelefone" type="text"
					value=""> <label for=txtNumeroTelefone>Numero</label>
			</div>

			<div class="input-field col s4">
				<input id="txtEmail" name="txtEmail" type="text" value=""> <label
					for="txtEmail">Email</label>
			</div>
			<div class="input-field col s4">
				<input id="txtSenha" name="txtSenha" type="text" value=""> <label
					for="txtSenha">Senha</label>
			</div>
			<div class="input-field col s4">
				<input id="txtSenhaConfirm" name="txtSenhaConfirm" type="text"
					value=""> <label for="txtSenhaConfirm">Confirmar
					Senha</label>
			</div>
			<div id="origem">
				<div class="input-field col s4">
					<input id="txtTipoResidencia" name="txtTipoResidencia" type="text"
						value=""> <label for="txtTipoResidencia">Tipo
						Residencia</label>
				</div>
				<div class="input-field col s4">
					<input id="txtTipoLogradouro" name="txtTipoLogradouro" type="text"
						value=""> <label for="txtTipoLogradouro">Tipo
						Logradouro</label>
				</div>
				<div class="input-field col s4">
					<input id="txtLogradouro" name="txtLogradouro" type="text" value="">
					<label for="txtLogradouro">"Logradouro"</label>
				</div>
				<div class="input-field col s4">
					<input id="txtNumeroEnd" name="txtNumeroEnd" type="text" value="">
					<label for="txtNumeroEnd">Numero de Endereço</label>
				</div>
				<div class="input-field col s4">
					<input id="txtBairro" name="txtBairro" type="text" value="">
					<label for="txtBairro">Bairro</label>
				</div>
				<div class="input-field col s4">
					<input id="txtCep" name="txtCep" type="text" value=""> <label
						for="txtCep">Cep</label>
				</div>
				<div class="input-field col s4">
					<input id="txtCidade" name="txtCidade" type="text" value="">
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



							<li><a href="Home.jsp" class="waves-effect waves-light btn" >Salvar</a></li>

		</form>
	</div>
	<!--Import jQuery before materialize.js-->
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/materialize.min.js"></script>

	  <script>$('select').material_select();</script>

</body>
</html>