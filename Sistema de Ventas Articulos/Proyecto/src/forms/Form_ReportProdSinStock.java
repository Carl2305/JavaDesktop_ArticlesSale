package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import beans.Producto;
import dao.Producto_dao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Form_ReportProdSinStock extends JInternalFrame implements ActionListener {
	private JLabel lblReporteArtculosSin;
	private JLabel lblCdigoDeProducto;
	private JTextField txtCodProd;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTable table;
	
	Producto_dao objProductoDao=new Producto_dao();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_ReportProdSinStock frame = new Form_ReportProdSinStock();
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
	public Form_ReportProdSinStock() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Archive/Archive_16x16.png"));
		setClosable(true);
		setIconifiable(true);
		setTitle("Reporte de Porductos Sin Stock");
		setBounds(100, 100, 498, 541);
		
		lblReporteArtculosSin = new JLabel("Reporte Art\u00EDculos Con Stock M\u00EDnimo");
		lblReporteArtculosSin.setBounds(0, 13, 482, 32);
		lblReporteArtculosSin.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteArtculosSin.setForeground(Color.BLACK);
		lblReporteArtculosSin.setFont(new Font("Sitka Text", Font.BOLD, 22));
		
		lblCdigoDeProducto = new JLabel("C\u00F3digo de Producto:");
		lblCdigoDeProducto.setBounds(12, 68, 143, 25);
		lblCdigoDeProducto.setForeground(Color.BLACK);
		lblCdigoDeProducto.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtCodProd = new JTextField();
		txtCodProd.setBounds(160, 69, 152, 25);
		txtCodProd.setForeground(Color.BLACK);
		txtCodProd.setFont(new Font("Arial", Font.PLAIN, 15));
		txtCodProd.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(330, 63, 140, 33);
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 114, 458, 378);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "NOMBRE", "STOCK"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(85);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		getContentPane().setLayout(null);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		getContentPane().add(lblCdigoDeProducto);
		getContentPane().add(txtCodProd);
		getContentPane().add(btnBuscar);
		getContentPane().add(lblReporteArtculosSin);
				
		muestra();
	}
	public void muestra(){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Producto obj:objProductoDao.LoadTableProductoStockMin()) {
			dt.addRow(new Object[]{
					obj.getIdProducto(),
					obj.getNameProducto(),
					obj.getStock()
			});
		}
	}
	private void muestraSearch(int cod){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Producto obj:objProductoDao.LoadTableProductoStockMinXcod(cod)) {
			dt.addRow(new Object[]{
					obj.getIdProducto(),
					obj.getNameProducto(),
					obj.getStock()
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
			muestraSearch(Integer.parseInt(txtCodProd.getText().trim()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "REVISE EL CÓDIGO INGRESADO !! \nEL CÓDIGO QUE INGRESÓ NO COINCIDE CON PRODUCTOS QUE TENGAN UN STOCK MÍNIMO");
		}
	}
}
