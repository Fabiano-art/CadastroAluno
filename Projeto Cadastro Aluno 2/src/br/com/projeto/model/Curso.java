package br.com.projeto.model;

public class Curso {
	private int codCurso;
	private String nomeCurso;
	
	public Curso() {
		
	}

	public Curso(int codCurso, String nomeCurso) {
		super();
		this.codCurso = codCurso;
		this.nomeCurso = nomeCurso;
	}

	public int getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(int codCurso) {
		this.codCurso = codCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	 public String toString() {
	        return getNomeCurso();
	 }
}
