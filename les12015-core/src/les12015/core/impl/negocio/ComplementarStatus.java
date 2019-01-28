package les12015.core.impl.negocio;



import les12015.core.IStrategy;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class ComplementarStatus implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
		if(entidade !=null){
			if (entidade instanceof Aluno ) {
				Aluno aluno = (Aluno) entidade;
				if(aluno.getSituacao() == null) {
					boolean status = true;		
					entidade.setStatus(status);
				}
			}
			else {
				boolean status = true;		
				entidade.setStatus(status);
			}

		}else{
			return "Entidade: "+entidade.getClass().getCanonicalName()+" nula!";
		}
		
		return null;
	}

}
