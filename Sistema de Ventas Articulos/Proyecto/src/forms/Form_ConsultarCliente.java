package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import beans.Cliente;
import dao.Cliente_dao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Form_ConsultarCliente extends JInternalFrame implements ActionListener {
	private JLabel lblConsultaCliente;
	private JScrollPane scrollPane;
	private JLabel lblDniCliente;
	private JTextField txtDniCliente;
	private JButton btnBuscar;
	private JTable tblCliente;
	
	Cliente_dao objClienteDao=new Cliente_dao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_ConsultarCliente frame = new Form_ConsultarCliente();
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
	public Form_ConsultarCliente() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Text Document/Text Document_16x16.png"));
		setIconifiable(true);
		setTitle("Consultar Cliente");
		setClosable(true);
		setBounds(100, 100, 953, 495);
		
		lblConsultaCliente = new JLabel("Consulta Cliente");
		lblConsultaCliente.setBounds(0, 13, 784, 32);
		lblConsultaCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaCliente.setForeground(Color.BLACK);
		lblConsultaCliente.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		lblDniCliente = new JLabel("DNI Cliente:");
		lblDniCliente.setBounds(50, 68, 87, 25);
		lblDniCliente.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDniCliente.setForeground(Color.BLACK);
		
		txtDniCliente = new JTextField();
		txtDniCliente.setBounds(153, 69, 234, 25);
		txtDniCliente.setFont(new Font("Arial", Font.PLAIN, 15));
		txtDniCliente.setForeground(Color.BLACK);
		txtDniCliente.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(486, 62, 120, 35);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBuscar.setForeground(Color.BLACK);
		getContentPane().setLayout(null);
		getContentPane().add(lblConsultaCliente);
		getContentPane().add(lblDniCliente);
		getContentPane().add(txtDniCliente);
		getContentPane().add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 115, 913, 331);
		
		tblCliente = new JTable();
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
		getContentPane().add(scrollPane);
		muestra();
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		muestraBuscar();
	}
	private void muestraBuscar(){
		DefaultTableModel dt= (DefaultTableModel)tblCliente.getModel();
		dt.setRowCount(0);
		for (Cliente obj:objClienteDao.LoadTableClienteBuscar(txtDniCliente.getText().trim().toUpperCase())) {
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
}
