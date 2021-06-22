package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class Conex_BD {
	
	public static Connection Conectar(){
		Connection cnx = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
	        String Url="jdbc:mysql://127.0.0.1/my_proyecto_javamysql";
	        String User="root";
	        String Pass="";
	        cnx = DriverManager.getConnection(Url,User,Pass);
	    } 
		catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null,"no se conectó"+ex);
	    } 
		catch (ClassNotFoundException ex) {
	        JOptionPane.showMessageDialog(null,"no se conectó"+ex);
		}
	     return cnx;
	}
	
}