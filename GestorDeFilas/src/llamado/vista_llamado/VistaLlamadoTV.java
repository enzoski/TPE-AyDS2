package llamado.vista_llamado;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JList;

public class VistaLlamadoTV extends JFrame {

	private JPanel contentPane;
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelNombre;
	private JPanel panelBox;
	private JLabel lblNombre;
	private JLabel lblBox;
	private JPanel panelListaNombre;
	private JPanel panelListaBox;
	// tendremos 2 listas visuales que muestren String's
	private JList<String> listNombre;
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
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		
		this.panelNorte = new JPanel();
		this.panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		this.panelNorte.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panelNombre = new JPanel();
		this.panelNombre.setBackground(new Color(173, 216, 230));
		this.panelNorte.add(this.panelNombre);
		
		this.lblNombre = new JLabel("NOMBRE");
		this.lblNombre.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.panelNombre.add(this.lblNombre);
		
		this.panelBox = new JPanel();
		this.panelBox.setBackground(new Color(173, 216, 230));
		this.panelNorte.add(this.panelBox);
		
		this.lblBox = new JLabel("BOX");
		this.lblBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.panelBox.add(this.lblBox);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(1, 2, 0, 0));
		
		this.panelListaNombre = new JPanel();
		this.panelListaNombre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.add(this.panelListaNombre);
		this.panelListaNombre.setLayout(new BorderLayout(0, 0));
		
		this.listNombre = new JList<String>();
		this.listNombre.setFont(new Font("Tahoma", Font.PLAIN, 25));
		this.listNombre.setVisibleRowCount(20);
		this.panelListaNombre.add(this.listNombre, BorderLayout.CENTER);
		
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
		
		this.listNombre.setModel(this.modeloListaDNI);
		this.listBox.setModel(this.modeloListaBox);
	}
	
	public void eliminarLlamados(String box) {
		// eliminar los llamados del num box de la lista que se muestra en pantalla (las filas dni-box del box deseado)
		int indice = 0;
		while(indice != -1) {
			indice = this.modeloListaBox.indexOf(box);
			if(indice >= 0) { // por si no hay un llamado previo del mismo box
				this.modeloListaBox.remove(indice);
				this.modeloListaDNI.remove(indice);
				this.repaint();
			}
			
		}
	}
	
	public void mostrarLlamado(String dni, String box) {
		this.modeloListaDNI.add(0, dni); // agrega al principio de la lista el dni a mostrar
		this.modeloListaBox.add(0, box); // agrega al principio de la lista el box a mostrar
		this.repaint(); // por las dudas redibujamos la ventana
		Toolkit.getDefaultToolkit().beep(); // emitimos un sonido ('beep' del sistema)
		
	}
	
	public void abrirVentana() {
		this.setVisible(true);
	}
	

}
