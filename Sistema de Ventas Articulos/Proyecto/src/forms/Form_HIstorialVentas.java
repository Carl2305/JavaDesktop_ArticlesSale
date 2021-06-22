package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import beans.Cliente;
import beans.Factura_Cabe;
import beans.Factura_Deta;
import dao.Facturacion_dao;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class Form_HIstorialVentas extends JInternalFrame implements ActionListener, MouseListener {
	private JLabel lblHistorialDeVentas;
	private JLabel lblPorFecha;
	private JLabel lblDesde;
	private JLabel lblHasta;
	private JButton btnBuscar;
	private JScrollPane scrollPane;
	private JTable table;
	private JDateChooser dateChooserFechaDesde;
	private JDateChooser dateChooserFechaHasta;
	private JComboBox cboFiltro;

	Form_ConsultarVenta frmConsVenta=new Form_ConsultarVenta();
	Facturacion_dao objFac=new Facturacion_dao();
	public int CodFac;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_HIstorialVentas frame = new Form_HIstorialVentas();
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
	public Form_HIstorialVentas() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Archive/Archive_16x16.png"));
		setTitle("Historial de Ventas");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 729, 620);
		
		lblHistorialDeVentas = new JLabel("Historial de Ventas");
		lblHistorialDeVentas.setBounds(0, 13, 713, 32);
		lblHistorialDeVentas.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDeVentas.setFont(new Font("Sitka Text", Font.BOLD, 22));
		lblHistorialDeVentas.setForeground(Color.BLACK);
		
		lblPorFecha = new JLabel("Filtro");
		lblPorFecha.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblPorFecha.setForeground(Color.BLACK);
		lblPorFecha.setBounds(12, 63, 72, 16);
		
		lblDesde = new JLabel("Desde:");
		lblDesde.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDesde.setForeground(Color.BLACK);
		lblDesde.setBounds(12, 101, 48, 16);
		
		lblHasta = new JLabel("Hasta:");
		lblHasta.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblHasta.setForeground(Color.BLACK);
		lblHasta.setBounds(310, 101, 48, 16);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBuscar.setForeground(Color.BLACK);
		btnBuscar.setBounds(564, 95, 137, 27);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 140, 689, 431);
		getContentPane().setLayout(null);
		getContentPane().add(lblHistorialDeVentas);
		getContentPane().add(lblPorFecha);
		getContentPane().add(lblDesde);
		
		dateChooserFechaDesde = new JDateChooser();
		dateChooserFechaDesde.setDateFormatString("dd-MM-yyyy");
		dateChooserFechaDesde.setForeground(Color.BLACK);
		dateChooserFechaDesde.setBounds(71, 95, 160, 22);
		getContentPane().add(dateChooserFechaDesde);
		getContentPane().add(lblHasta);
		
		dateChooserFechaHasta = new JDateChooser();
		dateChooserFechaHasta.setForeground(Color.BLACK);
		dateChooserFechaHasta.setDateFormatString("dd-MM-yyyy");
		dateChooserFechaHasta.setBounds(364, 95, 160, 22);
		getContentPane().add(dateChooserFechaHasta);
		getContentPane().add(btnBuscar);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00D3DIGO", "FECHA", "CLIENTE", "IMPORTE TOTAL"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		cboFiltro = new JComboBox();
		cboFiltro.addActionListener(this);
		cboFiltro.setModel(new DefaultComboBoxModel(new String[] {"FECHA", "IMPORTES TOTALES MAYORES A S/. 500"}));
		cboFiltro.setBounds(71, 61, 291, 22);
		getContentPane().add(cboFiltro);
		
		DeshabilitarComponentes();
		muestra();
	}
	public void DeshabilitarComponentes(){
		lblDesde.setEnabled(false);
		dateChooserFechaDesde.setEnabled(false);
		lblHasta.setEnabled(false);
		dateChooserFechaHasta.setEnabled(false);
		btnBuscar.setEnabled(false);
	}
	public void HabilitarComponentes(){
		lblDesde.setEnabled(true);
		dateChooserFechaDesde.setEnabled(true);
		lblHasta.setEnabled(true);
		dateChooserFechaHasta.setEnabled(true);
		btnBuscar.setEnabled(true);
	}
	String fecha_Desde()
	{
		SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
		String date = dcn.format(dateChooserFechaDesde.getDate() );
		return date;
	}
	
	String fecha_Hasta()
	{
		SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
		String date = dcn.format(dateChooserFechaHasta.getDate() );
		return date;
	}
	private void muestraBuscar_Fecha(){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Factura_Deta obj:objFac.LoadTableFactCabeceraSearchFecha(fecha_Desde(),fecha_Hasta())) {
			dt.addRow(new Object[]{
					obj.getIdPedido(),
					obj.getFechaPedido(),
					obj.getNameCliente(),
					obj.getTotal()
			});
		}
	}
	private void muestraBuscar_500(){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Factura_Deta obj:objFac.LoadTableFactCabeceraSearchTotal_500()) {
			dt.addRow(new Object[]{
					obj.getIdPedido(),
					obj.getFechaPedido(),
					obj.getNameCliente(),
					obj.getTotal()
			});
		}
	}
	private void muestra(){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Factura_Deta obj:objFac.LoadTableFactCabecera()) {
			dt.addRow(new Object[]{
					obj.getIdPedido(),
					obj.getFechaPedido(),
					obj.getNameCliente(),
					obj.getTotal()
			});
		}
	}
	void selecciona() {
		try {
			CodFac=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
			//JOptionPane.showMessageDialog(null, ""+CodFac);
			frmConsVenta.txtCodFactura.setText("");
			if (frmConsVenta.isIcon()) {
				MDI_Ventas.mntmConsultarVenta.isEnabled();
		        frmConsVenta.txtCodFactura.setText(String.valueOf(CodFac));
				JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
				try {
					frmConsVenta.setIcon(false);
			        frmConsVenta.txtCodFactura.setText(String.valueOf(CodFac));
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					MDI_Ventas.desktopPane.add(frmConsVenta);
					//centra el JInternalFrame 
			        Dimension desktopSize = MDI_Ventas.desktopPane.getSize();
			        Dimension FrameSize = frmConsVenta.getSize();
			        frmConsVenta.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
			        frmConsVenta.setVisible(true);
			        frmConsVenta.txtCodFactura.setText(String.valueOf(CodFac));
				} catch (Exception ex) {
					MDI_Ventas.desktopPane.add(frmConsVenta);
					frmConsVenta.txtCodFactura.setText(String.valueOf(CodFac));
				}
			}
		}
		catch(Exception e){
			
		}
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
		}
		if (arg0.getSource() == cboFiltro) {
			actionPerformedCboFiltro(arg0);
		}
	}
	protected void actionPerformedCboFiltro(ActionEvent arg0) {
		if(cboFiltro.getSelectedIndex()==0){
			muestra();
			HabilitarComponentes();
		}
		else if(cboFiltro.getSelectedIndex()==1){
			DeshabilitarComponentes();
			muestraBuscar_500();
		}
		else{
			DeshabilitarComponentes();
			muestra();
			JOptionPane.showMessageDialog(null, "SELECCIONE UN TIPO DE FILTRO");
		}
	}
	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		try {
			if(dateChooserFechaDesde.getDate().toString()==""){
				JOptionPane.showMessageDialog(null, "INGRESE EL LA FECHA DESDE PARA BUSCAR ");
			}
			else{
				if(dateChooserFechaHasta.getDate().toString()==""){
					JOptionPane.showMessageDialog(null, "INGRESE EL LA FECHA HASTA PARA BUSCAR ");
				}
				else{
					muestraBuscar_Fecha();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR AL BUSCAR LAS FACTURAS ENTRE EL INTERVALO DE FECHA SOLICITADO "+e.getMessage());
		}
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			mouseClickedTable(arg0);
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
	protected void mouseClickedTable(MouseEvent arg0) {
		selecciona();
	}
}
