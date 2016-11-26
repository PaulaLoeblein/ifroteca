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


public class BuscaBibliotecario extends JFrame {
	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField();
	private JButton btnBuscar = new JButton("Buscar");
	private JPanel painel1 = new JPanel();

	private JTable tabela;

	public BuscaBibliotecario() {
		initComponents();
	}

	private void initComponents() {
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
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
			JOptionPane.showMessageDialog(this, "N�o ha registros!");
			return;
		}
		Vector cabecalhos = new Vector();
		Vector linhas = new Vector();
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			cabecalhos.addElement("C�digo");
			cabecalhos.addElement("Nome");
			cabecalhos.addElement("CPF");
			cabecalhos.addElement("RG");
			cabecalhos.addElement("Email");
			cabecalhos.addElement("Telefone");
			cabecalhos.addElement("Data de Nasc.");
			cabecalhos.addElement("Endere�o");
			cabecalhos.addElement("Cidade");
			
			do {
				linhas.addElement(getProximaLinha(rs, rsmd));
			} while (rs.next());
			tabela = new JTable(linhas, cabecalhos);
			JScrollPane scroller = new JScrollPane(tabela);
			getContentPane().add(scroller);
			scroller.setBounds(160, 200, 900, 350);

			getContentPane().add(lblNome);
			pack();
			lblNome.setBounds(360, 140, 120, 24);
			getContentPane().add(txtNome);
			txtNome.setBounds(360, 165, 500, 24);
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
			String sql = "select nome_bib, cpf_bib, rg_bib, email_bib, fone_bib, dataNasc_bib, endereco_bib, cidade_bib from bibliotecario where nome_bib like '%"
+ txtNome.getText() + "%'";
			ResultSet rs = stm.executeQuery(sql);
			exibeResultSet(rs);
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BuscaBibliotecario bb = new BuscaBibliotecario();
		bb.setVisible(true);
		bb.setBounds(80, 05, 0, 0);
		bb.setPreferredSize(new Dimension(1220, 720));
		bb.setResizable(false);
		bb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bb.pack();
		bb.buscar();
		bb.setTitle("Buscar Cadastro de Bibliotecario");
		bb.getContentPane().setLayout(null);
	}

}