package les12015.core.impl.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import les12015.core.IStrategy;
import les12015.core.impl.dao.ReservaDAO;
import les12015.core.impl.dao.StatusMotivoDAO;
import les12015.dominio.Aluno;
import les12015.dominio.DataEntregar;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Quantidade;
import les12015.dominio.Reserva;


public class ValidarStatus implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
//		int initialDelay = 30000; // start after 30 seconds


if(true) {
	
}
			Reserva reserva = new Reserva();
			reserva.setTipoUsuario(entidade.getTipoUser());
			reserva.setId_usuario(entidade.getIdUser());
			reserva.setCondicao("SITUACAO");
			ReservaDAO reservaDAO = new ReservaDAO();
			//	Aluno aluno = new Aluno();
				
				List<EntidadeDominio> reservas = new ArrayList<EntidadeDominio>();
				try {
					if(reservaDAO.consultar(reserva).isEmpty()) {
						return "Usuario inativo por atraso";
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		
		
		return null;
	}

}
