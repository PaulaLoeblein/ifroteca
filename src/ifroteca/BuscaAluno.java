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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BuscaAluno extends JFrame {
	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField();
	private JButton btnBuscar = new JButton("Buscar");

	private JTable tabela;

	public void getTabela() {
		Statement sentenca;
		ResultSet rs;
		try {
			SingletonConexao singleton = SingletonConexao.getInstance();
			Connection con = singleton.getConexao();
			String query = "select * from aluno";
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
			// for (int i = 1; i<=rsmd.getColumnCount();i++)
			cabecalhos.addElement("Código");
			cabecalhos.addElement("Nome");
			cabecalhos.addElement("CPF");
			cabecalhos.addElement("RG");
			cabecalhos.addElement("Email");
			cabecalhos.addElement("Telefone");
			cabecalhos.addElement("Data de Nasc.");
			cabecalhos.addElement("Endereço");
			cabecalhos.addElement("Cidade");
			cabecalhos.addElement("Turma");
			cabecalhos.addElement("Curso");
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

			btnBuscar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					buscar();
				}
			});

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
			String sql = "select cod_alu, nome_alu, cpf_alu, rg_alu, email_alu, fone_alu, dataNasc_alu, endereco_alu, cidade_alu, turma_alu, curso_alu from aluno where nome_alu like '%"
					+ txtNome.getText() + "%'";
			ResultSet rs = stm.executeQuery(sql);
			exibeResultSet(rs);
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BuscaAluno ba = new BuscaAluno();
		ba.setVisible(true);
		ba.setBounds(80, 05, 0, 0);
		ba.setPreferredSize(new Dimension(1220, 720));
		ba.setResizable(false);
		ba.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ba.pack();
		ba.buscar();
		ba.setTitle("Buscar Cadastro de Aluno");
		ba.getContentPane().setLayout(null);
	}

}
