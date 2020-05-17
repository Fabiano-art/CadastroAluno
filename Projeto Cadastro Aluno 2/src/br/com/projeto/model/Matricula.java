package br.com.projeto.model;

public class Matricula {
	private int codMatricula;
	private Aluno aluno;
	private Curso curso;
	private String campus;
	private String periodo;
	
	public Matricula() {
		
	}

	public Matricula(int codMatricula, Aluno aluno, Curso curso, String campus, String periodo) {
		super();
		this.codMatricula = codMatricula;
		this.aluno = aluno;
		this.curso = curso;
		this.campus = campus;
		this.periodo = periodo;
	}

	public int getCodMatricula() {
		return codMatricula;
	}

	public void setCodMatricula(int codMatricula) {
		this.codMatricula = codMatricula;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Object object) {
		this.curso = (Curso) object;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
