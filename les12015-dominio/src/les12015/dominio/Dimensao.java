package les12015.dominio;

public class Dimensao extends EntidadeDominio {
	
	private String Altura;
	private String largura;
	private String peso;
	private String profundidade;
	public String getAltura() {
		return Altura;
	}
	public void setAltura(String altura) {
		Altura = altura;
	}
	public String getLargura() {
		return largura;
	}
	public void setLargura(String largura) {
		this.largura = largura;
	}
	public String getPeso() {
		return peso;
	}
	public void setPeso(String peso) {
		this.peso = peso;
	}
	public String getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}
}
