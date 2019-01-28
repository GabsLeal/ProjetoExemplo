package les12015.dominio;

import java.util.Date;

public class Analise extends EntidadeDominio{
	private String qtAluno;
	private String qtProfessor;
	private String qtBiblioteca;
	private String dataAnalise;
	private String categoria;
	private String ano;
	public String getQtAluno() {
		return qtAluno;
	}
	public void setQtAluno(String qtAluno) {
		this.qtAluno = qtAluno;
	}
	public String getQtProfessor() {
		return qtProfessor;
	}
	public void setQtProfessor(String qtProfessor) {
		this.qtProfessor = qtProfessor;
	}
	public String getQtBiblioteca() {
		return qtBiblioteca;
	}
	public void setQtBiblioteca(String qtBiblioteca) {
		this.qtBiblioteca = qtBiblioteca;
	}
	public String getDataAnalise() {
		return dataAnalise;
	}
	public void setDataAnalise(String dataAnalise) {
		this.dataAnalise = dataAnalise;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}


}
