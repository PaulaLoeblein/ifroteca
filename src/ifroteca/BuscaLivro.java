package ifroteca;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class BuscaLivro extends JFrame {
	private JLabel lblTitulo = new JLabel("Titulo");
	private JTextField txtTitulo = new JTextField();
	private JButton btnBuscar = new JButton("Buscar");
	private JPanel painel1 = new JPanel();

	private JTable tabela;

	public BuscaLivro() {
		initComponents();
	}

	private void initComponents() {
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		
		setBounds(80, 05, 0, 0);
		this.setPreferredSize(new Dimension(1220, 720));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.buscar();
		this.setTitle("Buscar Cadastro de Livro");
		this.getContentPane().setLayout(null);
	}

	public void getTabela() {
		Statement sentenca;
		ResultSet rs;
		try {
			SingletonConexao singleton = SingletonConexao.getInstance();
			Connection con = singleton.getConexao();
			String query = "select * from bibliotecario";
			sentenca = con.createStatement();
			rs = sentenca.executeQuery(query);
			exibeResultSet(rs);
			sentenca.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	private void exibeResultSet(ResultSet rs) throws SQLException {
		boolean maisRegistros = rs.next();
		if (!maisRegistros) {
			JOptionPane.showMessageDialog(this, "Não ha registros!");
			return;
		}
		Vector cabecalhos = new Vector();
		Vector linhas = new Vector();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			cabecalhos.addElement("Código");
			cabecalhos.addElement("Titulo");
			cabecalhos.addElement("Autor");
			cabecalhos.addElement("Editora");
			cabecalhos.addElement("Quantidade");
			cabecalhos.addElement("Data de Cadastro");
			cabecalhos.addElement("Idioma");
			cabecalhos.addElement("Cód. Livro");
			cabecalhos.addElement("Gênero");
			cabecalhos.addElement("Área de Ensino");
			
			do {
				linhas.addElement(getProximaLinha(rs, rsmd));
			} while (rs.next());
			tabela = new JTable(linhas, cabecalhos);
			JScrollPane scroller = new JScrollPane(tabela);
			getContentPane().add(scroller);
			scroller.setBounds(160, 200, 900, 350);

			getContentPane().add(lblTitulo);
			pack();
			lblTitulo.setBounds(360, 140, 120, 24);
			getContentPane().add(txtTitulo);
			txtTitulo.setBounds(360, 165, 500, 24);
			getContentPane().add(btnBuscar);
			btnBuscar.setBounds(570, 585, 80, 24);

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

	private Vector getProximaLinha(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
		Vector linhaCorrente = new Vector();
		for (int i = 1; i <= rsmd.getColumnCount(); ++i)
			switch (rsmd.getColumnType(i)) {
			case Types.VARCHAR:
				linhaCorrente.addElement(rs.getString(i));
				break;
			case Types.INTEGER:
				linhaCorrente.addElement(new Long(rs.getLong(i)));
				break;
			case Types.NUMERIC:
				linhaCorrente.addElement(new Double(rs.getDouble(i)));
				break;
			default:
				linhaCorrente.addElement(rs.getString(i));
				break;
			}
		return linhaCorrente;
	}

	public void buscar() {
		try {
			SingletonConexao singleton = SingletonConexao.getInstance();
			Connection con = singleton.getConexao();
			Statement stm;
			stm = con.createStatement();
			String sql = "select cod_liv, titulo_liv, autor_liv, editora_liv, quantidade_liv, dataCadastro_liv, idioma_liv, codigoLivro_liv, genero_liv, areaEnsino_liv from livro where titulo_liv like '%"
+ txtTitulo.getText() + "%'";
			ResultSet rs = stm.executeQuery(sql);
			exibeResultSet(rs);
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BuscaLivro bl = new BuscaLivro();
		bl.setVisible(true);
		
	}

}
