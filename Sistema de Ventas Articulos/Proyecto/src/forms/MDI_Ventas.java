package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.mysql.cj.xdevapi.Result;

import beans.Empleado;
import beans.Usuario;
import dao.Usuario_dao;

import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.awt.event.InputEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Toolkit;

public class MDI_Ventas implements ActionListener {

	public static JFrame frmPrincipal;
	public static JDesktopPane desktopPane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenuItem mntmCambiarContrasea;
	private JMenuItem mntmIniciarSesin;
	private JSeparator separator;
	private JMenuItem mntmSalir;
	public JMenu mnMantenimiento;
	private static JMenuItem mntmHistorialDeVentas;
	private static JMenuItem mntmModificarProductos;
	private JSeparator separator_2;
	private JMenu mnVentas;
	private static JMenuItem mntmVender;
	private static JMenuItem mntmRegistrarCliente;
	private JMenu mnConsultas;
	private static JMenuItem mntmConsultarCliente;
	public static JMenuItem mntmConsultarVenta;
	private JMenu mnAyuda;
	private static JMenuItem mntmInformacionDelSoftware;
	private static JMenuItem mntmContactosDelDesarrollador;
	private static JMenuItem mntmRegistrarEmpleado;
	private JMenu mnReportes;
	private JMenu mnProductos;
	private static JMenuItem mntmSinStock;
	Usuario modM;
	
	public static int codEmp = 0;

	public static int getCodEmp() {
		return codEmp;
	}


	public static void setCodEmp(int codEmp) {
		MDI_Ventas.codEmp = codEmp;
	}
	/**
	 *intancias de metodos para abrir los JInternalFrame 
	 */
	
	static Form_InfoDeveloper frmInfDe=new Form_InfoDeveloper();
	static Form_InfoSoftware frmInfSoft=new Form_InfoSoftware();
	static Form_RegistrarCliente frmRegCli=new Form_RegistrarCliente();
	static Form_Ventas frmVentas=new Form_Ventas();
	static Form_RegistrarEmpleado frmRegEmple=new Form_RegistrarEmpleado();
	static Form_ConsultarVenta frmConsVenta=new Form_ConsultarVenta();
	static Form_RegistrarProducto frmModprod=new Form_RegistrarProducto();
	static Form_ConsultarCliente frmConsClien=new Form_ConsultarCliente();
	static Form_HIstorialVentas frmHistVenta=new Form_HIstorialVentas();
	static Form_ReportProdSinStock frmReportStcok=new Form_ReportProdSinStock();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MDI_Ventas window = new MDI_Ventas();
					window.frmPrincipal.setLocationRelativeTo(null);
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MDI_Ventas() {
		initialize();
	}
	public MDI_Ventas(Usuario mod){
		initialize();
		frmPrincipal.setLocationRelativeTo(null);
		this.modM=mod;
		if(mod.getIdCargo()==1){
		}
		else if(mod.getIdCargo()!=1){
			mnMantenimiento.setVisible(false);
		}
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage("../Proyecto/Iconos/User/User_16x16.png"));
		frmPrincipal.setMinimumSize(new Dimension(800, 500));
		frmPrincipal.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmPrincipal.setTitle("SuperMarket");
		frmPrincipal.setBounds(100, 100, 1500, 950);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Class_PaintFondo FIMG=new Class_PaintFondo("../Proyecto/Iconos/menu.jpg");
		desktopPane = new JDesktopPane();
		desktopPane=FIMG;
		frmPrincipal.getContentPane().add(desktopPane, BorderLayout.CENTER);
				
		menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		mnArchivo = new JMenu("Archivo");
		mnArchivo.setIcon(new ImageIcon("../Proyecto/Iconos/Archive/Archive_24x24.png"));
		mnArchivo.setForeground(Color.BLACK);
		mnArchivo.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);
		
		mntmCambiarContrasea = new JMenuItem("Cambiar Contrase\u00F1a");
		mntmCambiarContrasea.addActionListener(this);
		mntmCambiarContrasea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmCambiarContrasea.setForeground(Color.BLACK);
		mntmCambiarContrasea.setIcon(new ImageIcon("../Proyecto/Iconos/Key/Key_24x24.png"));
		mntmCambiarContrasea.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnArchivo.add(mntmCambiarContrasea);
		
		mntmIniciarSesin = new JMenuItem("Iniciar Sesi\u00F3n");
		mntmIniciarSesin.addActionListener(this);
		mntmIniciarSesin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmIniciarSesin.setForeground(Color.BLACK);
		mntmIniciarSesin.setIcon(new ImageIcon("../Proyecto/Iconos/User/User_24x24.png"));
		mntmIniciarSesin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnArchivo.add(mntmIniciarSesin);
		
		separator = new JSeparator();
		mnArchivo.add(separator);
		
		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmSalir.setForeground(Color.BLACK);
		mntmSalir.setIcon(new ImageIcon("../Proyecto/Iconos/Log Out/Log Out_24x24.png"));
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setIcon(new ImageIcon("../Proyecto/Iconos/Settings/Settings_24x24.png"));
		mnMantenimiento.setForeground(Color.BLACK);
		mnMantenimiento.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnMantenimiento.setMnemonic('M');
		menuBar.add(mnMantenimiento);
		
		mntmHistorialDeVentas = new JMenuItem("Historial de Ventas");
		mntmHistorialDeVentas.addActionListener(this);
		mntmHistorialDeVentas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmHistorialDeVentas.setForeground(Color.BLACK);
		mntmHistorialDeVentas.setIcon(new ImageIcon("../Proyecto/Iconos/Archive/Archive_24x24.png"));
		mntmHistorialDeVentas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.ALT_MASK));
		mnMantenimiento.add(mntmHistorialDeVentas);
		
		separator_2 = new JSeparator();
		mnMantenimiento.add(separator_2);
		
		mntmModificarProductos = new JMenuItem("Productos");
		mntmModificarProductos.addActionListener(this);
		mntmModificarProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmModificarProductos.setForeground(Color.BLACK);
		mntmModificarProductos.setIcon(new ImageIcon("../Proyecto/Iconos/Text Document/Text Document_24x24.png"));
		mntmModificarProductos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnMantenimiento.add(mntmModificarProductos);
		
		mntmRegistrarEmpleado = new JMenuItem("Empleado");
		mntmRegistrarEmpleado.setIcon(new ImageIcon("../Proyecto/Iconos/User/User_24x24.png"));
		mntmRegistrarEmpleado.setForeground(Color.BLACK);
		mntmRegistrarEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmRegistrarEmpleado.addActionListener(this);
		mnMantenimiento.add(mntmRegistrarEmpleado);
		
		mntmRegistrarCliente = new JMenuItem("Cliente");
		mntmRegistrarCliente.setIcon(new ImageIcon("../Proyecto/Iconos/User/User_24x24.png"));
		mnMantenimiento.add(mntmRegistrarCliente);
		mntmRegistrarCliente.addActionListener(this);
		mntmRegistrarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmRegistrarCliente.setForeground(Color.BLACK);
		
		mnVentas = new JMenu("Ventas");
		mnVentas.setIcon(new ImageIcon("../Proyecto/Iconos/Properties/Properties_24x24.png"));
		mnVentas.setForeground(Color.BLACK);
		mnVentas.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnVentas.setMnemonic('V');
		menuBar.add(mnVentas);
		
		mntmVender = new JMenuItem("Vender");
		mntmVender.setIcon(new ImageIcon("../Proyecto/Iconos/Properties/Properties_24x24.png"));
		mntmVender.addActionListener(this);
		mntmVender.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmVender.setForeground(Color.BLACK);
		mntmVender.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_MASK));
		mnVentas.add(mntmVender);
		
		mnConsultas = new JMenu("Consultas");
		mnConsultas.setIcon(new ImageIcon("../Proyecto/Iconos/Search/Search_24x24.png"));
		mnConsultas.setForeground(Color.BLACK);
		mnConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnConsultas.setMnemonic('C');
		menuBar.add(mnConsultas);
		
		mntmConsultarCliente = new JMenuItem("Consultar Cliente");
		mntmConsultarCliente.setIcon(new ImageIcon("../Proyecto/Iconos/Preview/Preview_24x24.png"));
		mntmConsultarCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmConsultarCliente.addActionListener(this);
		mntmConsultarCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmConsultarCliente.setForeground(Color.BLACK);
		mnConsultas.add(mntmConsultarCliente);
		
		mntmConsultarVenta = new JMenuItem("Consultar Venta");
		mntmConsultarVenta.setIcon(new ImageIcon("../Proyecto/Iconos/Find/Find_24x24.png"));
		mntmConsultarVenta.addActionListener(this);
		mntmConsultarVenta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmConsultarVenta.setForeground(Color.BLACK);
		mntmConsultarVenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnConsultas.add(mntmConsultarVenta);
		
		mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon("../Proyecto/Iconos/Text Document/Text Document_24x24.png"));
		mnReportes.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnReportes.setForeground(Color.BLACK);
		menuBar.add(mnReportes);
		
		mnProductos = new JMenu("Productos");
		mnProductos.setIcon(new ImageIcon("../Proyecto/Iconos/Text Document/Text Document_24x24.png"));
		mnProductos.setForeground(Color.BLACK);
		mnProductos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnReportes.add(mnProductos);
		
		mntmSinStock = new JMenuItem("Sin Stock");
		mntmSinStock.setIcon(new ImageIcon("../Proyecto/Iconos/Stock Index Down/Stock Index Down_24x24.png"));
		mntmSinStock.addActionListener(this);
		mntmSinStock.setForeground(Color.BLACK);
		mntmSinStock.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mnProductos.add(mntmSinStock);
		
		mnAyuda = new JMenu("Ayuda");
		mnAyuda.setIcon(new ImageIcon("../Proyecto/Iconos/Help/Help_24x24.png"));
		mnAyuda.setForeground(Color.BLACK);
		mnAyuda.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnAyuda.setMnemonic('H');
		menuBar.add(mnAyuda);
		
		mntmContactosDelDesarrollador = new JMenuItem("Contactos del Desarrollador");
		mntmContactosDelDesarrollador.addActionListener(this);
		mntmContactosDelDesarrollador.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmContactosDelDesarrollador.setForeground(Color.BLACK);
		mntmContactosDelDesarrollador.setIcon(new ImageIcon("../Proyecto/Iconos/Globe/Globe_24x24.png"));
		mnAyuda.add(mntmContactosDelDesarrollador);
		
		mntmInformacionDelSoftware = new JMenuItem("Informacion del Software");
		mntmInformacionDelSoftware.addActionListener(this);
		mntmInformacionDelSoftware.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmInformacionDelSoftware.setForeground(Color.BLACK);
		mntmInformacionDelSoftware.setIcon(new ImageIcon("../Proyecto/Iconos/Information/Information_24x24.png"));
		mnAyuda.add(mntmInformacionDelSoftware);
		mntmInformacionDelSoftware.setVisible(false);
		codEmp = Usuario_dao.SearchCodEmple(Form_LogIn.getUser_());
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == mntmSinStock) {
			actionPerformedMntmSinStock(arg0);
		}
		if (arg0.getSource() == mntmCambiarContrasea) {
			actionPerformedMntmCambiarContrasea(arg0);
		}
		if (arg0.getSource() == mntmHistorialDeVentas) {
			actionPerformedMntmHistorialDeVentas(arg0);
		}
		if (arg0.getSource() == mntmConsultarCliente) {
			actionPerformedMntmConsultarCliente(arg0);
		}
		if (arg0.getSource() == mntmModificarProductos) {
			actionPerformedMntmModificarProductos(arg0);
		}
		if (arg0.getSource() == mntmConsultarVenta) {
			actionPerformedMntmConsultarVenta(arg0);
		}
		if (arg0.getSource() == mntmIniciarSesin) {
			actionPerformedMntmIniciarSesin(arg0);
		}
		if (arg0.getSource() == mntmRegistrarEmpleado) {
			actionPerformedMntmRegistrarEmpleado(arg0);
		}
		if (arg0.getSource() == mntmVender) {
			actionPerformedMntmVender(arg0);
		}
		if (arg0.getSource() == mntmRegistrarCliente) {
			actionPerformedMntmRegistrarCliente(arg0);
		}
		if (arg0.getSource() == mntmInformacionDelSoftware) {
			actionPerformedMntmInformacionDelSoftware(arg0);
		}
		if (arg0.getSource() == mntmContactosDelDesarrollador) {
			actionPerformedMntmContactosDelDesarrollador(arg0);
		}
		if (arg0.getSource() == mntmSalir) {
			actionPerformedMntmSalir(arg0);
		}
	}
	public static void cerrar(){
		Form_Exit frmEx=new Form_Exit();
		frmEx.setVisible(true);
	}
	protected static void actionPerformedMntmSalir(ActionEvent arg0) {
		cerrar();
	}
	protected static void actionPerformedMntmContactosDelDesarrollador(ActionEvent arg0) {
		if(frmInfDe.isIcon()){
			mntmContactosDelDesarrollador.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmInfDe.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmInfDe);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmInfDe.getSize();
		        frmInfDe.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
				frmInfDe.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmInfDe);
			}
		}
	}
	protected static void actionPerformedMntmInformacionDelSoftware(ActionEvent arg0) {
		if (frmInfSoft.isIcon()) {
			mntmInformacionDelSoftware.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmInfSoft.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmInfSoft);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmInfSoft.getSize();
		        frmInfSoft.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmInfSoft.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmInfSoft);
			}
		}
	}
	protected static void actionPerformedMntmRegistrarCliente(ActionEvent arg0) {
		if (frmRegCli.isIcon()) {
			mntmRegistrarCliente.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmRegCli.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmRegCli);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmRegCli.getSize();
		        frmRegCli.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmRegCli.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmRegCli);
			}
		}
	}
	protected static void actionPerformedMntmVender(ActionEvent arg0) {
		if (frmVentas.isIcon()) {
			mntmVender.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmVentas.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmVentas);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmVentas.getSize();
		        frmVentas.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmVentas.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmVentas);
			}
		}
	}
	protected static void actionPerformedMntmRegistrarEmpleado(ActionEvent arg0) {
		if (frmRegEmple.isIcon()) {
			mntmRegistrarEmpleado.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmRegEmple.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmRegEmple);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmRegEmple.getSize();
		        frmRegEmple.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmRegEmple.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmRegEmple);
			}
		}
	}
	protected static void actionPerformedMntmIniciarSesin(ActionEvent arg0) {
		MDI_Ventas.frmPrincipal.dispose();
		Form_LogIn frmLogin=new Form_LogIn();
		frmLogin.setVisible(true);
	}
	protected static void actionPerformedMntmConsultarVenta(ActionEvent arg0) {
		if (frmConsVenta.isIcon()) {
			mntmConsultarVenta.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmConsVenta.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmConsVenta);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmConsVenta.getSize();
		        frmConsVenta.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmConsVenta.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmConsVenta);
			}
		}
	}
	protected static void actionPerformedMntmModificarProductos(ActionEvent arg0) {
		if (frmModprod.isIcon()) {
			mntmModificarProductos.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmModprod.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmModprod);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmModprod.getSize();
		        frmModprod.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmModprod.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmModprod);
			}
		}
	}
	protected static void actionPerformedMntmConsultarCliente(ActionEvent arg0) {
		if (frmConsClien.isIcon()) {
			mntmConsultarCliente.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmConsClien.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmConsClien);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmConsClien.getSize();
		        frmConsClien.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmConsClien.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmConsClien);
			}
		}
	}
	protected static void actionPerformedMntmHistorialDeVentas(ActionEvent arg0) {
		if (frmHistVenta.isIcon()) {
			mntmHistorialDeVentas.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmHistVenta.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmHistVenta);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmHistVenta.getSize();
		        frmHistVenta.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmHistVenta.setVisible(true);
			} catch (Exception ex) {
				desktopPane.add(frmHistVenta);
			}
		}
	}
	protected static void actionPerformedMntmCambiarContrasea(ActionEvent arg0) {
		Form_ModifyPassword frmModPass=new Form_ModifyPassword();
		frmModPass.setVisible(true);
	}
	protected static void actionPerformedMntmSinStock(ActionEvent arg0) {
		if (frmReportStcok.isIcon()) {
			mntmSinStock.isEnabled();
			JOptionPane.showMessageDialog(null, "Ya existe una misma ventana abierta");
			try {
				frmReportStcok.setIcon(false);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				desktopPane.add(frmReportStcok);
				//centra el JInternalFrame 
		        Dimension desktopSize = desktopPane.getSize();
		        Dimension FrameSize = frmReportStcok.getSize();
		        frmReportStcok.setLocation((desktopSize.width - FrameSize.width)/2, (desktopSize.height- FrameSize.height)/2);
		        frmReportStcok.setVisible(true);
		        frmReportStcok.muestra();
			} catch (Exception ex) {
				desktopPane.add(frmReportStcok);
			}
		}
	}
}
