package ifroteca;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CadastrarLivro extends JFrame {

	private JLabel lblTitulo = new JLabel("Titulo"), lblAutor = new JLabel("Autor"), lblEditora = new JLabel("Editora"),
			lblQuantidade = new JLabel("Quantidade"), lblDataCadastro = new JLabel("Data de Cadastro"),
			lblIdioma = new JLabel("Idioma"), lblCodigoLivro = new JLabel("Código do Livro"),
			lblGenero = new JLabel("Gênero"), lblAreaEnsino = new JLabel("Área de Ensino"), lblAno = new JLabel("Ano");

	private JTextField txtTitulo = new JTextField(), txtAutor = new JTextField(), txtEditora = new JTextField(),
			txtQuantidade = new JTextField(), txtIdioma = new JTextField(), txtCodigoLivro = new JTextField(),
			txtGenero = new JTextField(), txtAreaEnsino = new JTextField();

	private JFormattedTextField txtDataCadastro = new JFormattedTextField(),
								txtAno = new JFormattedTextField();

	private ArrayList<JTextField> textFields = new ArrayList();

	private JButton btnSalvar = new JButton("Salvar"), btnLimpar = new JButton("Limpar"),
			btnExibir = new JButton("Exibir");

	public CadastrarLivro() {
		super();
		iniComponents();
	}

	public CadastrarLivro(Livro l) {

		super();
		iniComponents();
		this.setFields(l);
	}

	private void setFields(Livro l) {
		txtTitulo.setText(l.getTitulo());
		txtAutor.setText(l.getAutor());
		txtEditora.setText(l.getEditora());
		txtAno.setText(l.getAno());
		txtQuantidade.setText(l.getQuantidade());
		txtDataCadastro.setText(l.getDataCadastro());
		txtIdioma.setText(l.getIdioma());
		txtCodigoLivro.setText(l.getCodigoLivro());
		txtGenero.setText(l.getGenero());
		txtAreaEnsino.setText(l.getAreaEnsino());

	}
	
	private void iniComponents() {	
		
		try{
            txtDataCadastro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        }catch (Exception e){
            e.printStackTrace();
        }
		
		try{
			txtAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        }catch (Exception e){
            e.printStackTrace();
        }
		
			
            
		iniArray();

		setTitle("Cadastrar Livro");
		getContentPane().setLayout(null);

		this.setResizable(false);

		getContentPane().add(lblTitulo);
		lblTitulo.setBounds(360, 120, 120, 24);
		getContentPane().add(txtTitulo);
		txtTitulo.setBounds(360, 145, 500, 24);

		getContentPane().add(lblAutor);
		lblAutor.setBounds(360, 180, 120, 24);
		getContentPane().add(txtAutor);
		txtAutor.setBounds(360, 205, 500, 24);

		getContentPane().add(lblQuantidade);
		lblQuantidade.setBounds(760, 240, 100, 24);
		getContentPane().add(txtQuantidade);
		txtQuantidade.setBounds(760, 265, 100, 24);

		getContentPane().add(lblEditora);
		lblEditora.setBounds(360, 240, 120, 24);
		getContentPane().add(txtEditora);
		txtEditora.setBounds(360, 265, 270, 24);
		
		getContentPane().add(lblAno);
		lblAno.setBounds(660, 240, 120, 24);
		getContentPane().add(txtAno);
		txtAno.setBounds(660, 265, 70, 24);

		getContentPane().add(lblDataCadastro);
		lblDataCadastro.setBounds(360, 300, 120, 24);
		getContentPane().add(txtDataCadastro);
		txtDataCadastro.setBounds(360, 325, 270, 24);

		getContentPane().add(lblIdioma);
		lblIdioma.setBounds(660, 300, 120, 24);
		getContentPane().add(txtIdioma);
		txtIdioma.setBounds(660, 325, 200, 24);

		getContentPane().add(lblCodigoLivro);
		lblCodigoLivro.setBounds(360, 360, 120, 24);
		getContentPane().add(txtCodigoLivro);
		txtCodigoLivro.setBounds(360, 385, 500, 24);

		getContentPane().add(lblGenero);
		lblGenero.setBounds(360, 420, 120, 24);
		getContentPane().add(txtGenero);
		txtGenero.setBounds(360, 445, 190, 24);

		getContentPane().add(lblAreaEnsino);
		lblAreaEnsino.setBounds(580, 420, 120, 24);
		getContentPane().add(txtAreaEnsino);
		txtAreaEnsino.setBounds(580, 445, 280, 24);

		getContentPane().add(btnSalvar);
		btnSalvar.setBounds(480, 510, 80, 24);
		getContentPane().add(btnExibir);
		btnExibir.setBounds(570, 510, 80, 24);
		getContentPane().add(btnLimpar);
		btnLimpar.setBounds(660, 510, 80, 24);

		btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});

		btnExibir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exibir();
			}
		});

		this.setPreferredSize(new Dimension(1220, 800));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();

	}

	public void salvar() {
		try {
			SingletonConexao singleton = SingletonConexao.getInstance();
			Connection con = singleton.getConexao();
			PreparedStatement pstm = con.prepareStatement("insert into livro (titulo_liv, autor_liv, editora_liv, anoPubli_liv, quantidade_liv, dataCadastro_liv, idioma_liv, codigoLivro_liv, genero_liv, areaEnsino_liv) values (?,?,?,?,?,?,?,?,?,?)");
			
			pstm.setString(1, txtTitulo.getText());
			pstm.setString(2, txtAutor.getText());
			pstm.setString(3, txtEditora.getText());
			
			pstm.setString(4, txtEditora.getText());
			pstm.setString(5, String.valueOf(txtQuantidade.getText()));

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataCadastro = sdf.parse(txtDataCadastro.getText());
			java.sql.Date sqlDate = new java.sql.Date(dataCadastro.getTime());

			pstm.setDate(6, sqlDate);
			pstm.setString(7, txtIdioma.getText());
			pstm.setString(8, txtCodigoLivro.getText());
			pstm.setString(9, txtGenero.getText());
			pstm.setString(10, txtAreaEnsino.getText());

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
			String sql = "select * from livro";
			Statement stm = con.createStatement();
    		ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				System.out.println("Titulo: " + rs.getString("titulo_liv"));
				System.out.println("Autor: " + rs.getString("autor_liv"));
				System.out.println("Editora: " + rs.getString("editora_liv"));
				System.out.println("Ano de Publicação: " + rs.getString("anoPubli_liv"));
				System.out.println("Quantidade: " + rs.getString("quantidade_liv"));
				System.out.println("Data de Cadastro: " + rs.getString("dataCadastro_liv"));
				System.out.println("Idioma: " + rs.getString("idioma_liv"));
				System.out.println("CodigoLivro: " + rs.getString("codigoLivro_liv"));
				System.out.println("Genero: " + rs.getString("genero_liv"));
				System.out.println("AreaEnsino: " + rs.getString("areaEnsino_liv"));

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

		Livro l = new Livro();
		l.setTitulo("");
		l.setAutor("");
		l.setEditora("");
		l.setAno("");
		l.setQuantidade(String.valueOf(0));
		l.setDataCadastro("");
		l.setIdioma("");
		l.setCodigoLivro("");
		l.setGenero("");
		l.setAreaEnsino("");

		CadastrarLivro cl = new CadastrarLivro();
		cl.setVisible(true);

	}

	private void iniArray() {
		textFields.add(txtTitulo);
		textFields.add(txtAutor);
		textFields.add(txtEditora);
		textFields.add(txtAno);
		textFields.add(txtQuantidade);
		textFields.add(txtDataCadastro);
		textFields.add(txtIdioma);
		textFields.add(txtCodigoLivro);
		textFields.add(txtGenero);
		textFields.add(txtAreaEnsino);

	}

}
