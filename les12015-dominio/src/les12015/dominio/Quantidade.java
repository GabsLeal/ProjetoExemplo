package les12015.dominio;

public class Quantidade extends EntidadeDominio  {
	
	private String descricao;
	private int id_livro;
	private int id_biblioteca;
	private int id_usuario;
	private String tipoUsuario;
	private String situacao;
	private String nome;
	
	public String getQuantidade() {
		return descricao;
	}

	public void setQuantidade(String descricao) {
		this.descricao = descricao;
	}

	public int getId_livro() {
		return id_livro;
	}

	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}

	public int getId_biblioteca() {
		return id_biblioteca;
	}

	public void setId_biblioteca(int id_biblioteca) {
		this.id_biblioteca = id_biblioteca;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
