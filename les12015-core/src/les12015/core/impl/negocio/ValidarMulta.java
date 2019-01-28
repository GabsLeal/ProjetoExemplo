package les12015.core.impl.negocio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import les12015.core.IStrategy;
import les12015.core.impl.dao.MultaDAO;
import les12015.core.impl.dao.ReservaDAO;
import les12015.core.impl.dao.StatusMotivoDAO;
import les12015.dominio.Aluno;
import les12015.dominio.DataEntregar;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Quantidade;
import les12015.dominio.Reserva;


public class ValidarMulta implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
//		int initialDelay = 30000; // start after 30 seconds
	//	Reserva reserva = new Reserva();
	//	reserva.setCondicao("LIVROENTREGAR");
		MultaDAO multaDAO = new MultaDAO();
		StatusMotivoDAO statusMotivo = new StatusMotivoDAO();
	//	Aluno aluno = new Aluno();
	
		int total= 0;
		String valorFinal;
		int period = 10000; 
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			int i = 1;
			 public void run() {
					System.out.println(i++);
					List<EntidadeDominio> multas = new ArrayList<EntidadeDominio>();
					List<EntidadeDominio> resultado = new ArrayList<EntidadeDominio>();

					try {
						multas = statusMotivo.consultar(entidade);
						if(!multas.isEmpty()) {
							for (EntidadeDominio mu : multas ){
								    
									Date agora = new Date();
									int id_usuario = mu.getId();

						//			String tipo_usuario =  ((Reserva) re).getTipoUsuario();
									resultado = multaDAO.consultar(mu);
									if(resultado.isEmpty()) {
										mu.setMotivo("2");
										mu.setResponsavel("pagar");
										multaDAO.salvar(mu);

									}
									else {
										for(EntidadeDominio enti: resultado) {
											int valor = Integer.valueOf(enti.getResponsavel());
											valor = valor + 2;
											enti.setResponsavel(Integer.toString(valor));
											multaDAO.alterar(enti);
										}
						
										
									}

									
								}
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			  }
			};
			 timer.scheduleAtFixedRate(task, 0, period);

		
		return null;
	}

}
