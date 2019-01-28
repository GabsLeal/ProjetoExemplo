package les12015.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import les12015.dominio.Aluno;
import les12015.dominio.Categoria;
import les12015.dominio.Cidade;
import les12015.dominio.Dimensao;
import les12015.dominio.Endereco;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Estado;
import les12015.dominio.Logradouro;
import les12015.dominio.Pais;

public class EnderecoDAO extends AbstractJdbcDAO {

	public EnderecoDAO() {
		super("tb_aluno_endereco", "id_aluno");

	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Aluno aluno = (Aluno) entidade;
		int j = 0;
		openConnection();
		PreparedStatement pst = null;
		if (aluno.getEndereco() != null) {
			try {
				connection.setAutoCommit(false);

		
					StringBuilder sql = new StringBuilder();
					sql.append(
							"INSERT INTO tb_aluno_endereco(id_aluno, tipo_residencia, tipo_logradouro, logradouro, numero, ");
					sql.append(
							"bairro, cep, cidade, estado, pais) VALUES (?,?,?,?,?,?,?,?,?,?)");

					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
					String timestamp =  new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());

						pst.setInt(1, aluno.getId());

					pst.setString(2, aluno.getEndereco().getTipo());
					pst.setString(3, aluno.getEndereco().getLogradouro().getTipo());
					pst.setString(4, aluno.getEndereco().getLogradouro().getNome());
					pst.setString(5, aluno.getEndereco().getNumero());
					pst.setString(6, aluno.getEndereco().getBairro());
					pst.setString(7, aluno.getEndereco().getCep());
					pst.setString(8, aluno.getEndereco().getCidade().getNome());
					pst.setString(9, aluno.getEndereco().getCidade().getEstado().getNome());
					pst.setString(10, aluno.getEndereco().getCidade().getPais().getNome());
					pst.executeUpdate();
					ResultSet rs = pst.getGeneratedKeys();
					
					
					int id = 0;
					
					if (rs.next())
						id = rs.getInt(1);
					if( j == 0){
						aluno.getEndereco().setId(id);
						}
						aluno.getEndereco().setId(id);
					connection.commit();
					j++;
				

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
		//	alunoDAO cli = new alunoDAO();
		

		//	cli.alterar(aluno);

		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
	

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

		return null;
	}
	

}
