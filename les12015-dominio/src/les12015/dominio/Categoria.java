package les12015.dominio;


public class Categoria extends EntidadeDominio {
	
	private String  descricao;
	private String tipo_cat;
	private int recebe;
	private String situacao;
	private int relacat;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo_cat() {
		return tipo_cat;
	}
	public void setTipo_cat(String tipo_cat) {
		this.tipo_cat = tipo_cat;
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
	public int getRelacat() {
		return relacat;
	}
	public void setRelacat(int relacat) {
		this.relacat = relacat;
	}
	

}
