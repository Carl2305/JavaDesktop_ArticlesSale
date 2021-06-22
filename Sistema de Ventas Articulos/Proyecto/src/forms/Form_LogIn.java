package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import dao.*;
import util.Conex_BD;
import beans.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JProgressBar;

public class Form_LogIn extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblBienvenidoAGamestore;
	private JLabel label_1;
	private JTextField txtUser;
	private JPasswordField pswfPassword;
	private JLabel label_2;
	private JButton btnEntrar;
	private JButton btnCerrar;
	private JLabel lblNewLabel;
	public static String Usuario="";
	
	public static String getUser_(){
		return Usuario;
	}
	public void SetUser_(String usuario) {
		Form_LogIn.Usuario=usuario;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form_LogIn frame = new Form_LogIn();
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
	public Form_LogIn() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("../Proyecto/Iconos/User\\User_24x24.png"));
		setTitle("Iniciar Sesi\u00F3n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 570, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblBienvenidoAGamestore = new JLabel("Bienvenido a GameStore");
		lblBienvenidoAGamestore.setBounds(0, 32, 564, 32);
		lblBienvenidoAGamestore.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBienvenidoAGamestore.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenidoAGamestore.setForeground(Color.BLACK);
		lblBienvenidoAGamestore.setFont(new Font("Sitka Text", Font.BOLD, 25));
		contentPane.add(lblBienvenidoAGamestore);
		
		label_1 = new JLabel("Usuario");
		label_1.setBounds(72, 103, 94, 16);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(label_1);
		
		txtUser = new JTextField();
		txtUser.setBounds(178, 100, 195, 22);
		txtUser.setForeground(Color.BLACK);
		txtUser.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUser.setColumns(10);
		contentPane.add(txtUser);
		
		pswfPassword = new JPasswordField();
		pswfPassword.setBounds(178, 141, 195, 22);
		pswfPassword.setForeground(Color.BLACK);
		pswfPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		contentPane.add(pswfPassword);
		
		label_2 = new JLabel("Contrase\u00F1a");
		label_2.setBounds(72, 143, 94, 16);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(label_2);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(72, 204, 129, 32);
		btnEntrar.addActionListener(this);
		btnEntrar.setForeground(Color.BLACK);
		btnEntrar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btnEntrar);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(244, 204, 129, 32);
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Color.BLACK);
		btnCerrar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		contentPane.add(btnCerrar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(425, 77, 129, 124);
		
		ImageIcon imagen= new ImageIcon("../Proyecto/Iconos/User/User_256x256.png");
        Icon icono= new ImageIcon(imagen.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_DEFAULT));
        lblNewLabel.setIcon(icono);
		
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
		if (e.getSource() == btnEntrar) {
			actionPerformedBtnEntrar(e);
		}
	}
	public void Iniciar_Sesion(){
		LogInDao mod=new LogInDao();
		Usuario user=new Usuario();
		Usuario = txtUser.getText();
		String pass=new String(pswfPassword.getPassword());
		if(!txtUser.getText().equals("")&&!pass.equals("")){
			if(mod.Estado_User(Usuario)==false){
				Form_ModifyPassword CambPass=new Form_ModifyPassword();
				CambPass.txtUser.setEditable(false);
				CambPass.setVisible(true);
			}
			else if(mod.Estado_User(Usuario)==true){
				
				String newpass=DigestUtils.md5Hex(pass);
				user.setUser(txtUser.getText());
				user.setPassword(newpass);
				if(mod.LogIn(user)){
					new MDI_Ventas(user);
					 MDI_Ventas.frmPrincipal.setLocationRelativeTo(null);
					 MDI_Ventas.frmPrincipal.setVisible(true);
					this.dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Datos Incorrectos");
				}
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Debe Ingresar Datos");
		}
	}
	protected void actionPerformedBtnEntrar(ActionEvent e) {
		Iniciar_Sesion();
	}
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		System.exit(0);
	}
}
