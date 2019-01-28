package les12015.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Admin;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;
import les12015.dominio.Quantidade;

public class EstoqueDAO  extends AbstractJdbcDAO {

	public EstoqueDAO() {
		super("tb_estoque_livro", "id_estoque");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

		Quantidade estoque = (Quantidade) entidade;
		openConnection();
		PreparedStatement pst = null;
		if (estoque.getId() == null) {
		
				try {
					connection.setAutoCommit(false);

					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO tb_estoque_livro(id_livro,qt,id_biblioteca) VALUES (?,?,?)");
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
				//	Timestamp time = new Timestamp(livro.getDtCadastro().getTime());
					pst.setInt(1, estoque.getId_livro());
					pst.setString(2, estoque.getQuantidade());
					pst.setInt(3, estoque.getId_biblioteca());

			//		pst.setTimestamp(3, time);
					pst.executeUpdate();
					ResultSet rs = pst.getGeneratedKeys();
//					int id = 0;
//					if (rs.next())
//						id = rs.getInt(1);
//					livro.setId(id);
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
				// RelacaoLivroCategoriaDAO re = new RelacaoLivroCategoriaDAO();
				
			
		}
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Quantidade estoque = (Quantidade) entidade;
		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_estoque_livro SET qt=?  WHERE id_estoque=?");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(estoque.getNome() != null) {
				if(estoque.getNome().equals("devolverEstoque")) {
					pst.setString(1, estoque.getResponsavel());
					pst.setInt(2, estoque.getId_biblioteca());
				}
			}
			else {
				pst.setString(1, estoque.getQuantidade());
				pst.setInt(2, estoque.getId());
			}
			pst.executeUpdate();
	//		ResultSet rs = pst.getGeneratedKeys();
		//	if (rs.next())
	//			id = rs.getInt(1);

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
	
		if(estoque.getMotivo() == null) {
			ReservaDAO reserva = new ReservaDAO();
			reserva.salvar(estoque);
		}

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();
		Quantidade estoque = (Quantidade) entidade;

		if(estoque.getSituacao() != null) {
			if(estoque.getSituacao().equals("biblioteca")) {
				sql.append("select * from tb_estoque_livro where id_livro="+ estoque.getId_livro()+ " and id_biblioteca="+estoque.getId_biblioteca());			

			}
		}
		else {
			sql.append("select * from tb_estoque_livro");			
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> estoques = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				
					Quantidade qt = new Quantidade();
						qt.setQuantidade(rs.getString("qt"));
						qt.setId(rs.getInt("id_estoque"));
						estoques.add(qt);

												
			}
			return estoques;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
