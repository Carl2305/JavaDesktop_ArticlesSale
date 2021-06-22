package forms;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import dao.Facturacion_dao;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import beans.Factura_Deta;
import beans.Producto;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Panel_Fact_Print extends JPanel implements Printable{
	private JLabel lblFacturaN;
	private JLabel lblFecha;
	private JLabel lblFechabd;
	private JLabel lblNumfacbd;
	private JLabel lblCliente;
	private JLabel lblDniCliente;
	private JLabel lblClientebd;
	private JLabel lblDniclientebd;
	private JLabel lblImporteTotal;
	private JLabel lblTotalcompra;
	private JLabel lblImg;
	private JLabel lblRuc;
	
	Facturacion_dao objFacDao=new Facturacion_dao();
	private JScrollPane scrollPane;
	private JTable table;
	/**
	 * Create the panel.
	 */
	public Panel_Fact_Print() {
		lblFacturaN = new JLabel("FACTURA N\u00B0");
		lblFacturaN.setBounds(225, 27, 94, 19);
		lblFacturaN.setFont(new Font("Consolas", Font.BOLD, 15));
		lblFacturaN.setForeground(Color.BLACK);
		lblFacturaN.setHorizontalAlignment(SwingConstants.TRAILING);
		
		lblFecha = new JLabel("FECHA:");
		lblFecha.setBounds(12, 128, 48, 19);
		lblFecha.setFont(new Font("Consolas", Font.BOLD, 15));
		lblFecha.setForeground(Color.BLACK);
		
		lblFechabd = new JLabel("fecha_BD");
		lblFechabd.setBounds(78, 128, 152, 19);
		lblFechabd.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblFechabd.setForeground(Color.BLACK);
		lblFechabd.setText(new SimpleDateFormat("dd-MM-yyyy").format(Facturacion_dao.SearchDateFactura(Form_Ventas.codFac)));
		
		lblNumfacbd = new JLabel("numfac_BD");
		lblNumfacbd.setBounds(337, 27, 72, 19);
		lblNumfacbd.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblNumfacbd.setForeground(Color.BLACK);
		lblNumfacbd.setText(String.valueOf(Form_Ventas.codFac));
		
		lblCliente = new JLabel("CLIENTE:");
		lblCliente.setBounds(12, 165, 64, 19);
		lblCliente.setFont(new Font("Consolas", Font.BOLD, 15));
		lblCliente.setForeground(Color.BLACK);
		
		lblDniCliente = new JLabel("DNI CLIENTE:");
		lblDniCliente.setBounds(12, 202, 96, 19);
		lblDniCliente.setFont(new Font("Consolas", Font.BOLD, 15));
		lblDniCliente.setForeground(Color.BLACK);
		
		lblClientebd = new JLabel("cliente_BD");
		lblClientebd.setBounds(94, 165, 315, 19);
		lblClientebd.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblClientebd.setForeground(Color.BLACK);
		lblClientebd.setText(Facturacion_dao.SearchClienteFactura(Form_Ventas.codFac));
		
		lblDniclientebd = new JLabel("dnicliente_BD");
		lblDniclientebd.setBounds(126, 202, 148, 19);
		lblDniclientebd.setFont(new Font("Consolas", Font.PLAIN, 15));
		lblDniclientebd.setForeground(Color.BLACK);
		lblDniclientebd.setText(Facturacion_dao.SearchDniClienteFactura(Form_Ventas.codFac));
		
		lblImporteTotal = new JLabel("IMPORTE TOTAL:");
		lblImporteTotal.setBounds(12, 239, 112, 19);
		lblImporteTotal.setForeground(Color.BLACK);
		lblImporteTotal.setFont(new Font("Consolas", Font.BOLD, 15));
		
		lblTotalcompra = new JLabel("totalcompra");
		lblTotalcompra.setBounds(142, 239, 88, 19);
		lblTotalcompra.setForeground(Color.BLACK);
		lblTotalcompra.setFont(new Font("Consolas", Font.PLAIN, 15));
		//lblTotalcompra.setText(String.valueOf(importeTotalCompra()));

		lblImg = new JLabel("");
		lblImg.setBounds(12, 13, 218, 80);
		
		ImageIcon imagen= new ImageIcon("D:\\Users\\carlos\\Downloads\\logo.jpg");
        Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_DEFAULT));
        lblImg.setIcon(icono);
		
		setLayout(null);
		add(lblFacturaN);
		add(lblFecha);
		add(lblFechabd);
		add(lblNumfacbd);
		add(lblCliente);
		add(lblDniCliente);
		add(lblClientebd);
		add(lblDniclientebd);
		add(lblImporteTotal);
		add(lblTotalcompra);
		add(lblImg);
		
		lblRuc = new JLabel("RUC: 10186670469");
		lblRuc.setHorizontalAlignment(SwingConstants.CENTER);
		lblRuc.setFont(new Font("Consolas", Font.BOLD, 18));
		lblRuc.setForeground(Color.BLACK);
		lblRuc.setBounds(242, 59, 167, 16);
		add(lblRuc);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 291, 466, 396);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PRODUCTO", "CANT.", "PRECIO", "DESC.", "TOTAL"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(280);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		muestra();
	}
	private void muestra(){
		DefaultTableModel dt= (DefaultTableModel)table.getModel();
		dt.setRowCount(0);
		for (Factura_Deta obj:objFacDao.LoadPrintTableFact(Form_Ventas.codFac)) {
			dt.addRow(new Object[]{
					obj.getNomProd(),
					obj.getCantProd(),
					obj.getPrecioProd(),
					obj.getDescuento(),
					obj.getTotal()
			});
		}
		importeTotalCompra();
	}
	public void importeTotalCompra(){
		double total=0;
		try {
			DefaultTableModel dt= (DefaultTableModel)table.getModel();
			for(int i=0; i<table.getRowCount();i++){
				total+=Double.parseDouble(dt.getValueAt(i, 4).toString());
			}
			lblTotalcompra.setText(String.valueOf(total));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if(pageIndex==0){
			Graphics2D g=(Graphics2D) graphics;
			g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
			printAll(g);
			return PAGE_EXISTS;
		}
		else{
			return NO_SUCH_PAGE;
		}
	}
}
