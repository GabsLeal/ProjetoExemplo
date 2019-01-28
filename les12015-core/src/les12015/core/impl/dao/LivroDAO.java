
package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import les12015.dominio.Categoria;
import les12015.dominio.Dimensao;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;
import les12015.dominio.Quantidade;

public class LivroDAO extends AbstractJdbcDAO {

	public LivroDAO() {
		super("tb_livro", "id_livro");
	}

	public void salvar(EntidadeDominio entidade) throws SQLException {

		Livro livro = (Livro) entidade;
		openConnection();
		PreparedStatement pst = null;
		if (livro.getId() == null || livro.getId().equals("")) {
			if (livro.getSituacao() == null) {
				try {
					connection.setAutoCommit(false);

					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO tb_livro(data_cadastro) VALUES (?)");
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
					Timestamp time = new Timestamp(livro.getDtCadastro().getTime());
					pst.setTimestamp(1, time);
					pst.executeUpdate();
					ResultSet rs = pst.getGeneratedKeys();
					int id = 0;
					if (rs.next())
						id = rs.getInt(1);
					livro.setId(id);
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
				DadosLivroDAO livrodao = new DadosLivroDAO();
				livrodao.salvar(livro);
				CategoriaLivroDadosDAO catlivro = new CategoriaLivroDadosDAO();
				catlivro.salvar(livro);
				// re.salvar(livro);
			}
		}
		if (livro.getSituacao() != null) {
			if (livro.getSituacao().equals("alterarlivro")) {
				DadosLivroDAO livrodao = new DadosLivroDAO();
				livrodao.salvar(livro);
			}

		}
	}

	/**
	 * TODO Descri��o do M�todo
	 * 
	 * @param entidade
	 * @see fai.dao.IDAO#alterar(fai.domain.EntidadeDominio)
	 */
	@Override
	public void alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		Livro livro = (Livro) entidade;
		int id = 0;
		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_livro SET id_re_livro=?  WHERE id_livro=?");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, livro.getRecebe());
			pst.setInt(2, livro.getId());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
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
	 * TODO Descri��o do M�todo
	 * 
	 * @param entidade
	 * @return
	 * @see fai.dao.IDAO#consulta(fai.domain.EntidadeDominio)
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();
		Livro livro = (Livro) entidade;
		StringBuilder select = new StringBuilder();
		StringBuilder group = new StringBuilder();
		String id_usuario = String.valueOf(livro.getId_usuario());
		if(id_usuario.equals("0")) {
			id_usuario = null;
		}
		String id_biblioteca = "0";
		if(livro.getQuantidade() != null ){
			 id_biblioteca = String.valueOf(livro.getQuantidade().getId_biblioteca());

		}

		String estoque = null ;
			if (livro.getSituacao() != null && livro.getSituacao() != "") {
			if (livro.getSituacao().equals("historico")) {

//				sql.append("SELECT * FROM tb_livro JOIN tb_re_livro USING (id_livro) ");
				group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
						"isbn,pagina,sinopse,altura,largura, profundidade,"+
						"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso ");
				select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
						"isbn,pagina,sinopse,altura,largura, profundidade,"+
						"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,"+
						"string_agg(categoria, ', ') as categoria ");
				sql.append(select + " FROM tb_livro join  tb_re_livro USING (id_livro)  join  tb_categoria_livro_dados"+
						" USING (id_livro) " + group);
			}
			else if (livro.getSituacao().equals("alterarlivro")) {

//				sql.append("SELECT * FROM tb_livro JOIN tb_re_livro USING (id_re_livro) ");
				group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
						"isbn,pagina,sinopse,altura,largura, profundidade,"+
						"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso ");
				select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
						"isbn,pagina,sinopse,altura,largura, profundidade,"+
						"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,"+
						"string_agg(categoria, ', ') as categoria ");
				sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
						" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro "+
						 group);
			}

			else if(livro.getSituacao().equals("estoque")) {
				group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,tb_estoque_livro.id_biblioteca,id_estoque,autor,editora,titulo,edicao,"+
						"isbn,pagina,sinopse,altura,largura, profundidade,"+
						"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,qt  ");
				select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
						"isbn,pagina,sinopse,altura,largura, profundidade,"+
						"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,qt,tb_estoque_livro.id_biblioteca,id_estoque, "+
						"string_agg(categoria, ', ') as categoria ");
				sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
						" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro  JOIN tb_estoque_livro on tb_livro.id_livro=tb_estoque_livro.id_livro "+
						group);		
				estoque = "estoque";
			}
			 else {

					group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso ");
					select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,"+
							"string_agg(categoria, ', ') as categoria ");
					sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
							" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro "+
							group);		}
			
		}
			else if(id_usuario != null || id_biblioteca != null) {
				if(id_usuario != null) {
					group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,tb_estoque_livro.id_biblioteca,id_estoque,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,tb_re_livro.status,ano,tb_re_livro.data_cadastro,cod_barra,peso,qt,tb_estoque_livro.id_biblioteca,id_estoque, nome  ");
					select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,tb_re_livro.status,ano,tb_re_livro.data_cadastro,cod_barra,peso,qt,tb_estoque_livro.id_biblioteca,id_estoque, nome,  "+
							"string_agg(categoria, ', ') as categoria ");
					sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
							" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro  JOIN tb_estoque_livro on tb_livro.id_livro=tb_estoque_livro.id_livro "+
							" JOIN tb_re_biblioteca on  tb_estoque_livro.id_biblioteca = tb_re_biblioteca.id_biblioteca "+group);		
					estoque = "estoque";
				}
				else if(id_biblioteca != "0" ) {
					group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,tb_estoque_livro.id_biblioteca,id_estoque,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,tb_re_livro.status,ano,tb_re_livro.data_cadastro,cod_barra,peso,qt,tb_estoque_livro.id_biblioteca,id_estoque, nome  ");
					select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,tb_re_livro.status,ano,tb_re_livro.data_cadastro,cod_barra,peso,qt,tb_estoque_livro.id_biblioteca,id_estoque, nome,  "+
							"string_agg(categoria, ', ') as categoria ");
					sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
							" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro  JOIN tb_estoque_livro on tb_livro.id_livro=tb_estoque_livro.id_livro "+
							" JOIN tb_re_biblioteca on  tb_estoque_livro.id_biblioteca = tb_re_biblioteca.id_biblioteca "+group);		
					estoque = "estoque";
				}
				else {
					group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso ");
					select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
							"isbn,pagina,sinopse,altura,largura, profundidade,"+
							"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,"+
							"string_agg(categoria, ', ') as categoria ");
					sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
							" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro "+
							group);		}
				
			}
		else {
			group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
					"isbn,pagina,sinopse,altura,largura, profundidade,"+
					"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso ");
			select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
					"isbn,pagina,sinopse,altura,largura, profundidade,"+
					"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,"+
					"string_agg(categoria, ', ') as categoria ");
			sql.append(select + " FROM tb_livro JOIN tb_re_livro ON tb_livro.id_re_livro= tb_re_livro.id_re_livro"+
					" JOIN tb_categoria_livro_dados on tb_livro.id_livro= tb_categoria_livro_dados.id_livro "+
					group);		}
		int i = 0;
		boolean out = false;
		if (livro.getAutor() != null && livro.getAutor() != "" || livro.getId() != null || livro.getTitulo() != null && livro.getTitulo() != "" || livro.getEditora() != null && livro.getEditora() != ""
				|| livro.getEdicao() != null && livro.getEdicao() != "" || livro.getISBN() != null && livro.getISBN() != "" ||
				livro.getCod_barra() != null &&  livro.getCod_barra() != "") {
			sql.append("HAVING ");
			if (livro.getAutor() != null && livro.getAutor() != "") {
				sql.append("autor LIKE ? ");
				i++;
			}
			if (livro.getId() != null) {
				if (livro.getAutor() != null && livro.getAutor() != "")
					sql.append("AND ");
				i++;
				if (livro.getSituacao() != null) {
					if (livro.getSituacao().equals("historico")) {
						sql.append("id_livro=?");
					} else if (livro.getSituacao().equals("alterarlivro")) {
						sql.append("tb_livro.id_livro=?");
					}
				} 

			}
			if (livro.getTitulo() != null && livro.getTitulo() != "") {
				if (livro.getAutor() != null && livro.getAutor() != "" || livro.getId() != null)
					sql.append("AND ");
				i++;
				sql.append("titulo=?");

			}
			if (livro.getEditora() != null && livro.getEditora() != "") {
				if (livro.getAutor() != null && livro.getAutor() != "" || livro.getId() != null
						|| livro.getTitulo() != null && livro.getTitulo() != "")
					sql.append("AND ");
				i++;
				sql.append("editora=?");

			}
			if (livro.getEdicao() != null && livro.getEdicao() != "") {
				if (livro.getAutor() != null && livro.getAutor() != "" || livro.getId() != null
						|| livro.getTitulo() != null && livro.getTitulo() != ""
						|| livro.getEditora() != null && livro.getEditora() != "")
					sql.append("AND ");
				i++;
				sql.append("edicao=?");

			}
			if (livro.getISBN() != null && livro.getISBN() != "") {
				if (livro.getAutor() != null && livro.getAutor() != "" || livro.getId() != null
						|| livro.getTitulo() != null && livro.getTitulo() != ""
						|| livro.getEditora() != null && livro.getEditora() != ""
						|| livro.getEdicao() != null && livro.getEdicao() != "")
					sql.append("AND ");
				i++;
				sql.append("isbn=?");

			}
			if (livro.getCod_barra() != null && livro.getCod_barra() != "") {
				if (livro.getAutor() != null && livro.getAutor() != "" || livro.getId() != null
						|| livro.getTitulo() != null && livro.getTitulo() != ""
						|| livro.getEditora() != null && livro.getEditora() != ""
						|| livro.getEdicao() != null && livro.getEdicao() != ""
						|| livro.getISBN() != null && livro.getISBN() != "")
					sql.append("AND ");
				i++;
				sql.append("cod_barra=?");
			}
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			int j = 1;
			if (livro.getAutor() != "" && livro.getAutor() != null) {
				pst.setString(j, livro.getAutor());
				j++;
			}
			if (livro.getId() != null) {
				pst.setInt(j, livro.getId());
				j++;
			}
			if (livro.getTitulo() != null && livro.getTitulo() != "") {
				pst.setString(j, livro.getTitulo());
				j++;
			}
			if (livro.getEditora() != "" && livro.getEditora() != null) {
				pst.setString(j, livro.getEditora());
				j++;
			}
			if (livro.getEdicao() != "" && livro.getEdicao() != null) {
				pst.setString(j, livro.getEdicao());
				j++;
			}
			if (livro.getISBN() != "" && livro.getISBN() != null) {
				pst.setString(j, livro.getISBN());
				j++;
			}
			if (livro.getCod_barra() != "" && livro.getCod_barra() != null) {
				pst.setString(j, livro.getCod_barra());
				j++;
			}

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> livros = new ArrayList<EntidadeDominio>();
			String categoria;
			while (rs.next()) {
				
					Livro l = new Livro();
					out = false;
						l.setId(rs.getInt("id_livro"));
						l.setRecebe(rs.getInt("id_re_livro"));
						l.setAutor(rs.getString("autor"));
						l.setEditora(rs.getString("editora"));
						l.setTitulo(rs.getString("titulo"));
						l.setEdicao(rs.getString("edicao"));
						l.setISBN(rs.getString("isbn"));
						l.setnPaginas(rs.getString("pagina"));
						l.setSinopse(rs.getString("sinopse"));
						l.setDimensao(new Dimensao());
						l.getDimensao().setAltura(rs.getString("altura"));
						l.getDimensao().setLargura(rs.getString("largura"));
						l.getDimensao().setProfundidade(rs.getString("profundidade"));
						l.setResponsavel(rs.getString("responsavel"));
						l.setStatus(rs.getBoolean("status"));
						l.setAno(rs.getString("ano"));
						l.setData(rs.getString("data_cadastro"));
						l.setCod_barra(rs.getString("cod_barra"));
						l.getDimensao().setPeso(rs.getString("peso"));
						categoria = rs.getString("categoria");
						String[] categorias = categoria.split(",");
						List<Categoria> listaCat = new ArrayList<Categoria>();
						for(int k =0; k<categorias.length; k++){
							Categoria cat = new Categoria();
							cat.setDescricao(categorias[k]);
							listaCat.add(cat);
						}
						l.setCategoria(listaCat);
//						if(categorias != null){
////							for(String listcat : categorias){
////								Categoria cat = new Categoria();
////								cat.setDescricao(listcat);
////								listaCat.add(cat);
////							}
//
//						}
						if(estoque !=null) {
							l.setQuantidade(new Quantidade());
							l.getQuantidade().setQuantidade(rs.getString("qt"));
							l.getQuantidade().setId(rs.getInt("id_estoque"));
							l.getQuantidade().setId_biblioteca(rs.getInt("id_biblioteca"));
							l.getQuantidade().setNome(rs.getString("nome"));
							int id_bibliotecaInt = 0;
							if(id_biblioteca != "") {
								id_bibliotecaInt = Integer.valueOf(id_biblioteca);
							}
							if(l.getQuantidade().getId_biblioteca() == id_bibliotecaInt) {
								out = true;
							}
						}
						if(!out) {
							livros.add(l);
						}

												
			}
			return livros;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
