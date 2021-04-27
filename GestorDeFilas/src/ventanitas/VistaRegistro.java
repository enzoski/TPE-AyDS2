package ventanitas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class VistaRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textField_dni;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public VistaRegistro() {
		setTitle("Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(10, 0));
		
		JPanel panel_texto_dni = new JPanel();
		panelCentral.add(panel_texto_dni, BorderLayout.WEST);
		panel_texto_dni.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_texto_dni.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("    Ingrese su DNI:    ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_texto_dni.add(lblNewLabel);
		
		JPanel panel_ingreso_dni = new JPanel();
		panelCentral.add(panel_ingreso_dni, BorderLayout.CENTER);
		panel_ingreso_dni.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panel_ingreso_dni.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_ingreso_dni.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField_dni = new JTextField();
		textField_dni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_dni.setHorizontalAlignment(SwingConstants.CENTER);
		textField_dni.setText("12.345.678");
		textField_dni.setEditable(false);
		panel_1.add(textField_dni);
		textField_dni.setColumns(20);
		
		JPanel panel_boton_registrar = new JPanel();
		panelCentral.add(panel_boton_registrar, BorderLayout.EAST);
		panel_boton_registrar.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_boton_registrar.add(panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_boton_registrar.add(panel_4);
		panel_4.setLayout(new BorderLayout(5, 5));
		
		JButton boton_registrar = new JButton("Registrar");
		boton_registrar.setForeground(new Color(0, 0, 128));
		boton_registrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		boton_registrar.setBackground(new Color(30, 144, 255));
		panel_4.add(boton_registrar);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.WEST);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.EAST);
		
		JPanel panelNorte = new JPanel();
		panelNorte.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBienvenida = new JPanel();
		panelNorte.add(panelBienvenida, BorderLayout.CENTER);
		
		JLabel lblBienvenido = new JLabel("\u00A1 BIENVENIDO !");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelBienvenida.add(lblBienvenido);
		
		JPanel panelSur = new JPanel();
		panelSur.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(4, 3, 0, 0));
		
		JPanel panel_num1 = new JPanel();
		panelSur.add(panel_num1);
		
		JButton boton_num1 = new JButton("1");
		panel_num1.add(boton_num1);
		
		JPanel panel_num2 = new JPanel();
		panelSur.add(panel_num2);
		
		JButton boton_num2 = new JButton("2");
		panel_num2.add(boton_num2);
		
		JPanel panel_num3 = new JPanel();
		panelSur.add(panel_num3);
		
		JButton boton_num3 = new JButton("3");
		panel_num3.add(boton_num3);
		
		JPanel panel_num4 = new JPanel();
		panelSur.add(panel_num4);
		
		JButton boton_num4 = new JButton("4");
		panel_num4.add(boton_num4);
		
		JPanel panel_num5 = new JPanel();
		panelSur.add(panel_num5);
		
		JButton boton_num5 = new JButton("5");
		panel_num5.add(boton_num5);
		
		JPanel panel_num6 = new JPanel();
		panelSur.add(panel_num6);
		
		JButton boton_num6 = new JButton("6");
		panel_num6.add(boton_num6);
		
		JPanel panel_num7 = new JPanel();
		panelSur.add(panel_num7);
		
		JButton boton_num7 = new JButton("7");
		panel_num7.add(boton_num7);
		
		JPanel panel_num8 = new JPanel();
		panelSur.add(panel_num8);
		
		JButton boton_num8 = new JButton("8");
		panel_num8.add(boton_num8);
		
		JPanel panel_num9 = new JPanel();
		panelSur.add(panel_num9);
		
		JButton boton_num9 = new JButton("9");
		panel_num9.add(boton_num9);
		
		JPanel panel_vacio1 = new JPanel();
		panelSur.add(panel_vacio1);
		
		JPanel panel_num0 = new JPanel();
		panelSur.add(panel_num0);
		
		JButton boton_num0 = new JButton("0");
		panel_num0.add(boton_num0);
		
		JPanel panel_vacio2 = new JPanel();
		panelSur.add(panel_vacio2);
	}

}
