package forms;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Form_Exit extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnNo;
	private JButton btnSi;
	private JLabel lblestasSeguroDe;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			Form_Exit dialog = new Form_Exit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form_Exit() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("../Proyecto/Iconos/Log Out/Log Out_24x24.png"));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Salir");
		setBounds(100, 100, 490, 236);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lblestasSeguroDe = new JLabel("\u00BFEstas Seguro de Salir?");
			lblestasSeguroDe.setForeground(Color.BLACK);
			lblestasSeguroDe.setFont(new Font("Sitka Text", Font.BOLD, 20));
			lblestasSeguroDe.setBounds(196, 52, 259, 33);
			contentPanel.add(lblestasSeguroDe);
			
			btnSi = new JButton("SI");
			btnSi.addActionListener(this);
			btnSi.setForeground(Color.BLACK);
			btnSi.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnSi.setBounds(206, 117, 78, 33);
			contentPanel.add(btnSi);
			
			btnNo = new JButton("NO");
			btnNo.addActionListener(this);
			btnNo.setForeground(Color.BLACK);
			btnNo.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNo.setBounds(358, 117, 78, 33);
			contentPanel.add(btnNo);
			
			lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(36, 33, 128, 122);
			
			//redimenciona la imagen al tamaño del lblNewLabel
	        ImageIcon imagen= new ImageIcon("../Proyecto/Iconos/Help/Help_256x256.png");
	        Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
	        lblNewLabel.setIcon(icono);
			contentPanel.add(lblNewLabel);
			setLocationRelativeTo(null);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNo) {
			actionPerformedBtnNo(e);
		}
		if (e.getSource() == btnSi) {
			actionPerformedBtnSi(e);
		}
	}
	protected void actionPerformedBtnSi(ActionEvent e) {
		System.exit(0);
	}
	protected void actionPerformedBtnNo(ActionEvent e) {
		dispose();
	}
}
