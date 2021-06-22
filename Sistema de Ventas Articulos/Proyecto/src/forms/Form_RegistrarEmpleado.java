package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import beans.Empleado;
import dao.Empleado_dao;

import java.awt.event.ActionListener;
import java.nio.channels.ReadPendingException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Form_RegistrarEmpleado extends JInternalFrame implements ActionListener, MouseListener {
	private JLabel lblApellidos;
	private JLabel label_2;
	private JLabel lblDireccion;
	private JLabel lblCargo;
	private JLabel label_5;
	private JButton btnCrear;
	private JTextField txtNombEmpleado;
	private JTextField txtApeEmpleado;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JScrollPane scrollPane;
	private JTable tblEmpleado;
	private JLabel lblCdigo;
	private JTextField txtIdEmpleado;
	private JComboBox cboCargo;
	private JLabel lblEmpleados;
	private JButton btnActualizar;
	private JButton btnDeshabilitar;
	
	Empleado_dao objEmpleDao=new Empleado_dao();
	Empleado objempledao=new Empleado();
	private JLabel lblEstado;
	private JTextField txtEstado;
	private JLabel lblBuscarPorApellido;
	private JTextField txtBuscar;
	private JButton btnBuscar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_RegistrarEmpleado frame = new Form_RegistrarEmpleado();
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
	public Form_RegistrarEmpleado() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/User/User_24x24.png"));
		setTitle("Mantenimiento Empleado");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 1012, 687);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(526, 162, 125, 25);
		lblApellidos.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblApellidos.setForeground(Color.BLACK);
		
		label_2 = new JLabel("Tel\u00E9fono:");
		label_2.setBounds(526, 206, 125, 25);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_2.setForeground(Color.BLACK);
		
		lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setBounds(12, 250, 125, 25);
		lblDireccion.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDireccion.setForeground(Color.BLACK);
		
		lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(12, 206, 125, 25);
		lblCargo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCargo.setForeground(Color.BLACK);
		
		label_5 = new JLabel("Nombre:");
		label_5.setBounds(12, 162, 125, 25);
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 16));
		label_5.setForeground(Color.BLACK);
		
		btnCrear = new JButton("Crear");
		btnCrear.setIcon(new ImageIcon("../Proyecto/Iconos/Save/Save_24x24.png"));
		btnCrear.setBounds(155, 294, 140, 45);
		btnCrear.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnCrear.addActionListener(this);
		btnCrear.setForeground(Color.BLACK);
		
		txtNombEmpleado = new JTextField();
		txtNombEmpleado.setBounds(155, 163, 313, 25);
		txtNombEmpleado.setFont(new Font("Arial", Font.PLAIN, 15));
		txtNombEmpleado.setForeground(Color.BLACK);
		txtNombEmpleado.setColumns(10);
		
		txtApeEmpleado = new JTextField();
		txtApeEmpleado.setBounds(669, 163, 313, 25);
		txtApeEmpleado.setFont(new Font("Arial", Font.PLAIN, 15));
		txtApeEmpleado.setForeground(Color.BLACK);
		txtApeEmpleado.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(669, 207, 220, 25);
		txtTelefono.setFont(new Font("Arial", Font.PLAIN, 15));
		txtTelefono.setForeground(Color.BLACK);
		txtTelefono.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(155, 251, 514, 25);
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDireccion.setForeground(Color.BLACK);
		txtDireccion.setColumns(10);
		
		lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(12, 118, 125, 25);
		lblCdigo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCdigo.setForeground(Color.BLACK);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.setBounds(155, 119, 164, 25);
		txtIdEmpleado.setEditable(false);
		txtIdEmpleado.setFont(new Font("Arial", Font.PLAIN, 15));
		txtIdEmpleado.setForeground(Color.BLACK);
		txtIdEmpleado.setColumns(10);
		txtIdEmpleado.setText(String.valueOf(Empleado_dao.ShowCodEmple()+1));
		
		lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setBounds(0, 13, 996, 32);
		lblEmpleados.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblEmpleados.setForeground(Color.BLACK);
		lblEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		
		cboCargo = new JComboBox<>();
		cboCargo.setBounds(155, 207, 313, 25);
		cboCargo.setFont(new Font("Arial", Font.PLAIN, 15));
		cboCargo.setForeground(Color.BLACK);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 357, 970, 278);
		
		tblEmpleado = new JTable();
		tblEmpleado.addMouseListener(this);
		tblEmpleado.setForeground(Color.BLACK);
		scrollPane.setViewportView(tblEmpleado);
		tblEmpleado.getTableHeader().setReorderingAllowed(false) ;
		tblEmpleado.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombre", "Apellidos", "Cargo", "Tel\u00E9fono", "Direcci\u00F3n", "Estado"
			}
		));
		tblEmpleado.getColumnModel().getColumn(1).setPreferredWidth(220);
		tblEmpleado.getColumnModel().getColumn(2).setPreferredWidth(220);
		tblEmpleado.getColumnModel().getColumn(3).setPreferredWidth(270);
		tblEmpleado.getColumnModel().getColumn(4).setPreferredWidth(120);
		tblEmpleado.getColumnModel().getColumn(5).setPreferredWidth(400);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("../Proyecto/Iconos/Refresh/Refresh_24x24.png"));
		btnActualizar.setBounds(351, 294, 150, 45);
		btnActualizar.addActionListener(this);
		btnActualizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnActualizar.setForeground(Color.BLACK);
		
		btnDeshabilitar = new JButton("Deshabilitar");
		btnDeshabilitar.setIcon(new ImageIcon("../Proyecto/Iconos/Remove/Remove_24x24.png"));
		btnDeshabilitar.setBounds(551, 294, 170, 45);
		btnDeshabilitar.addActionListener(this);
		btnDeshabilitar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnDeshabilitar.setForeground(Color.BLACK);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(526, 123, 125, 25);
		lblEstado.setForeground(Color.BLACK);
		lblEstado.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtEstado = new JTextField();
		txtEstado.setBounds(669, 120, 136, 25);
		txtEstado.setEditable(false);
		txtEstado.setForeground(Color.BLACK);
		txtEstado.setFont(new Font("Arial", Font.PLAIN, 15));
		txtEstado.setColumns(10);
		
		lblBuscarPorApellido = new JLabel("Buscar por apellido");
		lblBuscarPorApellido.setBounds(254, 63, 185, 25);
		lblBuscarPorApellido.setForeground(Color.BLACK);
		lblBuscarPorApellido.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(457, 64, 313, 25);
		txtBuscar.setForeground(Color.BLACK);
		txtBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
		txtBuscar.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(788, 55, 140, 45);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		getContentPane().setLayout(null);
		getContentPane().add(lblEmpleados);
		getContentPane().add(lblCdigo);
		getContentPane().add(txtIdEmpleado);
		getContentPane().add(label_5);
		getContentPane().add(txtNombEmpleado);
		getContentPane().add(lblApellidos);
		getContentPane().add(txtApeEmpleado);
		getContentPane().add(lblCargo);
		getContentPane().add(cboCargo);
		getContentPane().add(lblDireccion);
		getContentPane().add(txtDireccion);
		getContentPane().add(label_2);
		getContentPane().add(txtTelefono);
		getContentPane().add(btnCrear);
		getContentPane().add(btnActualizar);
		getContentPane().add(btnDeshabilitar);
		getContentPane().add(scrollPane);
		getContentPane().add(lblEstado);
		getContentPane().add(txtEstado);
		getContentPane().add(lblBuscarPorApellido);
		getContentPane().add(txtBuscar);
		getContentPane().add(btnBuscar);
		muestra();
		llenarCBOCargo();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == btnDeshabilitar) {
			actionPerformedBtnDeshabilitar(arg0);
		}
		if (arg0.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(arg0);
		}
		if (arg0.getSource() == btnCrear) {
			actionPerformedBtnCrear(arg0);
		}
	}
	protected void actionPerformedBtnCrear(ActionEvent arg0) {
		SaveData();
	}
	protected void actionPerformedBtnActualizar(ActionEvent arg0) {
		UpdateData();
	}
	protected void actionPerformedBtnDeshabilitar(ActionEvent arg0) {
		UpdateStadoEmpleado();
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		muestraBuscar();
		Limpiar();
	}
	public int readCod(){
		return Integer.parseInt(txtIdEmpleado.getText().trim().toUpperCase());
	}
	public String readName(){
		return txtNombEmpleado.getText().trim().toUpperCase();
	}
	public String readApellido(){
		return txtApeEmpleado.getText().trim().toUpperCase();
	}
	public String readDireccion(){
		return txtDireccion.getText().trim().toUpperCase();
	}
	public String readFono(){
		return txtTelefono.getText().trim().toUpperCase();
	}
	public int readCodCargo(){
		int CodCargo=Empleado_dao.SearchCodCargo(cboCargo.getSelectedItem().toString().toUpperCase());
		return CodCargo;
	}
	public int readEstado(){
		int estado=1;
		if(txtEstado.getText().trim()=="ACTIVO"){
			estado=1;
		}else if (txtEstado.getText().trim()=="INACTIVO") {
			estado=0;
		}
		return estado;
	}
	public int readCodUser(){
		int cod=Empleado_dao.SearchCodUser(GeneUser());
		return cod;
	}
	public String GeneUser(){
		String User="G"+txtApeEmpleado.getText().trim().toUpperCase().substring(0,2)+
				txtNombEmpleado.getText().trim().toUpperCase().substring(0,2)+
				txtTelefono.getText().trim().substring(txtTelefono.getText().trim().toUpperCase().length()-2,txtTelefono.getText().trim().toUpperCase().length());
		
		return User;
	}
	private void muestra(){
		DefaultTableModel dt= (DefaultTableModel)tblEmpleado.getModel();
		dt.setRowCount(0);
		for (Empleado obj:objEmpleDao.LoadTableEmployee()) {
			dt.addRow(new Object[]{
					obj.getIdEmpleado(),
					obj.getNameEmpleado(),
					obj.getApeEmple(),
					obj.getCargo(),
					obj.getFono(),
					obj.getDireccion(),
					obj.getEstado()
			});
		}
	}
	public void llenarCBOCargo(){
		ArrayList<String> ListCbo=new ArrayList<String>();
		ListCbo=Empleado_dao.FillComboboxCargo();
		for(int i=0;i<ListCbo.size();i++){
			cboCargo.addItem(ListCbo.get(i));
		}
	}
	void SaveData(){
		try {
			objEmpleDao.SaveDataUser(GeneUser());
			objEmpleDao.SaveDataEmpleado(readName(), readApellido(), readFono(), readDireccion(), readCodUser(), readCodCargo());
			this.btnCrear.setEnabled(false);
			txtIdEmpleado.setText("");
			txtIdEmpleado.setText(String.valueOf(Empleado_dao.ShowCodEmple()+1));
			JOptionPane.showMessageDialog(null, "Su usuario es: "+GeneUser());
			JOptionPane.showMessageDialog(null,"Se registrarón correctamente los Dartos !!!");
			muestra();
			Limpiar();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Error al ingresar los Datos en la Base de Datos !!!");
		}
	}
	void UpdateData() {
		if (readCod()!=-1) {
			Empleado emple = new Empleado(readCod(), readCodCargo(), objempledao.getIdUser(), readName(), readApellido(), readDireccion(), cboCargo.getSelectedItem().toString(), readFono(), String.valueOf(readEstado()));
			objEmpleDao.UpdateEmple(emple);
			muestra();
			Limpiar();
			btnCrear.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null,"Error al Actualizar Datos en la Base de Datos !!!");
		}
	}
	void UpdateStadoEmpleado() {
		if (readCod()!=-1) {
			objEmpleDao.UpdateEstadoEmple(0, readCod());
			muestra();
			btnCrear.setEnabled(true);
		}else {
			JOptionPane.showMessageDialog(null,"Debe seleccionar un registro");
		}
	}
	void selecciona() {
		try {
			txtIdEmpleado.setText(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 0).toString());
			txtNombEmpleado.setText(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 1).toString());
			txtApeEmpleado.setText(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 2).toString());
			cboCargo.setSelectedItem(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 3).toString());
			txtTelefono.setText(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 4).toString());
			txtDireccion.setText(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 5).toString());
			txtEstado.setText(tblEmpleado.getValueAt(tblEmpleado.getSelectedRow(), 6).toString());
			this.btnCrear.setEnabled(false);
		}catch(Exception e) {
			
		}
	}
	private void muestraBuscar(){
		DefaultTableModel dt= (DefaultTableModel)tblEmpleado.getModel();
		dt.setRowCount(0);
		for (Empleado obj:objEmpleDao.LoadTableEmployee(txtBuscar.getText().trim())) {
			dt.addRow(new Object[]{
					obj.getIdEmpleado(),
					obj.getNameEmpleado(),
					obj.getApeEmple(),
					obj.getCargo(),
					obj.getFono(),
					obj.getDireccion(),
					obj.getEstado()
			});
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblEmpleado) {
			mouseClickedTblEmpleado(arg0);
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
	protected void mouseClickedTblEmpleado(MouseEvent arg0) {
		selecciona();
	}
	void Limpiar(){
		txtBuscar.setText("");
		txtNombEmpleado.setText("");
		txtApeEmpleado.setText("");
		txtEstado.setText("");
		cboCargo.setSelectedItem("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtNombEmpleado.requestFocus();
	}
}
