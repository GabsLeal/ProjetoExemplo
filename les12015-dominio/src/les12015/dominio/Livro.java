package les12015.dominio;

import java.util.List;

public class Livro extends EntidadeDominio{
	private String ano;
	private String autor;
	private String titulo;
	private String editora;
	private String edicao;
	private String ISBN;
	private String nPaginas;
	private String sinopse;
	private String precificacao;
	private Dimensao dimensao;
	private List<Categoria> categoria;
	private String cod_barra;
	private int recebe;
	private String situacao;
	private String data;
	private StatusMotivo statusmotivo;
	private Quantidade quantidade;
	private int id_usuario;
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getnPaginas() {
		return nPaginas;
	}

	public void setnPaginas(String nPaginas) {
		this.nPaginas = nPaginas;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getPrecificacao() {
		return precificacao;
	}

	public void setPrecificacao(String precificacao) {
		this.precificacao = precificacao;
	}

	public Dimensao getDimensao() {
		return dimensao;
	}

	public void setDimensao(Dimensao dimensao) {
		this.dimensao = dimensao;
	}


	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getCod_barra() {
		return cod_barra;
	}

	public void setCod_barra(String cod_barra) {
		this.cod_barra = cod_barra;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}



	public int getRecebe() {
		return recebe;
	}

	public void setRecebe(int recebe) {
		this.recebe = recebe;
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

	public StatusMotivo getStatusmotivo() {
		return statusmotivo;
	}

	public void setStatusmotivo(StatusMotivo statusmotivo) {
		this.statusmotivo = statusmotivo;
	}

	public Quantidade getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Quantidade quantidade) {
		this.quantidade = quantidade;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}





}
