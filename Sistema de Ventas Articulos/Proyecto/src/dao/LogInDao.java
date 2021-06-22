package dao;

import java.sql.*;
import java.util.logging.*;

import javax.swing.JOptionPane;

import beans.*;
import util.Conex_BD;

public class LogInDao {
	Connection con=null;
	
	public static boolean LogIn(Usuario user) {
		CallableStatement cs=null;
		ResultSet rs=null;
		try {
			Connection cn=Conex_BD.Conectar();
			cs=cn.prepareCall("{call LogIn (?)}");
			cs.setString(1, user.getUser());
			rs=cs.executeQuery();
			if(rs.next()){
				if(user.getPassword().equals(rs.getString("PASSWORD"))){
					if(rs.getInt("ESTADO")==1){
						user.setUser(rs.getString("USERNAME"));
						user.setPassword(rs.getString("PASSWORD"));
						user.setIdCargo(rs.getInt("IDCARGO"));
						user.setIniSesion(rs.getBoolean("IDSESION"));
						return true;
					} else {
						JOptionPane.showMessageDialog(null, "El Usuario no tiene acceso debido a su estado inactivo");
						return false;
					}
				}
				else{
					return false;
				}
			}
			return false;
		} catch (SQLException e) {
			Logger.getLogger(LogInDao.class.getName()).log(Level.SEVERE, null,e);
			return false;
		}
	}
	public boolean Estado_User(String user) {
		boolean estado=false;
		int s=0;
		try {
			Connection cn=Conex_BD.Conectar();
			CallableStatement caStatement=cn.prepareCall("{call IniSesion (?,?)}");
			caStatement.setString(1, user);
			caStatement.registerOutParameter("INISESION", Types.TINYINT);
			caStatement.execute();
			s=caStatement.getInt("INISESION");
			if(s==0){
				estado=false;
			}
			else{
				estado=true;
			}
			cn.close();
		} catch (Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "Error al buscar el estado del usuario "+e);
		}
		return estado;
	}
}
