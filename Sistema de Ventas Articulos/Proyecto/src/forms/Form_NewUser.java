package forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalityType;
import java.awt.Color;
import javax.swing.JProgressBar;
import javax.swing.ImageIcon;

public class Form_NewUser extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUser;
	private JPasswordField txtPasswordNew;
	private JPasswordField txtPass;
	private JButton btnRegistrar;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label;
	private static JProgressBar pgbNewUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Form_NewUser dialog = new Form_NewUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form_NewUser() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 288, 377);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(32, 178, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		label_5 = new JLabel("Hola");
		label_5.setBounds(45, 13, 25, 16);
		
		contentPanel.add(label_5);
		label = new JLabel("NombreEmpleado");
		label.setBounds(106, 13, 101, 16);
		contentPanel.add(label);
		
		label_4 = new JLabel("Es Hora de Crear tu Usuario y contrase\u00F1a");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(12, 47, 263, 16);
		contentPanel.add(label_4);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(12, 141, 263, 22);
		contentPanel.add(txtUser);
		
		label_1 = new JLabel("Usuario:");
		label_1.setBounds(12, 118, 48, 16);
		contentPanel.add(label_1);
		
		label_2 = new JLabel("Contrase\u00F1a:");
		label_2.setBounds(12, 181, 70, 16);
		contentPanel.add(label_2);
		
		label_3 = new JLabel("Confirmar Contrase\u00F1a:");
		label_3.setBounds(12, 244, 131, 16);
		contentPanel.add(label_3);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon("../Proyecto/Iconos/Save/Save_16x16.png"));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(79, 314, 112, 25);
		contentPanel.add(btnRegistrar);
		
		txtPasswordNew = new JPasswordField();
		txtPasswordNew.setBounds(12, 204, 263, 22);
		contentPanel.add(txtPasswordNew);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(12, 267, 263, 22);
		contentPanel.add(txtPass);
		
		pgbNewUser = new JProgressBar();
		pgbNewUser.setBounds(12, 356, 263, 8);
		contentPanel.add(pgbNewUser);

		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			actionPerformedButton(arg0);
		}
	}
	protected void actionPerformedButton(ActionEvent arg0) {
		char pase1[]=txtPasswordNew.getPassword();
		char pase2[]=txtPass.getPassword();
		String Pass1=new String(pase1);
		String pass2=new String(pase2);
		if(Pass1.equals(pass2)){
			this.dispose();
		}
		else{
			JOptionPane.showMessageDialog(null, "Verifique la contraseña!!!");
		}
	}
	
}
