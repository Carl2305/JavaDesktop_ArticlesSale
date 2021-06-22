package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import beans.Factura_Deta;
import dao.Facturacion_dao;

import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Form_ConsultarVenta extends JInternalFrame implements ActionListener {
	private JLabel lblCdigoDeVenta;
	public JTextField txtCodFactura;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblConsultaVenta;
	
	Facturacion_dao objFac=new Facturacion_dao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_ConsultarVenta frame = new Form_ConsultarVenta();
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
	public Form_ConsultarVenta() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Text Document/Text Document_16x16.png"));
		setTitle("Consultar Venta");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 635, 425);
		
		lblCdigoDeVenta = new JLabel("C\u00F3digo de Venta:");
		lblCdigoDeVenta.setBounds(12, 63, 130, 25);
		lblCdigoDeVenta.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblCdigoDeVenta.setForeground(Color.BLACK);
		
		txtCodFactura = new JTextField();
		txtCodFactura.setBounds(160, 64, 152, 25);
		txtCodFactura.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCodFactura.setForeground(Color.BLACK);
		txtCodFactura.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(384, 64, 140, 33);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBuscar.setForeground(Color.BLACK);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 124, 595, 260);
		
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Cod. Venta", "Cod. Producto", "Nombre", "Precio", "Cantidad", "Importe"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		getContentPane().setLayout(null);
		
		lblConsultaVenta = new JLabel("Consulta Venta");
		lblConsultaVenta.setBounds(0, 13, 619, 32);
		lblConsultaVenta.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblConsultaVenta.setForeground(Color.BLACK);
		lblConsultaVenta.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblConsultaVenta);
		getContentPane().add(lblCdigoDeVenta);
		getContentPane().add(txtCodFactura);
		getContentPane().add(btnBuscar);
		getContentPane().add(scrollPane);

	}
	public int getCodigo(){
		int cod=0;
		try {
			cod=Integer.parseInt(txtCodFactura.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "INGRESE EL CODIGO DE LA VENTA PARA REALIZAR LA BUSQUEDA");
		}
		return cod;
	}
	private void muestra(){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Factura_Deta obj:objFac.LoadTableFactDetalleCod(getCodigo())) {
			dt.addRow(new Object[]{
					obj.getIdPedido(),
					obj.getIdProducto(),
					obj.getNomProd(),
					obj.getPrecioProd(),
					obj.getCantProd(),
					obj.getTotal()
			});
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		try {
			if(txtCodFactura.getText().trim()==""){
				JOptionPane.showMessageDialog(null, "INGRESE EL CÓDIGO DE LA VENTA");
				txtCodFactura.requestFocus();
			}
			else{
				muestra();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
