package beans;

public class Cliente {
	int idCliente, idPais;
	String nombCliente, direCliente, fonoCliente, nombPais, DNI;
	public Cliente(){
		
	}
	public Cliente(int idCliente, int idPais, String nombCliente, String direCliente, String fonoCliente,
			String nombPais, String dNI) {
		this.idCliente = idCliente;
		this.idPais = idPais;
		this.nombCliente = nombCliente;
		this.direCliente = direCliente;
		this.fonoCliente = fonoCliente;
		this.nombPais = nombPais;
		DNI = dNI;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNombCliente() {
		return nombCliente;
	}
	public void setNombCliente(String nombCliente) {
		this.nombCliente = nombCliente;
	}
	public String getDireCliente() {
		return direCliente;
	}
	public void setDireCliente(String direCliente) {
		this.direCliente = direCliente;
	}
	public String getFonoCliente() {
		return fonoCliente;
	}
	public void setFonoCliente(String fonoCliente) {
		this.fonoCliente = fonoCliente;
	}
	public String getNombPais() {
		return nombPais;
	}
	public void setNombPais(String nombPais) {
		this.nombPais = nombPais;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
		
}
