package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import beans.Empleado;
import beans.Producto;
import dao.Empleado_dao;
import dao.Producto_dao;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Form_RegistrarProducto extends JInternalFrame implements ActionListener, MouseListener {
	private JLabel lblCdigo;
	private JLabel lblProductos;
	private JTextField txtCodigo;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblPrecioUnidad;
	private JTextField txtPrecioUnidad;
	private JLabel lblPrecio;
	private JTextField txtStock;
	private JScrollPane scrollPane;
	private JTable tblProducto;
	private JButton btnGuardar;
	private JButton btnActualizar;
	private JLabel lblBuscarPorNombre;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	private JLabel lblCategora;
	private JComboBox cboCategoria;
	private JLabel lblProveedor;
	private JComboBox cboProveedor;
	private JLabel lblCantidadXUnidad;
	private JTextField txtCantXunidad;
	private JLabel lblUnidadesPedidas;
	private JTextField txtPedidos;
	
	
	Producto objProducto=new Producto();
	Producto_dao objProductoDao=new Producto_dao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_RegistrarProducto frame = new Form_RegistrarProducto();
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
	public Form_RegistrarProducto() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Text Document/Text Document_16x16.png"));
		setTitle("Modificar Productos");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1000, 699);
		
		lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(12, 126, 70, 25);
		lblCdigo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCdigo.setForeground(Color.BLACK);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(100, 127, 116, 25);
		txtCodigo.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCodigo.setForeground(Color.BLACK);
		txtCodigo.setColumns(10);
		txtCodigo.setText(String.valueOf(Producto_dao.ShowCodProducto()+1));
		
		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 170, 78, 25);
		lblNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNombre.setForeground(Color.BLACK);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(108, 171, 467, 25);
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setColumns(10);
		
		lblPrecioUnidad = new JLabel("Precio x Unidad:");
		lblPrecioUnidad.setBounds(12, 257, 125, 25);
		lblPrecioUnidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPrecioUnidad.setForeground(Color.BLACK);
		
		txtPrecioUnidad = new JTextField();
		txtPrecioUnidad.setBounds(155, 258, 152, 25);
		txtPrecioUnidad.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPrecioUnidad.setForeground(Color.BLACK);
		txtPrecioUnidad.setColumns(10);
		
		lblPrecio = new JLabel("Stock Actual:");
		lblPrecio.setBounds(12, 301, 103, 25);
		lblPrecio.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPrecio.setForeground(Color.BLACK);
		
		txtStock = new JTextField();
		txtStock.setBounds(133, 302, 148, 25);
		txtStock.setFont(new Font("Arial", Font.PLAIN, 15));
		txtStock.setForeground(Color.BLACK);
		txtStock.setColumns(10);
		
		lblProductos = new JLabel("Productos");
		lblProductos.setBounds(0, 13, 796, 32);
		lblProductos.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblProductos.setForeground(Color.BLACK);
		lblProductos.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 339, 960, 308);
		
		tblProducto = new JTable();
		tblProducto.addMouseListener(this);
		tblProducto.setForeground(Color.BLACK);
		tblProducto.getTableHeader().setReorderingAllowed(false) ;		//evita el movimiento de las columnnas
		tblProducto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Categor\u00EDa", "Proveedor", "Cant. / Unidad", "Stock Actual", "Prec. / Unidad", "Unid. Pedidas"
			}
		));
		tblProducto.getColumnModel().getColumn(1).setPreferredWidth(280);
		tblProducto.getColumnModel().getColumn(2).setPreferredWidth(170);
		tblProducto.getColumnModel().getColumn(3).setPreferredWidth(260);
		tblProducto.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblProducto.getColumnModel().getColumn(5).setPreferredWidth(100);
		tblProducto.getColumnModel().getColumn(6).setPreferredWidth(100);
		tblProducto.getColumnModel().getColumn(7).setPreferredWidth(100);
		scrollPane.setViewportView(tblProducto);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("../Proyecto/Iconos/Save/Save_24x24.png"));
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(789, 126, 150, 45);
		btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnGuardar.setForeground(Color.BLACK);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("../Proyecto/Iconos/Refresh/Refresh_24x24.png"));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(789, 184, 150, 45);
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnActualizar.setForeground(Color.BLACK);
		
		lblBuscarPorNombre = new JLabel("Buscar por Nombre: ");
		lblBuscarPorNombre.setBounds(12, 74, 166, 25);
		lblBuscarPorNombre.setForeground(Color.BLACK);
		lblBuscarPorNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(208, 75, 367, 25);
		txtBuscar.setForeground(Color.BLACK);
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(593, 63, 140, 45);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblCategora = new JLabel("Categor\u00EDa:");
		lblCategora.setBounds(254, 127, 100, 25);
		lblCategora.setForeground(Color.BLACK);
		lblCategora.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		cboCategoria = new JComboBox();
		cboCategoria.addActionListener(this);
		cboCategoria.setBounds(372, 129, 203, 22);
		
		lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(12, 214, 91, 25);
		lblProveedor.setForeground(Color.BLACK);
		lblProveedor.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		cboProveedor = new JComboBox();
		cboProveedor.setBounds(121, 216, 454, 22);
		
		lblCantidadXUnidad = new JLabel("Cantidad x Unidad:");
		lblCantidadXUnidad.setBounds(325, 257, 150, 25);
		lblCantidadXUnidad.setForeground(Color.BLACK);
		lblCantidadXUnidad.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtCantXunidad = new JTextField();
		txtCantXunidad.setBounds(493, 258, 225, 25);
		txtCantXunidad.setForeground(Color.BLACK);
		txtCantXunidad.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCantXunidad.setColumns(10);
		
		lblUnidadesPedidas = new JLabel("Unidades Pedidas:");
		lblUnidadesPedidas.setBounds(325, 301, 150, 25);
		lblUnidadesPedidas.setForeground(Color.BLACK);
		lblUnidadesPedidas.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtPedidos = new JTextField();
		txtPedidos.setBounds(493, 302, 152, 25);
		txtPedidos.setForeground(Color.BLACK);
		txtPedidos.setFont(new Font("Arial", Font.PLAIN, 15));
		txtPedidos.setColumns(10);
		getContentPane().setLayout(null);
		getContentPane().add(lblCantidadXUnidad);
		getContentPane().add(txtCantXunidad);
		getContentPane().add(lblUnidadesPedidas);
		getContentPane().add(txtPedidos);
		getContentPane().add(lblProductos);
		getContentPane().add(lblNombre);
		getContentPane().add(txtNombre);
		getContentPane().add(lblPrecioUnidad);
		getContentPane().add(txtPrecioUnidad);
		getContentPane().add(lblPrecio);
		getContentPane().add(txtStock);
		getContentPane().add(btnGuardar);
		getContentPane().add(btnActualizar);
		getContentPane().add(scrollPane);
		getContentPane().add(lblProveedor);
		getContentPane().add(cboProveedor);
		getContentPane().add(lblCdigo);
		getContentPane().add(txtCodigo);
		getContentPane().add(lblCategora);
		getContentPane().add(cboCategoria);
		getContentPane().add(lblBuscarPorNombre);
		getContentPane().add(txtBuscar);
		getContentPane().add(btnBuscar);
		muestra();
		llenarCBOCategoria();
	}
	public int readCodigo(){
		return Integer.parseInt(txtCodigo.getText().trim());
	}
	public int readCategoria(){
		int cod=Producto_dao.SearchCodCategoria(cboCategoria.getSelectedItem().toString());
		return cod;
	}
	public String readNombre(){
		return txtNombre.getText().trim();
	}
	public int readProveedor(){
		int cod=Producto_dao.SearchCodProveedor(cboProveedor.getSelectedItem().toString());
		return cod;
	}
	public double readPrecio(){
		return Double.parseDouble(txtPrecioUnidad.getText().trim());
	}
	public String readCantXunidad(){
		return txtCantXunidad.getText().trim();
	}
	public int readStock(){
		return Integer.parseInt(txtStock.getText().trim());
	}
	public int readUnidadPedida(){
		return Integer.parseInt(txtPedidos.getText().trim());
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblProducto) {
			mouseClickedTblProducto(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblProducto(MouseEvent arg0) {
	selecciona();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == cboCategoria) {
			actionPerformedCboCategoria(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(arg0);
		}
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		muestraBuscar();
	}
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		SaveData();
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		UpdateData();
	}
	protected void actionPerformedCboCategoria(ActionEvent arg0) {
		llenarCBOProveedor();
	}
	public void llenarCBOCategoria(){
		ArrayList<String> ListCbo=new ArrayList<String>();
		ListCbo=Producto_dao.FillComboboxCategoria();
		for(int i=0;i<ListCbo.size();i++){
			cboCategoria.addItem(ListCbo.get(i));
		}
	}
	public void llenarCBOProveedor(){
		cboProveedor.removeAllItems();
		ArrayList<String> ListCbo=new ArrayList<String>();
		ListCbo=Producto_dao.FillComboboxProveedor(cboCategoria.getSelectedItem().toString());
		for(int i=0;i<ListCbo.size();i++){
			cboProveedor.addItem(ListCbo.get(i));
		}
	}
	void selecciona() {
		try {
			txtCodigo.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 0).toString());
			txtNombre.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 1).toString());
			cboCategoria.setSelectedItem(tblProducto.getValueAt(tblProducto.getSelectedRow(), 2).toString());
			cboProveedor.setSelectedItem(tblProducto.getValueAt(tblProducto.getSelectedRow(), 3).toString());
			txtCantXunidad.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 4).toString());
			txtStock.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 5).toString());
			txtPrecioUnidad.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 6).toString());
			txtPedidos.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 7).toString());
			this.btnGuardar.setEnabled(false);
		}catch(Exception e) {
			
		}
	}
	private void muestra(){
		DefaultTableModel dt= (DefaultTableModel)tblProducto.getModel();
		dt.setRowCount(0);
		for (Producto obj:objProductoDao.LoadTableProducto()) {
			dt.addRow(new Object[]{
					obj.getIdProducto(),
					obj.getNameProducto(),
					obj.getNameCategoria(),
					obj.getNameProveedor(),
					obj.getCantXunidad(),
					obj.getStock(),
					obj.getPrecioUnidad(),
					obj.getUnidPedido()
			});
		}
	}
	void SaveData(){
		try {
			objProductoDao.SaveDataProducto(readNombre(), readProveedor(), readCategoria(), 
						readCantXunidad(), readPrecio(), readStock(), readUnidadPedida());
			JOptionPane.showMessageDialog(null,""+readNombre()+" "+ readProveedor()+" "+  readCategoria()+" "+ 
					readCantXunidad()+" "+ readPrecio()+" "+ readStock()+" "+ readUnidadPedida());
			this.btnGuardar.setEnabled(false);
			txtCodigo.setText("");
			txtCodigo.setText(String.valueOf(Producto_dao.ShowCodProducto()+1));
			JOptionPane.showMessageDialog(null,"Se registrarón correctamente los Dartos !!!");
			muestra();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al ingresar los Datos en la Base de Datos !!!");
		}
	}
	void UpdateData() {
		if (readCodigo()!=-1) {
			Producto prod = new Producto(readCategoria(), readCodigo(), readProveedor(), readStock(), readUnidadPedida(), readPrecio(), readCantXunidad(), readNombre());
			objProductoDao.UpdateProducto(prod);
			muestra();
			btnGuardar.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null,"Error al Actualizar Datos en la Base de Datos !!!");
		}
	}
	private void muestraBuscar(){
		DefaultTableModel dt= (DefaultTableModel)tblProducto.getModel();
		dt.setRowCount(0);
		for (Producto obj:objProductoDao.LoadTableProducto(txtBuscar.getText().trim())) {
			dt.addRow(new Object[]{
					obj.getIdProducto(),
					obj.getNameProducto(),
					obj.getNameCategoria(),
					obj.getNameProveedor(),
					obj.getCantXunidad(),
					obj.getStock(),
					obj.getPrecioUnidad(),
					obj.getUnidPedido()
			});
		}
	}
}
