package beans;

public class Empleado {
	int idEmpleado, idCargo, idUser;
	String nameEmpleado, apeEmple, Direccion, cargo, fono, estado;
	public Empleado(){
		
	}
	public Empleado(int idEmpleado, int idCargo, int idUser, String nameEmpleado, String apeEmple, String direccion,
			String cargo, String fono, String estado) {
		this.idEmpleado = idEmpleado;
		this.idCargo = idCargo;
		this.idUser = idUser;
		this.nameEmpleado = nameEmpleado;
		this.apeEmple = apeEmple;
		Direccion = direccion;
		this.cargo = cargo;
		this.fono = fono;
		this.estado = estado;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public int getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getNameEmpleado() {
		return nameEmpleado;
	}
	public void setNameEmpleado(String nameEmpleado) {
		this.nameEmpleado = nameEmpleado;
	}
	public String getApeEmple() {
		return apeEmple;
	}
	public void setApeEmple(String apeEmple) {
		this.apeEmple = apeEmple;
	}
	public String getDireccion() {
		return Direccion;
	}
	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getFono() {
		return fono;
	}
	public void setFono(String fono) {
		this.fono = fono;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}