package registro.vista_registro;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class VistaRegistroConfirmacion extends JFrame implements I_VistaRegistro {
	
	private JPanel contentPane;
	private JPanel panelCentral;
	private JPanel panelSur;
	private JPanel panelModificar;
	private JPanel panelConfirmar;
	private JButton btnModificar;
	private JButton btnConfirmar;
	private JPanel panelVacio;
	private JPanel panelMuestra;
	private JPanel panelLabelDNI;
	private JLabel lblDNI;
	private JPanel panelDNI;
	private JTextArea txtAreaDNI;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaRegistroConfirmacion frame = new VistaRegistroConfirmacion();
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
	public VistaRegistroConfirmacion() { //ver despues que hacer con esto
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //por defecto, al cerrar la ventana con la 'X', solo se ocultará, no terminará la ejecución de todo el programa
		setMinimumSize(new Dimension(350, 200));
		setTitle("Confirmaci\u00F3n Registro");
		setBounds(100, 100, 350, 200);
		setLocationRelativeTo(null); // para que la ventana aparezca en el centro de nuestra pantalla
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(3, 1, 0, 0));
		
		this.panelVacio = new JPanel();
		this.panelCentral.add(this.panelVacio);
		
		this.panelMuestra = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelMuestra.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		this.panelCentral.add(this.panelMuestra);
		
		this.panelLabelDNI = new JPanel();
		this.panelMuestra.add(this.panelLabelDNI);
		
		this.lblDNI = new JLabel("DNI:");
		this.lblDNI.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.panelLabelDNI.add(this.lblDNI);
		
		this.panelDNI = new JPanel();
		this.panelMuestra.add(this.panelDNI);
		
		this.txtAreaDNI = new JTextArea();
		this.txtAreaDNI.setEditable(false);
		this.txtAreaDNI.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.txtAreaDNI.setBackground(UIManager.getColor("Panel.background"));
		this.txtAreaDNI.setText("");
		this.panelDNI.add(this.txtAreaDNI);
		
		this.panelSur = new JPanel();
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);
		
		this.panelModificar = new JPanel();
		this.panelSur.add(this.panelModificar);
		
		this.btnModificar = new JButton("Modificar");
		this.btnModificar.setActionCommand(AC_MODIFICAR);
		this.btnModificar.setForeground(new Color(139, 0, 0));
		this.btnModificar.setBackground(new Color(255, 160, 122));
		this.panelModificar.add(this.btnModificar);
		
		this.panelConfirmar = new JPanel();
		this.panelSur.add(this.panelConfirmar);
		
		this.btnConfirmar = new JButton("Confirmar");
		this.btnConfirmar.setActionCommand(AC_CONFIRMAR);
		this.btnConfirmar.setBackground(new Color(176, 224, 230));
		this.btnConfirmar.setForeground(new Color(0, 0, 128));
		this.panelConfirmar.add(this.btnConfirmar);
	}
	
	public void mostrarDni(String dni) {
		this.txtAreaDNI.setText(dni);
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
		this.btnModificar.addActionListener(c);
		this.btnConfirmar.addActionListener(c);
	}

}
