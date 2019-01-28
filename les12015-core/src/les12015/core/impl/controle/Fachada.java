package les12015.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import les12015.core.IDAO;
import les12015.core.IFachada;
import les12015.core.IStrategy;
import les12015.core.aplicacao.Resultado;
import les12015.core.impl.dao.AdminDAO;
import les12015.core.impl.dao.AlunoDAO;
import les12015.core.impl.dao.AnaliseDAO;
import les12015.core.impl.dao.BibiotecaDAO;
import les12015.core.impl.dao.CategoriaDAO;
import les12015.core.impl.dao.CategoriaLivroDadosDAO;
import les12015.core.impl.dao.DataEntregarDAO;
import les12015.core.impl.dao.EstoqueDAO;
import les12015.core.impl.dao.LivroDAO;
import les12015.core.impl.dao.MultaDAO;
import les12015.core.impl.dao.ReservaDAO;
import les12015.core.impl.dao.StatusMotivoDAO;
import les12015.core.impl.negocio.ComplementarDtCadastro;
import les12015.core.impl.negocio.ComplementarStatus;
import les12015.core.impl.negocio.ValidadorDadosObrigatoriosLivro;
import les12015.core.impl.negocio.ValidarDadosObrigatoriosAluno;
import les12015.core.impl.negocio.ValidarEntrega;
import les12015.core.impl.negocio.ValidarEntregaBiblioteca;
import les12015.core.impl.negocio.ValidarLoginAdmin;
import les12015.core.impl.negocio.ValidarLoginAluno;
import les12015.core.impl.negocio.ValidarLoginBiblioteca;
import les12015.core.impl.negocio.ValidarMotivoStatus;
import les12015.core.impl.negocio.ValidarMulta;
import les12015.core.impl.negocio.ValidarReserva;
import les12015.core.impl.negocio.ValidarRetirada;
import les12015.core.impl.negocio.ValidarStatus;
import les12015.dominio.Admin;
import les12015.dominio.Aluno;
import les12015.dominio.Analise;
import les12015.dominio.Biblioteca;
import les12015.dominio.Categoria;
import les12015.dominio.Categoriadados;
import les12015.dominio.DataEntregar;
import les12015.dominio.EntidadeDominio;
import les12015.dominio.Livro;
import les12015.dominio.Multa;
import les12015.dominio.Quantidade;
import les12015.dominio.Reserva;
import les12015.dominio.Retirado;

public class Fachada implements IFachada {

	/** 
	 * Mapa de DAOS, ser� indexado pelo nome da entidade 
	 * O valor � uma inst�ncia do DAO para uma dada entidade; 
	 */
	private Map<String, IDAO> daos;
	
	/**
	 * Mapa para conter as regras de neg�cio de todas opera��es por entidade;
	 * O valor � um mapa que de regras de neg�cio indexado pela opera��o
	 */
	private Map<String, Map<String, List<IStrategy>>> rns;
	
