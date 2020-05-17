package br.com.projeto.model;

public class Aluno {
	
	private String rgm;
	private String nome;
	private String dataNasc;
	private String cpf;
	private String email;
	private String municipio;
	private String uf;
	private String celular;
	private String end;
	
	public Aluno() {
		
	}
	
	public Aluno(String rgm, String nome, String dataNasc, String cpf, String email, String municipio, String uf,
			String celular, String end) {
		super();
		this.rgm = rgm;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.cpf = cpf;
		this.email = email;
		this.municipio = municipio;
		this.uf = uf;
		this.celular = celular;
		this.end = end;
	}

	public String getRgm() {
		return rgm;
	}

	public void setRgm(String rgm) {
		this.rgm = rgm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	
	
}
