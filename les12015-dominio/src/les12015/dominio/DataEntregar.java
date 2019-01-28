package les12015.dominio;

import java.util.Date;

public class DataEntregar extends EntidadeDominio{

	private int id_reserva;
	private Date data_entregar;
	private String tipoUsuario;
	private int id_usuario;
	private int id_livro;
	private int id_estoque;
	public int getId_reserva() {
		return id_reserva;
	}
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	public Date getData_entregar() {
		return data_entregar;
	}
	public void setData_entregar(Date data_entregar) {
		this.data_entregar = data_entregar;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_livro() {
		return id_livro;
	}
	public void setId_livro(int id_livro) {
		this.id_livro = id_livro;
	}
	public int getId_estoque() {
		return id_estoque;
	}
	public void setId_estoque(int id_estoque) {
		this.id_estoque = id_estoque;
	}
}
