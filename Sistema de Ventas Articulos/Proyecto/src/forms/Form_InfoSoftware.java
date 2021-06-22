package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;

public class Form_InfoSoftware extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSistemaDeVenta;
	private JScrollPane scrollPane;
	private JTextArea txtS;
	private JLabel lblVersin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_InfoSoftware frame = new Form_InfoSoftware();
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
	public Form_InfoSoftware() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Information/Information_16x16.png"));
		setTitle("Informaci\u00F3n Software");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 565, 757);
		
		lblSistemaDeVenta = new JLabel("Sistema de Venta de Videojuegos");
		lblSistemaDeVenta.setBounds(12, 13, 525, 48);
		lblSistemaDeVenta.setForeground(Color.BLACK);
		lblSistemaDeVenta.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSistemaDeVenta.setHorizontalAlignment(SwingConstants.CENTER);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 131, 525, 584);
		
		lblVersin = new JLabel("Versi\u00F3n 1.0");
		lblVersin.setBounds(12, 79, 525, 34);
		lblVersin.setHorizontalAlignment(SwingConstants.CENTER);
		lblVersin.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		lblVersin.setForeground(Color.BLACK);
		
		txtS = new JTextArea();
		txtS.setEditable(false);
		scrollPane.setViewportView(txtS);
		getContentPane().setLayout(null);
		getContentPane().add(lblVersin);
		getContentPane().add(lblSistemaDeVenta);
		getContentPane().add(scrollPane);

	}
}
