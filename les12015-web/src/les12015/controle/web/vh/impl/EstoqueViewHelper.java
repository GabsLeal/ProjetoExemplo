
package les12015.controle.web.vh.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import les12015.controle.web.vh.IViewHelper;
import les12015.core.aplicacao.Resultado;
import les12015.core.util.ConverteDate;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Dimensao;
import les12015.dominio.Livro;
import les12015.dominio.Quantidade;
import les12015.dominio.SubCategoria;
import les12015.dominio.Categoria;

public class EstoqueViewHelper implements IViewHelper {
	/**
	 * TODO Descri��o do M�todo
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @see les2017.controle.web.vh.IViewHelper#getEntidade(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		
		String status = request.getParameter("idstatus");
		String id = request.getParameter("livroId");
		String id_alt = request.getParameter("txtId");
		String historico = request.getParameter("situacao");
		String historicoId = request.getParameter("historicoId");
		String situ= request.getParameter("recebeLivro");
		String estoque= request.getParameter("estoque");
		String tipoUsuario= request.getParameter("tipoUsuario");


		Livro livro = new Livro();
		Quantidade estoque1 = new Quantidade();
		if(request.getParameter("operacao").equals("SALVAR") || request.getParameter("operacao").equals("ALTERAR")){

			String qt = request.getParameter("txtQt");
			String id_livro = request.getParameter("txtIdLivro");
			String id_biblioteca = request.getParameter("idBiblioteca");
			String idLivro = request.getParameter("idLivro");
			String idBiblioteca = request.getParameter("idBiblioteca");
			String idUsuario = request.getParameter("idUsuario");
			String idEstoque = request.getParameter("idEstoque");
			String qtEstoque = request.getParameter("qtEstoque");

			
			
			
			
			if(idLivro != null) {
				estoque1.setId_livro(Integer.valueOf(idLivro));
				estoque1.setId_biblioteca(Integer.valueOf(idBiblioteca));
				estoque1.setId_usuario(Integer.valueOf(idUsuario));
				estoque1.setId(Integer.valueOf(idEstoque));
				estoque1.setQuantidade(qtEstoque);
				estoque1.setTipoUsuario(tipoUsuario);
				estoque1.setTipoUser(tipoUsuario);
				estoque1.setIdUser(Integer.valueOf(idUsuario));


			}
			else
			{
				estoque1.setQuantidade(qt);
				estoque1.setId_livro(Integer.valueOf(id_livro));
				estoque1.setId_biblioteca(Integer.valueOf(id_biblioteca));
			}
			
	//		livro.getQuantidade().setQuantidade(qt);
	//		livro.setId(Integer.valueOf(id_livro));
		


		
		}
		if(request.getParameter("operacao").equals("CONSULTAR")){
			
			if(situ == null) {
				String autor = request.getParameter("txtAutor");
				String titulo = request.getParameter("txtTitulo");
				String editora = request.getParameter("txtEditora");
				String edicao = request.getParameter("txtEdicao");
				String isbn = request.getParameter("txtISBN");
				String precificacao = request.getParameter("txtPrecificacao");
				String cod_barra = request.getParameter("txtCodBarra");
				livro.setAutor(autor);
				livro.setTitulo(titulo);
				livro.setEditora(editora);
				livro.setEdicao(edicao);
				livro.setISBN(isbn);
				livro.setPrecificacao(precificacao);
				livro.setCod_barra(cod_barra);
			}
			else {
				livro.setSituacao(situ);
			}

		}

		
		return estoque1;
	}

	/**
	 * TODO Descri��o do M�todo
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @see les2017.controle.web.vh.IViewHelper#setView(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String operacao = request.getParameter("operacao");

		RequestDispatcher d = null;
		String def = request.getParameter("historicoId");
		String situ= request.getParameter("recebeLivro");
		String tipoUsuario= request.getParameter("tipoUsuario");


		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("Home.jsp");
			} else if (operacao.equals("ALTERAR")) {
				if(tipoUsuario != null) {
					if(tipoUsuario.equals("biblioteca")) {
						d = request.getRequestDispatcher("PerfilBiblioteca.jsp");
					}
					if(tipoUsuario.equals("aluno")) {
						d = request.getRequestDispatcher("PerfilAluno.jsp");
					}
				}
				
			} else if (operacao.equals("CONSULTAR")) {
				if (def != null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("HistoricoLivro.jsp");
				}
				if (request.getParameter("txtAutor") != null || request.getParameter("txtTitulo") != null
						|| request.getParameter("txtEditora") != null || request.getParameter("txtEdicao") != null
						|| request.getParameter("txtISBN") != null || request.getParameter("txtPrecificacao") != null
						|| request.getParameter("txtCodBarra") != null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("Livro.jsp");
				}
				
				if (request.getParameter("consulta") != null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("Livro.jsp");
				}
				if(request.getParameter("livroId") !=null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("AlterarLivro.jsp");
				}
				if(situ != null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("AdicionarEstoque.jsp");
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
