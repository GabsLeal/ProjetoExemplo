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


public class CategoriaLivroDadosDAO extends AbstractJdbcDAO {

	public CategoriaLivroDadosDAO() {
		super("tb_categoria_livro_dados", "id_categoria_livro_dados");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Livro livro = (Livro) entidade;

		int id = 0;
		openConnection();
		PreparedStatement pst = null;
		int j=0;
		if(livro.getCategoria() != null){
			try {
				connection.setAutoCommit(false);
			for(Categoria cat : livro.getCategoria()){
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO tb_categoria_livro_dados(id_livro, categoria) ");
				sql.append("VALUES (?,?)");
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, livro.getId());
				pst.setString(2, cat.getDescricao());
				pst.executeUpdate();
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next())
					id = rs.getInt(1);
		//		cliente.getCartao().get(j).setId(id);
				livro.getCategoria().get(j).setRecebe(id);
			//	cat.setRecebe(id);
				connection.commit();
				j++;
				}
			}catch (SQLException e) {
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


		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Livro livro = (Livro) entidade;
		openConnection();
		PreparedStatement pst = null;
			try {
				connection.setAutoCommit(false);
				for(Categoria cat : livro.getCategoria()){

				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tb_categoria_livro_dados SET id_re_li_cat=? WHERE id_categoria_livro_dados=?");
				pst = connection.prepareStatement(sql.toString());
				pst.setInt(1, cat.getRelacat());
				pst.setInt(2, cat.getRecebe());
				pst.executeUpdate();
				

				connection.commit();
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

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Categoria cat = (Categoria) entidade;
		PreparedStatement pst = null;
		String sql = null;
		if(cat.getSituacao() != null){
			sql="SELECT * FROM tb_cliente JOIN tb_cliente_endereco USING (id_cliente) WHERE id_cliente=?";
		}
		else{
			sql="SELECT * FROM public.tb_categoria JOIN public.tb_categoria_livro_dados ON public.tb_categoria_livro_dados.id_cat = public.tb_categoria.id_cat JOIN public.tb_re_li_cat ON public.tb_re_li_cat.id_re_li_cat = public.tb_categoria_livro_dados.id_re_li_cat  where public.tb_re_li_cat.id_livro=?";

		}
		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			pst.setInt(1, cat.getRecebe());
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				Categoria cate = new Categoria();
	//			listaEnd = new ArrayList<Endereco>();
				cate.setId(rs.getInt("id_cat"));
				cate.setDescricao(rs.getString("descricao"));
				cate.setRecebe(rs.getInt("id_livro"));
				cate.setRelacat(rs.getInt("id_re_li_cat"));
								
			//	listaEnd.add(ende);
				categorias.add(cate);
			}
			return categorias;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	

	}
