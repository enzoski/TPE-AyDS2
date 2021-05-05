package atencion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ControladorAtencion {
	
	private static final String IP = "192.168.0.7"; // inicializarla bien cuando lo sepamos
	private static final int PORT_1 = 90; // puerto para deshabilitar box
	private static final int PORT_2 = 100; // puerto para llamar prox cliente
	
	private VistaAtencionInicio vistaInicio;
	private VistaAtencionLlamarCliente vistaLlamarCliente;
	private int boxActual = -1;
	
	public ControladorAtencion(VistaAtencionInicio vistaInicio, VistaAtencionLlamarCliente vistaLlamarCliente) {
		this.vistaInicio = vistaInicio;
		this.vistaLlamarCliente = vistaLlamarCliente;
		
		this.vistaInicio.setVisible(true);
	}
	
	public void habilitarBox(int num) {
		if(num >= 0) {
			this.boxActual = num;
			this.vistaInicio.setVisible(false);
			this.vistaLlamarCliente.setVisible(true);
		}
		else
			this.vistaInicio.errorBox();
	}
	
	public void deshabilitarBox() {//agregar al diagrama de secuencia lo del socket.
		// avisar al server el nro de box
		this.avisoDeshabilitacion(this.boxActual);
		this.boxActual = -1;
		this.vistaLlamarCliente.setVisible(false);
		this.vistaInicio.setVisible(true);
	}
	
	private void avisoDeshabilitacion(int box) {
		try {
			Socket socket = new Socket(IP,PORT_1);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(box);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void llamarProximoCliente() {
		try {
			Socket socket = new Socket(IP,PORT_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(this.boxActual);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
