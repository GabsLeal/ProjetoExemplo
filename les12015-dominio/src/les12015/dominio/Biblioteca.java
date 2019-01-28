package les12015.dominio;

public class Biblioteca extends EntidadeDominio {
	private String nome;
	private String numero_iden;
	private String email;
	private String senha;
	private String data_funda;
	private Telefone telefone;
	private Endereco endereco;
	private int recebe;
	private String senhaconfirma;
	private String situacao;
	private String data;
	private Integer id_alt;
	private String dado;
	private Livro livro;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNumero_iden() {
		return numero_iden;
	}
	public void setNumero_iden(String numero_iden) {
		this.numero_iden = numero_iden;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getData_funda() {
		return data_funda;
	}
	public void setData_funda(String data_funda) {
		this.data_funda = data_funda;
	}
	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public int getRecebe() {
		return recebe;
	}
	public void setRecebe(int recebe) {
		this.recebe = recebe;
	}
	public String getSenhaconfirma() {
		return senhaconfirma;
	}
	public void setSenhaconfirma(String senhaconfirma) {
		this.senhaconfirma = senhaconfirma;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDado() {
		return dado;
	}
	public void setDado(String dado) {
		this.dado = dado;
	}
	public Integer getId_alt() {
		return id_alt;
	}
	public void setId_alt(Integer id_alt) {
		this.id_alt = id_alt;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
