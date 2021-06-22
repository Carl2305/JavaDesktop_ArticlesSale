package forms;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import beans.Usuario;
import dao.Usuario_dao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Form_ModifyPassword extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblUsuario;
	private JLabel lblContraseaActual;
	private JLabel lblContraseaNueva;
	private JLabel lblConfirmarContrasea;
	public JTextField txtUser;
	private JPasswordField passOld;
	private JPasswordField passNew;
	private JPasswordField passConfirm;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblCambiarContrasea;
	Usuario_dao userDAO=new Usuario_dao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Form_ModifyPassword dialog = new Form_ModifyPassword();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Form_ModifyPassword() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("../Proyecto/Iconos/Rename/Rename_16x16.png"));
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Cambiar Contrase\u00F1a");
		setBounds(100, 100, 437, 345);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(17, 64, 192, 16);
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblContraseaActual = new JLabel("Contrase\u00F1a Actual");
		lblContraseaActual.setBounds(17, 103, 192, 16);
		lblContraseaActual.setForeground(Color.BLACK);
		lblContraseaActual.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblContraseaNueva = new JLabel("Contrase\u00F1a Nueva");
		lblContraseaNueva.setBounds(17, 146, 192, 16);
		lblContraseaNueva.setForeground(Color.BLACK);
		lblContraseaNueva.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		lblConfirmarContrasea = new JLabel("Confirmar Contrase\u00F1a");
		lblConfirmarContrasea.setBounds(17, 192, 192, 16);
		lblConfirmarContrasea.setForeground(Color.BLACK);
		lblConfirmarContrasea.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUser.setBounds(221, 61, 185, 25);
		txtUser.setForeground(Color.BLACK);
		txtUser.setColumns(10);
		txtUser.setText(Form_LogIn.getUser_());
		
		passOld = new JPasswordField();
		passOld.setFont(new Font("Arial", Font.PLAIN, 15));
		passOld.setBounds(221, 100, 185, 25);
		passOld.setForeground(Color.BLACK);
		
		passNew = new JPasswordField();
		passNew.setFont(new Font("Arial", Font.PLAIN, 15));
		passNew.setBounds(221, 143, 185, 25);
		passNew.setForeground(Color.BLACK);
		
		passConfirm = new JPasswordField();
		passConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
		passConfirm.setBounds(221, 189, 185, 25);
		passConfirm.setForeground(Color.BLACK);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon("../Proyecto/Iconos/Save/Save_24x24.png"));
		btnGuardar.addActionListener(this);
		btnGuardar.setBounds(58, 250, 151, 30);
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("../Proyecto/Iconos/Cancel/Cancel_24x24.png"));
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(221, 250, 151, 30);
		btnCancelar.setForeground(Color.BLACK);
		btnCancelar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPanel.setLayout(null);
		
		lblCambiarContrasea = new JLabel("Cambiar Contrase\u00F1a");
		lblCambiarContrasea.setBounds(5, 18, 373, 25);
		lblCambiarContrasea.setHorizontalAlignment(SwingConstants.CENTER);
		lblCambiarContrasea.setForeground(Color.BLACK);
		lblCambiarContrasea.setFont(new Font("Sitka Text", Font.BOLD, 25));
		contentPanel.add(lblCambiarContrasea);
		contentPanel.add(btnGuardar);
		contentPanel.add(lblUsuario);
		contentPanel.add(lblContraseaActual);
		contentPanel.add(lblContraseaNueva);
		contentPanel.add(lblConfirmarContrasea);
		contentPanel.add(passConfirm);
		contentPanel.add(passNew);
		contentPanel.add(passOld);
		contentPanel.add(txtUser);
		contentPanel.add(btnCancelar);

		setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnGuardar) {
			actionPerformedBtnGuardar(arg0);
		}
		if (arg0.getSource() == btnCancelar) {
			actionPerformedBtnCancelar(arg0);
		}
	}
	protected void actionPerformedBtnCancelar(ActionEvent arg0) {
		dispose();
	}
	protected void actionPerformedBtnGuardar(ActionEvent arg0) {
		Usuario_dao obj=new Usuario_dao();
		String Usr=txtUser.getText().trim();
		char passOld[]=this.passOld.getPassword();
		String StrPassOld=new String(passOld);
		char passNew[]=this.passNew.getPassword();
		String StrPassNew=new String(passNew);
		char passConfirm[]=this.passConfirm.getPassword();
		String StrPassConfirm=new String(passConfirm);
		if(!this.txtUser.getText().equals("")&&!this.passOld.equals("")&&
			!this.passNew.getPassword().equals("")&&
			!this.passConfirm.getPassword().equals("")){
			String pass_BD=obj.getPassword_(Usr);
			//String pass_BD=DigestUtils.md5Hex(pass);
			String pass_Sys=DigestUtils.md5Hex(StrPassOld);
			if(pass_BD.equals(pass_Sys)||pass_BD.equals("PASS123")){
				if(StrPassNew.equals(StrPassConfirm)){
					String passNewCifrada=DigestUtils.md5Hex(StrPassConfirm);
					try {
						obj.ModifyPasswordUser(passNewCifrada, Usr);
						JOptionPane.showMessageDialog(this, "Se ha realizado el cambio de manera correcta\nYa puede cerrar esta ventana");
						btnCancelar.setText("Cerrar");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(this, "Sucedio un Error al Cambiar la Contaseña");
					}
				}
				else{
					JOptionPane.showMessageDialog(this, "La contraseña nueva y su confirmación, no coinciden ");
					return;
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Verifique la contraseña antigua " + StrPassOld+" contraseña BD: "+ pass_BD);
				this.passOld.setText("");
				this.passOld.requestFocus();
				return;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Todos los campos deben estar completos");
			return;
		}
	}
}
