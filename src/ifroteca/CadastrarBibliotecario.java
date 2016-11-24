
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

	public class CadastrarBibliotecario extends JFrame {

	    private JLabel lblNome = new JLabel("Nome"),
	    			   lblFone = new JLabel("Telefone"),
	    			   lblDataNasc = new JLabel("Data de Nascimento"),
	    			   lblEndereco = new JLabel("Endereço"),
	    			   lblCidade = new JLabel("Cidade"),
	    			   lblCPF = new JLabel("CPF"),
	    			   lblRG = new JLabel("RG"),
	    			   lblEmail = new JLabel("E-mail");
	    			
	    private JTextField txtNome = new JTextField(),
	            		   txtEndereco = new JTextField(),
	            		   txtCidade= new JTextField(),
	            		   txtRG = new JTextField(),
	            		   txtEmail = new JTextField();
	            		
	    private JFormattedTextField txtFone = new JFormattedTextField(),
	            		   txtDataNasc = new JFormattedTextField(),
                                   txtCPF = new JFormattedTextField();
	    
	    private ArrayList<JTextField> textFields = new ArrayList();
	    
	    private JButton btnSalvar = new JButton("Salvar"),
	    				btnLimpar = new JButton("Limpar"),
	    				btnExibir = new JButton("Exibir");


	    public CadastrarBibliotecario() {
	        super();
	        iniComponents();
	    }
	    
            public boolean validaCampos(){
        if(txtNome.getText().trim().length() == 0 || txtCPF.getText().trim().length() == 0 || txtCidade.getText().trim().length() == 0 || txtDataNasc.getText().trim().length() == 0 || txtEmail.getText().trim().length() == 0 || txtEndereco.getText().trim().length() == 0 || txtFone.getText().trim().length() == 0 || txtRG.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido!");
            return false;
        }
        return true;
    }
            
	    public CadastrarBibliotecario(Bibliotecario b) {
	        // na hora de editar
	        super();
	        iniComponents();
	        this.setFields(b);
	    }
	    
	    private void setFields(Bibliotecario b){
	        txtNome.setText(b.getNome());
	        txtFone.setText(b.getTelefone());
	        txtDataNasc.setText(b.getDataDeNascimento());
	        txtEndereco.setText(b.getEndereco());
	        txtCidade.setText(b.getCidade());
	        txtCPF.setText(b.getCpf());
	        txtRG.setText(b.getRg());
	        txtEmail.setText(b.getEmail());
	        

	    }

	    private void iniComponents() {
                
                try{
            txtCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            txtDataNasc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
        try{
            txtFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-####")));
        }catch (Exception e){
            e.printStackTrace();
        }
        
	        iniArray();

	        setTitle("Cadastrar Bibliotecário");
	        getContentPane().setLayout(null);
	    	
	        this.setResizable(false);
	        

			getContentPane().add(lblNome);
			lblNome.setBounds(360, 120, 120, 24);
			getContentPane().add(txtNome);
			txtNome.setBounds(360, 145, 500, 24);
			
			getContentPane().add(lblFone);
			lblFone.setBounds(360, 300, 120, 24);
			getContentPane().add(txtFone);
			txtFone.setBounds(360, 325, 270, 24);
			
			getContentPane().add(lblDataNasc);
			lblDataNasc.setBounds(660, 300, 120, 24);
			getContentPane().add(txtDataNasc);
			txtDataNasc.setBounds(660, 325, 200, 24);
			
			getContentPane().add(lblEndereco);
			lblEndereco.setBounds(360, 360, 120, 24);
			getContentPane().add(txtEndereco);
			txtEndereco.setBounds(360, 385, 500, 24);
			
			getContentPane().add(lblCidade);
			lblCidade.setBounds(360, 420, 120, 24);
			getContentPane().add(txtCidade);
			txtCidade.setBounds(360, 445, 190, 24);
				
			getContentPane().add(lblCPF);
			lblCPF.setBounds(360, 180, 120, 24);
			getContentPane().add(txtCPF);
			txtCPF.setBounds(360, 205, 270, 24);
			
			getContentPane().add(lblRG);
			lblRG.setBounds(660, 180, 120, 24);
			getContentPane().add(txtRG);
			txtRG.setBounds(660, 205, 200, 24);
			
			getContentPane().add(lblEmail);
			lblEmail.setBounds(360, 240, 120, 24);
			getContentPane().add(txtEmail);
			txtEmail.setBounds(360, 265, 500, 24);

			
	        
	       
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
                PreparedStatement pstm = conexao.prepareStatement("insert into bibliotecario (nome_bib, fone_bib, dataNasc_bib, endereco_bib, cidade_bib, cpf_bib, rg_bib, email_bib) values (?,?,?,?,?,?,?,?)");
                pstm.setString(1, txtNome.getText());
                pstm.setString(2, txtFone.getText());

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");       
                Date dataNasc = sdf.parse(txtDataNasc.getText());
                java.sql.Date sqlDate = new java.sql.Date(dataNasc.getTime());
                
                pstm.setDate(3, sqlDate);
                pstm.setString(4, txtEndereco.getText());
                pstm.setString(5, txtCidade.getText());                
                pstm.setString(6, txtCPF.getText());
                pstm.setString(7, txtRG.getText());
                pstm.setString(8, txtEmail.getText());
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
    		String sql = "select * from bibliotecario";
    		ResultSet rs = stm.executeQuery(sql);
    		while (rs.next()){
    			System.out.println("Nome: " + rs.getString("nome_bib"));
    			System.out.println("CPF: " + rs.getString("cpf_bib"));
    			System.out.println("RG: " + rs.getString("rg_bib"));
    			System.out.println("Email: " + rs.getString("email_bib"));
    			System.out.println("Telefone: " + rs.getString("fone_bib"));
    			System.out.println("Data de Nascimento: " + rs.getString("dataNasc_bib"));
    			System.out.println("Endereco: " + rs.getString("endereco_bib"));
    			System.out.println("Cidade: " + rs.getString("cidade_bib"));
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
	        
	    	Bibliotecario b  = new Bibliotecario();
	        b.setNome("");
	        b.setTelefone("");
	        b.setDataDeNascimento("");
	        b.setEndereco("");
	        b.setCidade("");
	        b.setCpf("");
	        b.setRg("");
	        b.setEmail("");
	      
	       
	        CadastrarBibliotecario b2 = new CadastrarBibliotecario();
	        b2.setVisible(true);
	      
	    }


	    private void iniArray(){
	        textFields.add(txtNome);
	        textFields.add(txtFone);
	        textFields.add(txtDataNasc);
	        textFields.add(txtEndereco);
	        textFields.add(txtCidade);
	        textFields.add(txtCPF);
	        textFields.add(txtRG);
	        textFields.add(txtEmail);
	        
	      
	        
	 
	    }

	}