package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import beans.Cliente;
import beans.Empleado;
import dao.Cliente_dao;
import dao.Empleado_dao;
import util.Conex_BD;

import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Form_RegistrarCliente extends JInternalFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblNombreApellido;
	private JLabel lblTelfono;
	private JLabel lblDireccin;
	private JLabel lblPais;
	private JTextField txtNombreApellido;
	private JTextField txtDireccion;
	private JTextField txtFono;
	private JButton btnCrear;
	private JScrollPane scrollPane;
	private JTable tblCliente;
	private DefaultTableModel model;
	private JLabel lblClientes;
	private JButton btnActualizar;
	private JComboBox cboPais;
	
	
	Cliente_dao objClienteDao=new Cliente_dao();
	private JTextField txtBuscar;
	private JLabel lblBuscarPorNombre;
	private JButton btnBuscar;
	private JLabel lblCdigo;
	private JTextField txtIdCliente;
	private JTextField txtDni;
	private JLabel lblDni;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_RegistrarCliente frame = new Form_RegistrarCliente();
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
	public Form_RegistrarCliente() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/User/User_24x24.png"));
		setTitle("Registro de Cliente");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 1012, 705);
		
		lblNombreApellido = new JLabel("Nombres y Apellidos:");
		lblNombreApellido.setBounds(12, 168, 194, 25);
		lblNombreApellido.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNombreApellido.setForeground(Color.BLACK);
		
		lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(12, 257, 125, 25);
		lblTelfono.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTelfono.setForeground(Color.BLACK);
		
		lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(12, 213, 125, 25);
		lblDireccin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDireccin.setForeground(Color.BLACK);
		
		lblPais = new JLabel("Pais");
		lblPais.setBounds(420, 257, 71, 25);
		lblPais.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPais.setForeground(Color.BLACK);
		
		txtNombreApellido = new JTextField();
		txtNombreApellido.setBounds(218, 169, 565, 25);
		txtNombreApellido.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombreApellido.setForeground(Color.BLACK);
		txtNombreApellido.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(155, 214, 562, 25);
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDireccion.setForeground(Color.BLACK);
		txtDireccion.setColumns(10);
		
		txtFono = new JTextField();
		txtFono.setBounds(155, 258, 154, 25);
		txtFono.setFont(new Font("Arial", Font.PLAIN, 15));
		txtFono.setForeground(Color.BLACK);
		txtFono.setColumns(10);
		
		btnCrear = new JButton("Crear");
		btnCrear.setIcon(new ImageIcon("../Proyecto/Iconos/Save/Save_24x24.png"));
		btnCrear.setBounds(824, 168, 160, 45);
		btnCrear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCrear.addActionListener(this);
		btnCrear.setForeground(Color.BLACK);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 353, 970, 305);
		
		tblCliente = new JTable(model);
		tblCliente.addMouseListener(this);
		tblCliente.getTableHeader().setReorderingAllowed(false) ;		//evita el movimiento de las columnnas
		tblCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres y Apellidos", "Direcci\u00F3n", "Pais", "Tel\u00E9fono", "DNI"
			}
		));
		tblCliente.getColumnModel().getColumn(1).setPreferredWidth(300);
		tblCliente.getColumnModel().getColumn(2).setPreferredWidth(300);
		tblCliente.getColumnModel().getColumn(3).setPreferredWidth(150);
		tblCliente.getColumnModel().getColumn(4).setPreferredWidth(150);
		tblCliente.getColumnModel().getColumn(5).setPreferredWidth(150);
		tblCliente.setForeground(Color.BLACK);
		scrollPane.setViewportView(tblCliente);
		
		lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(0, 13, 996, 32);
		lblClientes.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setForeground(Color.BLACK);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("../Proyecto/Iconos/Refresh/Refresh_24x24.png"));
		btnActualizar.setBounds(822, 257, 160, 45);
		btnActualizar.addActionListener(this);
		btnActualizar.setForeground(Color.BLACK);
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		cboPais = new JComboBox();
		cboPais.setBounds(503, 259, 214, 22);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(278, 115, 335, 25);
		txtBuscar.setForeground(Color.BLACK);
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBuscar.setColumns(10);
		
		lblBuscarPorNombre = new JLabel("Buscar por DNI");
		lblBuscarPorNombre.setBounds(12, 114, 248, 25);
		lblBuscarPorNombre.setForeground(Color.BLACK);
		lblBuscarPorNombre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.setBounds(644, 107, 139, 37);
		btnBuscar.addActionListener(this);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(752, 63, 99, 25);
		lblCdigo.setForeground(Color.BLACK);
		lblCdigo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtIdCliente = new JTextField();
		txtIdCliente.setBounds(869, 64, 115, 25);
		txtIdCliente.setEditable(false);
		txtIdCliente.setForeground(Color.BLACK);
		txtIdCliente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIdCliente.setColumns(10);
		txtIdCliente.setText(String.valueOf(Cliente_dao.ShowCodCliente()+1));
		
		txtDni = new JTextField();
		txtDni.setBounds(155, 310, 154, 25);
		txtDni.setForeground(Color.BLACK);
		txtDni.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDni.setColumns(10);
		
		lblDni = new JLabel("DNI:");
		lblDni.setBounds(12, 309, 125, 25);
		lblDni.setForeground(Color.BLACK);
		lblDni.setFont(new Font("Times New Roman", Font.BOLD, 16));
		getContentPane().setLayout(null);
		getContentPane().add(lblClientes);
		getContentPane().add(btnCrear);
		getContentPane().add(btnActualizar);
		getContentPane().add(scrollPane);
		getContentPane().add(lblBuscarPorNombre);
		getContentPane().add(txtBuscar);
		getContentPane().add(btnBuscar);
		getContentPane().add(lblNombreApellido);
		getContentPane().add(txtNombreApellido);
		getContentPane().add(lblDireccin);
		getContentPane().add(txtDireccion);
		getContentPane().add(lblTelfono);
		getContentPane().add(txtFono);
		getContentPane().add(lblPais);
		getContentPane().add(cboPais);
		getContentPane().add(lblCdigo);
		getContentPane().add(txtIdCliente);
		getContentPane().add(txtDni);
		getContentPane().add(lblDni);
		muestra();
		llenarCBOPais();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(arg0);
		}
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrearCliente(arg0);
		}
	}
	protected void actionPerformedBtnCrearCliente(ActionEvent arg0) {
		SaveData();
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		UpdateData();
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		muestraBuscar();
	}
	public int readCod(){
		return Integer.parseInt(txtIdCliente.getText().trim().toUpperCase());
	}
	public String readName(){
		return txtNombreApellido.getText().trim().toUpperCase();
	}
	public String readDireccion(){
		return txtDireccion.getText().trim().toUpperCase();
	}
	public String readFono(){
		return txtFono.getText().trim().toUpperCase();
	}
	public int readCodPais(){
		int CodPais=Cliente_dao.SearchCodPais(cboPais.getSelectedItem().toString());
		return CodPais;
	}
	public String readDni(){
		return txtDni.getText().trim().toUpperCase();
	}
	public void llenarCBOPais(){
		ArrayList<String> ListCbo=new ArrayList<String>();
		ListCbo=Cliente_dao.FillComboboxPais();
		for(int i=0;i<ListCbo.size();i++){
			cboPais.addItem(ListCbo.get(i));
		}
	}
	private void muestra(){
		DefaultTableModel dt= (DefaultTableModel)tblCliente.getModel();
		dt.setRowCount(0);
		for (Cliente obj:objClienteDao.LoadTableCliente()) {
			dt.addRow(new Object[]{
					obj.getIdCliente(),
					obj.getNombCliente(),
					obj.getDireCliente(),
					obj.getNombPais(),
					obj.getFonoCliente(),
					obj.getDNI()
			});
		}
	}
	private void muestraBuscar(){
		DefaultTableModel dt= (DefaultTableModel)tblCliente.getModel();
		dt.setRowCount(0);
		for (Cliente obj:objClienteDao.LoadTableClienteBuscar(txtBuscar.getText().trim().toUpperCase())) {
			dt.addRow(new Object[]{
					obj.getIdCliente(),
					obj.getNombCliente(),
					obj.getDireCliente(),
					obj.getNombPais(),
					obj.getFonoCliente(),
					obj.getDNI()
			});
		}
	}
	void SaveData(){
		try {
			objClienteDao.SaveDataCliente(readName(), readDireccion(), readCodPais(), readFono(), readDni());
			this.btnCrear.setEnabled(false);
			txtIdCliente.setText("");
			txtIdCliente.setText(String.valueOf(Cliente_dao.ShowCodCliente()+1));
			JOptionPane.showMessageDialog(null,"Se registrarón correctamente los Dartos !!!");
			muestra();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al ingresar los Datos en la Base de Datos !!!");
		}
	}
	void UpdateData() {
		if (readCod()!=-1) {
			Cliente cliente =new Cliente(readCod(), readCodPais(), readName(), readDireccion(), readFono(), "",readDni());
			objClienteDao.UpdateCiente(cliente);
			muestra();
			btnCrear.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null,"Error al Actualizar Datos en la Base de Datos !!!");
		}
	}
	void selecciona() {
		try {
			txtIdCliente.setText(tblCliente.getValueAt(tblCliente.getSelectedRow(), 0).toString());
			txtNombreApellido.setText(tblCliente.getValueAt(tblCliente.getSelectedRow(), 1).toString());
			txtDireccion.setText(tblCliente.getValueAt(tblCliente.getSelectedRow(), 2).toString());
			cboPais.setSelectedItem(tblCliente.getValueAt(tblCliente.getSelectedRow(), 3).toString());
			txtFono.setText(tblCliente.getValueAt(tblCliente.getSelectedRow(), 4).toString());
			txtDni.setText(tblCliente.getValueAt(tblCliente.getSelectedRow(), 5).toString());
			this.btnCrear.setEnabled(false);
		}catch(Exception e) {
			
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblCliente) {
			mouseClickedTblCliente(arg0);
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
	protected void mouseClickedTblCliente(MouseEvent arg0) {
		selecciona();
	}
}
