package beans;

public class Producto {
	int idCategoria, idProducto, idProveedor, stock, unidPedido;
	double PrecioUnidad;
	String CantXunidad, nameProducto, nameCategoria, nameProveedor;
	public Producto(){
		
	}
	public Producto(int idCategoria, int idProducto, int idProveedor, int stock, int unidPedido, double precioUnidad,
			String cantXunidad, String nameProducto) {
		this.idCategoria = idCategoria;
		this.idProducto = idProducto;
		this.idProveedor = idProveedor;
		this.stock = stock;
		this.unidPedido = unidPedido;
		PrecioUnidad = precioUnidad;
		CantXunidad = cantXunidad;
		this.nameProducto = nameProducto;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getUnidPedido() {
		return unidPedido;
	}
	public void setUnidPedido(int unidPedido) {
		this.unidPedido = unidPedido;
	}
	public double getPrecioUnidad() {
		return PrecioUnidad;
	}
	public void setPrecioUnidad(double precioUnidad) {
		PrecioUnidad = precioUnidad;
	}
	public String getCantXunidad() {
		return CantXunidad;
	}
	public void setCantXunidad(String cantXunidad) {
		CantXunidad = cantXunidad;
	}
	public String getNameProducto() {
		return nameProducto;
	}
	public void setNameProducto(String nameProducto) {
		this.nameProducto = nameProducto;
	}
	public String getNameCategoria() {
		return nameCategoria;
	}
	public void setNameCategoria(String nameCategoria) {
		this.nameCategoria = nameCategoria;
	}
	public String getNameProveedor() {
		return nameProveedor;
	}
	public void setNameProveedor(String nameProveedor) {
		this.nameProveedor = nameProveedor;
	}
	
	
}
