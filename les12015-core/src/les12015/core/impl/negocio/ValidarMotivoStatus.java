package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class ValidarMotivoStatus implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if(entidade instanceof Aluno){
			Aluno aluno = (Aluno)entidade;

			
			String motivo = aluno.getMotivo();
				
	
			if(motivo.trim().equals("") || motivo == null  ){
				return "Não pode ser nulo";
			}
		
		}else{
			return "Deve ser registrado um usuario!";
		}
		return null;
	}

}
