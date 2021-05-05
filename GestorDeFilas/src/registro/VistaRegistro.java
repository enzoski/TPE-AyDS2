package registro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class VistaRegistro extends JFrame {

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
	private JPanel panelVacio2;
	private JPanel panelVacio7;
	private JPanel panelVacio8;
	private JPanel panelVacio9;
	private JPanel panelVacio10;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		this.btnRegistrar.setForeground(new Color(0, 0, 128));
		this.btnRegistrar.setBackground(new Color(30, 144, 255));
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
		this.panelDigitos.add(this.btn1);
		
		this.btn2 = new JButton("2");
		this.panelDigitos.add(this.btn2);
		
		this.btn3 = new JButton("3");
		this.panelDigitos.add(this.btn3);
		
		this.btn4 = new JButton("4");
		this.panelDigitos.add(this.btn4);
		
		this.btn5 = new JButton("5");
		this.panelDigitos.add(this.btn5);
		
		this.btn6 = new JButton("6");
		this.panelDigitos.add(this.btn6);
		
		this.btn7 = new JButton("7");
		this.panelDigitos.add(this.btn7);
		
		this.btn8 = new JButton("8");
		this.panelDigitos.add(this.btn8);
		
		this.btn9 = new JButton("9");
		this.panelDigitos.add(this.btn9);
		
		this.panelVacio1 = new JPanel();
		this.panelDigitos.add(this.panelVacio1);
		
		this.btn0 = new JButton("0");
		this.panelDigitos.add(this.btn0);
		
		this.panelVacio2 = new JPanel();
		this.panelDigitos.add(this.panelVacio2);
		
		this.panelVacio7 = new JPanel();
		this.panelSur.add(this.panelVacio7, BorderLayout.NORTH);
		
		this.panelVacio8 = new JPanel();
		this.panelSur.add(this.panelVacio8, BorderLayout.SOUTH);
		
		this.panelVacio9 = new JPanel();
		this.panelSur.add(this.panelVacio9, BorderLayout.EAST);
		
		this.panelVacio10 = new JPanel();
		this.panelSur.add(this.panelVacio10, BorderLayout.WEST);
	}
	
	public void errorDNI() {
		// implementar mostrar el mensajito.
	}

}
