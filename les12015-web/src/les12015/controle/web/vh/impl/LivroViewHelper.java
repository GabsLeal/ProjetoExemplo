
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

public class LivroViewHelper implements IViewHelper {
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


		Livro livro = new Livro();

		if(request.getParameter("operacao").equals("SALVAR") || request.getParameter("operacao").equals("ALTERAR")){
			String[] idCats = request.getParameterValues("idCat");
			String[] idSubCats = request.getParameterValues("idSubCat");
			String categoriape = request.getParameter("txtCategoria");
			String autor = request.getParameter("txtAutor");
			String ano = request.getParameter("dtAno");
			String titulo = request.getParameter("txtTitulo");
			String editora = request.getParameter("txtEditora");
			String edicao = request.getParameter("txtEdicao");
			String isbn = request.getParameter("txtISBN");
			String pagina = request.getParameter("txtPagina");
			String sinopse = request.getParameter("txtSinopse");
			String altura = request.getParameter("txtAltura");
			String largura = request.getParameter("txtLargura");
			String peso = request.getParameter("txtPeso");
			String profundidade = request.getParameter("txtProfundidade");
			String precificacao = request.getParameter("txtPrecificacao");
			String responsavel = request.getParameter("txtResponsavel");
			String cod_barra = request.getParameter("txtCodBarra");				

			livro.setAutor(autor);
			livro.setAno(ano);
			livro.setTitulo(titulo);
			livro.setEditora(editora);
			livro.setEdicao(edicao);
			livro.setISBN(isbn);
			livro.setnPaginas(pagina);
			livro.setSinopse(sinopse);
			livro.setDimensao(new Dimensao());
			livro.getDimensao().setAltura(altura);
			livro.getDimensao().setLargura(largura);
			livro.getDimensao().setPeso(peso);
			livro.getDimensao().setProfundidade(profundidade);
			livro.setPrecificacao(precificacao);
			livro.setResponsavel(responsavel);
			livro.setCod_barra(cod_barra);
			List<Categoria> listaCat = new ArrayList<Categoria>();
			if(idCats != null){
				for(String idCat : idCats){
					Categoria cat = new Categoria();
					cat.setDescricao(idCat);
					listaCat.add(cat);
				}
			}
			livro.setCategoria(listaCat);

		
		}
		if(request.getParameter("operacao").equals("CONSULTAR")){
			
			
			String usuario = request.getParameter("txtIdUsuario");
			String txtIdBiblioteca = request.getParameter("txtIdBiblioteca");

			if(situ == null && usuario == null && txtIdBiblioteca == null) {
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
			else if(usuario != null) {
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
				livro.setId_usuario(Integer.valueOf(usuario));
			}
			else if(txtIdBiblioteca != null) {
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
				livro.setQuantidade(new Quantidade());
				livro.getQuantidade().setId_biblioteca(Integer.valueOf(txtIdBiblioteca));
			}
			else {
				livro.setSituacao(situ);
			}

		}

		
		if (id_alt != null) {
			livro.setId(Integer.valueOf(id_alt));
			livro.setStatus(Boolean.valueOf(status));
			livro.setSituacao("alterarlivro");

		}
		if (id != null) {
			livro.setId(Integer.valueOf(id));
			livro.setSituacao("alterarlivro");
		}
//		if (historico != null ) {
//			livro.setSituacao(historico);
//			livro.setId(Integer.valueOf(historicoId));
//		}
		return livro;
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
		
		String id_usuario= request.getParameter("txtIdUsuario");

		String txtIdBiblioteca= request.getParameter("txtIdBiblioteca");



		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				d = request.getRequestDispatcher("Home.jsp");
			} else if (operacao.equals("ALTERAR")) {
				d = request.getRequestDispatcher("Home.jsp");
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
				if(id_usuario != null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("LivroEstoque.jsp");
				}
				if(txtIdBiblioteca != null) {
					request.setAttribute("listaLivro", resultado.getEntidades());
					d = request.getRequestDispatcher("LivroEstoqueBiblioteca.jsp");
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
