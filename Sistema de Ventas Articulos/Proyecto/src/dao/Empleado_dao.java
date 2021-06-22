package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.Empleado;
import util.Conex_BD;

public class Empleado_dao {
	public ArrayList<Empleado> LoadTableEmployee(){
		ArrayList<Empleado> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call LISTAR_EMPLEADOS ");
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Empleado objEmple=new Empleado();
				objEmple.setIdEmpleado(set.getInt("IDEMPLOYEE"));
				objEmple.setNameEmpleado(set.getString("NAMEEMPLOYEE"));
				objEmple.setApeEmple(set.getString("LASTNAMEEMPLOYEE"));
				objEmple.setCargo(set.getString("NAMECARGO"));
				objEmple.setFono(set.getString("PHONE"));
				objEmple.setDireccion(set.getString("ADDRESS"));
				if(set.getInt("ESTADO")==1){
					objEmple.setEstado("ACTIVO");
				}
				else if(set.getInt("ESTADO")==0){
					objEmple.setEstado("INACTIVO");
				}
				List.add(objEmple);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA EMPLEADO");
		}
		return List;
	}
	public static ArrayList<String> FillComboboxCargo(){
		ArrayList<String>List= new ArrayList<String>();
		try {
			String consulta="select * from CARGO";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement preparedStatement=cnx.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()){
				List.add(resultSet.getString("NAMECARGO"));
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL LLENAR EL COMBOBOX DE CARGO");
		}
		return List;
	}
	public static int ShowCodEmple(){
		int cod=0;
		try {
			String consulta="SELECT MAX(IDEMPLOYEE) AS id FROM employee";
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
	public static int SearchCodUser(String username) {
		int codUser=0;
		try {
			String consulta="select IDUSER from login where USERNAME =?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1,username);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			codUser=resultSet.getInt("IDUSER");
		}catch(Exception e){
			e.getStackTrace();
		}
		return codUser;
	}
	public static int SearchCodCargo(String nombCargo) {
		int codCargo=0;
		try {
			String consulta="select IDCARGO from CARGO where NAMECARGO =?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1,nombCargo);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			codCargo=resultSet.getInt("IDCARGO");
		}catch(Exception e){
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR EL CODIGO DEL CARGO");
		}
		return codCargo;
	}
	public int SaveDataUser(String User){
		int Rpta=0;
		try{
			String consulta="insert into login (USERNAME) values(?)";
			Connection cn=Conex_BD.Conectar();
			PreparedStatement pt= cn.prepareStatement(consulta);
			pt.setString(1, User);
			Rpta=pt.executeUpdate();
			cn.close();
		} catch(Exception e ){
			Rpta=0;
		}
		return Rpta;
	}
	public int SaveDataEmpleado(String nomb, String apellido, String fono, String direccion, int user, int cargo){
		int Rpta=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call REGISTRAR_EMPLE (?,?,?,?,?,?)");
			pt.setString(1, nomb);
			pt.setString(2, apellido);
			pt.setString(3, fono);
			pt.setString(4, direccion);
			pt.setInt(5, user);
			pt.setInt(6, cargo);
			Rpta=pt.executeUpdate();
			cn.close();
		} catch(Exception e ){
			Rpta=0;
		}
		return Rpta;
	}
	public int UpdateEmple(Empleado objEmpleado){
		int i=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call ACTUALIZAR_EMPLE(?,?,?,?,?,?)");
			pt.setString(1, objEmpleado.getNameEmpleado());
			pt.setString(2, objEmpleado.getApeEmple());
			pt.setString(3, objEmpleado.getFono());
			pt.setString(4, objEmpleado.getDireccion());
			pt.setInt(5, objEmpleado.getIdCargo());
			pt.setInt(6, objEmpleado.getIdEmpleado());
			i=pt.executeUpdate();
			cn.close();
		}catch(Exception e ){
			i=0;
		}
		return i;
	}
	public int UpdateEstadoEmple(int estado, int idEmple) {
		int i=0;
		try {
			String consulta="update employee set ESTADO=? where IDEMPLOYEE=?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setInt(1, estado);
			preparedStatement.setInt(2, idEmple);
			i=preparedStatement.executeUpdate();
			connection.close();
		}catch(Exception e){
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR EL ESTADO DEL EMPLEADO \n"+e);
		}
		return i;
	}
	public ArrayList<Empleado> LoadTableEmployee(String apellido){
		ArrayList<Empleado> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call BUSCAR_EMPLExAPELLIDO (?)");
			statement.setString(1, apellido);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Empleado objEmple=new Empleado();
				objEmple.setIdEmpleado(set.getInt("IDEMPLOYEE"));
				objEmple.setNameEmpleado(set.getString("NAMEEMPLOYEE"));
				objEmple.setApeEmple(set.getString("LASTNAMEEMPLOYEE"));
				objEmple.setCargo(set.getString("NAMECARGO"));
				objEmple.setFono(set.getString("PHONE"));
				objEmple.setDireccion(set.getString("ADDRESS"));
				if(set.getInt("ESTADO")==1){
					objEmple.setEstado("ACTIVO");
				}
				else if(set.getInt("ESTADO")==0){
					objEmple.setEstado("INACTIVO");
				}
				List.add(objEmple);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LA DATA DEL EMPLEADO POR APELLIDO");
		}
		return List;
	}
}
