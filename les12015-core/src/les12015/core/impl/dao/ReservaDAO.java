package les12015.core.impl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import les12015.dominio.Categoria;
import les12015.dominio.Dimensao;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;
import les12015.dominio.Quantidade;
import les12015.dominio.Reserva;

public class ReservaDAO  extends AbstractJdbcDAO {

	public ReservaDAO() {
		super("tb_reserva", "id_reserva");
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

		Quantidade estoque = (Quantidade) entidade;
		openConnection();
		PreparedStatement pst = null;
		
		try {
			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_reserva(id_estoque,id_usuario,qt,condicao,data,tipo_usuario) VALUES (?,?,?,?,?,?)");
			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			//String timestamp = new java.text.SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
			Timestamp time = new Timestamp(estoque.getDtCadastro().getTime());
			pst.setInt(1, estoque.getId());
			pst.setInt(2, estoque.getId_usuario());
			pst.setString(3, "1");
			pst.setString(4, "RESERVADO");
			pst.setTimestamp(5, time);
			pst.setString(6, estoque.getTipoUsuario());


	//		pst.setTimestamp(3, time);
			pst.executeUpdate();
//			ResultSet rs = pst.getGeneratedKeys();
//			int id = 0;
//			if (rs.next())
//				id = rs.getInt(1);
//			livro.setId(id);
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
		
	}

	@Override
	public void alterar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub

		int id = 0;
		String tipoUsuario = null;
		Reserva reserva = (Reserva) entidade;
		openConnection();
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		StringBuilder sql2 = new StringBuilder();
		int id_livro = 0;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
	//		sql.append("UPDATE tb_reserva SET condicao=?  WHERE id_reserva=?\n  RETURNING *; ");
			sql.append("UPDATE tb_reserva SET condicao=?  WHERE id_reserva=? ");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			if(reserva.getCondicao() != null) {
				if(reserva.getCondicao().equals("retirando")) {
					pst.setString(1, "RETIRADO");
				}
				else if(reserva.getCondicao().equals("DEVOLVER")){
					pst.setString(1, "DEVOLVER");
				}
				else if(reserva.getCondicao().equals("DEVOLVIDO")){
					pst.setString(1, "DEVOLVIDO");
				}
			}
			else {
				pst.setString(1, "EM ESPERA");
			}
			pst.setInt(2, reserva.getId());
			pst.executeUpdate();
		//	 
					sql2.append("select * from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque  where id_reserva = "+reserva.getId() + " ");
			pst2 = connection.prepareStatement(sql2.toString());	
			ResultSet rs = pst2.executeQuery();
				if (rs.next()) {
					 id = rs.getInt("id_usuario");
					 tipoUsuario = rs.getString("tipo_usuario");
					 id_livro = rs.getInt("id_livro");
				}

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
		if(reserva.getCondicao() != null) {
			if(reserva.getCondicao().equals("DEVOLVIDO")){
				int qt = 0;
				qt = Integer.valueOf(reserva.getQtEstoque());
//				qt = qt + 1;
				EstoqueDAO estoque = new EstoqueDAO ();
				Quantidade quantidade = new Quantidade();
				quantidade.setQuantidade( Integer.toString(qt));
				quantidade.setId(reserva.getId_estoque());
				quantidade.setMotivo("DEVOLVIDO");
				if(id != 0) {
					if(tipoUsuario != null) {
						if(tipoUsuario.equals("biblioteca")) {
							quantidade.setId_biblioteca(id);
							quantidade.setTipoUsuario(tipoUsuario);
							quantidade.setNome("devolverEstoque");
							quantidade.setId_livro(id_livro);
							quantidade.setSituacao("biblioteca");
							List<EntidadeDominio> estoques = new ArrayList<EntidadeDominio>();
							estoques = estoque.consultar(quantidade);
						//	int qt = estoques.get(0).getClass().getName().get
							for (EntidadeDominio obj : estoques) {
								if (obj instanceof Quantidade) {
									String qts = ((Quantidade) obj).getQuantidade();
									int qtInt = Integer.valueOf(qts) - 1;
									quantidade.setResponsavel(String.valueOf(qtInt));
									quantidade.setId_biblioteca(((Quantidade) obj).getId());
									}
								}
							estoque.alterar(quantidade);
							
						}
					}
				}
				quantidade.setNome(null);
				estoque.alterar(quantidade);
				
				}
			else if(reserva.getCondicao().equals("retirando")) {
				EstoqueDAO estoque = new EstoqueDAO ();
				Quantidade quantidade = new Quantidade();
				quantidade.setQuantidade("1");
		//		quantidade.setId(reserva.getId_estoque());
				quantidade.setId_biblioteca(reserva.getId_usuario());
				quantidade.setId_livro(reserva.getId_livro());
				quantidade.setSituacao("biblioteca");
				boolean t = estoque.consultar(quantidade).isEmpty();
				if(estoque.consultar(quantidade).isEmpty()) {
					estoque.salvar(quantidade);
				}
				else {
					List<EntidadeDominio> estoques = new ArrayList<EntidadeDominio>();
					estoques = estoque.consultar(quantidade);
				//	int qt = estoques.get(0).getClass().getName().get
					for (EntidadeDominio obj : estoques) {
						if (obj instanceof Quantidade) {
							String qt = ((Quantidade) obj).getQuantidade();
							int qtInt = Integer.valueOf(qt) + 1;
							quantidade.setQuantidade(String.valueOf(qtInt));
							quantidade.setId(((Quantidade) obj).getId());
							}
						}
				//	quantidade.setId(reserva.getId_estoque());
					quantidade.setMotivo("biblioteca");
					estoque.alterar(quantidade);
					
				}
			}
			}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement pst = null;

		StringBuilder sql = new StringBuilder();
		String condicao = null;
		String situacao = null;
		Reserva reserva = (Reserva) entidade;
		if(reserva.getCondicao() != null) {
			if(reserva.getCondicao().equals("RETIRADOS")) {
				if(reserva.getTipoUsuario() != null) {
					if(reserva.getTipoUsuario().equals("aluno")) {
						sql.append("select distinct  tb_livro_entregar.id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, data_retirado, data_entregar, tipo_usuario " + 
								" from tb_reserva "+
								" join tb_livro_entregar on  tb_reserva.id_reserva = tb_livro_entregar.id_reserva" + 
								" join tb_re_aluno on tb_reserva.id_usuario = tb_re_aluno.id_aluno" + 
								"  join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro" + 
								" where id_usuario = " + reserva.getId_usuario() +" and condicao = 'RETIRADO'");
						condicao = "RETIRADOS";
					}
					else if(reserva.getTipoUsuario().equals("biblioteca")) {
						sql.append("select distinct  tb_livro_entregar.id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, data_retirado, data_entregar, tipo_usuario " + 
								" from tb_reserva "+
								" join tb_livro_entregar on  tb_reserva.id_reserva = tb_livro_entregar.id_reserva" + 
								" join tb_re_biblioteca on tb_reserva.id_usuario = tb_re_biblioteca.id_biblioteca" + 
								"  join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro" + 
								" where id_usuario = " + reserva.getId_usuario() +" and condicao = 'RETIRADO'");
						condicao = "RETIRADOS";
					}
				}

			}
			else if(reserva.getCondicao().equals("LIVROENTREGAR")) {
				sql.append("select distinct  tb_livro_entregar.id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, data_retirado, data_entregar, tipo_usuario " + 
						" from tb_reserva" + 
						" join tb_livro_entregar on  tb_reserva.id_reserva = tb_livro_entregar.id_reserva" + 
						" join tb_re_aluno on tb_reserva.id_usuario = tb_re_aluno.id_aluno" + 
						" join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
						" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro" + 
						" where condicao = 'RETIRADO'");
				condicao = "LIVROENTREGAR";
			}
			else if(reserva.getCondicao().equals("LIVROENTREGARBIBLIOTECA")) {
				sql.append("select distinct  tb_livro_entregar.id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, data_retirado, data_entregar, tipo_usuario " + 
						" from tb_reserva" + 
						" join tb_livro_entregar on  tb_reserva.id_reserva = tb_livro_entregar.id_reserva" + 
						" join tb_re_biblioteca on tb_reserva.id_usuario = tb_re_biblioteca.id_biblioteca" + 
						" join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque" + 
						" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro" + 
						" where condicao = 'RETIRADO'");
				condicao = "LIVROENTREGAR";
			}
			else if(reserva.getCondicao().equals("SITUACAO")) {
				sql.append("select distinct id_reserva, id_usuario, condicao, data, tb_reserva.tipo_usuario " +  
						" from tb_reserva " + 
						" join tb_statusmotivo on tb_reserva.id_usuario = tb_statusmotivo.id_aluno " + 
						" where id_usuario = "+ reserva.getId_usuario() + " and tb_reserva.tipo_usuario = '"+reserva.getTipoUsuario()+
						"' and status = true");
				situacao = "SITUACAO";
			}
			else if(reserva.getCondicao().equals("ESPERA")) {
				if(reserva.getTipoUsuario() !=null) {
					if(reserva.getTipoUsuario().equals("biblioteca")) {
						sql.append("select distinct  id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, tipo_usuario, tb_re_livro.id_livro " + 
								" from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque"+
								" join tb_re_biblioteca on tb_reserva.id_usuario = tb_re_biblioteca.id_biblioteca"+
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro"+
								" where condicao = 'EM ESPERA'");
					}
					else if (reserva.getTipoUsuario().equals("aluno")) {
						sql.append("select distinct  id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, tipo_usuario, tb_re_livro.id_livro " + 
								" from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque"+
								" join tb_re_aluno on tb_reserva.id_usuario = tb_re_aluno.id_aluno"+
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro"+
								" where condicao = 'EM ESPERA'");
					}
				}

				condicao = "ESPERA";
			}
			
			else if(reserva.getCondicao().equals("DEVOLVER")) {
				
				if(reserva.getTipoUsuario() !=null) {
					if(reserva.getTipoUsuario().equals("aluno")) {
						sql.append("select distinct  id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, tb_estoque_livro.qt, tipo_usuario, tb_re_livro.id_livro " + 
								" from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque"+
								" join tb_re_aluno on tb_reserva.id_usuario = tb_re_aluno.id_aluno"+
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro"+
								" where condicao = 'DEVOLVER'");
					}
					else if (reserva.getTipoUsuario().equals("biblioteca")) {
						sql.append("select distinct  id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, tb_estoque_livro.qt, tipo_usuario, tb_re_livro.id_livro " + 
								" from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque"+
								" join tb_re_biblioteca on tb_reserva.id_usuario = tb_re_biblioteca.id_biblioteca"+
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro"+
								" where condicao = 'DEVOLVER'");
					}
					else if (reserva.getTipoUsuario().equals("professor")) {
						sql.append("select distinct  id_reserva, tb_estoque_livro.id_estoque, id_usuario, condicao, titulo, data, nome, tb_estoque_livro.qt, tipo_usuario, tb_re_livro.id_livro " + 
								" from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque"+
								" join tb_re_professor on tb_reserva.id_usuario = tb_re_professor.id_professor"+
								" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro"+
								" where condicao = 'DEVOLVER'");
					}
				}
				
				condicao = "DEVOLVER";
			}
		}
		else {
			sql.append("select distinct id_reserva, tb_reserva.id_estoque, id_usuario, condicao, titulo, data, tipo_usuario "+
					" from tb_reserva join tb_estoque_livro on tb_reserva.id_estoque = tb_estoque_livro.id_estoque"+
					" join tb_re_livro on tb_estoque_livro.id_livro = tb_re_livro.id_livro"+
					" where id_usuario = "+reserva.getId_usuario() + " and condicao = 'RESERVADO'");
		}
	//	sql.append("select * from tb_reserva where id_usuario ="+reserva.getId_usuario()+"" );
		
	
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			ResultSet rs = pst.executeQuery();
			List<EntidadeDominio> reservas = new ArrayList<EntidadeDominio>();
			while (rs.next()) {
				
						Reserva r = new Reserva();
						r.setId(rs.getInt("id_reserva"));
						if(condicao != null) {
							if(condicao.equals("ESPERA")) {
								r.setResponsavel(rs.getString("nome"));
								r.setId_livro(rs.getInt("id_livro"));
							}
							else if(condicao.equals("DEVOLVER")) {
								r.setResponsavel(rs.getString("nome"));
								r.setQtEstoque(rs.getString("qt"));

							}
							else if(condicao.equals("RETIRADOS")) {
								r.setDataEntregar(rs.getDate("data_entregar"));
								r.setDateRetirado(rs.getDate("data_retirado"));
							}
							
							else if(condicao.equals("LIVROENTREGAR")) {
								r.setDataEntregar(rs.getDate("data_entregar"));
								r.setDateRetirado(rs.getDate("data_retirado"));
								r.setResponsavel(rs.getString("nome"));

							}
						}
						if(situacao == null){
						r.setId_estoque(rs.getInt("id_estoque"));
						r.setId_usuario(rs.getInt("id_usuario"));
						r.setCondicao(rs.getString("condicao"));
						r.setTitulo(rs.getString("titulo"));
						r.setDtCadastro(rs.getDate("data"));
						r.setTipoUsuario(rs.getString("tipo_usuario"));
						}
						else {
							r.setId_usuario(rs.getInt("id_usuario"));
							r.setCondicao(rs.getString("condicao"));
							r.setDtCadastro(rs.getDate("data"));
							r.setTipoUsuario(rs.getString("tipo_usuario"));
						}
						reservas.add(r);

												
			}
			return reservas;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
