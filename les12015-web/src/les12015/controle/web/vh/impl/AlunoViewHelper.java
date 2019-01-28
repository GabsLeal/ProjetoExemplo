package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Aluno;
import les12015.dominio.Cidade;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Estado;
import les12015.dominio.Logradouro;
import les12015.dominio.Pais;
import les12015.dominio.Telefone;

public class AlunoViewHelper implements IViewHelper{

	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Aluno aluno = new Aluno();

		String id = request.getParameter("alunoId");
		String id_alt = request.getParameter("txtId");
		String situacao = request.getParameter("situacao");
		String historicoId = request.getParameter("historicoId");;
		String status = request.getParameter("idstatus");
		String motivo = request.getParameter("txtMotivo");
		String login = request.getParameter("login");

		if (request.getParameter("operacao").equals("SALVAR") || request.getParameter("txtMotivo") == null) {
			String genero = request.getParameter("txtGenero");
			String nome = request.getParameter("txtNome");
			String data_nascimento = request.getParameter("txtNascimento");
			String ra = request.getParameter("txtRA");
			String tipo_telefone = request.getParameter("txtTipoTelefone");
			String ddd = request.getParameter("txtDDD");
			String telefone = request.getParameter("txtNumeroTelefone");
			String email = request.getParameter("txtEmail");
			String matricula = request.getParameter("txtMatricula");
			String senha = request.getParameter("txtSenha");
			String confir_senha = request.getParameter("txtSenhaConfirm");
			String tipo_residencia = request.getParameter("txtTipoResidencia");
			String tipo_logradouro = request.getParameter("txtTipoLogradouro");
			String logradouro = request.getParameter("txtLogradouro");
			String numero = request.getParameter("txtNumeroEnd");
			String bairro = request.getParameter("txtBairro");
			String cep = request.getParameter("txtCep");
			String cidade = request.getParameter("txtCidade");
			String estado = request.getParameter("txtEstado");
			String pais = request.getParameter("txtPais");
			

		
				
				aluno.setNome(nome);
				aluno.setGenero(genero);
				aluno.setData_nascimento(data_nascimento);
				aluno.setRa(ra);
				aluno.setTelefone(new Telefone());
				aluno.getTelefone().setTipo(tipo_telefone);
				aluno.getTelefone().setDdd(ddd);
				aluno.getTelefone().setNumero(telefone);
				aluno.setEmail(email);
				aluno.setSenha(senha);
				aluno.setMatricula(matricula);
				aluno.setSenhaconfirma(confir_senha);
				aluno.setEndereco(new Endereco());
				aluno.getEndereco().setTipo(tipo_residencia);
				aluno.getEndereco().setLogradouro(new Logradouro());
				aluno.getEndereco().getLogradouro().setTipo(tipo_logradouro);
				aluno.getEndereco().getLogradouro().setNome(logradouro);
				aluno.getEndereco().setNumero(numero);
				aluno.getEndereco().setCep(cep);
				aluno.getEndereco().setCidade(new Cidade());
				aluno.getEndereco().getCidade().setNome(cidade);
				aluno.getEndereco().getCidade().setEstado(new Estado());
				aluno.getEndereco().getCidade().setPais(new Pais());
				aluno.getEndereco().getCidade().getPais().setNome(pais);

				aluno.getEndereco().getCidade().getEstado().setNome(estado);




				aluno.getEndereco().setBairro(bairro);

				
			
			

		}
		if(motivo != null) {
			
			String id_aluno = request.getParameter("AlunoId");

			aluno.setMotivo(motivo);
			aluno.setId(Integer.valueOf(id_aluno));
			aluno.setStatus(Boolean.valueOf(status));
			aluno.setSituacao("alterarstatus");

		}
		if (request.getParameter("operacao").equals("CONSULTAR")) {
			if(id == null || id.equals("")){
				String genero = request.getParameter("txtGenero");
				String nome = request.getParameter("txtNome");
				String ra = request.getParameter("txtra");
				aluno.setNome(nome);
				aluno.setGenero(genero);
				aluno.setRa(ra);
			}
			if(login != null) {
				String senhaLogin = request.getParameter("txtSenha");
				String raLogin = request.getParameter("txtRa");
				aluno.setSenha(senhaLogin);
				aluno.setRa(raLogin);
				aluno.setSituacao(login);
			}
			else{
				aluno.setId_alt(Integer.valueOf(id));
			}
			
		}

		if (id_alt != null) {
			aluno.setId(Integer.valueOf(id_alt));
			aluno.setSituacao("alteraraluno");
		}
		if (situacao != null ) {
			if(situacao.equals("historico")) {
				aluno.setSituacao(situacao);
				aluno.setId(Integer.valueOf(historicoId));
			}
			else if(situacao.equals("status")) {
				aluno.setSituacao(situacao);
				aluno.setId(Integer.valueOf(historicoId));
			}

		}
		

		return aluno;

	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;
		String def = request.getParameter("historicoId");
		String situacao = request.getParameter("situacao");
		String login = request.getParameter("login");


		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("Home.jsp");
			} else if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("Home.jsp");
			} else if (operacao.equals("CONSULTAR")) {
				if (request.getParameter("txtra") != null || request.getParameter("txtNome") != null
						|| request.getParameter("txtGenero") != null) {
					request.setAttribute("listaAluno", resultado.getEntidades());
					d = request.getRequestDispatcher("Aluno.jsp");
				}
				if (def != null) {
					request.setAttribute("listaAluno", resultado.getEntidades());
					d = request.getRequestDispatcher("HistoricoAluno.jsp");
				}
				if(request.getParameter("alunoId") !=null) {
					request.setAttribute("listaAluno", resultado.getEntidades());
					d = request.getRequestDispatcher("AlterarAluno.jsp");
				}
				if(situacao != null) {
					if(situacao.equals("status")) {
						request.setAttribute("listaAluno", resultado.getEntidades());
						d = request.getRequestDispatcher("Status.jsp");
					}
				}
				if(login != null) {
					HttpSession session = request.getSession(true);
					session.setAttribute("listaAluno", resultado.getEntidades());
					d = request.getRequestDispatcher("PerfilAluno.jsp");
				}
				
			}
		} else {
			request.setAttribute("resposta", resultado.getMsg());
			d = request.getRequestDispatcher("Resposta.jsp");
		}

		try {
			d.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	}


