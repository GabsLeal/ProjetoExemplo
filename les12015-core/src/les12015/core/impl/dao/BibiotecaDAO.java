package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Aluno;
import les12015.dominio.Biblioteca;
import les12015.dominio.Cidade;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Estado;
import les12015.dominio.Livro;
import les12015.dominio.Logradouro;
import les12015.dominio.Pais;
import les12015.dominio.Telefone;

public class BibiotecaDAO extends AbstractJdbcDAO {

	public BibiotecaDAO() {
		super("tb_biblioteca", "id_biblioteca");
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
		Biblioteca biblioteca = (Biblioteca) entidade;
	//	Pessoa pessoa = (Pessoa) entidade;
		boolean login = false;
		StringBuilder select = new StringBuilder();
		StringBuilder group = new StringBuilder();
			if (biblioteca.getSituacao() != null && biblioteca.getSituacao() != "") {
			if (biblioteca.getSituacao().equals("historico"))  {
				group.append("GROUP BY  tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, tb_re_aluno.data_cadastro, genero, nome, "
							+"data_nascimento, ra, tipo_telefone, ddd, numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "
						    +"logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo ");
			select.append("Select tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, tb_re_aluno.data_cadastro, "
						 +"genero, nome, data_nascimento, ra, tipo_telefone, ddd, numero_telefone, email, senha, "
						 +"tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, "
						 +"status, matricula, motivo from tb_aluno join tb_re_aluno USING(id_aluno) join tb_aluno_endereco USING (id_aluno)  join tb_statusmotivo USING(id_aluno)");
			sql.append(select+" "+ group);
			}
			if (biblioteca.getSituacao().equals("alteraraluno") || biblioteca.getSituacao().equals("status")) {
				group.append("group by tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
							"tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, "+ 
							"numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "+
							 "logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo ");
				select.append("select  tb_aluno.id_aluno, tb_re_aluno.id_re_aluno, "+
							 "tb_re_aluno.data_cadastro, genero, nome, data_nascimento, ra, tipo_telefone, ddd, numero_telefone, "+
							 "email, senha, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais, status, matricula, motivo "+
							 "from tb_aluno JOIN  tb_re_aluno ON  tb_aluno.id_re_aluno= tb_re_aluno.id_re_aluno  join "+
							 "tb_aluno_endereco ON tb_aluno.id_aluno=tb_aluno_endereco.id_aluno join tb_statusmotivo ON tb_aluno.id_statusmotivo=tb_statusmotivo.id_statusmotivo");
				sql.append(select+" "+ group);
			}
			
		} 
			else if(biblioteca.getLivro() == null) {
			group.append("group by tb_biblioteca.id_biblioteca, tb_re_biblioteca.id_re_biblioteca, "+
					"tb_re_biblioteca.data_cadastro, nome, data_funda, registro_biblioteca, tipo_telefone, ddd, "+ 
					"numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "+
					 "logradouro, numero, bairro, cep, cidade, estado, pais ");
		select.append("select tb_biblioteca.id_biblioteca, tb_re_biblioteca.id_re_biblioteca, "+
					 "tb_re_biblioteca.data_cadastro, nome, data_funda, registro_biblioteca, tipo_telefone, ddd, numero_telefone, "+
					 "email, senha, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais "+
					 "from tb_biblioteca JOIN  tb_re_biblioteca ON  tb_biblioteca.id_re_biblioteca= tb_re_biblioteca.id_re_biblioteca  join "+
					 "tb_biblioteca_endereco ON tb_biblioteca.id_biblioteca=tb_biblioteca_endereco.id_biblioteca");
		sql.append(select+" "+ group);	
		login =false;
		
		}
			else {
			group.append("group by tb_biblioteca.id_biblioteca, tb_re_biblioteca.id_re_biblioteca, "+
					"tb_re_biblioteca.data_cadastro, nome, data_funda, registro_biblioteca, tipo_telefone, ddd, "+ 
					"numero_telefone, email, senha, tipo_residencia, tipo_logradouro, "+
					 "logradouro, numero, bairro, cep, cidade, estado, pais ");
		select.append("select tb_biblioteca.id_biblioteca, tb_re_biblioteca.id_re_biblioteca, "+
					 "tb_re_biblioteca.data_cadastro, nome, data_funda, registro_biblioteca, tipo_telefone, ddd, numero_telefone, "+
					 "email, senha, tipo_residencia, tipo_logradouro, logradouro, numero, bairro, cep, cidade, estado, pais "+
					 "from tb_biblioteca JOIN  tb_re_biblioteca ON  tb_biblioteca.id_re_biblioteca= tb_re_biblioteca.id_re_biblioteca  join "+
					 "tb_biblioteca_endereco ON tb_biblioteca.id_biblioteca=tb_biblioteca_endereco.id_biblioteca");
		sql.append(select+" "+ group);	
		login =true;
		
		}
		int i = 0;
		
		if (biblioteca.getNome() != null && biblioteca.getNome() != "" || biblioteca.getId() != null || biblioteca.getId_alt() !=null || biblioteca.getNumero_iden() != null && biblioteca.getNumero_iden() != ""
				) {
			sql.append("HAVING ");
			if (biblioteca.getNome() != null && biblioteca.getNome() != "") {
				sql.append("nome LIKE ? ");
				i++;
			}
			if (biblioteca.getId() != null) {
				if (biblioteca.getNome() != null && biblioteca.getNome() != "")
					sql.append("AND ");
				i++;
				if (biblioteca.getSituacao() != null) {
					if (biblioteca.getSituacao().equals("historico")) {
						sql.append("id_aluno=?");
					} else if (biblioteca.getSituacao().equals("alteraraluno")) {
						sql.append(" tb_aluno.id_aluno=?");
					}
					else if (biblioteca.getSituacao().equals("status")) {
						sql.append(" tb_aluno.id_aluno=?");
					}
				} 

			}
			if (biblioteca.getNumero_iden() != null && biblioteca.getNumero_iden() != "") {
				if (biblioteca.getNome() != null && biblioteca.getNome() != "" || biblioteca.getId() != null)
					sql.append("AND ");
				i++;
				sql.append("registro_biblioteca=?");

			}
		
			if (biblioteca.getId_alt() != null) {
				if (biblioteca.getNome() != null && biblioteca.getNome() != "" || biblioteca.getId() != null
						|| biblioteca.getNumero_iden() != null && biblioteca.getNumero_iden() != "")
					sql.append("AND ");
				i++;
				sql.append("tb_aluno.id_aluno=?");

			}
			
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			int j = 1;
			if (biblioteca.getNome() != null && biblioteca.getNome() != "") {
				pst.setString(j, biblioteca.getNome());
				j++;
			}
			if (biblioteca.getId() != null) {
				pst.setInt(j, biblioteca.getId());
				j++;
			}
			if (biblioteca.getId_alt() != null) {
				pst.setInt(j, biblioteca.getId_alt());
				j++;
			}
			if (biblioteca.getNumero_iden() != null && biblioteca.getNumero_iden() != "") {
				pst.setString(j, biblioteca.getNumero_iden());
				j++;
			}
					

			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> bibliotecas = new ArrayList<EntidadeDominio>();
			String end;
			while (rs.next()) {
				
						Biblioteca bibli = new Biblioteca();
						bibli.setId(rs.getInt("id_biblioteca"));
						bibli.setRecebe(rs.getInt("id_re_biblioteca"));
						bibli.setNome(rs.getString("nome"));
						bibli.setData_funda(rs.getString("data_funda"));
						bibli.setNumero_iden(rs.getString("registro_biblioteca"));
						bibli.setTelefone(new Telefone());
						bibli.getTelefone().setTipo(rs.getString("tipo_telefone"));
						bibli.getTelefone().setDdd(rs.getString("ddd"));
						bibli.getTelefone().setNumero(rs.getString("numero_telefone"));
						bibli.setEmail(rs.getString("email"));
						bibli.setSenha(rs.getString("senha"));
						bibli.setData(rs.getString("data_cadastro"));
						bibli.setEndereco(new Endereco());
						bibli.getEndereco().setTipo(rs.getString("tipo_residencia"));
						bibli.getEndereco().setLogradouro(new Logradouro());
						bibli.getEndereco().getLogradouro().setTipo(rs.getString("tipo_logradouro"));
						bibli.getEndereco().getLogradouro().setNome(rs.getString("logradouro"));
						bibli.getEndereco().setNumero(rs.getString("numero"));
						bibli.getEndereco().setCep(rs.getString("cep"));
						bibli.getEndereco().setCidade(new Cidade());
						bibli.getEndereco().getCidade().setNome(rs.getString("cidade"));
						bibli.getEndereco().getCidade().setEstado(new Estado());
						bibli.getEndereco().getCidade().setPais(new Pais());
						bibli.getEndereco().getCidade().getPais().setNome(rs.getString("pais"));

						bibli.getEndereco().getCidade().getEstado().setNome(rs.getString("estado"));
						
						if(login) {
							bibli.setLivro(new Livro());
							bibli.getLivro().setId(biblioteca.getLivro().getId());
						}
					



						bibli.getEndereco().setBairro(rs.getString("bairro"));
						bibliotecas.add(bibli);

												
			}
			return bibliotecas;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	
	
	}
	
	

}
