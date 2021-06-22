package beans;

public class Usuario extends Empleado {
	// usuario, password
	String user, password;
	// id sesion
	boolean IniSesion;
	public Usuario(){
		
	}
	public Usuario(String user, String password, boolean iniSesion) {
		this.user = user;
		this.password = password;
		//this.cargo = cargo;
		IniSesion = iniSesion;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIniSesion() {
		return IniSesion;
	}
	public void setIniSesion(boolean iniSesion) {
		IniSesion = iniSesion;
	}
	
	
}
