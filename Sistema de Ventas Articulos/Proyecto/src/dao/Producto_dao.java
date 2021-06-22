package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.Empleado;
import beans.Producto;
import util.Conex_BD;

public class Producto_dao {
	public ArrayList<Producto> LoadTableProducto(){
		ArrayList<Producto> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call LISTAR_PRODUCTOS ");
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Producto objProd=new Producto();
				objProd.setIdProducto(set.getInt("IDPRODUCTO"));
				objProd.setNameProducto(set.getString("NAMEPRODUCTO"));
				objProd.setNameCategoria(set.getNString("NAMECATEGORIA"));
				objProd.setNameProveedor(set.getNString("NAMEPROVEEDOR"));
				objProd.setCantXunidad(set.getString("CANTxUNIDAD"));
				objProd.setStock(set.getInt("UNIDADEXISTENCIA"));
				objProd.setPrecioUnidad(set.getDouble("PRECIOUNIDAD"));
				objProd.setUnidPedido(set.getInt("UNIDADPEDIDO"));
				List.add(objProd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA PRODUCTO");
		}
		return List;
	}
	public static ArrayList<String> FillComboboxCategoria(){
		ArrayList<String>List= new ArrayList<String>();
		try {
			String consulta="select * from categoria";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement preparedStatement=cnx.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			while (resultSet.next()){
				List.add(resultSet.getString("NAMECATEGORIA"));
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL LLENAR EL COMBOBOX DE CATEGORIA");
		}
		return List;
	}
	public static ArrayList<String> FillComboboxProveedor(String categoria){
		ArrayList<String>List= new ArrayList<String>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call LISTAR_PROVEEDOR_x_CATEGORIA (?)");
			statement.setString(1, categoria);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()){
				List.add(resultSet.getString("NAMEPROVEEDOR"));
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL LLENAR EL COMBOBOX DE LOS PROVEEDORES");
		}
		return List;
	}
	public static ArrayList<String> FillComboboxProducto(String categoria){
		ArrayList<String>List= new ArrayList<String>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call LISTAR_PRODUCTO_x_CATEGORIA (?)");
			statement.setString(1, categoria);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()){
				List.add(resultSet.getString("NAMEPRODUCTO"));
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL LLENAR EL COMBOBOX DE LOS PRODUCTOS");
		}
		return List;
	}
	public static double LoadPrecio(String Producto){
		double precio=0;
		try {
			String consulta="SELECT PRECIOUNIDAD FROM productos WHERE NAMEPRODUCTO =?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1,Producto);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			precio=resultSet.getDouble("PRECIOUNIDAD");
		} catch (Exception e) {
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR EL PRECIO DEL PRODUCTO");
		}
		return precio;
	}
	public static int SearchCodCategoria (String nombCategoria) {
		int codCargo=0;
		try {
			String consulta="select IDCATEGORIA from CATEGORIA where NAMECATEGORIA =?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1,nombCategoria);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			codCargo=resultSet.getInt("IDCATEGORIA");
		}catch(Exception e){
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR EL CODIGO DE LA CATEGORIA");
		}
		return codCargo;
	}
	public static int SearchCodProveedor (String nombProveedor) {
		int codCargo=0;
		try {
			String consulta="select IDPROVEEDORES from PROVEEDORES where NAMEPROVEEDOR =?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1, nombProveedor);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			codCargo=resultSet.getInt("IDPROVEEDORES");
		}catch(Exception e){
			e.getStackTrace();
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR EL CODIGO DEL PROVEEDOR");
		}
		return codCargo;
	}
	public static int ShowCodProducto(){
		int cod=0;
		try {
			String consulta="SELECT MAX(IDPRODUCTO) AS id FROM productos";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			cod=resultSet.getInt("id");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER EL CODIGO DEL PRODUCTO DE LA BD");;
		}
		
		return cod;
	}
	public int SearchCodProducto(String Producto){
		int cod=0;
		try {
			String consulta="SELECT IDPRODUCTO FROM productos where NAMEPRODUCTO = ?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setString(1, Producto);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			cod=resultSet.getInt("IDPRODUCTO");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER EL CODIGO DEL PRODUCTO DE LA BD");;
		}
		
		return cod;
	}
	public int SaveDataProducto(String nomb, int proveedor, int categoria, String cantXunidad, 
								double precio,int stock, int pedido){
		int Rpta=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call REGISTRAR_PRODUCTO (?,?,?,?,?,?,?) ");
			pt.setString(1, nomb);
			pt.setInt(2, proveedor);
			pt.setInt(3, categoria);
			pt.setString(4, cantXunidad);
			pt.setDouble(5, precio);
			pt.setInt(6, stock);
			pt.setInt(7, pedido);
			Rpta=pt.executeUpdate();
			cn.close();
		} catch(Exception e ){
			Rpta=0;
		}
		return Rpta;
	}
	public int UpdateProducto(Producto producto){
		int i=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call ACTUALIZAR_PRODUCTO (?,?,?,?,?,?,?,?)");
			pt.setString(1, producto.getNameProducto());
			pt.setInt(2, producto.getIdProveedor());
			pt.setInt(3, producto.getIdCategoria());
			pt.setString(4, producto.getCantXunidad());
			pt.setDouble(5, producto.getPrecioUnidad());
			pt.setInt(6, producto.getStock());
			pt.setInt(7, producto.getUnidPedido());
			pt.setInt(8, producto.getIdProducto());
			i=pt.executeUpdate();
			cn.close();
		}catch(Exception e ){
			i=0;
		}
		return i;
	}
	public ArrayList<Producto> LoadTableProducto(String Nombre){
		ArrayList<Producto> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call BUSCAR_PRODUCTOxNOMBRE (?)");
			statement.setString(1, Nombre);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Producto objProd=new Producto();
				objProd.setIdProducto(set.getInt("IDPRODUCTO"));
				objProd.setNameProducto(set.getString("NAMEPRODUCTO"));
				objProd.setNameCategoria(set.getNString("NAMECATEGORIA"));
				objProd.setNameProveedor(set.getNString("NAMEPROVEEDOR"));
				objProd.setCantXunidad(set.getString("CANTxUNIDAD"));
				objProd.setStock(set.getInt("UNIDADEXISTENCIA"));
				objProd.setPrecioUnidad(set.getDouble("PRECIOUNIDAD"));
				objProd.setUnidPedido(set.getInt("UNIDADPEDIDO"));
				List.add(objProd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA PRODUCTO");
		}
		return List;
	}
	public static int UpdateStockProducto(int IdProd, int cantidad){
		int respuesta=0;
		try {
			String consulta="UPDATE productos SET CANTIDAD =? where IDPRODUCTO = ?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setInt(1, cantidad);
			preparedStatement.setInt(2, IdProd);
			respuesta=preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return respuesta;
	}
	public int SearchStockProducto(int IdProd){
		int stockActual=0;
		try {
			String consulta="SELECT UNIDADEXISTENCIA FROM productos where IDPRODUCTO = ?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setInt(1, IdProd);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			stockActual=resultSet.getInt("UNIDADEXISTENCIA");
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return stockActual;
	}
	public ArrayList<Producto> LoadTableProductoStockMin(){
		ArrayList<Producto> List=new ArrayList<>();
		try {
			String consulta="SELECT DISTINCT P.IDPRODUCTO, P.NAMEPRODUCTO, P.UNIDADEXISTENCIA FROM productos P WHERE P.UNIDADEXISTENCIA<5";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement statement=cnx.prepareStatement(consulta);
			//statement.setInt(1, IdProd);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Producto objProd=new Producto();
				objProd.setIdProducto(set.getInt("IDPRODUCTO"));
				objProd.setNameProducto(set.getString("NAMEPRODUCTO"));
				objProd.setStock(set.getInt("UNIDADEXISTENCIA"));
				List.add(objProd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA PRODUCTO");
		}
		return List;
	}
	public ArrayList<Producto> LoadTableProductoStockMinXcod(int cod){
		ArrayList<Producto> List=new ArrayList<>();
		try {
			String consulta="SELECT DISTINCT P.IDPRODUCTO, P.NAMEPRODUCTO, P.UNIDADEXISTENCIA FROM productos P WHERE P.UNIDADEXISTENCIA<5 AND P.IDPRODUCTO=?";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement statement=cnx.prepareStatement(consulta);
			statement.setInt(1, cod);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Producto objProd=new Producto();
				objProd.setIdProducto(set.getInt("IDPRODUCTO"));
				objProd.setNameProducto(set.getString("NAMEPRODUCTO"));
				objProd.setStock(set.getInt("UNIDADEXISTENCIA"));
				List.add(objProd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA PRODUCTO");
		}
		return List;
	}
	
	/*
	 * SELECT DISTINCT P.IDPRODUCTO, P.NAMEPRODUCTO, P.UNIDADEXISTENCIA FROM productos P
    	WHERE P.UNIDADEXISTENCIA<5
	 */
}
