package les12015.controle.web.vh.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.dominio.Biblioteca;
import les12015.dominio.Categoriadados;
import les12015.dominio.Cidade;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class BibliotecaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Biblioteca biblioteca = new Biblioteca();
		String id_livro = request.getParameter("recebeLivro");
		String user = request.getParameter("txtUser");
		String senha = request.getParameter("txtSenha");
		if (request.getParameter("operacao").equals("CONSULTAR")) {
			if(id_livro != null){
				biblioteca.setLivro(new Livro());
				biblioteca.getLivro().setId(Integer.valueOf(id_livro));

			}
			else {
				biblioteca.setNome(user);
				biblioteca.setSenha(senha);
			}
			
		}
		return biblioteca;
		
		}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
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
				if (def != null) {
					request.setAttribute("listaAluno", resultado.getEntidades());
					d = request.getRequestDispatcher("HistoricoAluno.jsp");
				}
				if(request.getParameter("alunoId") !=null) {
					request.setAttribute("listaAluno", resultado.getEntidades());
					d = request.getRequestDispatcher("AlterarAluno.jsp");
				}
				if(situacao != null) {
					if(situacao.equals("addEstoque")) {
						request.setAttribute("listaBiblioteca", resultado.getEntidades());
						d = request.getRequestDispatcher("AdicionarEstoque.jsp");
					}
				}
				if(login != null) {
					HttpSession session = request.getSession(true);
					session.setAttribute("listaBiblioteca", resultado.getEntidades());
					d = request.getRequestDispatcher("PerfilBiblioteca.jsp");
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
