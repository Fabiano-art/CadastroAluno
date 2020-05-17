package br.com.projeto.dao;

import java.sql.*;

import br.com.projeto.model.Aluno;
import br.com.projeto.util.ConnectionFactory;

public class AlunoDAO {
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public AlunoDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public boolean salvar(Aluno aluno) throws Exception {
		boolean salvo = false;
		try {
			String sql = "insert into aluno (rgm, nome, dataNasc, cpf, email, endereco, municipio, celular, uf)" + 
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			st = con.prepareStatement(sql);
			st.setString(1, aluno.getRgm());
			st.setString(2, aluno.getNome());
			st.setString(3, aluno.getDataNasc());
			st.setString(4, aluno.getCpf());
			st.setString(5, aluno.getEmail());
			st.setString(6, aluno.getEnd());
			st.setString(7, aluno.getMunicipio());
			st.setString(8, aluno.getCelular());
			st.setString(9, aluno.getUf());
			
			st.executeUpdate();
			
			salvo = true;
			return salvo;
		}
		catch (SQLIntegrityConstraintViolationException e) {
			return salvo;
		}
		catch (Exception e1) {
			throw new Exception(e1.getMessage());
		}
	}
	
	public Aluno consultar(String rgm) throws Exception {
		try {
			String sql = "select * from aluno where rgm = ?";
			st = con.prepareStatement(sql);
			st.setString(1, rgm);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				String rgmRs = rs.getString("rgm");
				String nome = rs.getString("nome");
				String data = rs.getString("dataNasc");
				String cpf = rs.getString("cpf");
				String email = rs.getString("email");
				String municipio = rs.getString("municipio");
				String uf = rs.getString("uf");
				String end = rs.getString("endereco");
				String celular = rs.getString("celular");
				
				Aluno aluno = new Aluno(rgmRs, nome, data, cpf, email, municipio, uf, celular, end);
				
				return aluno;
			}
		}
		catch (Exception e) {
			throw new Exception (e.getMessage());
		}
		return null;
	}
	
	public int excluir(String rgm) throws Exception{
		try {
			String sql = "delete from aluno where rgm = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, rgm);
			
			return st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void alterar(Aluno aluno) throws Exception{
		try {
			String sql = "update aluno set nome = ?, dataNasc = ?, cpf = ?, email = ?, municipio = ?, uf = ?, celular = ?, endereco = ?"
					+ "where rgm = ?";
			st = con.prepareStatement(sql);
			
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getDataNasc());
			st.setString(3, aluno.getCpf());
			st.setString(4, aluno.getEmail());
			st.setString(5, aluno.getMunicipio());
			st.setString(6, aluno.getUf());
			st.setString(7, aluno.getCelular());
			st.setString(8, aluno.getEnd());
			st.setString(9, aluno.getRgm());
			
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
