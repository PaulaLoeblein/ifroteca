
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

public class Reserva extends JFrame{

    private JLabel lblDataReserva = new JLabel("Data da Reserva"),
    			   lblDataEmp = new JLabel("Data Prevista para Empréstimo"),
    			   lblAluno = new JLabel("CPF do Aluno"),
    			   lblBibliotecario = new JLabel("CPF do Bibliotecário"),
                           lblCodLivro = new JLabel("Código do Livro");
    private JTextField txtCodLivro = new JTextField();
    private JFormattedTextField txtDataReserva = new JFormattedTextField(),
                                txtDataEmp = new JFormattedTextField(),
                                txtAluno = new JFormattedTextField(),
                                txtBibliotecario = new JFormattedTextField();
    
    
    private ArrayList<JTextField> textFields = new ArrayList();
    
    private JButton btnSalvar = new JButton("Salvar"),
    				btnLimpar = new JButton("Limpar"),
    				btnExibir = new JButton("Exibir");


    public Reserva() {
        super();
        iniComponents();
    }
    
    public boolean validaCampos(){
        if(txtAluno.getText().trim().length() == 0 || txtBibliotecario.getText().trim().length() == 0 || txtDataEmp.getText().trim().length() == 0 || txtDataReserva.getText().trim().length() == 0 || txtCodLivro.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido!");
            return false;
        }
        return true;
    }
    
    public Reserva(Livro l, Aluno a, ReservaLivros rl, Bibliotecario b) {
    
        super();
        iniComponents();
        this.setFields(l, a, rl, b);
    }
    
    private void setFields(Livro l, Aluno a, ReservaLivros rl, Bibliotecario b){
        txtCodLivro.setText(l.getCodigoLivro());
        txtDataReserva.setText(rl.getDataReserva());
        txtDataEmp.setText(rl.getDataEmp());
        txtAluno.setText(a.getCpf());
        txtBibliotecario.setText(b.getCpf());
    }

    private void iniComponents() {
        try{
            txtAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            txtBibliotecario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            txtDataReserva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            txtDataEmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        iniArray();

        setTitle("Reserva");
        getContentPane().setLayout(null);
    	
        this.setResizable(false);
        

		getContentPane().add(lblCodLivro);
		lblCodLivro.setBounds(360, 190, 120, 24);
		getContentPane().add(txtCodLivro);
		txtCodLivro.setBounds(360, 215, 500, 24);
		
		getContentPane().add(lblDataReserva);
		lblDataReserva.setBounds(360, 250, 120, 24);
		getContentPane().add(txtDataReserva);
		txtDataReserva.setBounds(360, 275, 235, 24);
		
		getContentPane().add(lblDataEmp);
		lblDataEmp.setBounds(625, 250, 200, 24);
		getContentPane().add(txtDataEmp);
		txtDataEmp.setBounds(625, 275, 235, 24);
		
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
                if (validaCampos()){
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
	setBounds(80, 05, 0,0);	   
        this.setPreferredSize(new Dimension(1220, 720));
        

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        
    }  

private Connection conexao;
private Statement stm;
private String url = "jdbc:mysql://localhost/ifroteca",
			   usuario = "root",
			   senha = "root";


    public void salvar() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conexao = DriverManager.getConnection(url, usuario, senha);
    		stm = conexao.createStatement();
                
                PreparedStatement pstm = conexao.prepareStatement("insert into reserva (select livro.codigoLivro_liv, reserva.dataReserva, reserva.dataPrevistaEmp, aluno.cpf_alu, bibliotecario.cpf_bib from reserva left join bibliotecario on\n" +
"reserva.cod_res = bibliotecario.cod_bib left join livro on\n" + "reserva.cod_res = livro.cod_liv left join aluno on\n" + "reserva.cod_res = aluno.cod_alu) values (?,?)");
                              
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");       
                Date dataReserva = sdf.parse(txtDataReserva.getText());
                java.sql.Date sqlDate = new java.sql.Date(dataReserva.getTime());
                
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");       
                Date dataPrevistaEmp = sdf2.parse(txtDataEmp.getText());
                java.sql.Date sqlDate2 = new java.sql.Date(dataPrevistaEmp.getTime());

                pstm.setDate(1, sqlDate);
                pstm.setDate(2, sqlDate2);
                
                pstm.execute();
    		
    		JOptionPane.showMessageDialog(null, "Dados gravados com sucesso!");
                limpar();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }

    public void exibir() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		conexao = DriverManager.getConnection(url, usuario, senha);
    		stm = conexao.createStatement();
    		String sql = "select livro.codigoLivro_liv, reserva.dataReserva, reserva.dataPrevistaEmp, aluno.cpf_alu, bibliotecario.cpf_bib from reserva left join bibliotecario on\n" +
"reserva.cod_res = bibliotecario.cod_bib left join livro on\n" + "reserva.cod_res = livro.cod_liv left join aluno on\n" + "reserva.cod_res = aluno.cod_alu";
    		ResultSet rs = stm.executeQuery(sql);
    		while (rs.next()){
    			System.out.println("Código do Livro: " + rs.getString("codigoLivro_liv"));
    			System.out.println("Data da Reserva: " + rs.getString("dataReserva"));
    			System.out.println("Data Prevista para Empréstimo: " + rs.getString("dataPrevistaEmp"));
    			System.out.println("CPF do Aluno: " + rs.getString("cpf_alu"));
    			System.out.println("CPF do Bibliotecário: " + rs.getString("cpf_bib"));
    			
    		}
    		stm.close();
    	} catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    private void limpar(){
        for (JTextField j : this.textFields){
            j.setText("");
        }
    }

    public static void main(String[] args) {
        
    	Aluno a  = new Aluno();
        Livro l  = new Livro();
        ReservaLivros r  = new ReservaLivros();
        Bibliotecario b  = new Bibliotecario();
               
        l.setCodigoLivro("");
        r.setDataReserva("");
        r.setDataEmp("");
        a.setCpf("");
        b.setCpf("");
       
        Reserva r2 = new Reserva();
        r2.setVisible(true);
      
    }


    private void iniArray(){
        textFields.add(txtCodLivro);
        textFields.add(txtDataReserva);
        textFields.add(txtDataEmp);
        textFields.add(txtAluno);
        textFields.add(txtBibliotecario);

    }

}
