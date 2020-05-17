package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.model.Curso;
import br.com.projeto.util.ConnectionFactory;

public class CursoDAO {

	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	private Curso curso;

	public CursoDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Curso consultar(String descricaoCurso) throws Exception {
		try {
			String sql = "select * from curso where nomeCurso = ?";
			st = con.prepareStatement(sql);
			st.setString(1, descricaoCurso);
	
			rs = st.executeQuery();
	
			while (rs.next()) {
				int codCurso = rs.getInt("codCurso");
				String nomeCurso = rs.getString("nomeCurso");
				curso = new Curso(codCurso, nomeCurso);
				return curso;
			}
			return null;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Curso> consultarTodos() throws Exception {
		try {
			List<Curso> cursos = new ArrayList<Curso>();
			String sql = "select * from curso";
			st = con.prepareStatement(sql);
	
			rs = st.executeQuery();
	
			while (rs.next()) {
				int codCurso = rs.getInt("codCurso");
				String nomeCurso = rs.getString("nomeCurso");
				cursos.add(new Curso(codCurso, nomeCurso));
			}
			return cursos;
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
