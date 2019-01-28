package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Categoria;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class RelacaoLivroCategoriaDAO extends AbstractJdbcDAO {
	public RelacaoLivroCategoriaDAO() {
		super("tb_re_li_cat", "id_livro");
	}

	public void salvar(EntidadeDominio entidade) throws SQLException {
	
		Livro livro = (Livro) entidade;
		openConnection();
		PreparedStatement pst = null;
		int j=0;
		try {
			connection.setAutoCommit(false);

			for(Categoria cat : livro.getCategoria()){
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO tb_re_li_cat(id_categoria_livro_dados, id_livro) ");
				sql.append(	"VALUES (?,?)");

				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1,cat.getRecebe());
				pst.setInt(2, livro.getId());
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				int id=0;
				if (rs.next())
					id = rs.getInt(1);
		//		cliente.getCartao().get(j).setId(id);
				livro.getCategoria().get(j).setRelacat(id);
				connection.commit();
				j++;
			}
			
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
		CategoriaLivroDadosDAO catlivro = new CategoriaLivroDadosDAO();
		catlivro.alterar(livro);
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
				Categoria categoria = new Categoria();
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



