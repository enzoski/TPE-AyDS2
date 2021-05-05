package registro;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class VistaRegistroConfirmacion extends JFrame {

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

	/**
	 * Create the frame.
	 */
	public VistaRegistroConfirmacion() {
		setMinimumSize(new Dimension(350, 200));
		setTitle("Confirmaci\u00F3n Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 200);
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
		this.txtAreaDNI.setText("aca iria el dni");
		this.panelDNI.add(this.txtAreaDNI);
		
		this.panelSur = new JPanel();
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);
		
		this.panelModificar = new JPanel();
		this.panelSur.add(this.panelModificar);
		
		this.btnModificar = new JButton("Modificar");
		this.btnModificar.setForeground(new Color(128, 0, 0));
		this.btnModificar.setBackground(new Color(250, 128, 114));
		this.panelModificar.add(this.btnModificar);
		
		this.panelConfirmar = new JPanel();
		this.panelSur.add(this.panelConfirmar);
		
		this.btnConfirmar = new JButton("Confirmar");
		this.btnConfirmar.setBackground(new Color(30, 144, 255));
		this.btnConfirmar.setForeground(new Color(0, 0, 128));
		this.panelConfirmar.add(this.btnConfirmar);
	}

}
