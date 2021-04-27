package ventanitas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;

public class VistaAtencionInicio extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public VistaAtencionInicio() {
		setTitle("Atenci\u00F3n: ingreso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_norte = new JPanel();
		contentPane.add(panel_norte, BorderLayout.NORTH);
		
		JPanel panel_texto_ingreso = new JPanel();
		panel_norte.add(panel_texto_ingreso);
		
		JLabel lblIngreseSuNmero = new JLabel("Ingrese su n\u00FAmero de BOX");
		lblIngreseSuNmero.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_texto_ingreso.add(lblIngreseSuNmero);
		
		JPanel panel_central = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_central.getLayout();
		flowLayout.setVgap(20);
		contentPane.add(panel_central, BorderLayout.CENTER);
		
		JPanel panel_box = new JPanel();
		panel_central.add(panel_box);
		
		textField = new JTextField();
		panel_box.add(textField);
		textField.setColumns(10);
		
		JPanel panel_ingresar = new JPanel();
		panel_central.add(panel_ingresar);
		
		JButton boton_conectar = new JButton("Conectar");
		boton_conectar.setForeground(new Color(0, 0, 128));
		boton_conectar.setBackground(new Color(30, 144, 255));
		panel_ingresar.add(boton_conectar);
	}

}
