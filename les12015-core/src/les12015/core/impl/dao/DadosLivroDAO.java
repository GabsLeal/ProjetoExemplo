package les12015.core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;

public class DadosLivroDAO extends AbstractJdbcDAO {
	
	public DadosLivroDAO() {
		super("tb_re_livro", "id_re_livro");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Livro livro = (Livro) entidade;

		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_re_livro(id_livro, autor, titulo, editora, edicao, isbn, ");
			sql.append(
					"pagina, sinopse, altura, largura, profundidade, responsavel, status, ano, cod_barra, peso, data_cadastro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, livro.getId());
			pst.setString(2, livro.getAutor());
			pst.setString(3, livro.getTitulo());
			pst.setString(4, livro.getEditora());
			pst.setString(5, livro.getEdicao());
			pst.setString(6, livro.getISBN());
			pst.setString(7, livro.getnPaginas());
			pst.setString(8, livro.getSinopse());
			pst.setString(9, livro.getDimensao().getAltura());
			pst.setString(10, livro.getDimensao().getLargura());
			pst.setString(11, livro.getDimensao().getProfundidade());
			pst.setString(12, livro.getResponsavel());
		//	String timestamp =  new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
			Timestamp time = new Timestamp(livro.getDtCadastro().getTime());

			pst.setBoolean(13, livro.getStatus());
			pst.setString(14, livro.getAno());
			pst.setString(15, livro.getCod_barra());
			pst.setString(16, livro.getDimensao().getPeso());
			pst.setTimestamp(17, time);
			
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
				id = rs.getInt(1);
			livro.setRecebe(id);

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
	LivroDAO livrodao = new LivroDAO();
	livrodao.alterar(livro);


	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Livro livro = (Livro) entidade;
		int id = 0;
		openConnection();
		PreparedStatement pst = null;
		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_re_livro SET status=?  WHERE id_livro=?");
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

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}