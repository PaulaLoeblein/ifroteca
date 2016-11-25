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

public class CadastrarAluno extends JFrame{

    private JLabel lblNome = new JLabel("Nome"),
    			   lblCPF = new JLabel("CPF"),
    			   lblRG = new JLabel("RG"),
    			   lblEmail = new JLabel("E-mail"),
    			   lblFone = new JLabel("Telefone"),
    			   lblDataNasc = new JLabel("Data de Nascimento"),
    			   lblEndereco = new JLabel("Endereço"),
    			   lblCidade = new JLabel("Cidade"),
    			   lblTurma = new JLabel("Turma"),
    			   lblCurso = new JLabel("Curso");
    private JTextField txtNome = new JTextField(),
            		   txtRG = new JTextField(),
            		   txtEmail = new JTextField(),
            		   txtEndereco = new JTextField(),
            		   txtCidade = new JTextField(),
            		   txtTurma = new JTextField(),
            		   txtCurso = new JTextField();  
    
    private JFormattedTextField txtCPF = new JFormattedTextField(),
                                txtDataNasc = new JFormattedTextField(),
                                txtFone = new JFormattedTextField();
    
    
    private ArrayList<JTextField> textFields = new ArrayList();
    
    private JButton btnSalvar = new JButton("Salvar"),
    				btnLimpar = new JButton("Limpar"),
    				btnExibir = new JButton("Exibir");


    public CadastrarAluno() {
        super();
        iniComponents();
    }
    
    public boolean validaCampos(){
        if(txtNome.getText().trim().length() == 0 || txtCPF.getText().trim().length() == 0 || txtCidade.getText().trim().length() == 0 || txtCurso.getText().trim().length() == 0 || txtDataNasc.getText().trim().length() == 0 || txtEmail.getText().trim().length() == 0 || txtTurma.getText().trim().length() == 0 || txtRG.getText().trim().length() == 0 || txtEndereco.getText().trim().length() == 0 || txtFone.getText().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Algum campo não foi preenchido!");
            return false;
        }
        return true;
    }
    
    public CadastrarAluno(Aluno a) {
    
        super();
        iniComponents();
        this.setFields(a);
    }
    
