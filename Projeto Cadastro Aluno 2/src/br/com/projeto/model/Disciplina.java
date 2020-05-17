package br.com.projeto.model;

public class Disciplina {
	private int codDisciplina;
	private String descricao;
	
	public Disciplina() {
		
	}

	public Disciplina(int codDisciplina, String descricao) {
		super();
		this.codDisciplina = codDisciplina;
		this.descricao = descricao;
	}

	public int getCodDisciplina() {
		return codDisciplina;
	}

	public void setCodDisciplina(int codDisciplina) {
		this.codDisciplina = codDisciplina;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return getDescricao();
	}
	
}
