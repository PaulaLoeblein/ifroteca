package ifroteca;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Emprestimo extends JFrame {

	private JLabel lblDataEmp = new JLabel("Data do Empréstimo"),
			lblDataDev = new JLabel("Data Prevista para Devolução"), lblAluno = new JLabel("CPF do Aluno"),
			lblBibliotecario = new JLabel("CPF do Bibliotecário"), lblCodLivro = new JLabel("Código do Livro");
	
	private JTextField txtCodLivro = new JTextField();
	
	private JFormattedTextField txtDataEmp = new JFormattedTextField(), txtDataDev = new JFormattedTextField(),
			txtAluno = new JFormattedTextField(), txtBibliotecario = new JFormattedTextField();

	private ArrayList<JTextField> textFields = new ArrayList();

	private JButton btnSalvar = new JButton("Salvar"), btnLimpar = new JButton("Limpar"), btnExibir = new JButton("Exibir");

	public Emprestimo() {
		super();
		iniComponents();
	}

	public boolean validaCampos() {
		if (txtAluno.getText().trim().length() == 0 || txtBibliotecario.getText().trim().length() == 0
				|| txtDataEmp.getText().trim().length() == 0 || txtDataDev.getText().trim().length() == 0
				|| txtCodLivro.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido!");
			return false;
		}
		return true;
	}

	public Emprestimo(Livro l, Aluno a, EmprestimoLivro rl, Pessoa b) {

		super();
		iniComponents();
		this.setFields(l, a, rl, b);
	}

	private void setFields(Livro l, Aluno a, EmprestimoLivro el, Pessoa b) {
		txtCodLivro.setText(l.getCodigoLivro());
		txtDataEmp.setText(el.getDataEmp());
		txtDataDev.setText(el.getDataDev());
		txtAluno.setText(a.getCpf());
		txtBibliotecario.setText(b.getCpf());
	}

	private void iniComponents() {
		try {
			txtAluno.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			txtBibliotecario.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			txtDataEmp.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			txtDataDev.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		iniArray();

		setTitle("Empréstimo");
		getContentPane().setLayout(null);

		this.setResizable(false);

		getContentPane().add(lblCodLivro);
		lblCodLivro.setBounds(360, 190, 120, 24);
		getContentPane().add(txtCodLivro);
		txtCodLivro.setBounds(360, 215, 500, 24);

		getContentPane().add(lblDataEmp);
		lblDataEmp.setBounds(360, 250, 120, 24);
		getContentPane().add(txtDataEmp);
		txtDataEmp.setBounds(360, 275, 235, 24);

		getContentPane().add(lblDataDev);
		lblDataDev.setBounds(625, 250, 200, 24);
		getContentPane().add(txtDataDev);
		txtDataDev.setBounds(625, 275, 235, 24);

		getContentPane().add(lblAluno);
		lblAluno.setBounds(360, 310, 120, 24);
		getContentPane().add(txtAluno);
		txtAluno.setBounds(360, 335, 235, 24);

		getContentPane().add(lblBibliotecario);
		lblBibliotecario.setBounds(625, 310, 200, 24);
		getContentPane().add(txtBibliotecario);
		txtBibliotecario.setBounds(625, 335, 235, 24);

		getContentPane().add(btnSalvar);
		btnSalvar.setBounds(480, 400, 80, 24);
		getContentPane().add(btnExibir);
		btnExibir.setBounds(570, 400, 80, 24);
		getContentPane().add(btnLimpar);
		btnLimpar.setBounds(660, 400, 80, 24);

		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validaCampos()) {
					salvar();
				}
			}
		});

		btnExibir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exibir();
			}
		});
		setBounds(80, 05, 0, 0);
		this.setPreferredSize(new Dimension(1220, 720));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

	}

	
	public void salvar() {
		try {
			SingletonConexao singleton = SingletonConexao.getInstance();
			Connection con = singleton.getConexao();

			PreparedStatement pstm = con.prepareStatement(
			"insert into reserva (codigoLivro_liv, dataEmprestimo_emp, dataPrevistaDev_emp, cpf_alu, cpf_bib) values (?,?,?,?,?) "
			+ "SELECT livro.codigoLivro_liv, emprestimo.dataEmprestimo_emp, emprestimo.dataPrevistaDev_emp, aluno.cpf_alu, "
					+ "bibliotecario.cpf_bib from emprestimo where emprestimo.cod_emp = aluno.cod_alu and emprestimo.cod_emp = bibliotecario.cod_bib and emprestimo.cod_emp = "
			+ "livro.cod_liv");
			
			
			
			pstm.setString(1, txtCodLivro.getText());
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataReserva = sdf.parse(txtDataEmp.getText());
			java.sql.Date sqlDate = new java.sql.Date(dataReserva.getTime());

			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			Date dataPrevistaEmp = sdf2.parse(txtDataEmp.getText());
			java.sql.Date sqlDate2 = new java.sql.Date(dataPrevistaEmp.getTime());
			
			pstm.setDate(1, sqlDate);
			pstm.setDate(2, sqlDate2);
			pstm.setString(3, txtAluno.getText());
			pstm.setString(1, txtBibliotecario.getText());
			
			pstm.execute();

			JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
			limpar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exibir() {
		try {
			SingletonConexao singleton = SingletonConexao.getInstance();
			Connection con = singleton.getConexao();
			Statement stm;
			stm = con.createStatement();
			String sql = "select livro.codigoLivro_liv, emprestimo.dataEmprestimo_emp, emprestimo.dataPrevistaDev_emp, aluno.cpf_alu, "
					+ "bibliotecario.cpf_bib from emprestimo left join bibliotecario on\n"
							+ "emprestimo.cod_emp = bibliotecario.cod_bib left join livro on\n"
							+ "emprestimo.cod_emp = livro.cod_liv left join aluno on\n"
							+ "emprestimo.cod_emp = aluno.cod_alu";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Código do Livro: " + rs.getString("codigoLivro_liv"));
				System.out.println("Data de Empréstimo: " + rs.getString("dataReserva"));
				System.out.println("Data Prevista para Devolução: " + rs.getString("dataPrevistaEmp"));
				System.out.println("Código do Aluno: " + rs.getString("cpf_alu"));
				System.out.println("Código do Bibliotecário: " + rs.getString("cpf_bib"));

			}
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void limpar() {
		for (JTextField j : this.textFields) {
			j.setText("");
		}
	}

	public static void main(String[] args) {

		Aluno a = new Aluno();
		Livro l = new Livro();
		EmprestimoLivro r = new EmprestimoLivro();
		Pessoa b = new Pessoa();

		l.setCodigoLivro("");
		r.setDataEmp("");
		r.setDataDev("");
		a.setCpf("");
		b.setCpf("");
		

		Emprestimo r2 = new Emprestimo();
		r2.setVisible(true);

	}

	private void iniArray() {
		textFields.add(txtCodLivro);
		textFields.add(txtDataEmp);
		textFields.add(txtDataDev);
		textFields.add(txtAluno);
		textFields.add(txtBibliotecario);
	}

}
