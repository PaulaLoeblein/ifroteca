package ifroteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

	public class SingletonConexao {
	private static SingletonConexao instance;
	private Connection conexao;
	private Statement stm;
	private String url = "jdbc:mysql://localhost/ifroteca",
				   usuario = "root",
				   senha = "root";
	
	private SingletonConexao(){
		try{
		    Class.forName("com.mysql.jdbc.Driver");
    		    this.conexao = DriverManager.getConnection(url, usuario, senha);
    		    stm = conexao.createStatement();
		} catch (SQLException sqle) {		
	             sqle.printStackTrace();
	        } catch (ClassNotFoundException cnfe) {
	             cnfe.printStackTrace();
	        } 
	   }
	
	private static SingletonConexao getInstance(){
		if (instance == null){
			instance = new SingletonConexao();
		}
		return instance;
	}
		
	public Connection getConexao(){
	    return conexao;
	}
		
	/*
	* Modo de uso:
	* SingletonConexao singleton = SingetonConexao.getInstance();
	* Connection con = singleton.getConexao();
	* Agora você tem a conexão para utilizar.
	*/
}
