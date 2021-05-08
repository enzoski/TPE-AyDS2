package llamado;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.border.BevelBorder;

public class VistaLlamadoTV extends JFrame {

	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelDNI;
	private JPanel panelBox;
	private JLabel lblDNI;
	private JLabel lblBox;
	private JPanel panelListaDNI;
	private JPanel panelListaBox;
	// tendremos 2 listas visuales que muestren String's
	private JList<String> listDNI;
	private JList<String> listBox;
	// dichas listas visuales mostraran automaticamente lo que haya dentro de un DefaultListModel asociado
	private DefaultListModel<String> modeloListaDNI;
	private DefaultListModel<String> modeloListaBox;

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
		this.panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		this.panelNorte.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panelDNI = new JPanel();
		this.panelDNI.setBackground(new Color(173, 216, 230));
		this.panelNorte.add(this.panelDNI);
		
		this.lblDNI = new JLabel("DNI");
		this.lblDNI.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.panelDNI.add(this.lblDNI);
		
		this.panelBox = new JPanel();
		this.panelBox.setBackground(new Color(173, 216, 230));
		this.panelNorte.add(this.panelBox);
		
		this.lblBox = new JLabel("BOX");
		this.lblBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.panelBox.add(this.lblBox);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panelListaDNI = new JPanel();
		this.panelListaDNI.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panelListaDNI);
		this.panelListaDNI.setLayout(new BorderLayout(0, 0));
		
		this.listDNI = new JList<String>();
		this.listDNI.setFont(new Font("Tahoma", Font.PLAIN, 25));
		this.listDNI.setVisibleRowCount(20);
		this.panelListaDNI.add(this.listDNI, BorderLayout.CENTER);
		
		this.panelListaBox = new JPanel();
		this.panelListaBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panelListaBox);
		this.panelListaBox.setLayout(new BorderLayout(0, 0));
		
		this.listBox = new JList<String>();
		this.listBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		this.listBox.setVisibleRowCount(20);
		this.panelListaBox.add(this.listBox, BorderLayout.CENTER);
		
		// instanciamos y asociamos los modelos de lista a las listas visuales
		this.modeloListaDNI = new DefaultListModel<String>();
		this.modeloListaBox = new DefaultListModel<String>();
		
		this.listDNI.setModel(this.modeloListaDNI);
		this.listBox.setModel(this.modeloListaBox);
	}
	
	public void eliminarUltimoLlamado(String box) {
		// eliminar el ultimo llamado de num box de la lista que se muestra en pantalla (la fila dni-box deseada)
		int indice = this.modeloListaBox.indexOf(box);
		this.modeloListaBox.remove(indice);
		this.modeloListaDNI.remove(indice);
		this.repaint();
	}
	
	public void mostrarLlamado(String dni, String box) {
		this.modeloListaDNI.add(0, dni); // agrega al principio de la lista el dni a mostrar
		this.modeloListaBox.add(0, box); // agrega al principio de la lista el box a mostrar
		this.repaint(); // por las dudas redibujamos la ventana
		
	}
	
	public void abrirVentana() {
		this.setVisible(true);
	}
	

}
