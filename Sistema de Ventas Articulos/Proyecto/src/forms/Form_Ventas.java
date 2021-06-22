package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

import beans.Cliente;
import dao.Cliente_dao;
import dao.Facturacion_dao;
import dao.Producto_dao;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.io.Console;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.ImageIcon;

public class Form_Ventas extends JInternalFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCodventa;
	private JPanel panelCliente;
	private JLabel lblNombre;
	private JTextField txtNombCliente;
	private JLabel lblDni;
	private JTextField txtDniCliente;
	private JLabel lblVentas;
	private JLabel lblFecha;
	private JPanel panelProducto;
	private JLabel lblCategoria;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnComprar;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblBuscarDni;
	private JTextField txtBuscarDni;
	private JButton btnBuscar;
	private JComboBox cboCategoria;
	private JLabel lblProducto;
	private JComboBox cboProducto;
	private JLabel lblPrecio;
	private JTextField txtPrecio;
	private JLabel lblCantidad;
	private JTextField txtCantidad;
	
	Cliente_dao objClienteDao=new Cliente_dao();
	Producto_dao objProductoDao=new Producto_dao();
	Facturacion_dao objFacturacionDao=new Facturacion_dao();
	static int ContFila=0;
	private JLabel lblImporteTotal;
	private JLabel lblimporteTotal;
	public static int codFac;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_Ventas frame = new Form_Ventas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form_Ventas() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Properties/Properties_16x16.png"));
		setTitle("Ventas");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 678, 845);
		
		lblCodventa = new JLabel("CodVenta");
		lblCodventa.setForeground(Color.BLACK);
		lblCodventa.setFont(new Font("Consolas", Font.BOLD, 20));
		lblCodventa.setBounds(12, 58, 115, 25);
		lblCodventa.setText(String.valueOf(Facturacion_dao.ShowCodFactura()+1));
		
		panelCliente = new JPanel();
		panelCliente.setForeground(Color.BLACK);
		panelCliente.setBounds(12, 96, 638, 176);
		panelCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DATOS CLIENTE", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		lblNombre = new JLabel("Nombres y Apellidos:");
		lblNombre.setBounds(18, 81, 144, 25);
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNombre.setForeground(Color.BLACK);
		
		txtNombCliente = new JTextField();
		txtNombCliente.setEditable(false);
		txtNombCliente.setBounds(180, 82, 446, 25);
		txtNombCliente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombCliente.setForeground(Color.BLACK);
		txtNombCliente.setColumns(10);
		
		lblDni = new JLabel("DNI:");
		lblDni.setBounds(18, 125, 35, 25);
		lblDni.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDni.setForeground(Color.BLACK);
		
		txtDniCliente = new JTextField();
		txtDniCliente.setEditable(false);
		txtDniCliente.setBounds(71, 126, 200, 25);
		txtDniCliente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDniCliente.setForeground(Color.BLACK);
		txtDniCliente.setColumns(10);
		getContentPane().setLayout(null);
		
		lblVentas = new JLabel("Ventas");
		lblVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentas.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblVentas.setForeground(Color.BLACK);
		lblVentas.setBounds(0, 13, 662, 32);
		getContentPane().add(lblVentas);
		getContentPane().add(lblCodventa);
		
		lblFecha = new JLabel();
		lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecha.setForeground(Color.BLACK);
		lblFecha.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblFecha.setBounds(456, 58, 194, 25);
		getContentPane().add(lblFecha);
		getContentPane().add(panelCliente);
		
		lblBuscarDni = new JLabel("Buscar DNI");
		lblBuscarDni.setBounds(18, 29, 116, 25);
		lblBuscarDni.setForeground(Color.BLACK);
		lblBuscarDni.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtBuscarDni = new JTextField();
		txtBuscarDni.setBounds(152, 30, 245, 25);
		txtBuscarDni.setForeground(Color.BLACK);
		txtBuscarDni.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBuscarDni.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(423, 18, 145, 45);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		panelCliente.setLayout(null);
		panelCliente.add(lblBuscarDni);
		panelCliente.add(txtBuscarDni);
		panelCliente.add(btnBuscar);
		panelCliente.add(lblDni);
		panelCliente.add(txtDniCliente);
		panelCliente.add(lblNombre);
		panelCliente.add(txtNombCliente);
		
		panelProducto = new JPanel();
		panelProducto.setForeground(Color.BLACK);
		panelProducto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PRODUCTO", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelProducto.setBounds(12, 285, 638, 418);
		getContentPane().add(panelProducto);
		
		lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(18, 34, 108, 25);
		lblCategoria.setForeground(Color.BLACK);
		lblCategoria.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		btnAgregar = new JButton("AGREGAR");
		btnAgregar.setIcon(new ImageIcon("../Proyecto/Iconos/Add/Add_24x24.png"));
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(361, 109, 170, 45);
		btnAgregar.setForeground(Color.BLACK);
		btnAgregar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setIcon(new ImageIcon("../Proyecto/Iconos/Delete/Delete_24x24.png"));
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(361, 164, 170, 45);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 222, 608, 183);
		
		table = new JTable();
		table.getTableHeader().setReorderingAllowed(false);		//evita el movimiento de las columnnas
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Producto", "Cantidad", "Precio", "Total"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.setForeground(Color.BLACK);
		scrollPane.setViewportView(table);
		
		cboCategoria = new JComboBox();
		cboCategoria.addActionListener(this);
		cboCategoria.setBounds(144, 36, 285, 22);
		
		lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(18, 77, 108, 25);
		lblProducto.setForeground(Color.BLACK);
		lblProducto.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		cboProducto = new JComboBox();
		cboProducto.addActionListener(this);
		cboProducto.setBounds(144, 79, 372, 22);
		
		lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(18, 120, 108, 25);
		lblPrecio.setForeground(Color.BLACK);
		lblPrecio.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(144, 122, 152, 22);
		txtPrecio.setEditable(false);
		txtPrecio.setColumns(10);
		lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(18, 163, 108, 25);
		lblCantidad.setForeground(Color.BLACK);
		lblCantidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(144, 165, 152, 22);
		txtCantidad.setColumns(10);
		panelProducto.setLayout(null);
		panelProducto.add(lblCategoria);
		panelProducto.add(cboCategoria);
		panelProducto.add(lblProducto);
		panelProducto.add(cboProducto);
		panelProducto.add(btnEliminar);
		panelProducto.add(lblCantidad);
		panelProducto.add(txtCantidad);
		panelProducto.add(lblPrecio);
		panelProducto.add(txtPrecio);
		panelProducto.add(btnAgregar);
		panelProducto.add(scrollPane);
		
		btnComprar = new JButton("COMPRAR");
		btnComprar.setIcon(new ImageIcon("../Proyecto/Iconos/Properties/Properties_24x24.png"));
		btnComprar.addActionListener(this);
		btnComprar.setBounds(246, 751, 165, 45);
		getContentPane().add(btnComprar);
		btnComprar.setForeground(Color.BLACK);
		btnComprar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblImporteTotal = new JLabel("Importe Total: S/.");
		lblImporteTotal.setForeground(Color.BLACK);
		lblImporteTotal.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblImporteTotal.setBounds(385, 717, 131, 25);
		getContentPane().add(lblImporteTotal);
		
		lblimporteTotal = new JLabel("");
		lblimporteTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblimporteTotal.setForeground(Color.BLACK);
		lblimporteTotal.setFont(new Font("Consolas", Font.BOLD, 20));
		lblimporteTotal.setBounds(528, 716, 93, 25);
		getContentPane().add(lblimporteTotal);
		
		try {
			deshabilitar_Elementos();
		} catch (Exception e) {
			System.out.println("ERROR: "+e.getMessage());
		}
		

		Date myDate = new Date();
		//DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss");
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		        while (true) {
		            try {
		                Thread.sleep(500);
		                lblFecha.setText(new SimpleDateFormat("dd-MM-yyyy").format(myDate));
		                //lblCodventa.setText(formateador.format(LocalDateTime.now()));
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		};
		Thread hilo = new Thread(runnable);
		hilo.start();
	}
	public void deshabilitar_Elementos(){
		cboProducto.setEnabled(false);
		cboCategoria.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnEliminar.setEnabled(false);
		txtPrecio.setEnabled(false);
		txtCantidad.setEnabled(false);
		btnComprar.setEnabled(false);
		limpiar();
	}
	public void habilitar_Elmentos(){
		cboProducto.setEnabled(true);
		cboCategoria.setEnabled(true);
		btnAgregar.setEnabled(true);
		btnEliminar.setEnabled(true);
		txtPrecio.setEnabled(true);
		txtCantidad.setEnabled(true);
		btnComprar.setEnabled(true);
		table.setEnabled(true);
		llenarCBOCategoria();
	}
	public void limpiar(){
		txtBuscarDni.setText("");
		txtNombCliente.setText("");
		txtDniCliente.setText("");
		txtPrecio.setText("");
		txtCantidad.setText("");
		lblimporteTotal.setText("");
		txtBuscarDni.requestFocus();
	}
	public void inicializarJComboBox(){
		cboCategoria.setSelectedIndex(0);
		cboProducto.setSelectedIndex(0);
		try
		{
			DefaultTableModel dt= (DefaultTableModel)table.getModel();
			for (int i = dt.getRowCount()-1; i>=0; i--) {
                dt.removeRow(i);
            }
		}
		catch(Exception e){
			System.out.println("ERROR AL ELIMINAR LOS REGISTROS DE LA TABLA "+e);
			e.getStackTrace();
		}
	}
	public int readCantidad(){
		int cant=0;
		try {
			cant =Integer.parseInt(txtCantidad.getText().trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cant;
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnComprar) {
			actionPerformedBtnComprar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnAgregar) {
			actionPerformedBtnAgregar(arg0);
		}
		if (arg0.getSource() == cboProducto) {
			actionPerformedCboProducto(arg0);
		}
		if (arg0.getSource() == cboCategoria) {
			actionPerformedCboCategoria(arg0);
		}
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		String Buscar=txtBuscarDni.getText().trim();
		try {
			if(Buscar.equals("")){
				JOptionPane.showMessageDialog(null, "INGRESE EL DNI DEL CLIENTE");
				return;
			}
			else
			{
				String Nombre =objClienteDao.SearchCliente(Buscar);
				if(Nombre !=""){
					txtNombCliente.setText(Nombre);
					txtDniCliente.setText(txtBuscarDni.getText().trim().toUpperCase());
					habilitar_Elmentos();
				}
				else
				{
					txtNombCliente.setText("");
					txtDniCliente.setText("");
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL OBTENER LA DATA DEL CLIENTE");;
		}
	}
	protected void actionPerformedCboCategoria(ActionEvent arg0) {
		llenarCBOProductos();
	}
	public void llenarCBOCategoria(){
		ArrayList<String> ListCbo=new ArrayList<String>();
		ListCbo=Producto_dao.FillComboboxCategoria();
		for(int i=0;i<ListCbo.size();i++){
			cboCategoria.addItem(ListCbo.get(i));
		}
	}
	public void llenarCBOProductos(){
		cboProducto.removeAllItems();
		ArrayList<String> ListCbo=new ArrayList<String>();
		ListCbo=Producto_dao.FillComboboxProducto(cboCategoria.getSelectedItem().toString());
		for(int i=0;i<ListCbo.size();i++){
			cboProducto.addItem(ListCbo.get(i));
		}
	}
	protected void actionPerformedCboProducto(ActionEvent arg0) {
		try
		{
			LlenarPrecio();
			txtCantidad.setText("");
		}
		catch(Exception e){
			System.out.println("ERROR AL CARGAR EL PRECIO DEL PRODUCTO (getSelectItem-Producto)");
			e.getStackTrace();
		}
	}
	public void LlenarPrecio()
	{
		try
		{
			double precio = 0;
			if(cboProducto.getSelectedItem().toString() != "" )
			{
				precio=Producto_dao.LoadPrecio(cboProducto.getSelectedItem().toString());
				txtPrecio.setText(String.valueOf(precio));
			}
		}
		catch(Exception e){
			System.out.println("ERROR AL CARGAR EL PRECIO DEL PRODUCTO "+e);
			e.getStackTrace();
		}	
				
	}
	//valiadar registros
	public boolean ExisteRegistroTabla(JTable tabla, int cod_prod, int col){
		boolean Existe=false;
		for(int i=0; i<tabla.getRowCount();i++){
			if(tabla.getValueAt(i,col).equals(cod_prod)){
				Existe=true;
			}
		}
		return Existe;
	}
	public void AñadirDetalle()
	{
		if(txtCantidad.getText().trim().equals("")){
			JOptionPane.showMessageDialog(null, "LA CANTIDAD NO DEBE ESTAR VACIA");
			return;
		}else{
			try {
				if(readCantidad()>0){
					int cod_Prod=objProductoDao.SearchCodProducto(cboProducto.getSelectedItem().toString());
					int StockInDB=objProductoDao.SearchStockProducto(cod_Prod);
					int cantidad=Integer.parseInt(txtCantidad.getText().trim());
					double Total=Double.parseDouble(txtPrecio.getText())*Integer.parseInt(txtCantidad.getText());
					DecimalFormat df = new DecimalFormat("#.##");
					if(StockInDB>=readCantidad()){
						DefaultTableModel dt= (DefaultTableModel)table.getModel();
						if(ExisteRegistroTabla(table, cod_Prod, 0)==false){
							dt.addRow(new Object[]{
									cod_Prod, cboProducto.getSelectedItem().toString(), 
									cantidad, txtPrecio.getText(), df.format(Total)
							});
						}
						else{
							JOptionPane.showMessageDialog(null, "EL PRODUCTO YA EXISTE, SOLO SE SUMARÁ LA NUEVA CANTIDAD CON LA ACTUAL \n Y SE MODIFICARÁ EL TOTAL RESPECTIVO");
							try {
								for(int i=0; i<table.getRowCount();i++){
									if(table.getValueAt(i,0).equals(cod_Prod)){
										if(StockInDB>=(Integer.parseInt(table.getValueAt(i, 2).toString())+cantidad)){
											dt.setValueAt(Integer.parseInt(table.getValueAt(i, 2).toString())+cantidad, i, 2);
											dt.setValueAt(df.format(Integer.parseInt(table.getValueAt(i, 2).toString())*Double.parseDouble(table.getValueAt(i, 3).toString())), i, 4);
										}else {
											JOptionPane.showMessageDialog(null, "EL STOCK EN ALMACEN NO ES SUFICIENTE PARA LA CANTIDAD SOLICITADA \n POR ENDE NO SE MODIFICARÁ EL PRODUCTO YA AGREGADO EN LA TABLA");
										}
									}
								}
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "ERROR EN EL CATCH DEL ACTUALIZAR CANTIDAD Y TOTAL DEL PRODUCTO EN EL JTable"+e.getMessage());
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "LA CANTIDAD SOLICITADA SUPERA EL STOCK EN ALMACEN");
					}
					lblimporteTotal.setText(String.valueOf(importeTotalCompra()));
				}
				else if(readCantidad()==0){
					JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD SUPERIOR A CERO");
				}
				else{
					JOptionPane.showMessageDialog(null, "INGRESE UNA CANTIDAD VALIDA");
				}
				
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROR AL INGRESAR UN PRODCUTO EN EL CARRITO DE COMPRAS" +e.getMessage());
			}
		}
		
	}
	public void EliminarDetalle(){
		try
		{
			int numRows = table.getSelectedRows().length;
			DefaultTableModel dt= (DefaultTableModel)table.getModel();
			for(int i=0; i<numRows ; i++ ) {

			    dt.removeRow(table.getSelectedRow());
			}
			lblimporteTotal.setText(String.valueOf(importeTotalCompra()));
		}
		catch(Exception e){
			System.out.println("ERROR AL ELIMINAR EL REGISTRO SELECCIONADO");
			e.getStackTrace();
		}	
	}
	public double importeTotalCompra(){
		double total=0;
		try {
			DefaultTableModel dt= (DefaultTableModel)table.getModel();
			for(int i=0; i<table.getRowCount();i++){
				total+=Double.parseDouble(dt.getValueAt(i, 4).toString());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return total;
	}
	public int readCodCliente(){
		int cod=0;
		try {
			cod=objClienteDao.SearchCodCliente(txtDniCliente.getText().trim());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return cod;
	}
	public int readCodEmpleado(){
		int cod=0;
		try {
			cod=MDI_Ventas.getCodEmp();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cod;
	}
	public void GenerarCompra(){
		try {
			if(table.getRowCount()>0){
				int codCompra=Integer.parseInt(lblCodventa.getText());
				codFac=codCompra;
				double descuento=0;
				objFacturacionDao.SaveDataFactura_Cabecera(codCompra, readCodCliente(), readCodEmpleado());
				try {
					DefaultTableModel dt= (DefaultTableModel)table.getModel();
					for(int i=0; i<dt.getRowCount() ; i++ ) {
						objFacturacionDao.SaveDataFactura_Detalle(codCompra, objProductoDao.SearchCodProducto(dt.getValueAt(i, 1).toString()), Double.parseDouble(dt.getValueAt(i, 3).toString()), Integer.parseInt(dt.getValueAt(i, 2).toString()), descuento);
					}
					Form_Print frmEx=new Form_Print();
					frmEx.setVisible(true);
					JOptionPane.showMessageDialog(null, "GRACIAS POR SU COMPRA !!");

				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR EL DETALLE DE LA FACTURA "+e.getMessage());
				}
				deshabilitar_Elementos();
				inicializarJComboBox();
				lblCodventa.setText(String.valueOf(Facturacion_dao.ShowCodFactura()+1));
			}
			else{
				JOptionPane.showMessageDialog(null, "Agregue productos a la tabla para proseguir con la compra");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL GUARDAR LA FACTURA "+e.getMessage());
		}
	}
	protected void actionPerformedBtnAgregar(ActionEvent arg0) {
		AñadirDetalle();
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		EliminarDetalle();
	}
	protected void actionPerformedBtnComprar(ActionEvent arg0) {
		GenerarCompra();
	}
}
