package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import les12015.dominio.DataEntregar;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Reserva;

public class DataEntregarDAO  extends AbstractJdbcDAO {

	public DataEntregarDAO() {
		super("tb_livro_entregar", "id_livro_entregar");
	}
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		DataEntregar dataEntregar = (DataEntregar) entidade;
		openConnection();
		PreparedStatement pst = null;
		
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_livro_entregar(id_reserva,data_retirado,data_entregar) VALUES (?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
			Timestamp cadastro = new Timestamp(dataEntregar.getDtCadastro().getTime());
			Timestamp entregar = new Timestamp(dataEntregar.getData_entregar().getTime());

			pst.setInt(1, dataEntregar.getId_reserva());
			pst.setTimestamp(2, cadastro);
			pst.setTimestamp(3, entregar);


			pst.executeUpdate();

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		ReservaDAO reservaDAO = new ReservaDAO();
		Reserva reserva = new Reserva();
		reserva.setCondicao("retirando");
		reserva.setId(dataEntregar.getId_reserva());
		reserva.setTipoUsuario(dataEntregar.getTipoUsuario());
		reserva.setId_usuario(dataEntregar.getId_usuario());
		reserva.setId_livro(dataEntregar.getId_livro());
		reserva.setId_estoque(dataEntregar.getId_estoque());
		reservaDAO.alterar(reserva);
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
