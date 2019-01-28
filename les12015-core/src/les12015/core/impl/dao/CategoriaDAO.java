package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Categoria;
import les12015.dominio.Categoriadados;
import les12015.dominio.EntidadeDominio;

public class CategoriaDAO extends AbstractJdbcDAO {

	public CategoriaDAO() {
		super("tb_categoria", "id_cat");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {

	

	}

	/**
	 * TODO Descrição do Método
	 * 
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Categoriadados cat = (Categoriadados) entidade;

		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_categoria SET descricao=?, tipo_cat=?, editora=?, edicao=?, isbn=? WHERE id_cat=?");

			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, cat.getDescricao());
			pst.setString(2, cat.getTipo_cat());
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

	/**
	 * TODO Descrição do Método
	 * 
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

		PreparedStatement pst = null;

		String sql = null;
			sql="SELECT * FROM tb_categoria";
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Categoriadados categoria = new Categoriadados();
				categoria.setId(rs.getInt("id_cat"));
				categoria.setDescricao(rs.getString("descricao"));
				categoria.setTipo_cat(rs.getString("tipo_cat"));
				categorias.add(categoria);
			}
			return categorias;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}

