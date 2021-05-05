package llamado;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class VistaLlamadoTV extends JFrame {

	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelDNI;
	private JPanel panelBox;
	private JLabel lblDNI;
	private JLabel lblBox;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private JPanel panel_10;
	private JPanel panel_11;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLlamadoTV frame = new VistaLlamadoTV();
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
	public VistaLlamadoTV() {
		setTitle("Llamados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panelNorte = new JPanel();
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		this.panelNorte.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panelDNI = new JPanel();
		this.panelDNI.setBackground(new Color(173, 216, 230));
		this.panelNorte.add(this.panelDNI);
		
		this.lblDNI = new JLabel("DNI");
		this.lblDNI.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.panelDNI.add(this.lblDNI);
		
		this.panelBox = new JPanel();
		this.panelBox.setBackground(new Color(173, 216, 230));
		this.panelNorte.add(this.panelBox);
		
		this.lblBox = new JLabel("BOX");
		this.lblBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.panelBox.add(this.lblBox);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(10, 2, 0, 0));
		
		this.panel = new JPanel();
		this.panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel);
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_1);
		
		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_2);
		
		this.panel_3 = new JPanel();
		this.panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_3);
		
		this.panel_4 = new JPanel();
		this.panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_4);
		
		this.panel_5 = new JPanel();
		this.panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_5);
		
		this.panel_6 = new JPanel();
		this.panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_6);
		
		this.panel_7 = new JPanel();
		this.panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_7);
		
		this.panel_8 = new JPanel();
		this.panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_8);
		
		this.panel_9 = new JPanel();
		this.panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_9);
		
		this.panel_10 = new JPanel();
		this.panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_10);
		
		this.panel_11 = new JPanel();
		this.panel_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_11);
		
		this.panel_12 = new JPanel();
		this.panel_12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_12);
		
		this.panel_13 = new JPanel();
		this.panel_13.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_13);
		
		this.panel_14 = new JPanel();
		this.panel_14.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_14);
		
		this.panel_15 = new JPanel();
		this.panel_15.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_15);
		
		this.panel_16 = new JPanel();
		this.panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_16);
		
		this.panel_17 = new JPanel();
		this.panel_17.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_17);
		
		this.panel_18 = new JPanel();
		this.panel_18.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_18);
		
		this.panel_19 = new JPanel();
		this.panel_19.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panel_19);
	}
	
	public void eliminarUltimoLlamado(String box) {
		 int num_box = Integer.parseInt(box);
		 //eliminar el untimo llamado de num box de la lista que se muestra en pantalla
	}
	
	public void mostrar(String dni, String box){
		
	}
	

}
