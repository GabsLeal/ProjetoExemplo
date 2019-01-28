package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Admin;
import les12015.dominio.EntidadeDominio;


public class AdminDAO extends AbstractJdbcDAO {

	public AdminDAO() {
		super("tb_admin", "id_admin");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();
		Admin admin = (Admin) entidade;

		sql.append("select * from tb_admin where login = ? and senha = ?");			
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, admin.getNome());
			pst.setString(2, admin.getSenha());

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> admins = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				
					Admin ad = new Admin();
						ad.setId(rs.getInt("id_admin"));
						ad.setNome(rs.getString("login"));
						ad.setSenha(rs.getString("senha"));
						
						admins.add(ad);

												
			}
			return admins;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	
	}

}
