package les12015.dominio;


import java.util.Date;

public class EntidadeDominio implements IEntidade{
	
	private Integer id;
	private Date dtCadastro;
	private int idUser;
	private String tipoUser;
	private Boolean status = true;
	private String responsavel;
	private String motivo;
	private Integer idreserva;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public Integer getIdreserva() {
		return idreserva;
	}
	public void setIdreserva(Integer idreserva) {
		this.idreserva = idreserva;
	}
	public String getTipoUser() {
		return tipoUser;
	}
	public void setTipoUser(String tipoUser) {
		this.tipoUser = tipoUser;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	
	

}
