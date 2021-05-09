package atencion.vista_atencion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class VistaAtencionLlamarCliente extends JFrame implements I_VistaAtencion {

	private ActionListener controlador;
	
	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelSur;
	private JPanel panelLlamar;
	private JPanel panelDesconectar;
	private JButton btnLlamarCliente;
	private JButton btnDesconectar;
	private JPanel panelNorte;
	private JPanel panelLabelBox;
	private JLabel lblBox;
	private JTextField textFieldBox;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAtencionLlamarCliente frame = new VistaAtencionLlamarCliente();
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
	public VistaAtencionLlamarCliente() {
		setTitle("Atenci\u00F3n: llamar cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.panelLlamar = new JPanel();
		this.panelCentral.add(this.panelLlamar);
		
		this.btnLlamarCliente = new JButton("Llamar al siguiente Cliente");
		this.btnLlamarCliente.setActionCommand(AC_LLAMAR);
		this.btnLlamarCliente.setForeground(new Color(0, 128, 0));
		this.btnLlamarCliente.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.btnLlamarCliente.setBackground(new Color(152, 251, 152));
		this.btnLlamarCliente.setPreferredSize(new Dimension(250, 55));
		this.panelLlamar.add(this.btnLlamarCliente);
		
		this.panelSur = new JPanel();
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);
		
		this.panelDesconectar = new JPanel();
		this.panelSur.add(this.panelDesconectar);
		
		this.btnDesconectar = new JButton("Desconectar");
		this.btnDesconectar.setActionCommand(AC_DESCONECTAR);
		this.btnDesconectar.setForeground(new Color(139, 0, 0));
		this.btnDesconectar.setBackground(new Color(255, 160, 122));
		this.btnDesconectar.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.panelDesconectar.add(this.btnDesconectar);
		
		this.panelNorte = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelNorte.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		
		this.panelLabelBox = new JPanel();
		this.panelNorte.add(this.panelLabelBox);
		
		this.lblBox = new JLabel("Box:");
		this.panelLabelBox.add(this.lblBox);
		
		this.textFieldBox = new JTextField();
		this.textFieldBox.setEditable(false);
		this.textFieldBox.setHorizontalAlignment(SwingConstants.CENTER);
		this.panelLabelBox.add(this.textFieldBox);
		this.textFieldBox.setColumns(2);
	}

	public void mostrarNumBoxHabilitado(String box) {
		this.textFieldBox.setText(box);
	}
	
	/*
	public void mostrarDNIProximoCliente(String dni) {
		this.textFieldDNI.setText(dni);
	}
	
	public void errorProxCliente() {
		JOptionPane.showMessageDialog(this, "No hay más clientes esperando a ser atendidos.",
				"Error de Llamado", JOptionPane.WARNING_MESSAGE);
	}
	*/
	
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
		this.controlador = c;
		this.btnDesconectar.addActionListener(this.controlador);
		this.btnLlamarCliente.addActionListener(this.controlador);
		
	}

}
