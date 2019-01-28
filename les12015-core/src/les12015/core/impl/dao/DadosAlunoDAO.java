package les12015.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import les12015.dominio.Aluno;
import les12015.dominio.EntidadeDominio;

public class DadosAlunoDAO  extends AbstractJdbcDAO {


	public DadosAlunoDAO() {
		super("tb_re_aluno", "id_re_aluno");
	}


	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Aluno aluno = (Aluno) entidade;

		openConnection();
		PreparedStatement pst = null;
		int id_aluno = aluno.getId();
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_re_aluno(id_aluno, genero, nome, data_nascimento, ra, tipo_telefone, ddd, "); 
			sql.append("numero_telefone, email, senha, data_cadastro, matricula) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
			Timestamp time = new Timestamp(aluno.getDtCadastro().getTime());

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, aluno.getId());
			pst.setString(2, aluno.getGenero());
			pst.setString(3, aluno.getNome());
			pst.setString(4, aluno.getData_nascimento());
			pst.setString(5, aluno.getRa());
			pst.setString(6, aluno.getTelefone().getTipo());
			pst.setString(7, aluno.getTelefone().getDdd());
			pst.setString(8, aluno.getTelefone().getNumero());
			pst.setString(9, aluno.getEmail());
			pst.setString(10, aluno.getSenha());
			pst.setTimestamp(11, time);
			pst.setString(12, aluno.getMatricula());


			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();

			int id = 0;
			if (rs.next())
				id = rs.getInt(1);
			aluno.setRecebe(id);
			aluno.setId(id_aluno);
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
		AlunoDAO re = new AlunoDAO();
		re.alterar(aluno);

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
