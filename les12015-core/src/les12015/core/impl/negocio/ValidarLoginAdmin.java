package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.AdminDAO;
import les12015.core.impl.dao.AlunoDAO;
import les12015.dominio.Admin;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;

public class ValidarLoginAdmin implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if (entidade instanceof Admin) {
			Admin admin = (Admin) entidade;
			String nome = admin.getNome();
			String senha = admin.getSenha();
			AdminDAO adminDAO = new AdminDAO();
			if (nome.trim().equals("") || nome == null) {
				return "nome não pode ser vazio. ";
			}
			if (senha.trim().equals("") || senha == null) {
				return "senha não pode ser vazio. ";
			}	
			else {
				try {
					if(adminDAO.consultar(admin).isEmpty()) {
						return "senha ou email errado";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			return "Deve ser registrado um livro!";
		}

		return null;
	
	}

}
