package registro.vista_registro;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.event.ActionListener;

public class VistaRegistro extends JFrame implements I_VistaRegistro {
	
	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelBienvenida;
	private JLabel lblBienvenida;
	private JPanel panelLabelDNI;
	private JPanel panelDNI;
	private JPanel panelRegistrar;
	private JLabel lblDNI;
	private JPanel panelVacio3;
	private JTextField textFieldDNI;
	private JPanel panelVacio4;
	private JPanel panelBotonRegistrar;
	private JButton btnRegistrar;
	private JPanel panelVacio5;
	private JPanel panelVacio6;
	private JPanel panelSur;
	private JPanel panelDigitos;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JPanel panelVacio1;
	private JButton btn0;
	private JPanel panelVacio7;
	private JPanel panelVacio8;
	private JPanel panelVacio9;
	private JPanel panelVacio10;
	private JButton btnBorrar;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistro frame = new VistaRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public VistaRegistro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //significa que se cerraran todas las ventanas de la aplicación y terminará la ejecución
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null); // para que la ventana aparezca en el centro de nuestra pantalla
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panelNorte = new JPanel();
		this.panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		
		this.panelBienvenida = new JPanel();
		this.panelNorte.add(this.panelBienvenida);
		
		this.lblBienvenida = new JLabel("\u00A1 BIENVENIDO !");
		this.lblBienvenida.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.panelBienvenida.add(this.lblBienvenida);
		
		this.panelCentral = new JPanel();
		this.panelCentral.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(1, 3, 10, 0));
		
		this.panelLabelDNI = new JPanel();
		this.panelCentral.add(this.panelLabelDNI);
		this.panelLabelDNI.setLayout(new GridLayout(0, 1, 0, 0));
		
		this.lblDNI = new JLabel("Ingrese su DNI:");
		this.lblDNI.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelLabelDNI.add(this.lblDNI);
		
		this.panelDNI = new JPanel();
		this.panelCentral.add(this.panelDNI);
		this.panelDNI.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.panelVacio3 = new JPanel();
		this.panelDNI.add(this.panelVacio3);
		
		this.textFieldDNI = new JTextField();
		this.textFieldDNI.setHorizontalAlignment(SwingConstants.CENTER); //así el texto aparece centrado
		this.textFieldDNI.setText(""); //lo inicializó como un string vació por si llegará a estar por defencto en 'null'
		this.textFieldDNI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.textFieldDNI.setEditable(false);
		this.panelDNI.add(this.textFieldDNI);
		this.textFieldDNI.setColumns(10);
		
		this.panelRegistrar = new JPanel();
		this.panelCentral.add(this.panelRegistrar);
		this.panelRegistrar.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.panelVacio4 = new JPanel();
		this.panelRegistrar.add(this.panelVacio4);
		
		this.panelBotonRegistrar = new JPanel();
		this.panelRegistrar.add(this.panelBotonRegistrar);
		this.panelBotonRegistrar.setLayout(new BorderLayout(5, 5));
		
		this.btnRegistrar = new JButton("Registrar");
		this.btnRegistrar.setActionCommand(AC_REGISTRAR);
		this.btnRegistrar.setForeground(new Color(0, 0, 128));
		this.btnRegistrar.setBackground(new Color(176, 224, 230));
		this.btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.panelBotonRegistrar.add(this.btnRegistrar);
		
		this.panelVacio5 = new JPanel();
		this.panelBotonRegistrar.add(this.panelVacio5, BorderLayout.WEST);
		
		this.panelVacio6 = new JPanel();
		this.panelBotonRegistrar.add(this.panelVacio6, BorderLayout.EAST);
		
		this.panelSur = new JPanel();
		this.panelSur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);
		this.panelSur.setLayout(new BorderLayout(0, 0));
		
		this.panelDigitos = new JPanel();
		this.panelSur.add(this.panelDigitos);
		this.panelDigitos.setLayout(new GridLayout(4, 3, 15, 5));
		
		this.btn1 = new JButton("1");
		this.btn1.setActionCommand(AC_NUM_1);
		this.panelDigitos.add(this.btn1);
		
		this.btn2 = new JButton("2");
		this.btn2.setActionCommand(AC_NUM_2);
		this.panelDigitos.add(this.btn2);
		
		this.btn3 = new JButton("3");
		this.btn3.setActionCommand(AC_NUM_3);
		this.panelDigitos.add(this.btn3);
		
		this.btn4 = new JButton("4");
		this.btn4.setActionCommand(AC_NUM_4);
		this.panelDigitos.add(this.btn4);
		
		this.btn5 = new JButton("5");
		this.btn5.setActionCommand(AC_NUM_5);
		this.panelDigitos.add(this.btn5);
		
		this.btn6 = new JButton("6");
		this.btn6.setActionCommand(AC_NUM_6);
		this.panelDigitos.add(this.btn6);
		
		this.btn7 = new JButton("7");
		this.btn7.setActionCommand(AC_NUM_7);
		this.panelDigitos.add(this.btn7);
		
		this.btn8 = new JButton("8");
		this.btn8.setActionCommand(AC_NUM_8);
		this.panelDigitos.add(this.btn8);
		
		this.btn9 = new JButton("9");
		this.btn9.setActionCommand(AC_NUM_9);
		this.panelDigitos.add(this.btn9);
		
		this.panelVacio1 = new JPanel();
		this.panelDigitos.add(this.panelVacio1);
		
		this.btn0 = new JButton("0");
		this.btn0.setActionCommand(AC_NUM_0);
		this.panelDigitos.add(this.btn0);
		
		this.btnBorrar = new JButton("<");
		this.btnBorrar.setActionCommand(AC_BORRAR);
		this.btnBorrar.setBackground(Color.LIGHT_GRAY);
		this.panelDigitos.add(this.btnBorrar);
		
		this.panelVacio7 = new JPanel();
		this.panelSur.add(this.panelVacio7, BorderLayout.NORTH);
		
		this.panelVacio8 = new JPanel();
		this.panelSur.add(this.panelVacio8, BorderLayout.SOUTH);
		
		this.panelVacio9 = new JPanel();
		this.panelSur.add(this.panelVacio9, BorderLayout.EAST);
		
		this.panelVacio10 = new JPanel();
		this.panelSur.add(this.panelVacio10, BorderLayout.WEST);
	}
	
	public void mostrarDigito(String digito) {
		String digitos = this.textFieldDNI.getText();
		digitos += digito;
		this.textFieldDNI.setText(digitos);
	}
	
	public void borrarUltimoDigito() {
		String digitos = this.textFieldDNI.getText();
		if(!digitos.isEmpty()) {
			digitos = digitos.substring(0, digitos.length()-1);
			this.textFieldDNI.setText(digitos);
		}
	}
	
	public String getDniIngresado() {
		return this.textFieldDNI.getText();
	}
	
	public void limpiarCampoDNI() {
		this.textFieldDNI.setText("");
	}
	
	public void errorDNI() {
		JOptionPane.showMessageDialog(this, "El DNI ingresado no es válido. Por favor, modifíquelo.",
				"Error en el DNI", JOptionPane.WARNING_MESSAGE); // o bien JOptionPane.ERROR_MESSAGE
	}
	
	public void errorConexion() {
		JOptionPane.showMessageDialog(this, "Hubo un error de conexión. Inténtelo en otro momento.",
				"Error de Conexión", JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void abrirVentana() {
		this.setVisible(true);
	}

	@Override
	public void cerrarVentana() {
		this.setVisible(false);
	}

	@Override
	public void setControlador(ActionListener c) {
		// indicamos para cada botón, quién estará pendiente de que lo presionen (el controlador)
		// el controlador va a estar "escuchando" los eventos que ocurran en la vista
		this.btnRegistrar.addActionListener(c);
		this.btn0.addActionListener(c);
		this.btn1.addActionListener(c);
		this.btn2.addActionListener(c);
		this.btn3.addActionListener(c);
		this.btn4.addActionListener(c);
		this.btn5.addActionListener(c);
		this.btn6.addActionListener(c);
		this.btn7.addActionListener(c);
		this.btn8.addActionListener(c);
		this.btn9.addActionListener(c);
		this.btnBorrar.addActionListener(c);
	}

}
