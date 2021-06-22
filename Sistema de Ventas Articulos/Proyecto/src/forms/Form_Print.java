package forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Form_Print extends JDialog implements ActionListener {

	private JPanel contentPanel;
	private JButton btnImprimir;
	Panel_Fact_Print p=new Panel_Fact_Print();
	private JButton btnCerrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Form_Print dialog = new Form_Print();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form_Print() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("../Proyecto/Iconos/Print/Print_16x16.png"));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Print");
		setBounds(100, 100, 531, 806);
		getContentPane().setLayout(null);
		p.setBackground(Color.WHITE);
		
		p.setBounds(12, 13, 490, 700);
		getContentPane().add(p);
		
		btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.setIcon(new ImageIcon("../Proyecto/Iconos/Print/Print_24x24.png"));
		btnImprimir.addActionListener(this);
		btnImprimir.setBounds(180, 726, 115, 25);
		getContentPane().add(btnImprimir);
		
		btnCerrar = new JButton("CERRAR");
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(415, 726, 87, 25);
		getContentPane().add(btnCerrar);
		setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(arg0);
		}
		if (arg0.getSource() == btnImprimir) {
			actionPerformedBtnImprimir(arg0);
		}
	}
	protected void actionPerformedBtnImprimir(ActionEvent arg0) {
		PrinterJob job= PrinterJob.getPrinterJob();
		job.setPrintable(p);
		if(job.printDialog()){
			try {
				job.print();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "la impresion se cancelo");
		}
	}
	protected void actionPerformedBtnCerrar(ActionEvent arg0) {
		this.dispose();
	}
}
