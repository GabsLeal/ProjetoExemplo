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


public class ValidarEntrega implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
//		int initialDelay = 30000; // start after 30 seconds
		Reserva reserva = new Reserva();
		reserva.setCondicao("LIVROENTREGAR");
		ReservaDAO reservaDAO = new ReservaDAO();
		StatusMotivoDAO statusMotivo = new StatusMotivoDAO();
	//	Aluno aluno = new Aluno();
		
		int period = 10000; 
		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
			int i = 1;
			 public void run() {
					System.out.println(i++);
					List<EntidadeDominio> reservas = new ArrayList<EntidadeDominio>();
					try {
						reservas = reservaDAO.consultar(reserva);
						if(!reservas.isEmpty()) {
							for (EntidadeDominio re : reservas ){
								if (re instanceof Reserva) {
									Date agora = new Date();
									int id_usuario = ((Reserva) re).getId_usuario();
									Date retirado = ((Reserva) re).getDateRetirado();
									Date entregar = ((Reserva) re).getDataEntregar();
						//			String tipo_usuario =  ((Reserva) re).getTipoUsuario();
									
								
									if(entregar.compareTo(agora) < 0) {
										entidade.setId(id_usuario);
										System.out.println("Usuario inativo");
										entidade.setMotivo("multa");
										entidade.setStatus(false);
										statusMotivo.alterar(entidade);

									}
									else {
										System.out.println("Usuario ativo");
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
