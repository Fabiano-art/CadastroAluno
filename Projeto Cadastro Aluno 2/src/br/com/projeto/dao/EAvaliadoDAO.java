package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.model.Aluno;
import br.com.projeto.model.Disciplina;
import br.com.projeto.model.EAvaliado;
import br.com.projeto.util.ConnectionFactory;

public class EAvaliadoDAO {
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public EAvaliadoDAO() throws Exception {
		try {
			con = ConnectionFactory.getConnection();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void salvar(EAvaliado EAvaliado) throws Exception{
		try {
			String sql = "insert into e_avaliado (semestre, nota, faltas, aluno_FK, disciplina_FK)"
					+ "values(?, ?, ?, ?, ?)";
			st = con.prepareStatement(sql);
			
			st.setString(1, EAvaliado.getSemestre());
			st.setString(2, EAvaliado.getNota());
			st.setString(3, EAvaliado.getFaltas());
			st.setString(4, EAvaliado.getAluno().getRgm());
			st.setInt(5, EAvaliado.getDisciplina().getCodDisciplina());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void excluir(Aluno rgm, Disciplina disciplina) throws Exception{
		try {
			String sql = "delete from e_avaliado where aluno_FK = ? and disciplina_FK = ?";
			
			st = con.prepareStatement(sql);
			
			st.setString(1, rgm.getRgm());
			st.setInt(2, disciplina.getCodDisciplina());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void excluir(String rgm) throws Exception{
		try {
			String sql = "delete from e_avaliado where aluno_FK = ?";
			
			st = con.prepareStatement(sql);
			
			st.setString(1, rgm);
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<EAvaliado> consultar(String rgm) throws Exception{
		try {
			String sql = "select * from view_consulta_notas_faltas where rgm = ?";
			
			st = con.prepareStatement(sql);
			st.setString(1, rgm);
			
			rs = st.executeQuery();
			
			List<EAvaliado> avaliacoes = new ArrayList<EAvaliado>();
			Aluno aluno = new Aluno();
			
			while (rs.next()) {
				int codBoletim = rs.getInt("codBoletim");
				String rgm2 = rs.getString("rgm");
				String nome = rs.getString("nome");
				String semestre = rs.getString("semestre");
				String nota = rs.getString("nota");
				String faltas = rs.getString("faltas");
				int codDisciplina = rs.getInt("codDisciplina");
				String descricao = rs.getString("descricao");
				
				aluno.setRgm(rgm2);
				aluno.setNome(nome);
				
				avaliacoes.add(new EAvaliado(codBoletim, semestre, nota, faltas, aluno, new Disciplina(codDisciplina, descricao)));
			}
			
			return avaliacoes;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public void alterar (EAvaliado avaliado) throws Exception {
		try {
			String sql = "update e_avaliado set semestre = ?, nota = ?, faltas = ?, aluno_FK = ?, disciplina_FK = ? "
					+ "where disciplina_FK = ? and aluno_FK = ? ";
			st = con.prepareStatement(sql);
			
			st.setString(1, avaliado.getSemestre());
			st.setString(2, avaliado.getNota());
			st.setString(3, avaliado.getFaltas());
			st.setString(4, avaliado.getAluno().getRgm());
			st.setInt(5, avaliado.getDisciplina().getCodDisciplina());
			st.setInt(6, avaliado.getDisciplina().getCodDisciplina());
			st.setString(7, avaliado.getAluno().getRgm());
			
			st.executeUpdate();
		}
		catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
