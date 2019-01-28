package les12015.core.impl.negocio;

import java.util.List;

import les12015.core.IStrategy;
import les12015.dominio.Categoria;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class ValidarDadosPesquisa implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		if(entidade instanceof Livro){
			Livro livro = (Livro)entidade;

			
			String autor = livro.getAutor();
			String titulo = livro.getTitulo();
			String editora= livro.getEditora();
			String edicao = livro.getEdicao();
			String ISBN = livro.getISBN();
			String precificacao = livro.getPrecificacao();
			String responsavel = livro.getResponsavel();
			
			
	
			if(autor.trim().equals("") || editora.trim().equals("") || edicao.trim().equals("") || responsavel.trim().equals("") ||
					ISBN.trim().equals("") || precificacao.trim().equals("") || titulo.trim().equals("")  ){
				return "Autor, editora, ISBN, numero de paginas, precificação, sinopse, titulo, altura, largura, peso e profundidade, subcategoria são de preenchimento obrigatório!";
			}
		
		}else{
			return "Deve ser registrado um livro!";
		}
		
		
		return null;
	}

}
