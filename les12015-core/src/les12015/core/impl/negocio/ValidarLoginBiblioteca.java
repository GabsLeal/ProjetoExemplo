package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.AdminDAO;
import les12015.core.impl.dao.AlunoDAO;
import les12015.core.impl.dao.BibiotecaDAO;
import les12015.dominio.Admin;
import les12015.dominio.Aluno;
import les12015.dominio.Biblioteca;
import les12015.dominio.EntidadeDominio;

public class ValidarLoginBiblioteca implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if (entidade instanceof Biblioteca) {
			Biblioteca biblioteca = (Biblioteca) entidade;
			String nome = biblioteca.getNome();
			String senha = biblioteca.getSenha();
			BibiotecaDAO bibliotecaDAO = new BibiotecaDAO();
			if(biblioteca.getLivro() == null) {
				if (nome.trim().equals("") || nome == null) {
					return "nome n�o pode ser vazio. ";
				}
				if (senha.trim().equals("") || senha == null) {
					return "senha n�o pode ser vazio. ";
				}	
				else {
					try {
						if(bibliotecaDAO.consultar(biblioteca).isEmpty()) {
							return "senha ou email errado";
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} else {
			return "Deve ser registrado um livro!";
		}

		return null;
	
	}

}
