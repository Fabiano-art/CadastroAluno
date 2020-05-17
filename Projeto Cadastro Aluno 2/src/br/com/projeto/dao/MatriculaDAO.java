package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.projeto.model.Aluno;
import br.com.projeto.model.Curso;
import br.com.projeto.model.Matricula;
import br.com.projeto.util.ConnectionFactory;

public class MatriculaDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public MatriculaDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void salvar(Matricula matricula) throws Exception{
		try {
			String sql = "INSERT INTO MATRICULA (rgm_FK, codCurso_FK, campus, periodo)"
					+ "values (?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			st.setString(1, matricula.getAluno().getRgm());
			st.setInt(2, matricula.getCurso().getCodCurso());
			st.setString(3, matricula.getCampus());
			st.setString(4, matricula.getPeriodo());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Matricula consultar(String rgm) throws Exception{
		try {
			String sql = "select *\r\n" + 
					"from aluno\r\n" + 
					"join matricula on aluno.rgm = matricula.rgm_FK\r\n" + 
					"join curso on matricula.codCurso_FK = curso.codCurso "
					+ "where rgm_FK = ?";
			st = con.prepareStatement(sql);
			st.setString(1, rgm);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				int codMatricula = rs.getInt("codMatricula");
				String rgmFk = rs.getString("rgm");
				String nome = rs.getString("nome");
				String data = rs.getString("dataNasc");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String municipio = rs.getString("municipio");
				String uf = rs.getString("uf");
				String end = rs.getString("endereco");
				String celular = rs.getString("celular");
				String campus = rs.getString("campus");
				String periodo = rs.getString("periodo");
				String nomeDoCurso = rs.getString("nomeCurso");
				int codCurso = rs.getInt("codCurso");
				
				Aluno aluno = new Aluno(rgmFk, nome, data, cpf, email, municipio, uf, celular, end);
				Curso curso = new Curso(codCurso, nomeDoCurso);
				
				Matricula matricula = new Matricula(codMatricula, aluno, curso, campus, periodo);
				
				return matricula;
			}
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;
		
	}
	
	public int excluir(String rgm) throws Exception{
		try {
			String sql = "delete from matricula where rgm_FK = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, rgm);
			
			return st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void alterar(Matricula matricula) throws Exception{
		try {
			String sql = "update matricula set rgm_FK = ?, codCurso_FK = ?, campus = ?, periodo = ?"
					+ "where rgm_FK = ?";
			st = con.prepareStatement(sql);
			st.setString(1, matricula.getAluno().getRgm());
			st.setInt(2, matricula.getCurso().getCodCurso());
			st.setString(3, matricula.getCampus());
			st.setString(4, matricula.getPeriodo());
			st.setString(5, matricula.getAluno().getRgm()); 
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
