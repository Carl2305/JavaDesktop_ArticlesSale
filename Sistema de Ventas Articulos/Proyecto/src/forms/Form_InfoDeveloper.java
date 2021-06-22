package forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

public class Form_InfoDeveloper extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblSistemaDeVenta;
	private JLabel lblDesarrolladoPor;
	private JLabel lblMogollonEspinozaCarlos;
	private JLabel lblI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_InfoDeveloper frame = new Form_InfoDeveloper();
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
	public Form_InfoDeveloper() {
		setFrameIcon(new ImageIcon("../Proyecto/Iconos/Information/Information_16x16.png"));
		setTitle("Informaci\u00F3n de Desarrollador");
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 518, 242);
		
		lblSistemaDeVenta = new JLabel("Ventas 1.0.0");
		lblSistemaDeVenta.setBounds(0, 24, 502, 38);
		lblSistemaDeVenta.setForeground(Color.BLACK);
		lblSistemaDeVenta.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblSistemaDeVenta.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblDesarrolladoPor = new JLabel("Desarrollado por: ");
		lblDesarrolladoPor.setBounds(29, 92, 286, 26);
		lblDesarrolladoPor.setFont(new Font("Sitka Text", Font.BOLD, 20));
		lblDesarrolladoPor.setForeground(Color.BLACK);
		
		lblMogollonEspinozaCarlos = new JLabel("Mogollon Espinoza, Carlos");
		lblMogollonEspinozaCarlos.setBounds(29, 155, 286, 23);
		lblMogollonEspinozaCarlos.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		lblMogollonEspinozaCarlos.setForeground(Color.BLACK);
		
		lblI = new JLabel("i201922675");
		lblI.setBounds(354, 157, 83, 20);
		lblI.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		lblI.setForeground(Color.BLACK);
		getContentPane().setLayout(null);
		getContentPane().add(lblSistemaDeVenta);
		getContentPane().add(lblDesarrolladoPor);
		getContentPane().add(lblMogollonEspinozaCarlos);
		getContentPane().add(lblI);
	}
}
