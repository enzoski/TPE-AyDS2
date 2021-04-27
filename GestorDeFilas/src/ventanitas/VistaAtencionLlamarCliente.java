package ventanitas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Color;

public class VistaAtencionLlamarCliente extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAtencionLlamarCliente frame = new VistaAtencionLlamarCliente();
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
	public VistaAtencionLlamarCliente() {
		setTitle("Atenci\u00F3n: llamar cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(20, 20));
		setContentPane(contentPane);
		
		JPanel panel_central = new JPanel();
		contentPane.add(panel_central, BorderLayout.CENTER);
		panel_central.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_llamar = new JPanel();
		panel_central.add(panel_llamar);
		panel_llamar.setLayout(new BorderLayout(0, 0));
		
		JButton boton_llamar = new JButton("Llamar al siguiente Cliente");
		boton_llamar.setForeground(new Color(0, 128, 0));
		boton_llamar.setBackground(new Color(0, 255, 0));
		boton_llamar.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_llamar.add(boton_llamar);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);
		
		JPanel panel_sur = new JPanel();
		contentPane.add(panel_sur, BorderLayout.SOUTH);
		
		JPanel panel_desconectar = new JPanel();
		panel_sur.add(panel_desconectar);
		panel_desconectar.setLayout(new BorderLayout(0, 0));
		
		JButton boton_desconectar = new JButton("Desconectar");
		boton_desconectar.setForeground(new Color(220, 20, 60));
		boton_desconectar.setFont(new Font("Tahoma", Font.BOLD, 12));
		boton_desconectar.setBackground(Color.RED);
		panel_desconectar.add(boton_desconectar, BorderLayout.CENTER);
	}

}