	private Resultado resultado;
	
	
	public Fachada(){
		/* Int�nciando o Map de DAOS */
		daos = new HashMap<String, IDAO>();
		/* Int�nciando o Map de Regras de Neg�cio */
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		
		int initialDelay = 30000; // start after 30 seconds
		int period = 5000;        // repeat every 5 seconds
		Timer timer = new Timer();
		
		/* Criando inst�ncias dos DAOs a serem utilizados*/
		
//		CategoriaDAO categoriaDAO = new CategoriaDAO();
		LivroDAO livroDAO = new LivroDAO();
//		CartaoDAO cartaoDAO = new CartaoDAO();
		CategoriaLivroDadosDAO catlivroDAO = new CategoriaLivroDadosDAO();
		AlunoDAO alunoDAO = new AlunoDAO();
		EstoqueDAO estoqueDAO = new EstoqueDAO();
		BibiotecaDAO bibliotecaDAO = new BibiotecaDAO();
		ReservaDAO reservaDAO = new ReservaDAO();
		AdminDAO adminDAO = new AdminDAO();
		DataEntregarDAO dataEnregarDAO = new DataEntregarDAO();
		AnaliseDAO analiseDAO = new AnaliseDAO();
		MultaDAO multaDAO = new MultaDAO();
		//StatusMotivoDAO statusMotivoDAO = new StatusMotivoDAO();
		/* Adicionando cada dao no MAP indexando pelo nome da classe */

		daos.put(Livro.class.getName(), livroDAO);
//		daos.put(Categoriadados.class.getName(), categoriaDAO);

//		daos.put(CartaoCredito.class.getName(), cartaoDAO);
		daos.put(Categoria.class.getName(), catlivroDAO);

		daos.put(Aluno.class.getName(), alunoDAO);
		daos.put(Quantidade.class.getName(), estoqueDAO);
		daos.put(Biblioteca.class.getName(), bibliotecaDAO);
		daos.put(Reserva.class.getName(), reservaDAO);
		daos.put(Admin.class.getName(), adminDAO);
		daos.put(DataEntregar.class.getName(), dataEnregarDAO);
		daos.put(Analise.class.getName(), analiseDAO);
		daos.put(Multa.class.getName(), multaDAO);

	//	daos.put(Aluno.class.getName(), statusMotivoDAO);

		/* Criando inst�ncias de regras de neg�cio a serem utilizados*/		
		ValidadorDadosObrigatoriosLivro vrDadosObrigatoriosFornecedor = new ValidadorDadosObrigatoriosLivro();
		ComplementarDtCadastro vrDtCadastro = new ComplementarDtCadastro();
		ComplementarStatus vrStatus= new ComplementarStatus();
		ValidarDadosObrigatoriosAluno vrDadosObrigatoriosAluno = new ValidarDadosObrigatoriosAluno();
		ValidarLoginAluno vrValidarLoginAluno = new ValidarLoginAluno();
		ValidarReserva vrValidarReserva = new ValidarReserva();
		ValidarLoginAdmin vrValidarLoginAdmin = new ValidarLoginAdmin();
		ValidarLoginBiblioteca vrValidarLoginBiblioteca = new ValidarLoginBiblioteca();
		ValidarRetirada vrValidarRetirada = new ValidarRetirada();
		ValidarEntrega vrValidarEntrega = new ValidarEntrega();
		ValidarMulta vrValidarMulta = new ValidarMulta();
		ValidarStatus vrValidarStatus = new ValidarStatus();
		ValidarEntregaBiblioteca vrValidarEntregaBiblioteca = new ValidarEntregaBiblioteca();

	//	ValidarMotivoStatus vrMotivoStatus = new ValidarMotivoStatus();

		
		/* Criando uma lista para conter as regras de neg�cio de fornencedor
		 * quando a opera��o for salvar
		 */
		List<IStrategy> rnsSalvarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarLivro = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarAluno = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarAluno = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarEstoque = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarAdmin = new ArrayList<IStrategy>();
		List<IStrategy> rnsConsultarBiblioteca = new ArrayList<IStrategy>();
		List<IStrategy> rnsSalvarDataLivro = new ArrayList<IStrategy>();



	//	List<IStrategy> rnsConsultarLivro = new ArrayList<IStrategy>();

		/* Adicionando as regras a serem utilizadas na opera��o salvar do fornecedor*/
		rnsSalvarLivro.add(vrDadosObrigatoriosFornecedor);
		rnsSalvarLivro.add(vrDtCadastro);
		rnsSalvarLivro.add(vrStatus);
		
		rnsSalvarAluno.add(vrDtCadastro);
		rnsSalvarAluno.add(vrStatus);
		rnsSalvarAluno.add(vrDadosObrigatoriosAluno);
		rnsConsultarAluno.add(vrValidarLoginAluno);
		rnsAlterarEstoque.add(vrValidarReserva);
		rnsAlterarEstoque.add(vrDtCadastro);
		rnsAlterarEstoque.add(vrValidarStatus);

	//	rnsAlterarAluno.add(vrMotivoStatus);

		rnsSalvarLivro.add(vrStatus);

		rnsAlterarLivro.add(vrDadosObrigatoriosFornecedor);
		
		
		rnsConsultarAdmin.add(vrValidarLoginAdmin);
		rnsConsultarAdmin.add(vrValidarEntrega);
		rnsConsultarAdmin.add(vrValidarMulta);
		rnsConsultarAdmin.add(vrValidarEntrega);
		rnsConsultarAdmin.add(vrValidarEntregaBiblioteca);



		rnsConsultarBiblioteca.add(vrValidarLoginBiblioteca);

		rnsSalvarDataLivro.add(vrValidarRetirada);

	//	rnsConsultarLivro.add(vrValidadorDadosPesquisa);
		/* Cria o mapa que poder� conter todas as listas de regras de neg�cio espec�fica 
		 * por opera��o  do fornecedor
		 */
		Map<String, List<IStrategy>> rnsLivro = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsAluno = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsEstoque = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsAdmin = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsBiblioteca = new HashMap<String, List<IStrategy>>();
		Map<String, List<IStrategy>> rnsDataEntregar = new HashMap<String, List<IStrategy>>();

	//	Map<String, List<IStrategy>> rnsAlunoAlterar = new HashMap<String, List<IStrategy>>();

		/*
		 * Adiciona a listra de regras na opera��o salvar no mapa do fornecedor (lista criada na linha 70)
		 */
		rnsLivro.put("SALVAR", rnsSalvarLivro);	
		rnsLivro.put("ALTERAR", rnsAlterarLivro);	
		rnsAluno.put("SALVAR", rnsSalvarAluno);	
		rnsAluno.put("CONSULTAR", rnsConsultarAluno);	
		rnsEstoque.put("ALTERAR", rnsAlterarEstoque);	
		rnsAdmin.put("CONSULTAR", rnsConsultarAdmin);	
		rnsBiblioteca.put("CONSULTAR", rnsConsultarBiblioteca);	
		rnsDataEntregar.put("SALVAR", rnsSalvarDataLivro);	

	//	rnsAluno.put("ALTERAR", rnsAlterarAluno);	

	//	rnsMotivoStatus.put("ALTERAR", rnsSalvarMotivoStatus);	
//		rnsLivro.put("CONSULTAR", rnsConsultarLivro);
		/* Adiciona o mapa(criado na linha 79) com as regras indexadas pelas opera��es no mapa geral indexado 
		 * pelo nome da entidade
		 */
		rns.put(Livro.class.getName(), rnsLivro);
		rns.put(Aluno.class.getName(), rnsAluno);
		rns.put(Quantidade.class.getName(), rnsEstoque);
		rns.put(Admin.class.getName(), rnsAdmin);
		rns.put(Biblioteca.class.getName(), rnsBiblioteca);
		rns.put(DataEntregar.class.getName(), rnsDataEntregar);


	
		
	}
	
	
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "SALVAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;
	}

	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "ALTERAR");
	
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}

	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "EXCLUIR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar o registro!");
				
			}
		}else{
			resultado.setMsg(msg);
					
			
		}
		
		return resultado;

	}

	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();	
		
		String msg = executarRegras(entidade, "CONSULTAR");
		
		
		if(msg == null){
			IDAO dao = daos.get(nmClasse);
			try {
				
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel realizar a consulta!");
				
			}
		}else{
			resultado.setMsg(msg);
			
		}
		
		return resultado;

	}
	
	@Override
	public Resultado visualizar(EntidadeDominio entidade) {
		resultado = new Resultado();
		resultado.setEntidades(new ArrayList<EntidadeDominio>(1));
		resultado.getEntidades().add(entidade);		
		return resultado;

	}

	
	private String executarRegras(EntidadeDominio entidade, String operacao){
		String nmClasse = entidade.getClass().getName();		
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		
		if(regrasOperacao != null){
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null){
				for(IStrategy s: regras){			
					String m = s.processar(entidade);			
					
					if(m != null){
						msg.append(m);
						msg.append("\n");
					}			
				}	
			}			
			
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}
