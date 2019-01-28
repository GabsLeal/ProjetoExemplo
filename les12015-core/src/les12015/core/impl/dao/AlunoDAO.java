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
import les12015.dominio.Categoria;
import les12015.dominio.Cidade;
import les12015.dominio.Dimensao;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Estado;
import les12015.dominio.Livro;
import les12015.dominio.Logradouro;
import les12015.dominio.Pais;
import les12015.dominio.Pessoa;
import les12015.dominio.Telefone;

public class AlunoDAO extends AbstractJdbcDAO {

	public AlunoDAO() {
		super("tb_aluno", "id_aluno");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Aluno aluno = (Aluno) entidade;
		openConnection();
		PreparedStatement pst = null;
		if (aluno.getId() == null || aluno.getId().equals("")) {
			if (aluno.getSituacao() == null || aluno.getMotivo() == null) {
				try {
					connection.setAutoCommit(false);

					StringBuilder sql = new StringBuilder();
					sql.append("INSERT INTO tb_aluno(data_cadastro) VALUES (?)");
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
					Timestamp time = new Timestamp(aluno.getDtCadastro().getTime());
					pst.setTimestamp(1, time);
					pst.executeUpdate();
					ResultSet rs = pst.getGeneratedKeys();
					int id = 0;
					if (rs.next())
						id = rs.getInt(1);
					aluno.setId(id);
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
				DadosAlunoDAO alunodao = new DadosAlunoDAO();
				alunodao.salvar(aluno);
				EnderecoDAO end = new EnderecoDAO();
				end.salvar(aluno);
				StatusMotivoDAO statusmotivo = new StatusMotivoDAO();
				statusmotivo.salvar(aluno);
	//			CategoriaLivroDadosDAO catlivro = new CategoriaLivroDadosDAO();
	//			endaluno.salvar(aluno);
				// re.salvar(livro);
			}
		}
		if (aluno.getSituacao() != null) {
			if (aluno.getSituacao().equals("alteraraluno")) {
				DadosAlunoDAO alunodao = new DadosAlunoDAO();
				alunodao.salvar(aluno);
			}
			if (aluno.getSituacao().equals("alterarstatus")) {
				StatusMotivoDAO statusmotivo = new StatusMotivoDAO();
				statusmotivo.salvar(aluno);
			}

		}
//		if(aluno.getMotivo() != null) {
//			StatusMotivoDAO statusmotivo = new StatusMotivoDAO();
//			statusmotivo.salvar(aluno);
//		}

	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Aluno aluno = (Aluno) entidade;
		int id = 0;
		openConnection();
		PreparedStatement pst = null;
		if(aluno.getDado() == null) {
			try {
				connection.setAutoCommit(false);
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tb_aluno SET id_re_aluno=?  WHERE id_aluno=?");
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, aluno.getRecebe());
				pst.setInt(2, aluno.getId());
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
		else if (aluno.getDado().equals("first")){
			try {
				connection.setAutoCommit(false);
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE tb_aluno SET id_statusmotivo=?  WHERE id_aluno=?");
				pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pst.setInt(1, aluno.getRecebe());
				pst.setInt(2, aluno.getId());
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

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();
		Aluno aluno = (Aluno) entidade;
	//	Pessoa pessoa = (Pessoa) entidade;
		StringBuilder select = new StringBuilder();
		StringBuilder group = new StringBuilder();
			if (aluno.getSituacao() != null && aluno.getSituacao() != "") {
			if (aluno.getSituacao().equals("historico"))  {
//				sql.append("SELECT * FROM tb_livro JOIN tb_re_livro USING (id_livro) ");
			//	group.append("GROUP BY tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
	//					"isbn,pagina,sinopse,altura,largura, profundidade,"+
	//					"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso ");
	//			select.append("SELECT tb_livro.id_livro, tb_re_livro.id_re_livro,autor,editora,titulo,edicao,"+
	//					"isbn,pagina,sinopse,altura,largura, profundidade,"+
	//					"responsavel,status,ano,tb_re_livro.data_cadastro,cod_barra,peso,"+
	//					"string_agg(categoria, ', ') as categoria ");
	//			sql.append(select + " FROM tb_livro join  tb_re_livro USING (id_livro)  join  tb_categoria_livro_dados"+
	//					" USING (id_livro) " + group);
				
	//			sql.append("select * from tb_aluno JOIN  tb_re_aluno USING (id_aluno)  join  tb_aluno_endereco USING (id_aluno)");
				group.append("GROUP BY  tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, tb_re_aluno.data_cadastro, genero, nome, "
							+"data_nascimento, ra, tipo_telefone, ddd, numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "
						    +"logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo ");
			select.append("Select tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, tb_re_aluno.data_cadastro, "
						 +"genero, nome, data_nascimento, ra, tipo_telefone, ddd, numero_telefone, email, senha, "
						 +"tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, "
						 +"status, matricula, motivo from tb_aluno join tb_re_aluno USING(id_aluno) join tb_aluno_endereco USING (id_aluno)  join tb_statusmotivo USING(id_aluno)");
			sql.append(select+" "+ group);
			}
			if (aluno.getSituacao().equals("alteraraluno") || aluno.getSituacao().equals("status")) {
				group.append("group by tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
							"tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, "+ 
							"numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "+
							 "logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo ");
				select.append("select  tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
							 "tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, numero_telefone, "+
							 "email, senha, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo "+
							 "from tb_aluno JOIN  tb_re_aluno ON  tb_aluno.id_re_aluno= tb_re_aluno.id_re_aluno  join "+
							 "tb_aluno_endereco ON tb_aluno.id_aluno=tb_aluno_endereco.id_aluno join tb_statusmotivo ON tb_aluno.id_statusmotivo=tb_statusmotivo.id_tb_statusmotivo");
				sql.append(select+" "+ group);
			}
			 else {
					group.append("group by tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
							"tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, "+ 
							"numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "+
							 "logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo ");
				select.append("select  tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
							 "tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, numero_telefone, "+
							 "email, senha, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo "+
							 "from tb_aluno JOIN  tb_re_aluno ON  tb_aluno.id_re_aluno= tb_re_aluno.id_re_aluno  join "+
							 "tb_aluno_endereco ON tb_aluno.id_aluno=tb_aluno_endereco.id_aluno join tb_statusmotivo ON tb_aluno.id_statusmotivo=tb_statusmotivo.id_tb_statusmotivo");
				sql.append(select+" "+ group);		
				}
		} else {
			group.append("group by tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
					"tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, "+ 
					"numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "+
					 "logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo ");
		select.append("select  tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
					 "tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, numero_telefone, "+
					 "email, senha, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo "+
					 "from tb_aluno JOIN  tb_re_aluno ON  tb_aluno.id_re_aluno= tb_re_aluno.id_re_aluno  join "+
					 "tb_aluno_endereco ON tb_aluno.id_aluno=tb_aluno_endereco.id_aluno join tb_statusmotivo ON tb_aluno.id_statusmotivo=tb_statusmotivo.id_tb_statusmotivo");
		sql.append(select+" "+ group);		
		}
		int i = 0;
		
		if (aluno.getNome() != null && aluno.getNome() != "" || aluno.getId() != null || aluno.getId_alt() !=null || aluno.getRa() != null && aluno.getRa() != "" || aluno.getGenero() != null && aluno.getGenero() != ""
				|| aluno.getSenha() !=null && aluno.getSenha() !="") {
			sql.append(" HAVING ");
			if (aluno.getNome() != null && aluno.getNome() != "") {
				sql.append("nome LIKE ? ");
				i++;
			}
			if (aluno.getId() != null) {
				if (aluno.getNome() != null && aluno.getNome() != "")
					sql.append("AND ");
				i++;
				if (aluno.getSituacao() != null) {
					if (aluno.getSituacao().equals("historico")) {
						sql.append("id_aluno=?");
					} else if (aluno.getSituacao().equals("alteraraluno")) {
						sql.append(" tb_aluno.id_aluno=?");
					}
					else if (aluno.getSituacao().equals("status")) {
						sql.append(" tb_aluno.id_aluno=?");
					}
				} 

			}
			if (aluno.getRa() != null &&aluno.getRa() != "") {
				if (aluno.getNome() != null && aluno.getNome() != "" || aluno.getId() != null)
					sql.append("AND ");
				i++;
				sql.append("ra=?");

			}
			if (aluno.getGenero() != null && aluno.getGenero() != "") {
				if (aluno.getNome() != null && aluno.getNome() != "" || aluno.getId() != null
						|| aluno.getRa() != null && aluno.getRa() != "")
					sql.append("AND ");
				i++;
				sql.append("genero=?");

			}
			if (aluno.getId_alt() != null) {
				if (aluno.getNome() != null && aluno.getNome() != "" || aluno.getId() != null
						|| aluno.getRa() != null && aluno.getRa() != "" || aluno.getGenero() != null && aluno.getGenero() != "")
					sql.append("AND ");
				i++;
				sql.append("tb_aluno.id_aluno=?");

			}
			if (aluno.getSenha() !=null && aluno.getSenha() !="") {
				if (aluno.getNome() != null && aluno.getNome() != "" || aluno.getId() != null
						|| aluno.getRa() != null && aluno.getRa() != "" || aluno.getGenero() != null && aluno.getGenero() != "" || aluno.getId_alt() != null)
					sql.append("AND ");
				i++;
				sql.append("senha=?");

			}
			
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			int j = 1;
			if (aluno.getNome() != null && aluno.getNome() != "") {
				pst.setString(j, aluno.getNome());
				j++;
			}
			if (aluno.getId() != null) {
				pst.setInt(j, aluno.getId());
				j++;
			}
			if (aluno.getId_alt() != null) {
				pst.setInt(j, aluno.getId_alt());
				j++;
			}
			if (aluno.getRa() != null && aluno.getRa() != "") {
				pst.setString(j, aluno.getRa());
				j++;
			}
			if (aluno.getGenero() != "" && aluno.getGenero() != null) {
				pst.setString(j, aluno.getGenero());
				j++;
			}
			if (aluno.getSenha() !=null && aluno.getSenha() !="") {
				pst.setString(j, aluno.getSenha());
				j++;
			}
			

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> alunos = new ArrayList<EntidadeDominio>();
			String end;
			while (rs.next()) {
				
					Aluno al = new Aluno();
						al.setId(rs.getInt("id_aluno"));
						al.setRecebe(rs.getInt("id_re_aluno"));
						al.setGenero(rs.getString("genero"));
						al.setNome(rs.getString("nome"));
						al.setData_nascimento(rs.getString("data_nascimento"));
						al.setRa(rs.getString("ra"));
						al.setTelefone(new Telefone());
						al.getTelefone().setTipo(rs.getString("tipo_telefone"));
						al.getTelefone().setDdd(rs.getString("ddd"));
						al.getTelefone().setNumero(rs.getString("numero_telefone"));
						al.setEmail(rs.getString("email"));
						al.setSenha(rs.getString("senha"));
						al.setStatus(rs.getBoolean("status"));
						al.setData(rs.getString("data_cadastro"));
						al.setEndereco(new Endereco());
						al.getEndereco().setTipo(rs.getString("tipo_residencia"));
						al.getEndereco().setLogradouro(new Logradouro());
						al.getEndereco().getLogradouro().setTipo(rs.getString("tipo_logradouro"));
						al.getEndereco().getLogradouro().setNome(rs.getString("logradouro"));
						al.getEndereco().setNumero(rs.getString("numero"));
						al.getEndereco().setCep(rs.getString("cep"));
						al.getEndereco().setCidade(new Cidade());
						al.getEndereco().getCidade().setNome(rs.getString("cidade"));
						al.getEndereco().getCidade().setEstado(new Estado());
						al.getEndereco().getCidade().setPais(new Pais());
						al.getEndereco().getCidade().getPais().setNome(rs.getString("pais"));

						al.getEndereco().getCidade().getEstado().setNome(rs.getString("estado"));
						al.setMatricula(rs.getString("matricula"));




						al.getEndereco().setBairro(rs.getString("bairro"));

				//		end = rs.getString("categoria");
					//	String[] categorias = categoria.split(",");
				//		List<Categoria> listaCat = new ArrayList<Categoria>();
			//			for(int k =0; k<categorias.length; k++){
			//				Categoria cat = new Categoria();
			//				cat.setDescricao(categorias[k]);
			//				listaCat.add(cat);
				//		}
				//		l.setCategoria(listaCat);
//						if(categorias != null){
////							for(String listcat : categorias){
////								Categoria cat = new Categoria();
////								cat.setDescricao(listcat);
////								listaCat.add(cat);
////							}
//
//						}
						
						alunos.add(al);

												
			}
			return alunos;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	
	}

}