    private void setFields(Aluno a){
        txtNome.setText(a.getNome());
        txtCPF.setText(a.getCpf());
        txtRG.setText(a.getRg());
        txtEmail.setText(a.getEmail());
        txtFone.setText(a.getTelefone());
        txtEndereco.setText(a.getEndereco());
        txtCidade.setText(a.getCidade());
        txtDataNasc.setText(a.getDataDeNascimento());
        txtTurma.setText(a.getTurma());
        txtCurso.setText(a.getCurso());

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

        setTitle("Cadastrar Aluno");
        getContentPane().setLayout(null);
    	
        this.setResizable(false);
        

		getContentPane().add(lblNome);
		lblNome.setBounds(360, 140, 120, 24);
		getContentPane().add(txtNome);
		txtNome.setBounds(360, 165, 500, 24);
		
		getContentPane().add(lblCPF);
		lblCPF.setBounds(360, 200, 120, 24);
		getContentPane().add(txtCPF);
		txtCPF.setBounds(360, 225, 270, 24);
		
		getContentPane().add(lblRG);
		lblRG.setBounds(660, 200, 120, 24);
		getContentPane().add(txtRG);
		txtRG.setBounds(660, 225, 200, 24);
		
		getContentPane().add(lblEmail);
		lblEmail.setBounds(360, 260, 120, 24);
		getContentPane().add(txtEmail);
		txtEmail.setBounds(360, 285, 500, 24);
		
		getContentPane().add(lblFone);
		lblFone.setBounds(360, 320, 120, 24);
		getContentPane().add(txtFone);
		txtFone.setBounds(360, 345, 270, 24);
		
		getContentPane().add(lblDataNasc);
		lblDataNasc.setBounds(660, 320, 120, 24);
		getContentPane().add(txtDataNasc);
		txtDataNasc.setBounds(660, 345, 200, 24);
		
		getContentPane().add(lblEndereco);
		lblEndereco.setBounds(360, 380, 120, 24);
		getContentPane().add(txtEndereco);
		txtEndereco.setBounds(360, 405, 500, 24);
		
		getContentPane().add(lblCidade);
		lblCidade.setBounds(360, 440, 120, 24);
		getContentPane().add(txtCidade);
		txtCidade.setBounds(360, 465, 190, 24);
			
		getContentPane().add(lblTurma);
		lblTurma.setBounds(580, 440, 120, 24);
		getContentPane().add(txtTurma);
		txtTurma.setBounds(580, 465, 90, 24);
		
		getContentPane().add(lblCurso);
		lblCurso.setBounds(700, 440, 120, 24);
		getContentPane().add(txtCurso);
		txtCurso.setBounds(700, 465, 160, 24);
        
       
		getContentPane().add(btnSalvar);
		btnSalvar.setBounds(480, 530, 80, 24);
        getContentPane().add(btnExibir);
		btnExibir.setBounds(570, 530, 80, 24);
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(660, 530, 80, 24);
        
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
                PreparedStatement pstm = conexao.prepareStatement("insert into aluno (nome_alu, cpf_alu, rg_alu, email_alu, fone_alu, endereco_alu, dataNasc_alu, cidade_alu, turma_alu, curso_alu) values (?,?,?,?,?,?,?,?,?,?)");
                pstm.setString(1, txtNome.getText());
                pstm.setString(2, txtCPF.getText());
                pstm.setString(3, txtRG.getText());
                pstm.setString(4, txtEmail.getText());
                pstm.setString(5, txtFone.getText());
                pstm.setString(6, txtEndereco.getText());
                
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");       
                Date dataNasc = sdf.parse(txtDataNasc.getText());
                java.sql.Date sqlDate = new java.sql.Date(dataNasc.getTime());
                
                pstm.setDate(7, sqlDate);
                pstm.setString(8, txtCidade.getText());
                pstm.setString(9, txtTurma.getText());
                pstm.setString(10, txtCurso.getText());
                
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
    		String sql = "select * from aluno";
    		ResultSet rs = stm.executeQuery(sql);
    		while (rs.next()){
    			System.out.println("Nome: " + rs.getString("nome_alu"));
    			System.out.println("CPF: " + rs.getString("cpf_alu"));
    			System.out.println("RG: " + rs.getString("rg_alu"));
    			System.out.println("Email: " + rs.getString("email_alu"));
    			System.out.println("Telefone: " + rs.getString("fone_alu"));
    			System.out.println("Data de Nascimento: " + rs.getString("dataNasc_alu"));
    			System.out.println("Endereco: " + rs.getString("endereco_alu"));
    			System.out.println("Cidade: " + rs.getString("cidade_alu"));
    			System.out.println("Turma: " + rs.getString("turma_alu"));
    			System.out.println("Curso: " + rs.getString("curso_alu"));
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
        a.setNome("");
        a.setCpf("");
        a.setRg("");
        a.setEmail("");
        a.setTelefone("");
        a.setDataDeNascimento("");
        a.setEndereco("");
        a.setCidade("");
        a.setTurma("");
        a.setCurso("");
       
        CadastrarAluno ca2 = new CadastrarAluno();
        ca2.setVisible(true);
      
    }


    private void iniArray(){
        textFields.add(txtNome);
        textFields.add(txtCPF);
        textFields.add(txtRG);
        textFields.add(txtEmail);
        textFields.add(txtFone);
        textFields.add(txtDataNasc);
        textFields.add(txtEndereco);
        textFields.add(txtCidade);
        textFields.add(txtTurma);
        textFields.add(txtCurso);
 
    }

}
