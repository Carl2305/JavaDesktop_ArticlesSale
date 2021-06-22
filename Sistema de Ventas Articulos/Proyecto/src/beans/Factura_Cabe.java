package beans;

import java.util.Date;

public class Factura_Cabe {
	int IdPedido, IdCliente, IdEmpleado;
	Date FechaPedido;
	String nameCliente;
	public Factura_Cabe(){
		
	}
	public Factura_Cabe(int idPedido, int idCliente, int idEmpleado, Date fechaPedido, String nameCliente) {
		IdPedido = idPedido;
		IdCliente = idCliente;
		IdEmpleado = idEmpleado;
		FechaPedido = fechaPedido;
		this.nameCliente = nameCliente;
	}
	public int getIdPedido() {
		return IdPedido;
	}
	public void setIdPedido(int idPedido) {
		IdPedido = idPedido;
	}
	public int getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}
	public int getIdEmpleado() {
		return IdEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		IdEmpleado = idEmpleado;
	}
	public Date getFechaPedido() {
		return FechaPedido;
	}
	public void setFechaPedido(Date fechaPedido) {
		FechaPedido = fechaPedido;
	}
	public String getNameCliente() {
		return nameCliente;
	}
	public void setNameCliente(String nameCliente) {
		this.nameCliente = nameCliente;
	}
	
}
