package les12015.core.impl.negocio;

import java.util.ArrayList;
import java.util.List;

import les12015.core.IStrategy;
import les12015.dominio.Categoria;
import les12015.dominio.EntidadeDominio;

import les12015.dominio.Livro;

public class ValidadorDadosObrigatoriosLivro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		if (entidade instanceof Livro) {
			Livro livro = (Livro) entidade;

			String autor = livro.getAutor();
			String titulo = livro.getTitulo();
			String editora = livro.getEditora();
			String edicao = livro.getEdicao();
			String ISBN = livro.getISBN();
			String paginas = livro.getnPaginas();
			String sinopse = livro.getSinopse();
			String altura = livro.getDimensao().getAltura();
			String largura = livro.getDimensao().getLargura();
			String peso = livro.getDimensao().getPeso();
			String profundidade = livro.getDimensao().getProfundidade();
			String responsavel = livro.getResponsavel();
			List<Categoria> listaCat = livro.getCategoria();

			String situ = livro.getSituacao();

			if (situ == null) {
				if (autor == null || autor.trim().equals("")) {
					return "Autor nao pode ser vazio";
				}
				if (editora == null || editora.trim().equals("")) {
					return "editora nao pode ser vazio";
				}
				if (edicao == null || edicao.trim().equals("")) {
					return "edicao nao pode ser vazio";
				}
				if (responsavel == null || responsavel.trim().equals("")) {
					return "responsavel nao pode ser vazio";
				}
				if (paginas == null || paginas.trim().equals("")) {
					return "paginas nao pode ser vazio";
				}
				if (ISBN == null || ISBN.trim().equals("")) {
					return "ISBN nao pode ser vazio";
				}
				if (sinopse == null || sinopse.trim().equals("")) {
					return "sinopse nao pode ser vazio";
				}
				if (titulo == null || titulo.trim().equals("")) {
					return "titulo nao pode ser vazio";
				}
				if (altura == null || altura.trim().equals("")) {
					return "altura nao pode ser vazio";
				}
				if (largura == null || largura.trim().equals("")) {
					return "largura nao pode ser vazio";
				}
				if (peso == null || peso.trim().equals("")) {
					return "peso nao pode ser vazio";
				}
				if (profundidade == null || profundidade.trim().equals("")) {
					return "profundidade nao pode ser vazio";
				}
				if (listaCat == null) {
					return "Categoria nao pode ser vazio";
				}
			}
			if (situ != null) {
				if (situ.equals("alterarlivro")) {
					if (autor == null || autor.trim().equals("")) {
						return "Autor nao pode ser vazio";
					}
					if (editora == null || editora.trim().equals("")) {
						return "editora nao pode ser vazio";
					}
					if (edicao == null || edicao.trim().equals("")) {
						return "edicao nao pode ser vazio";
					}
					if (responsavel == null || responsavel.trim().equals("")) {
						return "responsavel nao pode ser vazio";
					}
					if (paginas == null || paginas.trim().equals("")) {
						return "paginas nao pode ser vazio";
					}
					if (ISBN == null || ISBN.trim().equals("")) {
						return "ISBN nao pode ser vazio";
					}
					if (sinopse == null || sinopse.trim().equals("")) {
						return "sinopse nao pode ser vazio";
					}
					if (titulo == null || titulo.trim().equals("")) {
						return "titulo nao pode ser vazio";
					}
					if (altura == null || altura.trim().equals("")) {
						return "altura nao pode ser vazio";
					}
					if (largura == null || largura.trim().equals("")) {
						return "largura nao pode ser vazio";
					}
					if (peso == null || peso.trim().equals("")) {
						return "peso nao pode ser vazio";
					}
					if (profundidade == null || profundidade.trim().equals("")) {
						return "profundidade nao pode ser vazio";
					}

				}
			}

		} else {
			return "Deve ser registrado um livro!";
		}

		return null;
	}

}
