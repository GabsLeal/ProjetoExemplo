package les12015.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;

public class MultaDAO extends AbstractJdbcDAO {

	public MultaDAO() {
		super("tb_multa", "id_multa");
	}
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
//		Aluno aluno = (Aluno) entidade;
		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_multa(id_usuario, valor, condicao) VALUES (?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
	//		Timestamp time = new Timestamp(entidade.getDtCadastro().getTime());

		//	pst.setInt(1, entidade.getIdreserva());
			pst.setInt(1, entidade.getId());
			pst.setString(2, entidade.getMotivo());
			pst.setString(3, entidade.getResponsavel());


			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
				id = rs.getInt(1);

			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		int id = 0;
		openConnection();
		PreparedStatement pst = null;
			try {
				connection.setAutoCommit(false);
				StringBuilder sql = new StringBuilder();
				if(entidade.getMotivo() !=null) {
					if(entidade.getMotivo().equals("pago")) {
						sql.append("UPDATE tb_multa SET condicao=?  WHERE id_usuario=?");
						pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
						pst.setString(1, entidade.getMotivo());
					}
				}else {
					sql.append("UPDATE tb_multa SET valor=?  WHERE id_usuario=?");
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					pst.setString(1, entidade.getResponsavel());
				}
				pst.setInt(2, entidade.getId());
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
			if(entidade.getMotivo() != null) {
				if(entidade.getMotivo().equals("pago")) {
					StatusMotivoDAO status = new StatusMotivoDAO();
					status.alterar(entidade);
				}
			}
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();


			sql.append("select * from tb_multa  where condicao = 'pagar' and id_usuario = " + entidade.getId());			
		

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> estoques = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				
					EntidadeDominio qt = new EntidadeDominio();
						qt.setResponsavel(rs.getString("valor"));
						qt.setId(rs.getInt("id_usuario"));
						estoques.add(qt);

												
			}
			return estoques;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
