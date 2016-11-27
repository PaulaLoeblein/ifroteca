package ifroteca;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuIfroteca extends JFrame implements ActionListener{
	
	private JMenuBar barraMenu = new JMenuBar();
	private JMenu menuCadastros = new JMenu("Cadastros"),
				  menuBusca = new JMenu("Busca"),
				  menuLivro = new JMenu("Pesquisa"),
			      menuAjuda = new JMenu ("Ajuda");
	private JMenuItem menuCadastrarAluno = new JMenuItem("Cadastrar Aluno"),
					  menuCadastrarBibliotecario = new JMenuItem("Cadastrar Bibliotecário"),
				      menuCadastrarLivro = new JMenuItem("Cadastrar Livro"),
				      menuBuscarAluno = new JMenuItem("Buscar Aluno"),
					  menuBuscarBibliotecario = new JMenuItem("Buscar Bibliotecário"),
					  menuBuscarLivro = new JMenuItem("Buscar Livro"), 
					  menuBuscarPendencia = new JMenuItem("Buscar Pendências"), 
					  menuReserva = new JMenuItem("Reserva"), 
					  menuDevolucao = new JMenuItem("Devolução"),
					  menuEmprestimo = new JMenuItem("Empréstimo"),
			          menuSair = new JMenuItem("Sair"),
			          menuConteudo = new JMenuItem("Conteúdo"),
			          menuSobre = new JMenuItem("Sobre");
	
	public MenuIfroteca () {
		menuCadastros.add(menuCadastrarAluno);
		menuCadastros.add(menuCadastrarBibliotecario);
		menuCadastros.add(menuCadastrarLivro);
		menuBusca.add(menuBuscarAluno);
		menuBusca.add(menuBuscarBibliotecario);
		menuBusca.add(menuBuscarLivro);
		menuBusca.add(menuBuscarPendencia);
		menuLivro.add(menuReserva);
		menuLivro.add(menuDevolucao);
		menuLivro.add(menuEmprestimo);
		menuAjuda.add(menuSair);
		menuAjuda.add(menuConteudo);
		menuAjuda.add(menuSobre);
		barraMenu.add(menuCadastros);
		barraMenu.add(menuBusca);
		barraMenu.add(menuLivro);
		barraMenu.add(menuAjuda);
		
		menuCadastrarAluno.addActionListener(this);
		menuCadastrarBibliotecario.addActionListener(this);
		menuCadastrarLivro.addActionListener(this);
		menuBuscarAluno.addActionListener(this);
		menuBuscarBibliotecario.addActionListener(this);
		menuBuscarLivro.addActionListener(this);
		menuBuscarPendencia.addActionListener(this);
		menuReserva.addActionListener(this);
		menuDevolucao.addActionListener(this);
		menuEmprestimo.addActionListener(this);
		menuConteudo.addActionListener(this);
		menuSobre.addActionListener(this);
		menuSair.addActionListener(this);
		
		setJMenuBar(barraMenu);
		setExtendedState(MAXIMIZED_BOTH);
	}
	
	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == menuSair) 
			System.exit(0);
		
		else if (e.getSource() == menuCadastrarAluno) {
			CadastrarAluno ca = new CadastrarAluno();
			ca.setVisible(true);
		}	
		
		else if (e.getSource() == menuCadastrarBibliotecario) {
			CadastrarBibliotecario cb = new CadastrarBibliotecario();
			cb.setVisible(true);
		}	
		
		else if (e.getSource() == menuCadastrarLivro) {
			CadastrarLivro cl = new CadastrarLivro();
			cl.setVisible(true);
		}	
		
		else if (e.getSource() == menuBuscarAluno) {
			BuscaAluno ba = new BuscaAluno();
			ba.setVisible(true);
		}	
		
		else if (e.getSource() == menuBuscarBibliotecario) {
			BuscaBibliotecario bb = new BuscaBibliotecario();
			bb.setVisible(true);
		}	
		
		else if (e.getSource() == menuBuscarLivro) {
			BuscaLivro bl = new BuscaLivro();
			bl.setVisible(true);
		}	
		
		/*else if (e.getSource() == menuBuscarPendencia) {
			BuscaPendencia bp = new BuscaPendencia();
			bp.setVisible(true);
		}	*/
		
		else if (e.getSource() == menuReserva) {
			Reserva res = new Reserva();
			res.setVisible(true);
		}	
		
		/*else if (e.getSource() == menuDevolucao) {
			Devolucao dv = new Devolucao();
			dv.setVisible(true);
		}	
		
		else if (e.getSource() == menuEmprestimo) {
			Emprestimo emp = new Emprestimo();
			emp.setVisible(true);
		}	
		
		
		else if (e.getSource() == menuConteudo) {
			Conteudo cont = new Conteudo();
			cont.setVisible(true);
		}	
		
		else if (e.getSource() == menuSobre) {
			Sobre res = new Sobre();
			res.setVisible(true);
		}	*/
	}
	
	public static void main(String[] args) {
		MenuIfroteca cm = new MenuIfroteca();
		cm.setVisible(true);
	}
}
