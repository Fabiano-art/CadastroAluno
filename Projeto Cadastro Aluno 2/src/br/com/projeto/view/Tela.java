package br.com.projeto.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.com.projeto.dao.AlunoDAO;
import br.com.projeto.dao.CursoDAO;
import br.com.projeto.dao.DisciplinaDAO;
import br.com.projeto.dao.EAvaliadoDAO;
import br.com.projeto.dao.MatriculaDAO;
import br.com.projeto.model.Aluno;
import br.com.projeto.model.Curso;
import br.com.projeto.model.Disciplina;
import br.com.projeto.model.EAvaliado;
import br.com.projeto.model.Matricula;
import java.awt.event.KeyAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tela extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblRgm_1;
	private JTextField txtRgmDados;
	private JLabel lblNome_1;
	private JTextField txtNome;
	private JLabel lblData_1;
	private JLabel lblCpf_1;
	private JLabel lblEmail_1;
	private JTextField txtEmail;
	private JLabel lblEnd_1;
	private JTextField txtEnd;
	private JLabel lblNewLabel_1;
	private JTextField txtMunicipio;
	private JLabel lblUf_1;
	private JComboBox cmbUf;
	private JLabel lblCelular_1;
	private JFormattedTextField ftfCpf;
	private JFormattedTextField ftfDataNascimento;
	private JFormattedTextField ftfCelular;
	private JLabel lblCurso;
	private JComboBox cmbCurso;
	private JLabel lblNewLabel_2;
	private JComboBox cmbCampus;
	private JLabel lblPerdo;
	private JRadioButton rdbManha;
	private JRadioButton rdbVespertino;
	private JRadioButton rdbNoturno;
	private JButton btnSalvar;
	private JButton btnNovo;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JMenuBar menuBar;
	private JMenu mnAluno;
	private JMenu mnNotasEFaltas;
	private JMenu mnSobre;
	private JMenuItem mntmSalvar;
	private JMenuItem mntmAlterar;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmExcluir;
	private JMenuItem mntmSair;
	private JSeparator separator;
	private JLabel lblRgm;
	private JTextField txtRgmNotas;
	private JLabel lblNomeAluno;
	private JLabel lblCursoAluno;
	private JComboBox cmbDisciplina;
	private JLabel lblDisciplina;
	private JLabel lblSemestre;
	private JComboBox cmbSemestre;
	private JComboBox cmbNota;
	private JLabel lblNota;
	private JLabel lblFaltas;
	private JTextField txtFaltas;
	private JButton btnSalvarNotas;
	private JButton btnNovoNotas;
	private JButton btnConsultarNotas;
	private JButton btnAlterarNotas;
	private JButton btnExcluirNotas;
	private final ButtonGroup btgPeriodo = new ButtonGroup();
	private JTextArea txtConsultaDisciplina;
	private JTextField txtRgmBoletim;
	private JLabel lblRgm_2;
	private JLabel lblConsulteOSeu;
	private JButton btnConsultarBoletim;
	private JButton btnLimparBoletim;
	private JTextArea txtBoletim;
	private JTextArea txtNotasEFaltas;
	private JMenuItem mntmSalvar_1;
	private JMenuItem mntmConsultar_1;
	private JMenuItem mntmConsultar_2;
	private JMenuItem mntmExcluir_1;
	private JMenuItem mntmSobre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela frame = new Tela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 411);
		setResizable(false);
		setTitle("Cadastro - Aluno");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnAluno = new JMenu("Aluno");
		menuBar.add(mnAluno);
		
		mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aluno aluno = new Aluno();
				
				aluno.setRgm(txtRgmDados.getText());
				aluno.setNome(txtNome.getText());
				aluno.setMunicipio(txtMunicipio.getText());
				aluno.setEnd(txtEnd.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setDataNasc(ftfDataNascimento.getText());
				aluno.setCpf(ftfCpf.getText());
				aluno.setCelular(ftfCelular.getText());
				aluno.setUf((String)cmbUf.getSelectedItem());
				
				Matricula matricula = new Matricula();
				
				matricula.setAluno(aluno);
				
				try {
					CursoDAO cursoDao = new CursoDAO();
					Curso curso = new Curso();
					curso = cursoDao.consultar((String)cmbCurso.getSelectedItem());
					matricula.setCurso(curso);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao carregar os cursos");
				}
				matricula.setCampus((String)cmbCampus.getSelectedItem());
				
				String periodo = "";
				
				if (rdbManha.isSelected()) {
					periodo = "Manha";
				}
				if (rdbNoturno.isSelected()) {
					periodo = "Noturno";
				}
				if (rdbVespertino.isSelected()) {
					periodo = "Vespertino";
				}
				
				matricula.setPeriodo(periodo);
				
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					MatriculaDAO matriculaDao = new MatriculaDAO();
					
					if (alunoDao.salvar(aluno)){
						matriculaDao.salvar(matricula);
						JOptionPane.showMessageDialog(null, "Aluno matriculado com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "O rgm ja foi cadastrado");
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao matricular o aluno");
				}
			}});
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnAluno.add(mntmSalvar);
		
		mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRgmDados.setEditable(false);
				Aluno aluno = new Aluno();
				Matricula matricula = new Matricula();
				
				aluno.setRgm(txtRgmDados.getText());
				aluno.setNome(txtNome.getText());
				aluno.setMunicipio(txtMunicipio.getText());
				aluno.setEnd(txtEnd.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setDataNasc(ftfDataNascimento.getText());
				aluno.setCpf(ftfCpf.getText());
				aluno.setCelular(ftfCelular.getText());
				aluno.setUf((String)cmbUf.getSelectedItem());
				
				matricula.setAluno(aluno);
				
				try {
					CursoDAO cursoDao = new CursoDAO();
					Curso curso = new Curso();
					curso = cursoDao.consultar((String)cmbCurso.getSelectedItem());
					matricula.setCurso(curso);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro durante a operação ");
				}
				matricula.setCampus((String)cmbCampus.getSelectedItem());
				
				String periodo = "";
				
				if (rdbManha.isSelected()) {
					periodo = "Manha";
				}
				if (rdbNoturno.isSelected()) {
					periodo = "Noturno";
				}
				if (rdbVespertino.isSelected()) {
					periodo = "Vespertino";
				}
				
				matricula.setPeriodo(periodo);
				
				int op = JOptionPane.showConfirmDialog(null, "Deseja alterar o aluno do RGM "+txtRgmDados.getText()+"?");
				try {
					if (op == 0) {
						AlunoDAO alunoDao = new AlunoDAO();
						MatriculaDAO matriculaDao = new MatriculaDAO();
						
						alunoDao.alterar(aluno);
						matriculaDao.alterar(matricula);
						
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
					}
					
				}catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao alterar os dados ");
				}
			}
		});
		mnAluno.add(mntmAlterar);
		
		mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rgm = JOptionPane.showInputDialog("Digite o RGM do aluno para consultar seus dados");
				txtRgmDados.setEditable(false);
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					Aluno aluno = new Aluno();
					aluno = alunoDao.consultar(rgm);
					
					txtRgmDados.setText(aluno.getRgm());
					txtNome.setText(aluno.getNome());
					txtEmail.setText(aluno.getEmail());
					txtMunicipio.setText(aluno.getMunicipio());
					txtEnd.setText(aluno.getEnd());
					ftfCelular.setText(aluno.getCelular());
					ftfCpf.setText(aluno.getCpf());
					ftfDataNascimento.setText(aluno.getDataNasc());
					cmbUf.setSelectedItem(aluno.getUf());
					
					MatriculaDAO matriculaDao = new MatriculaDAO();
					Matricula matricula = new Matricula();
					matricula = matriculaDao.consultar(rgm);
		
					cmbCurso.setSelectedItem(matricula.getCurso().getNomeCurso());
					cmbCampus.setSelectedItem(matricula.getCampus());
					
					if (matricula.getPeriodo().equals("Manha")) {
						rdbManha.setSelected(true);
					}
					if (matricula.getPeriodo().equals("Vespertino")) {
						rdbVespertino.setSelected(true);
					}
					if (matricula.getPeriodo().equals("Noturno")) {
						rdbNoturno.setSelected(true);
					}
					
					tabbedPane.setSelectedIndex(0);
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao consultar os dados do aluno");
				}
			}
		});
		mnAluno.add(mntmConsultar);
		
		mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					MatriculaDAO matriculaDao = new MatriculaDAO();
					
					String rgm = JOptionPane.showInputDialog("Digite o RGM do aluno a excluir");
					
					if (alunoDao.excluir(rgm) != 0) {
						matriculaDao.excluir(rgm);
						JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "O RGM digitado não consta nos nossos registros.");
					}
					
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o aluno");
				}
			}
		});
		mnAluno.add(mntmExcluir);
		
		separator = new JSeparator();
		mnAluno.add(separator);
		
		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnAluno.add(mntmSair);
		
		mnNotasEFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasEFaltas);
		
		mntmSalvar_1 = new JMenuItem("Salvar");
		mntmSalvar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					CursoDAO cursoDao = new CursoDAO();
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					
					Aluno aluno = new Aluno();
					Curso curso = new Curso();
					EAvaliado avaliado = new EAvaliado();
					Disciplina disciplina = new Disciplina();
					
					disciplina = disciplinaDao.consultarPorDescricao((String)cmbDisciplina.getSelectedItem());
					
					aluno = alunoDao.consultar(txtRgmNotas.getText());
					
					curso = cursoDao.consultar((String)cmbCurso.getSelectedItem());
					
					avaliado.setSemestre((String)cmbSemestre.getSelectedItem());
					avaliado.setNota((String)cmbNota.getSelectedItem());
					avaliado.setFaltas(txtFaltas.getText());
					avaliado.setAluno(aluno);
					avaliado.setDisciplina(disciplina);
					
					avaliadoDao.salvar(avaliado);
					
					JOptionPane.showMessageDialog(null, "Nota cadastrada");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao salvar a nota do aluno");
				}
			}
		});
		mnNotasEFaltas.add(mntmSalvar_1);
		
		mntmConsultar_1 = new JMenuItem("Alterar");
		mntmConsultar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					AlunoDAO alunoDao = new AlunoDAO();
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					
					EAvaliado avaliado = new EAvaliado();
					Aluno aluno = new Aluno();
					Disciplina disciplina = new Disciplina();
					
					if (txtRgmNotas.getText().length() != 0) {
						aluno = alunoDao.consultar(txtRgmNotas.getText());
						disciplina = disciplinaDao.consultarPorDescricao((String)cmbDisciplina.getSelectedItem());
						
						avaliado.setSemestre((String)cmbSemestre.getSelectedItem());
						avaliado.setNota((String)cmbNota.getSelectedItem());
						avaliado.setFaltas(txtFaltas.getText());
						avaliado.setAluno(aluno);
						avaliado.setDisciplina(disciplina);
						
						avaliadoDao.alterar(avaliado);
						
						JOptionPane.showMessageDialog(null, "Nota alterada com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "Digite o RGM do aluno");
					}
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao alterar a nota do aluno");
				}
			}
		});
		mnNotasEFaltas.add(mntmConsultar_1);
		
		mntmConsultar_2 = new JMenuItem("Consultar");
		mntmConsultar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRgmNotas.setText(null);
				txtRgmNotas.setEditable(false);
				cmbDisciplina.removeAllItems();
				txtNotasEFaltas.setText(null);
				lblNomeAluno.setText(null);
				lblCursoAluno.setText(null);
				cmbNota.setSelectedIndex(0);
				cmbSemestre.setSelectedIndex(0);
				txtFaltas.setText(null);
				try {
					txtConsultaDisciplina.setText(null);
					String rgm = JOptionPane.showInputDialog("Digite o rgm para consultar a nota");
					
					MatriculaDAO matriculaDao = new MatriculaDAO();
					Matricula matricula = new Matricula();
					List <EAvaliado> avaliadoLista = new ArrayList<EAvaliado>();
					
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					
					matricula = matriculaDao.consultar(rgm);
					avaliadoLista = avaliadoDao.consultar(rgm);
					
					txtRgmNotas.setText(matricula.getAluno().getRgm());
					lblNomeAluno.setText(matricula.getAluno().getNome());
					lblCursoAluno.setText(matricula.getCurso().getNomeCurso());
						
					for (Disciplina d: disciplinaDao.consultar(rgm)) {
						cmbDisciplina.addItem(d.getDescricao());
					}
					
					for(int i = 0; avaliadoLista.get(i) != null; i++) {
						txtConsultaDisciplina.append(avaliadoLista.get(i).getDisciplina().getDescricao()+"\n");
						txtNotasEFaltas.append("Nota: "+avaliadoLista.get(i).getNota()+"\tFaltas: "+avaliadoLista.get(i).getFaltas()+"\n");
					}
				}
				catch (IndexOutOfBoundsException e) {
					
				}
				catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Houve um erro ao consultar as notas do aluno");
				}
			}
		});
		mnNotasEFaltas.add(mntmConsultar_2);
		
		mntmExcluir_1 = new JMenuItem("Excluir");
		mntmExcluir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					AlunoDAO alunoDao = new AlunoDAO();
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					
					Aluno aluno = new Aluno();
					Disciplina disciplina = new Disciplina();
					
					int op = 1;
					op = JOptionPane.showConfirmDialog(null, "Deseja excluir a nota do aluno do RGM "
					+txtRgmNotas.getText()+" na disciplina "+cmbDisciplina.getSelectedItem()+"?");
					
					if (op == 0) {
						aluno = alunoDao.consultar(txtRgmNotas.getText());
						disciplina = disciplinaDao.consultarPorDescricao((String)cmbDisciplina.getSelectedItem());
					
						avaliadoDao.excluir(aluno, disciplina);
					
						JOptionPane.showMessageDialog(null, "Nota excluida com sucesso");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao excluir a nota do aluno");
				}
			}
		});
		mnNotasEFaltas.add(mntmExcluir_1);
		
		mnSobre = new JMenu("Ajuda");
		menuBar.add(mnSobre);
		
		mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Sistema para cadastro e gerenciamento de informações de alunos e matriculas\n"
						+ "Também é possível gerenciar e consultar as notas dos alunos");
			}
		});
		mnSobre.add(mntmSobre);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 483, 329);
		contentPane.add(tabbedPane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Dados Pessoais", null, panel_1, null);
		panel_1.setLayout(null);
		
		lblRgm_1 = new JLabel("RGM");
		lblRgm_1.setBounds(10, 31, 46, 14);
		panel_1.add(lblRgm_1);
		
		txtRgmDados = new JTextField();
		txtRgmDados.setColumns(10);
		txtRgmDados.setBounds(58, 28, 133, 20);
		panel_1.add(txtRgmDados);
		
		lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(202, 31, 46, 14);
		panel_1.add(lblNome_1);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(257, 28, 211, 20);
		panel_1.add(txtNome);
		
		lblData_1 = new JLabel("Data De Nascimento");
		lblData_1.setBounds(10, 82, 133, 14);
		panel_1.add(lblData_1);
		
		lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setBounds(268, 82, 26, 14);
		panel_1.add(lblCpf_1);
		
		lblEmail_1 = new JLabel("Email");
		lblEmail_1.setBounds(10, 140, 46, 14);
		panel_1.add(lblEmail_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(67, 137, 401, 20);
		panel_1.add(txtEmail);
		
		lblEnd_1 = new JLabel("End.");
		lblEnd_1.setBounds(10, 191, 34, 14);
		panel_1.add(lblEnd_1);
		
		txtEnd = new JTextField();
		txtEnd.setColumns(10);
		txtEnd.setBounds(67, 188, 401, 20);
		panel_1.add(txtEnd);
		
		lblNewLabel_1 = new JLabel("Munic\u00EDpio");
		lblNewLabel_1.setBounds(10, 255, 59, 14);
		panel_1.add(lblNewLabel_1);
		
		txtMunicipio = new JTextField();
		txtMunicipio.setColumns(10);
		txtMunicipio.setBounds(67, 252, 108, 20);
		panel_1.add(txtMunicipio);
		
		lblUf_1 = new JLabel("UF");
		lblUf_1.setBounds(195, 255, 18, 14);
		panel_1.add(lblUf_1);
		
		cmbUf = new JComboBox();
		cmbUf.setModel(new DefaultComboBoxModel(new String[] {"--", "AC", "AL", "AM", "AP", "BA", "CE", "DF", "ES", "GO", "MA", "MG", "MS", "MT", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"}));
		cmbUf.setBounds(223, 252, 52, 20);
		panel_1.add(cmbUf);
		
		lblCelular_1 = new JLabel("Celular");
		lblCelular_1.setBounds(289, 255, 52, 14);
		panel_1.add(lblCelular_1);
		try {
			ftfCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ftfCpf.setBounds(304, 79, 164, 20);
		panel_1.add(ftfCpf);
		try {
			ftfDataNascimento = new JFormattedTextField(new MaskFormatter("##/##/####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ftfDataNascimento.setBounds(140, 79, 95, 20);
		panel_1.add(ftfDataNascimento);
		try {
			ftfCelular = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ftfCelular.setBounds(335, 252, 133, 20);
		panel_1.add(ftfCelular);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Curso", null, panel_2, null);
		panel_2.setLayout(null);
		
		lblCurso = new JLabel("Curso");
		lblCurso.setBounds(26, 34, 46, 14);
		panel_2.add(lblCurso);
		
		cmbCurso = new JComboBox();
		cmbCurso.setModel(new DefaultComboBoxModel(new String[] {"Selecione"}));
		
		try {
			CursoDAO curso = new CursoDAO();
			
			for (Curso c: curso.consultarTodos()) {
				cmbCurso.addItem(c.getNomeCurso());
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro durante a operação ");
		}
		
		cmbCurso.setBounds(91, 31, 363, 20);
		panel_2.add(cmbCurso);
		
		lblNewLabel_2 = new JLabel("Campus");
		lblNewLabel_2.setBounds(26, 97, 62, 14);
		panel_2.add(lblNewLabel_2);
		
		cmbCampus = new JComboBox();
		cmbCampus.setModel(new DefaultComboBoxModel(new String[] {"Selecione", "Tatuap\u00E9", "Pinheiros"}));
		cmbCampus.setBounds(91, 94, 363, 20);
		panel_2.add(cmbCampus);
		
		lblPerdo = new JLabel("per\u00EDodo");
		lblPerdo.setBounds(26, 168, 46, 14);
		panel_2.add(lblPerdo);
		
		rdbManha = new JRadioButton("Manh\u00E3");
		btgPeriodo.add(rdbManha);
		rdbManha.setBounds(113, 164, 109, 23);
		panel_2.add(rdbManha);
		
		rdbVespertino = new JRadioButton("Vespertino");
		btgPeriodo.add(rdbVespertino);
		rdbVespertino.setBounds(220, 164, 109, 23);
		panel_2.add(rdbVespertino);
		
		rdbNoturno = new JRadioButton("Noturno");
		btgPeriodo.add(rdbNoturno);
		rdbNoturno.setBounds(333, 164, 109, 23);
		panel_2.add(rdbNoturno);
		
		btnSalvar = new JButton("");
		btnSalvar.setToolTipText("Salvar");
		btnSalvar.setBackground(Color.WHITE);
		ImageIcon iconeSalvar = new ImageIcon(getClass().getResource("/br/com/projeto/images/salvar.png"));
		iconeSalvar.setImage(iconeSalvar.getImage().getScaledInstance(50, 50, 50));
		btnSalvar.setIcon(iconeSalvar);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Aluno aluno = new Aluno();
				
				aluno.setRgm(txtRgmDados.getText());
				aluno.setNome(txtNome.getText());
				aluno.setMunicipio(txtMunicipio.getText());
				aluno.setEnd(txtEnd.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setDataNasc(ftfDataNascimento.getText());
				aluno.setCpf(ftfCpf.getText());
				aluno.setCelular(ftfCelular.getText());
				aluno.setUf((String)cmbUf.getSelectedItem());
				
				Matricula matricula = new Matricula();
				
				matricula.setAluno(aluno);
				
				try {
					CursoDAO cursoDao = new CursoDAO();
					Curso curso = new Curso();
					curso = cursoDao.consultar((String)cmbCurso.getSelectedItem());
					matricula.setCurso(curso);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao carregar os cursos");
				}
				matricula.setCampus((String)cmbCampus.getSelectedItem());
				
				String periodo = "";
				
				if (rdbManha.isSelected()) {
					periodo = "Manha";
				}
				if (rdbNoturno.isSelected()) {
					periodo = "Noturno";
				}
				if (rdbVespertino.isSelected()) {
					periodo = "Vespertino";
				}
				
				matricula.setPeriodo(periodo);
				
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					MatriculaDAO matriculaDao = new MatriculaDAO();
					
					if (alunoDao.salvar(aluno)){
						matriculaDao.salvar(matricula);
						JOptionPane.showMessageDialog(null, "Aluno matriculado com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "O rgm ja foi cadastrado");
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao matricular o aluno");
				}
			}});
		btnSalvar.setBounds(15, 223, 73, 54);
		panel_2.add(btnSalvar);
		
		btnNovo = new JButton("");
		btnNovo.setToolTipText("Novo");
		btnNovo.setBackground(Color.WHITE);
		
		ImageIcon iconeNovo = new ImageIcon(getClass().getResource("/br/com/projeto/images/novo.jpeg"));
		iconeNovo.setImage(iconeNovo.getImage().getScaledInstance(50, 50, 50));
		btnNovo.setIcon(iconeNovo);
		
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRgmDados.setEditable(true);
				txtRgmDados.setText(null);
				txtNome.setText(null);
				ftfDataNascimento.setText(null);
				ftfCpf.setText(null);
				txtEmail.setText(null);
				txtEnd.setText(null);
				txtMunicipio.setText(null);
				cmbUf.setSelectedIndex(0);
				ftfCelular.setText(null);
				cmbCurso.setSelectedIndex(0);
				cmbCampus.setSelectedIndex(0);
				btgPeriodo.clearSelection();
				
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNovo.setBounds(107, 223, 73, 54);
		panel_2.add(btnNovo);
		
		btnConsultar = new JButton("");
		btnConsultar.setToolTipText("Consultar");
		btnConsultar.setBackground(Color.WHITE);
		
		ImageIcon iconeConsultar = new ImageIcon(getClass().getResource("/br/com/projeto/images/consultar.jpg"));
		iconeConsultar.setImage(iconeConsultar.getImage().getScaledInstance(50, 50, 50));
		btnConsultar.setIcon(iconeConsultar);
		
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String rgm = JOptionPane.showInputDialog("Digite o RGM do aluno para consultar seus dados");
				txtRgmDados.setEditable(false);
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					Aluno aluno = new Aluno();
					aluno = alunoDao.consultar(rgm);
					
					if(aluno == null) {
						JOptionPane.showMessageDialog(null, "O RGM não existe");
					}
					else {
						txtRgmDados.setText(aluno.getRgm());
						txtNome.setText(aluno.getNome());
						txtEmail.setText(aluno.getEmail());
						txtMunicipio.setText(aluno.getMunicipio());
						txtEnd.setText(aluno.getEnd());
						ftfCelular.setText(aluno.getCelular());
						ftfCpf.setText(aluno.getCpf());
						ftfDataNascimento.setText(aluno.getDataNasc());
						cmbUf.setSelectedItem(aluno.getUf());
						
						MatriculaDAO matriculaDao = new MatriculaDAO();
						Matricula matricula = new Matricula();
						matricula = matriculaDao.consultar(rgm);
			
						cmbCurso.setSelectedItem(matricula.getCurso().getNomeCurso());
						cmbCampus.setSelectedItem(matricula.getCampus());
						
						if (matricula.getPeriodo().equals("Manha")) {
							rdbManha.setSelected(true);
						}
						if (matricula.getPeriodo().equals("Vespertino")) {
							rdbVespertino.setSelected(true);
						}
						if (matricula.getPeriodo().equals("Noturno")) {
							rdbNoturno.setSelected(true);
						}
						
						tabbedPane.setSelectedIndex(0);
					}
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao consultar os dados do aluno");
				}
			}
		});
		btnConsultar.setBounds(190, 223, 73, 54);
		panel_2.add(btnConsultar);
		
		btnAlterar = new JButton("");
		btnAlterar.setToolTipText("Alterar");
		btnAlterar.setBackground(Color.WHITE);
		
		ImageIcon iconeAlterar = new ImageIcon(getClass().getResource("/br/com/projeto/images/alterar.jpg"));
		iconeAlterar.setImage(iconeAlterar.getImage().getScaledInstance(50, 50, 50));
		btnAlterar.setIcon(iconeAlterar);
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRgmDados.setEditable(false);
				Aluno aluno = new Aluno();
				Matricula matricula = new Matricula();
				
				aluno.setRgm(txtRgmDados.getText());
				aluno.setNome(txtNome.getText());
				aluno.setMunicipio(txtMunicipio.getText());
				aluno.setEnd(txtEnd.getText());
				aluno.setEmail(txtEmail.getText());
				aluno.setDataNasc(ftfDataNascimento.getText());
				aluno.setCpf(ftfCpf.getText());
				aluno.setCelular(ftfCelular.getText());
				aluno.setUf((String)cmbUf.getSelectedItem());
				
				matricula.setAluno(aluno);
				
				try {
					CursoDAO cursoDao = new CursoDAO();
					Curso curso = new Curso();
					curso = cursoDao.consultar((String)cmbCurso.getSelectedItem());
					matricula.setCurso(curso);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao carregar os cursos");
				}
				matricula.setCampus((String)cmbCampus.getSelectedItem());
				
				String periodo = "";
				
				if (rdbManha.isSelected()) {
					periodo = "Manha";
				}
				if (rdbNoturno.isSelected()) {
					periodo = "Noturno";
				}
				if (rdbVespertino.isSelected()) {
					periodo = "Vespertino";
				}
				
				matricula.setPeriodo(periodo);
				
				int op = JOptionPane.showConfirmDialog(null, "Deseja alterar o aluno do RGM "+txtRgmDados.getText()+"?");
				try {
					if (op == 0) {
						AlunoDAO alunoDao = new AlunoDAO();
						MatriculaDAO matriculaDao = new MatriculaDAO();
						
						alunoDao.alterar(aluno);
						matriculaDao.alterar(matricula);
						
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
					}
					
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao alterar os dados do aluno");
				}
			}
		});
		btnAlterar.setBounds(289, 223, 73, 54);
		panel_2.add(btnAlterar);
		
		btnExcluir = new JButton("");
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBackground(Color.WHITE);
		
		ImageIcon iconeExcluir = new ImageIcon(getClass().getResource("/br/com/projeto/images/excluir.png"));
		iconeExcluir.setImage(iconeExcluir.getImage().getScaledInstance(50, 50, 50));
		btnExcluir.setIcon(iconeExcluir);
		
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					MatriculaDAO matriculaDao = new MatriculaDAO();
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					
					String rgm = JOptionPane.showInputDialog("Digite o RGM do aluno a excluir");
					
					if (alunoDao.excluir(rgm) != 0) {
						avaliadoDao.excluir(rgm);
						matriculaDao.excluir(rgm);
						JOptionPane.showMessageDialog(null, "Aluno excluido com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "O RGM digitado não consta nos nossos registros.");
					}
					
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o aluno");
				}
			}
		});
		btnExcluir.setBounds(381, 223, 73, 54);
		panel_2.add(btnExcluir);
		
		panel_3 = new JPanel();
		tabbedPane.addTab("Notas e Faltas", null, panel_3, null);
		panel_3.setLayout(null);
		
		lblRgm = new JLabel("RGM");
		lblRgm.setBounds(10, 14, 46, 14);
		panel_3.add(lblRgm);
		
		txtRgmNotas = new JTextField(8);
		txtRgmNotas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (txtRgmNotas.getText().length() == 8) {
					try {
						MatriculaDAO matriculaDao = new MatriculaDAO();
						Matricula matricula = new Matricula();
						
						DisciplinaDAO disciplinaDao = new DisciplinaDAO();
						
						matricula = matriculaDao.consultar(txtRgmNotas.getText());
						
						if (matricula == null) {
							JOptionPane.showMessageDialog(null, "O RGM não existe");
						}
						else {
							lblNomeAluno.setText(matricula.getAluno().getNome());
							lblCursoAluno.setText(matricula.getCurso().getNomeCurso());
								
							for (Disciplina d: disciplinaDao.consultar(txtRgmNotas.getText())) {
								cmbDisciplina.addItem(d.getDescricao());
							}
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Houve um erro ao consultar o RGM");
					}
				}
				else {
					lblNomeAluno.setText(null);	
					lblCursoAluno.setText(null);
					cmbDisciplina.removeAllItems();
				}
			}
		});
		txtRgmNotas.setBounds(44, 11, 180, 20);
		panel_3.add(txtRgmNotas);
		
		lblNomeAluno = new JLabel("");
		lblNomeAluno.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.BLACK, Color.LIGHT_GRAY));
		lblNomeAluno.setBounds(234, 11, 234, 24);
		panel_3.add(lblNomeAluno);
		
		lblCursoAluno = new JLabel("");
		lblCursoAluno.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, Color.BLACK, Color.LIGHT_GRAY));
		lblCursoAluno.setBounds(10, 48, 458, 24);
		panel_3.add(lblCursoAluno);
		
		cmbDisciplina = new JComboBox();
		cmbDisciplina.setBounds(72, 88, 396, 20);
		panel_3.add(cmbDisciplina);
		
		lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 91, 60, 14);
		panel_3.add(lblDisciplina);
		
		lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(10, 137, 60, 14);
		panel_3.add(lblSemestre);
		
		cmbSemestre = new JComboBox();
		cmbSemestre.setModel(new DefaultComboBoxModel(new String[] {"2020-1", "2020-2", "2021-1", "2021-2"}));
		cmbSemestre.setBounds(72, 134, 87, 20);
		panel_3.add(cmbSemestre);
		
		cmbNota = new JComboBox();
		cmbNota.setModel(new DefaultComboBoxModel(new String[] {"0,0", "0,5", "1,0", "1,5", "2,0", "2,5", "3,0", "3,5", "4,0", "4,5", "5,0", "5,5", "6,0", "6,5", "7,0", "7,5", "8,0", "8,5", "9,0", "9,5", "10,0"}));
		cmbNota.setBounds(218, 134, 60, 20);
		panel_3.add(cmbNota);
		
		lblNota = new JLabel("Nota");
		lblNota.setBounds(178, 137, 46, 14);
		panel_3.add(lblNota);
		
		lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(314, 137, 39, 14);
		panel_3.add(lblFaltas);
		
		txtFaltas = new JTextField();
		txtFaltas.setColumns(10);
		txtFaltas.setBounds(364, 134, 90, 20);
		panel_3.add(txtFaltas);
		
		btnSalvarNotas = new JButton("");
		btnSalvarNotas.setToolTipText("Salvar");
		btnSalvarNotas.setBackground(Color.WHITE);
		btnSalvarNotas.setIcon(iconeSalvar);
		
		btnSalvarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AlunoDAO alunoDao = new AlunoDAO();
					CursoDAO cursoDao = new CursoDAO();
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					
					Aluno aluno = new Aluno();
					Curso curso = new Curso();
					EAvaliado avaliado = new EAvaliado();
					Disciplina disciplina = new Disciplina();
					
					disciplina = disciplinaDao.consultarPorDescricao((String)cmbDisciplina.getSelectedItem());
					
					aluno = alunoDao.consultar(txtRgmNotas.getText());
					
					curso = cursoDao.consultar((String)cmbCurso.getSelectedItem());
					
					avaliado.setSemestre((String)cmbSemestre.getSelectedItem());
					avaliado.setNota((String)cmbNota.getSelectedItem());
					avaliado.setFaltas(txtFaltas.getText());
					avaliado.setAluno(aluno);
					avaliado.setDisciplina(disciplina);
					
					avaliadoDao.salvar(avaliado);
					
					JOptionPane.showMessageDialog(null, "Nota cadastrada");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao salvar a nota do aluno");
				}
			}
		});
		btnSalvarNotas.setBounds(20, 165, 73, 54);
		panel_3.add(btnSalvarNotas);
		
		btnNovoNotas = new JButton("");
		btnNovoNotas.setToolTipText("Novo");
		btnNovoNotas.setBackground(Color.WHITE);
		
		btnNovoNotas.setIcon(iconeNovo);
		
		btnNovoNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRgmNotas.setEditable(true);
				txtRgmNotas.setText(null);
				lblNomeAluno.setText(null);
				lblCursoAluno.setText(null);
				cmbDisciplina.removeAllItems();
				cmbSemestre.setSelectedIndex(0);
				cmbNota.setSelectedIndex(0);
				txtFaltas.setText(null);
				txtConsultaDisciplina.setText(null);
				txtNotasEFaltas.setText(null);
			}
		});
		btnNovoNotas.setBounds(107, 165, 73, 54);
		panel_3.add(btnNovoNotas);
		
		btnConsultarNotas = new JButton("");
		btnConsultarNotas.setToolTipText("Consultar");
		btnConsultarNotas.setBackground(Color.WHITE);
		
		btnConsultarNotas.setIcon(iconeConsultar);
		
		btnConsultarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtRgmNotas.setText(null);
				txtRgmNotas.setEditable(false);
				cmbDisciplina.removeAllItems();
				txtNotasEFaltas.setText(null);
				lblNomeAluno.setText(null);
				lblCursoAluno.setText(null);
				cmbNota.setSelectedIndex(0);
				cmbSemestre.setSelectedIndex(0);
				txtFaltas.setText(null);
				try {
					txtConsultaDisciplina.setText(null);
					String rgm = JOptionPane.showInputDialog("Digite o rgm para consultar a nota");
					
					MatriculaDAO matriculaDao = new MatriculaDAO();
					Matricula matricula = new Matricula();
					List <EAvaliado> avaliadoLista = new ArrayList<EAvaliado>();
					
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					
					matricula = matriculaDao.consultar(rgm);
					avaliadoLista = avaliadoDao.consultar(rgm);
					
					txtRgmNotas.setText(matricula.getAluno().getRgm());
					lblNomeAluno.setText(matricula.getAluno().getNome());
					lblCursoAluno.setText(matricula.getCurso().getNomeCurso());
						
					for (Disciplina d: disciplinaDao.consultar(rgm)) {
						cmbDisciplina.addItem(d.getDescricao());
					}
					
					for(int i = 0; avaliadoLista.get(i) != null; i++) {
						txtConsultaDisciplina.append(avaliadoLista.get(i).getDisciplina().getDescricao()+"\n");
						txtNotasEFaltas.append("Nota: "+avaliadoLista.get(i).getNota()+"\tFaltas: "+avaliadoLista.get(i).getFaltas()+"\n");
					}
				}
				catch (IndexOutOfBoundsException e) {
					
				}
				catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Houve um erro ao consultar as notas do aluno");
				}
			}
		});
		btnConsultarNotas.setBounds(205, 165, 73, 54);
		panel_3.add(btnConsultarNotas);
		
		btnAlterarNotas = new JButton("");
		btnAlterarNotas.setToolTipText("Alterar");
		btnAlterarNotas.setBackground(Color.WHITE);
		btnAlterarNotas.setIcon(iconeAlterar);
		btnAlterarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					AlunoDAO alunoDao = new AlunoDAO();
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					
					EAvaliado avaliado = new EAvaliado();
					Aluno aluno = new Aluno();
					Disciplina disciplina = new Disciplina();
					
					if (txtRgmNotas.getText().length() != 0) {
						aluno = alunoDao.consultar(txtRgmNotas.getText());
						disciplina = disciplinaDao.consultarPorDescricao((String)cmbDisciplina.getSelectedItem());
						
						avaliado.setSemestre((String)cmbSemestre.getSelectedItem());
						avaliado.setNota((String)cmbNota.getSelectedItem());
						avaliado.setFaltas(txtFaltas.getText());
						avaliado.setAluno(aluno);
						avaliado.setDisciplina(disciplina);
						
						avaliadoDao.alterar(avaliado);
						
						JOptionPane.showMessageDialog(null, "Nota alterada com sucesso");
					}
					else {
						JOptionPane.showMessageDialog(null, "Digite o RGM do aluno");
					}
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao alterar a nota do aluno");
				}
			}
		});
		btnAlterarNotas.setBounds(297, 165, 73, 54);
		panel_3.add(btnAlterarNotas);
		
		btnExcluirNotas = new JButton("");
		btnExcluirNotas.setToolTipText("Excluir");
		btnExcluirNotas.setBackground(Color.WHITE);
		btnExcluirNotas.setIcon(iconeExcluir);
		btnExcluirNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					AlunoDAO alunoDao = new AlunoDAO();
					DisciplinaDAO disciplinaDao = new DisciplinaDAO();
					
					Aluno aluno = new Aluno();
					Disciplina disciplina = new Disciplina();
					
					int op = 1;
					op = JOptionPane.showConfirmDialog(null, "Deseja excluir a nota do aluno do RGM "
					+txtRgmNotas.getText()+" na disciplina "+cmbDisciplina.getSelectedItem()+"?");
					
					if (op == 0) {
						aluno = alunoDao.consultar(txtRgmNotas.getText());
						disciplina = disciplinaDao.consultarPorDescricao((String)cmbDisciplina.getSelectedItem());
					
						avaliadoDao.excluir(aluno, disciplina);
					
						JOptionPane.showMessageDialog(null, "Nota excluida com sucesso");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao excluir a nota do aluno");
				}
			}
		});
		btnExcluirNotas.setBounds(395, 165, 73, 54);
		panel_3.add(btnExcluirNotas);
		
		txtConsultaDisciplina = new JTextArea();
		txtConsultaDisciplina.setBounds(10, 229, 252, 61);
		panel_3.add(txtConsultaDisciplina);
		
		panel = new JPanel();
		tabbedPane.addTab("Boletim", null, panel, null);
		panel.setLayout(null);
		
		txtRgmBoletim = new JTextField();
		txtRgmBoletim.setBounds(63, 73, 133, 20);
		panel.add(txtRgmBoletim);
		txtRgmBoletim.setColumns(10);
		
		lblRgm_2 = new JLabel("RGM:");
		lblRgm_2.setBounds(21, 76, 46, 14);
		panel.add(lblRgm_2);
		
		lblConsulteOSeu = new JLabel("Consulte o seu boletim");
		lblConsulteOSeu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConsulteOSeu.setBounds(139, 30, 192, 14);
		panel.add(lblConsulteOSeu);
		
		
		btnConsultarBoletim = new JButton("Consultar");
		btnConsultarBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MatriculaDAO matriculaDao = new MatriculaDAO();
					EAvaliadoDAO avaliadoDao = new EAvaliadoDAO();
					
					List <EAvaliado> avaliado = new ArrayList<EAvaliado>();
					Matricula matricula = new Matricula();
					
					avaliado = avaliadoDao.consultar(txtRgmBoletim.getText());
					matricula = matriculaDao.consultar(txtRgmBoletim.getText());
					
					txtBoletim.append("Nome: "+matricula.getAluno().getNome()+"\nCurso: "+matricula.getCurso().getNomeCurso()+"\n"
							+"Campus: "+matricula.getCampus()+"\tperiodo: "+matricula.getPeriodo()+"\nSemestre vigente: "+avaliado.get(0).getSemestre()+"\n\n"
							+ "Disciplinas: \n"
							+ avaliado.get(0).getDisciplina().getDescricao()+"\tNota: "+avaliado.get(0).getNota()+"\tFaltas: "+avaliado.get(0).getFaltas()+"\n"
							+ avaliado.get(1).getDisciplina().getDescricao()+"\tNota: "+avaliado.get(1).getNota()+"\tFaltas: "+avaliado.get(1).getFaltas()+"\n"
							+ avaliado.get(2).getDisciplina().getDescricao()+"\tNota: "+avaliado.get(2).getNota()+"\tFaltas: "+avaliado.get(2).getFaltas()+"\n");
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Houve um erro ao consultar o boletim do aluno");
				}
			}
		});
		btnConsultarBoletim.setBounds(227, 72, 104, 23);
		panel.add(btnConsultarBoletim);
		
		btnLimparBoletim = new JButton("Limpar tudo");
		btnLimparBoletim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtRgmBoletim.setText(null);
				txtBoletim.setText(null);
			}
		});
		btnLimparBoletim.setBounds(341, 72, 104, 23);
		panel.add(btnLimparBoletim);
		
		txtBoletim = new JTextArea();
		txtBoletim.setBounds(10, 126, 458, 153);
		panel.add(txtBoletim);
		txtBoletim.setEditable(false);
		
		
		txtConsultaDisciplina.setEditable(false);

		txtNotasEFaltas = new JTextArea();
		txtNotasEFaltas.setBounds(261, 229, 207, 61);
		txtNotasEFaltas.setEditable(false);
		panel_3.add(txtNotasEFaltas);
	}
}
