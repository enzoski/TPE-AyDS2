package ventanitas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class VistaLlamadoTV extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public VistaLlamadoTV() {
		setTitle("Llamados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_texto_dni = new JPanel();
		panel_texto_dni.setBackground(new Color(173, 216, 230));
		panel_texto_dni.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelNorte.add(panel_texto_dni);
		
		JLabel label_dni = new JLabel("DNI");
		label_dni.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_dni.setHorizontalAlignment(SwingConstants.CENTER);
		panel_texto_dni.add(label_dni);
		
		JPanel panel_texto_box = new JPanel();
		panel_texto_box.setBackground(new Color(173, 216, 230));
		panel_texto_box.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelNorte.add(panel_texto_box);
		
		JLabel label_box = new JLabel("BOX");
		label_box.setHorizontalAlignment(SwingConstants.CENTER);
		label_box.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_texto_box.add(label_box);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(10, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_8);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_12);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_13);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_14);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_16);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_17);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_18);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentral.add(panel_19);
	}

}
