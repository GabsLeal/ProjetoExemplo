package les12015.core.impl.negocio;

import les12015.core.IStrategy;
import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Quantidade;

public class ValidarReserva implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		if (entidade instanceof Quantidade) {
			Quantidade quantidade = (Quantidade) entidade;
			String qt = quantidade.getQuantidade();
			int qt_int = Integer.parseInt(qt);


			
			if (qt_int < 4) {
				return "Estoque em falta!";
			}
			qt_int = qt_int -1;
			qt = String.valueOf(qt_int);
			quantidade.setQuantidade(qt);
		} else {
			return "Deve ser registrado um livro!";
		}

		return null;
	}

}
