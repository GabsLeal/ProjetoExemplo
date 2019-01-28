package les12015.core.impl.negocio;

import java.util.Calendar;
import java.util.Date;

import les12015.core.IStrategy;
import les12015.dominio.DataEntregar;
import les12015.dominio.EntidadeDominio;


public class ValidarRetirada implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		
		if (entidade instanceof DataEntregar) {
			DataEntregar dataEntregar = (DataEntregar) entidade;
			String tipoUsuario = dataEntregar.getTipoUsuario();
			Date data = new Date();	
			Calendar c = Calendar.getInstance();
			Date entregar = new Date();
			if(tipoUsuario != null) {
				if(tipoUsuario.equals("aluno")) {
					//Date entregar = new Date(data.getTime() + (1000 * 60 * 60 * 168));
					c.setTime(entregar);
					c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 7);
					dataEntregar.setDtCadastro(data);
					dataEntregar.setData_entregar(c.getTime());
				}
				else if(tipoUsuario.equals("biblioteca")) {
					
					c.setTime(entregar);
					c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 6);
			//		Date entregar = new Date(data.getTime() + (1000 * 60 * 60 * 4380));
					dataEntregar.setDtCadastro(data);
					dataEntregar.setData_entregar(c.getTime());
				}
				else if (tipoUsuario.equals("professor")) {
					c.setTime(entregar);
					c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
			//		Date entregar = new Date(data.getTime() + (1000 * 60 * 60 * 4380));
					dataEntregar.setDtCadastro(data);
					dataEntregar.setData_entregar(c.getTime());
				}
			}

		}
		
		return null;
	}

}
