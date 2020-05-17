package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.model.Disciplina;
import br.com.projeto.util.ConnectionFactory;

public class DisciplinaDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public DisciplinaDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<Disciplina> consultar(String rgm) throws Exception{
		try {
			List<Disciplina> disciplinas = new ArrayList<Disciplina>();
	
			String sql = "select * from view_disciplinas_por_rgm where rgm = ?";
			st = con.prepareStatement(sql);
			st.setString(1, rgm);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				int cod = rs.getInt("codDisciplina");
				String nome = rs.getString("descricao");
				
				disciplinas.add (new Disciplina(cod, nome));
			}
			return disciplinas;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Disciplina consultarPorDescricao(String descricao) throws Exception{
		try {
			Disciplina disciplina = new Disciplina();
			String sql = "select * from view_disciplians_por_descricao where descricao = ? limit 1";
			st = con.prepareStatement(sql);
			st.setString(1, descricao);
			
			rs = st.executeQuery();
			
			while (rs.next()) {
				int cod = rs.getInt("codDisciplina");
				String nome = rs.getString("descricao");
				
				disciplina = new Disciplina(cod, nome);
			}
			return disciplina;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
