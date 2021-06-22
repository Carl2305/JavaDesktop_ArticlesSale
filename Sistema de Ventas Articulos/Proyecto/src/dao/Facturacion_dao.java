package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import beans.Cliente;
import beans.Factura_Deta;
import beans.Producto;
import util.Conex_BD;

public class Facturacion_dao {
	public static int ShowCodFactura(){
		int cod=0;
		try {
			String consulta="SELECT MAX(IDPEDIDO) AS id FROM pedidoscabecera";
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
	public int SaveDataFactura_Cabecera(int ID_PED, int ID_CLI, int ID_EMPLE){
		int Rpta=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call REGISTRAR_PEDIDO_CABECERA (?,?,?) ");
			pt.setInt(1, ID_PED);
			pt.setInt(2, ID_CLI);
			pt.setInt(3, ID_EMPLE);
			Rpta=pt.executeUpdate();
			cn.close();
		} catch(Exception e ){
			Rpta=0;
		}
		return Rpta;
	}
	public int SaveDataFactura_Detalle(int ID_PED, int ID_PROD, double PRECIO, int CANT, double DESCUENTO){
		int Rpta=0;
		try{
			Connection cn=Conex_BD.Conectar();
			CallableStatement pt= cn.prepareCall("call REGISTRAR_PEDIDO_DETALLE (?,?,?,?,?) ");
			pt.setInt(1, ID_PED);
			pt.setInt(2, ID_PROD);
			pt.setDouble(3, PRECIO);
			pt.setInt(4, CANT);
			pt.setDouble(5, DESCUENTO);
			Rpta=pt.executeUpdate();
			cn.close();
		} catch(Exception e ){
			Rpta=0;
		}
		return Rpta;
	}
	public static Date SearchDateFactura(int numFac){
		Date Fecha=null;
		try {
			String consulta="SELECT FECHAPEDIDO FROM pedidoscabecera WHERE IDPEDIDO=?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setInt(1, numFac);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			Fecha=resultSet.getDate("FECHAPEDIDO");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER LA FECHA DE LA FACTURA "+e);
		}
		return Fecha;
	}
	public static String SearchClienteFactura(int numFac){
		String nom="";
		try {
			String consulta="SELECT C.NAMECLIENTE FROM pedidoscabecera PC JOIN clientes C ON PC.IDCLIENTE=C.IDCLIENTES WHERE PC.IDPEDIDO=?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setInt(1, numFac);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			nom=resultSet.getString("NAMECLIENTE");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER EL DUEÑO DE LA FACTURA "+e);
		}
		return nom;
	}
	public static String SearchDniClienteFactura(int numFac){
		String nom="";
		try {
			String consulta="SELECT C.DNI FROM pedidoscabecera PC JOIN clientes C ON PC.IDCLIENTE=C.IDCLIENTES WHERE PC.IDPEDIDO=?";
			Connection connection=Conex_BD.Conectar();
			PreparedStatement preparedStatement=connection.prepareStatement(consulta);
			preparedStatement.setInt(1, numFac);
			ResultSet resultSet=preparedStatement.executeQuery();
			resultSet.next();
			nom=resultSet.getString("DNI");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER EL DNI DEL DUEÑO DE LA FACTURA "+e);
		}
		return nom;
	}
	public ArrayList<Factura_Deta> LoadPrintTableFact(int codFac){
		ArrayList<Factura_Deta> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call BUSCAR_FACTURA_PRINT (?) ");
			statement.setInt(1, codFac);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Factura_Deta objfacd=new Factura_Deta();
				objfacd.setNomProd(set.getString("NAMEPRODUCTO"));
				objfacd.setCantProd(set.getInt("CANTIDAD"));
				objfacd.setPrecioProd(set.getDouble("PRECIOUNIDAD"));
				objfacd.setDescuento(set.getDouble("DESCUENTO"));
				objfacd.setTotal(set.getDouble("TOTAL"));
				List.add(objfacd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA FACTURA "+e);
		}
		return List;
	}
	public ArrayList<Factura_Deta> LoadTableFactCabecera(){
		ArrayList<Factura_Deta> List=new ArrayList<>();
		try {
			String consulta="SELECT PC.IDPEDIDO, PC.FECHAPEDIDO, C.NAMECLIENTE, SUM(PD.PRECIOUNIDAD*PD.CANTIDAD) AS IMPORTE_TOTAL FROM pedidoscabecera PC "+ 
								"JOIN pedidosdetalle PD ON PC.IDPEDIDO=PD.IDPEDIDO JOIN clientes C ON PC.IDCLIENTE=C.IDCLIENTES GROUP BY (IDPEDIDO)";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement statement=cnx.prepareStatement(consulta);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Factura_Deta objfacd=new Factura_Deta();
				objfacd.setIdPedido(set.getInt("IDPEDIDO"));
				objfacd.setFechaPedido(set.getDate("FECHAPEDIDO"));
				objfacd.setNameCliente(set.getString("NAMECLIENTE"));
				objfacd.setTotal(set.getDouble("IMPORTE_TOTAL"));
				List.add(objfacd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA FACTURA CABECERA "+e);
		}
		return List;
	}
	public ArrayList<Factura_Deta> LoadTableFactCabeceraSearchTotal_500(){
		ArrayList<Factura_Deta> List=new ArrayList<>();
		try {
			String consulta="SELECT PC.IDPEDIDO, PC.FECHAPEDIDO, C.NAMECLIENTE, SUM(PD.PRECIOUNIDAD*PD.CANTIDAD) AS IMPORTE_TOTAL FROM pedidoscabecera PC "+ 
								"JOIN pedidosdetalle PD ON PC.IDPEDIDO=PD.IDPEDIDO JOIN clientes C ON PC.IDCLIENTE=C.IDCLIENTES GROUP BY (IDPEDIDO) HAVING IMPORTE_TOTAL>500";
			Connection cnx=Conex_BD.Conectar();
			PreparedStatement statement=cnx.prepareStatement(consulta);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Factura_Deta objfacd=new Factura_Deta();
				objfacd.setIdPedido(set.getInt("IDPEDIDO"));
				objfacd.setFechaPedido(set.getDate("FECHAPEDIDO"));
				objfacd.setNameCliente(set.getString("NAMECLIENTE"));
				objfacd.setTotal(set.getDouble("IMPORTE_TOTAL"));
				List.add(objfacd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA FACTURA CABECERA CUANDO EL TOTAL ES MAYOR A S/. 500 "+e);
		}
		return List;
	}
	public ArrayList<Factura_Deta> LoadTableFactCabeceraSearchFecha(String DATE_DESDE, String DATE_HASTA){
		ArrayList<Factura_Deta> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call BUSCAR_FACTURA_x_FECHA (?,?) ");
			statement.setString(1, DATE_DESDE);
			statement.setString(2, DATE_HASTA);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Factura_Deta objfacd=new Factura_Deta();
				objfacd.setIdPedido(set.getInt("IDPEDIDO"));
				objfacd.setFechaPedido(set.getDate("FECHAPEDIDO"));
				objfacd.setNameCliente(set.getString("NAMECLIENTE"));
				objfacd.setTotal(set.getDouble("IMPORTE_TOTAL"));
				List.add(objfacd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA FACTURA CABECERA CON FECHA "+e);
		}
		return List;
	}
	public ArrayList<Factura_Deta> LoadTableFactDetalleCod(int COD){
		ArrayList<Factura_Deta> List=new ArrayList<>();
		try {
			Connection cnx=Conex_BD.Conectar();
			CallableStatement statement=cnx.prepareCall("call BUSCAR_FACTURA_x_COD (?) ");
			statement.setInt(1, COD);
			ResultSet set=statement.executeQuery();
			while(set.next()){
				Factura_Deta objfacd=new Factura_Deta();
				objfacd.setIdPedido(set.getInt("IDPEDIDO"));
				objfacd.setIdProducto(set.getInt("IDPRODUCTO"));
				objfacd.setNomProd(set.getString("NAMEPRODUCTO"));
				objfacd.setPrecioProd(set.getDouble("PRECIOUNIDAD"));
				objfacd.setCantProd(set.getInt("CANTIDAD"));
				objfacd.setTotal(set.getDouble("IMPORTE"));
				List.add(objfacd);
			}
			cnx.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LA DATA DE LA TABLA FACTURA DETALLE "+e);
		}
		return List;
	}
	/*
	 CREATE PROCEDURE BUSCAR_FACTURA_x_COD (IN COD INT)
BEGIN
SELECT PC.IDPEDIDO, P.IDPRODUCTO, P.NAMEPRODUCTO,PD.PRECIOUNIDAD, PD.CANTIDAD, (PD.PRECIOUNIDAD*PD.CANTIDAD) AS IMPORTE FROM pedidoscabecera PC 
		JOIN pedidosdetalle PD ON PC.IDPEDIDO=PD.IDPEDIDO
        JOIN productos P ON PD.IDPRODUCTO=P.IDPRODUCTO
    WHERE PC.IDPEDIDO=COD
	ORDER BY (IDPEDIDO);
END ;
	 */
}
