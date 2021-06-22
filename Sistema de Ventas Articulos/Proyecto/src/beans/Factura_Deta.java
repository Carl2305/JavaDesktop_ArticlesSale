package beans;

public class Factura_Deta extends Factura_Cabe {
	int IdProducto, cantProd;
	String nomProd;
	double precioProd, Descuento, total;
	public Factura_Deta(){
		
	}
	public Factura_Deta(int idProducto, int cantProd, String nomProd, double precioProd, double descuento,
			double total) {
		IdProducto = idProducto;
		this.cantProd = cantProd;
		this.nomProd = nomProd;
		this.precioProd = precioProd;
		Descuento = descuento;
		this.total = total;
	}
	public int getIdProducto() {
		return IdProducto;
	}
	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}
	public int getCantProd() {
		return cantProd;
	}
	public void setCantProd(int cantProd) {
		this.cantProd = cantProd;
	}
	public String getNomProd() {
		return nomProd;
	}
	public void setNomProd(String nomProd) {
		this.nomProd = nomProd;
	}
	public double getPrecioProd() {
		return precioProd;
	}
	public void setPrecioProd(double precioProd) {
		this.precioProd = precioProd;
	}
	public double getDescuento() {
		return Descuento;
	}
	public void setDescuento(double descuento) {
		Descuento = descuento;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
}
