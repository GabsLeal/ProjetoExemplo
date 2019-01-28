package les12015.dominio;

import java.util.Date;

public class Reserva extends EntidadeDominio {
	
	private int id_estoque;
	private int id_usuario;
	private String qt;
	private String titulo;
	private String condicao;
	private Date dataEntregar;
	private Date dateRetirado;
	private String qtEstoque;
	private String tipoUsuario;
	private int id_livro;
	public int getId_estoque() {
		return id_estoque;
	}
	public void setId_estoque(int id_estoque) {
		this.id_estoque = id_estoque;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getQt() {
		return qt;
	}
	public void setQt(String qt) {
		this.qt = qt;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getCondicao() {
		return condicao;
	}
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	public Date getDataEntregar() {
		return dataEntregar;
	}
	public void setDataEntregar(Date dataEntregar) {
		this.dataEntregar = dataEntregar;
	}
	public Date getDateRetirado() {
		return dateRetirado;
	}
	public void setDateRetirado(Date dateRetirado) {
		this.dateRetirado = dateRetirado;
	}
	public String getQtEstoque() {
		return qtEstoque;
	}
	public void setQtEstoque(String qtEstoque) {
		this.qtEstoque = qtEstoque;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public int getId_livro() {
		return id_livro;
	}
	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}
}
