package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.Cliente;
import beans.Empleado;
import util.Conex_BD;

public class Cliente_dao {
	public ArrayList<Cliente> LoadTableCliente(){
		ArrayList<Cliente> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call LISTAR_CLIENTES ");
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Cliente objClient=new Cliente();
				objClient.setIdCliente(set.getInt("IDCLIENTES"));
				objClient.setNombCliente(set.getString("NAMECLIENTE"));
				objClient.setDireCliente(set.getString("DIRECCION"));
				objClient.setNombPais(set.getString("NAMEPAIS"));
				objClient.setFonoCliente(set.getString("PHONECLIENTE"));
				objClient.setDNI(set.getString("DNI"));
				List.add(objClient);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA CLIENTE");
		}
		return List;
	}
	public static ArrayList<String> FillComboboxPais(){
		ArrayList<String>List= new ArrayList<String>();
		try {
			String consulta="select * from paises";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement preparedStatement=cnx.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()){
				List.add(resultSet.getString("NAMEPAIS"));
			}
			cnx.close();
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL LLENAR EL COMBOBOX DE PAIS");
		}
		return List;
	}
	public ArrayList<Cliente> LoadTableClienteBuscar(String cadena){
		ArrayList<Cliente> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call BUSCAR_CLIENTE (?)");
			statement.setString(1, cadena);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Cliente objClient=new Cliente();
				objClient.setIdCliente(set.getInt("IDCLIENTES"));
				objClient.setNombCliente(set.getString("NAMECLIENTE"));
				objClient.setDireCliente(set.getString("DIRECCION"));
				objClient.setNombPais(set.getString("NAMEPAIS"));
				objClient.setFonoCliente(set.getString("PHONECLIENTE"));
				objClient.setDNI(set.getString("DNI"));
				List.add(objClient);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LA DATA DEL EMPLEADO POR APELLIDO");
		}
		return List;
	}
	public String SearchCliente(String Dni){
		String Nombre="";
		try {
			String consulta="SELECT NAMECLIENTE, DNI FROM clientes where DNI='"+Dni+"'";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			Nombre=resultSet.getString("NAMECLIENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "NO SE ENCUENTRA EL DNI DEL CLIENTE EN LA BASE DE DATOS");			
		}
		return Nombre;
	}
	public int SearchCodCliente(String Dni){
		int Cod=0;
		try {
			String consulta="SELECT IDCLIENTES FROM clientes where DNI='"+Dni+"'";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			Cod=resultSet.getInt("IDCLIENTES");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "NO SE ENCUENTRA EL DNI DEL CLIENTE EN LA BASE DE DATOS");			
		}
		return Cod;
	}
	public int SaveDataCliente(String nombre, String direccion, int pais, String fono, String dni){
		int Rpta=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call REGISTRAR_CLIENTE (?,?,?,?,?)");
			pt.setString(1, nombre);
			pt.setString(2, direccion);
			pt.setInt(3, pais);
			pt.setString(4, fono);
			pt.setString(5, dni);
			Rpta=pt.executeUpdate();
			cn.close();
		} catch(Exception e ){
			Rpta=0;
		}
		return Rpta;
	}
	public static int ShowCodCliente(){
		int cod=0;
		try {
			String consulta="SELECT MAX(IDCLIENTES) AS id FROM clientes";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			cod=resultSet.getInt("id");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER EL CODIGO DEL EMPLADO DE LA BD");;
		}
		
		return cod;
	}
	public static int SearchCodPais(String nombPasis) {
		int codUser=0;
		try {
			String consulta="select IDPAIS from paises where NAMEPAIS =?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1,nombPasis);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			codUser=resultSet.getInt("IDPAIS");
		}catch(Exception e){
			e.getStackTrace();
		}
		return codUser;
	}
	public int UpdateCiente(Cliente objCliente){
		int i=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call ACTUALIZAR_CLIENTE (?,?,?,?,?)");
			pt.setInt(1, objCliente.getIdCliente());
			pt.setString(2, objCliente.getNombCliente());
			pt.setString(3, objCliente.getDireCliente());
			pt.setInt(4, objCliente.getIdPais());
			pt.setString(5, objCliente.getFonoCliente());
			i=pt.executeUpdate();
			cn.close();
		}catch(Exception e ){
			i=0;
		}
		return i;
	}
}
