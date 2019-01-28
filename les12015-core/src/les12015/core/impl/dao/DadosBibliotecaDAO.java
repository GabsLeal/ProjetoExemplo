package les12015.core.impl.dao;

import java.sql.SQLException;
import java.util.List;

import les12015.dominio.EntidadeDominio;

public class DadosBibliotecaDAO  extends AbstractJdbcDAO {

	public DadosBibliotecaDAO() {
		super("tb_re_biblioteca", "id_re_biblioteca");
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
		return null;
	}

}
