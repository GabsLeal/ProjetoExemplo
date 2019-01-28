package les12015.core.impl.negocio;

import java.sql.SQLException;

import les12015.core.IStrategy;
import les12015.core.impl.dao.AlunoDAO;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;

public class ValidarLoginAluno implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if (entidade instanceof Aluno) {
			Aluno aluno = (Aluno) entidade;
			String ra = aluno.getRa();
			String senha = aluno.getSenha();
			String email = aluno.getEmail();
			String situ = aluno.getSituacao();
			AlunoDAO alunoDAO = new AlunoDAO();
			if (situ != null) {
				if (ra.trim().equals("") || ra == null) {
					return "ra não pode ser vazio. ";
				}
				if (senha.trim().equals("") || senha == null) {
					return "senha não pode ser vazio. ";
				}	
				else {
					try {
						if(alunoDAO.consultar(aluno).isEmpty()) {
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
