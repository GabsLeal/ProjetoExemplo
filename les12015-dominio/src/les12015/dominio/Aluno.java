package les12015.dominio;

import java.util.List;

public class Aluno extends EntidadeDominio {
	private String genero;
	private String nome;
	private String ra;
	private String email;
	private String senha;
	private String data_nascimento;
	private Telefone telefone;
	private Endereco endereco;
	private int recebe;
	private String senhaconfirma;
	private String situacao;
	private String data;
	private Integer id_alt;
	private String matricula;
	private String dado;


	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
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
	public Integer getId_alt() {
		return id_alt;
	}
	public void setId_alt(Integer id_alt) {
		this.id_alt = id_alt;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getDado() {
		return dado;
	}
	public void setDado(String dado) {
		this.dado = dado;
	}


}
