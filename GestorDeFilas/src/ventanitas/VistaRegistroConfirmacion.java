package ventanitas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaRegistroConfirmacion extends JFrame {

	private JPanel contentPane;

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
		setMaximumSize(new Dimension(450, 300));
		setMinimumSize(new Dimension(350, 200));
		setTitle("Confirmaci\u00F3n Registro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel = new JPanel();
		panelCentral.add(panel);
		
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		JPanel panel_texto_dni = new JPanel();
		panel_1.add(panel_texto_dni);
		panel_texto_dni.setLayout(new BorderLayout(0, 0));
		
		JLabel label_texto_dni = new JLabel("DNI:");
		label_texto_dni.setHorizontalAlignment(SwingConstants.CENTER);
		label_texto_dni.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_texto_dni.add(label_texto_dni, BorderLayout.CENTER);
		
		JPanel panel_campo_dni = new JPanel();
		panel_1.add(panel_campo_dni);
		panel_campo_dni.setLayout(new BorderLayout(0, 0));
		
		JLabel label_dni = new JLabel("12.345.678");
		label_dni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_dni.setHorizontalAlignment(SwingConstants.CENTER);
		panel_campo_dni.add(label_dni, BorderLayout.CENTER);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JPanel panel_modificar = new JPanel();
		panelSur.add(panel_modificar);
		
		JButton boton_modificar = new JButton("Modificar");
		boton_modificar.setForeground(new Color(128, 0, 0));
		boton_modificar.setBackground(new Color(250, 128, 114));
		panel_modificar.add(boton_modificar);
		
		JPanel panel_confirmar = new JPanel();
		panelSur.add(panel_confirmar);
		
		JButton boton_confirmar = new JButton("Confirmar");
		boton_confirmar.setForeground(new Color(0, 0, 128));
		boton_confirmar.setBackground(new Color(30, 144, 255));
		panel_confirmar.add(boton_confirmar);
	}

}
