package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;
import les12015.dominio.Pessoa;

public class LoginViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
	//	Professor professor = new Professor();
//		Instituicao instituicao = new Instituicao();

		
		if(request.getParameter("operacao").equals("CONSULTAR")){
		
			String tipoUsuario = request.getParameter("tipoUsuario");
			Livro livro = new Livro();
			Aluno aluno = new Aluno();
			Pessoa pessoa = new Pessoa();
			if(tipoUsuario.equals("admin")){
				String email = request.getParameter("txtEmail");
				String senha = request.getParameter("txtSenha");
				
				pessoa.setEmail(email);
				pessoa.setSenha(senha);
				pessoa.setNome(tipoUsuario);
				return pessoa;
			}
			else if(tipoUsuario.equals("aluno")){
				return aluno;
				
			}
			else if (tipoUsuario.equals("professor")){
				
			}
			else if (tipoUsuario.equals("instituicao")){
				
			}

		}
	
		return null;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;
		String def = request.getParameter("historicoId");


		if (resultado.getMsg() == null) {
			if (operacao.equals("CONSULTAR")) {
				d = request.getRequestDispatcher("Home.jsp");
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
