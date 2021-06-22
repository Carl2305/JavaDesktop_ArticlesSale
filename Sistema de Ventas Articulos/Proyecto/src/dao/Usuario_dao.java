package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import beans.Usuario;
import util.Conex_BD;

public class Usuario_dao {
	Connection cn=null;
	public int ModifyPasswordUser(String pass, String User){
		int respuesta=0;
		try {
			Connection connection=Conex_BD.Conectar();
			CallableStatement statement=connection.prepareCall("{call UPDATE_Pass (?,?)}");
			statement.setString(1, pass);
			statement.setString(2, User);
			respuesta=statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "error al cambiar la contraseña "+e);
			respuesta=0;
		}
		return respuesta;
	}
	public static String getPassword_(String user) {
		String pass="";
		try {
			String consulta="SELECT PASSWORD FROM login where USERNAME=?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1, user);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			pass=resultSet.getString("PASSWORD");
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "error de SQL "+e);
		}
		catch(Exception e){
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "error al validar la contaseña "+e);
		}
		return pass;
	}
	public static int SearchCodEmple(String CodEmple) {
		int codEmple=0;
		try {
			String consulta="select E.IDEMPLOYEE from LOGIN L JOIN employee E ON L.IDUSER=E.IDUSER WHERE L.USERNAME=?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1,CodEmple);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			codEmple=resultSet.getInt("IDEMPLOYEE");
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "error de SQL"+e);
		}
		catch(Exception e){
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "error al buscar codigo del Empleado"+e);
		}
		return codEmple;
	}
}
