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
import les12015.dominio.Livro;

public class StatusMotivoDAO extends AbstractJdbcDAO {

	public StatusMotivoDAO() {
		super("tb_statusmotivo", "id_statusmotivo");
	}
	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	//	Aluno aluno = (Aluno) entidade;
		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_statusmotivo(id_aluno, motivo, data_cadastro, status) VALUES (?,?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
			Timestamp time = new Timestamp(entidade.getDtCadastro().getTime());

			pst.setInt(1, entidade.getId());
			pst.setString(2, entidade.getMotivo());
			pst.setTimestamp(3, time);
			pst.setBoolean(4, entidade.getStatus());


			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
				id = rs.getInt(1);
	//		aluno.setRecebe(id);
	//		aluno.setDado("first");
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		
	//	AlunoDAO re = new AlunoDAO();
	//	re.alterar(aluno);
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
				sql.append("UPDATE tb_statusmotivo SET status=?, motivo=?  WHERE id_aluno=?");
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setBoolean(1, entidade.getStatus());
				pst.setString(2, entidade.getMotivo());

				pst.setInt(3, entidade.getId());

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
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();


			sql.append("select * from tb_statusmotivo  where status = false");			
		

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> estoques = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				
					EntidadeDominio qt = new EntidadeDominio();
						qt.setId(rs.getInt("id_aluno"));
						estoques.add(qt);

												
			}
			return estoques;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
