package br.com.projeto.model;

public class EAvaliado {
	private int codBoletim;
	private String semestre;
	private String nota;
	private String faltas;
	private Aluno aluno;
	private Disciplina disciplina;
	
	public EAvaliado() {
		
	}
	
	public EAvaliado(int codBoletim, String semestre, String nota, String faltas, Aluno aluno, Disciplina disciplina) {
		super();
		this.codBoletim = codBoletim;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
		this.aluno = aluno;
		this.disciplina = disciplina;
	}

	public int getCodBoletim() {
		return codBoletim;
	}

	public void setCodBoletim(int codBoletim) {
		this.codBoletim = codBoletim;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getFaltas() {
		return faltas;
	}

	public void setFaltas(String faltas) {
		this.faltas = faltas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
}
