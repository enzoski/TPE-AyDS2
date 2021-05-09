package atencion.vista_atencion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Color;

public class VistaAtencionInicio extends JFrame implements I_VistaAtencion {

	private ActionListener controlador;
	
	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelLabel;
	private JLabel lblBox;
	private JPanel panelBox;
	private JPanel panelConectar;
	private JTextField textFieldBox;
	private JButton btnConectar;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAtencionInicio frame = new VistaAtencionInicio();
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
	public VistaAtencionInicio() {
		setTitle("Atenci\u00F3n: ingreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 200);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panelNorte = new JPanel();
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		this.panelNorte.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.panelLabel = new JPanel();
		this.panelNorte.add(this.panelLabel);
		
		this.lblBox = new JLabel("Ingrese su n\u00FAmero de BOX");
		this.lblBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.panelLabel.add(this.lblBox);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		
		this.panelBox = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) this.panelBox.getLayout();
		this.panelCentral.add(this.panelBox);
		
		this.textFieldBox = new JTextField();
		this.textFieldBox.setText("");
		this.panelBox.add(this.textFieldBox);
		this.textFieldBox.setColumns(10);
		
		this.panelConectar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelConectar.getLayout();
		this.panelCentral.add(this.panelConectar);
		
		this.btnConectar = new JButton("Conectar");
		this.btnConectar.setActionCommand(AC_HABILITAR);
		this.btnConectar.setForeground(new Color(0, 0, 128));
		this.btnConectar.setBackground(new Color(176, 224, 230));
		this.panelConectar.add(this.btnConectar);
	}
	
	public String getBoxIngresado() {
		
		return this.textFieldBox.getText();
	}
	
	public void limpiarCampoBox() {
		this.textFieldBox.setText("");
		
	}
	
	public void errorBox() {
		JOptionPane.showMessageDialog(this, "El número de box ingresado no es válido. Por favor, modifíquelo.",
				"Error en el número de Box", JOptionPane.WARNING_MESSAGE);
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
		this.btnConectar.addActionListener(this.controlador);
		
	}

}
