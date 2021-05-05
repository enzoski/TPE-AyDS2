package registro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ControladorRegistro {
	
	private static final String IP = "192.168.0.7"; // inicializarla bien cuando lo sepamos
	private static final int PORT = 80; //podriamos poner un puerto distinto para cada 'subsistema'
	
	private VistaRegistro vistaRegistro;
	
	public ControladorRegistro(VistaRegistro vistaRegistro) { //ver donde poner la referencia de la otra ventanita
		this.vistaRegistro = vistaRegistro;
		this.vistaRegistro.setVisible(true); //revisar bien donde iria esto.
	}
	
	public void enviarDNI(String dni) {
		boolean valido = this.verificarDNI(dni);
		if(valido)
			this.registrarDNI(dni);
		else
			this.vistaRegistro.errorDNI();
	}
	
	private boolean verificarDNI(String dni) {
		int longDNI = dni.length();
		boolean charsValidos = false;
		boolean longitudValida = longDNI == 7 || longDNI == 8; // ver si sacamos la condicion del 7.
		if(longitudValida) {
			int i = 0;
			while(i < longDNI && (dni.charAt(i) >= '0' && dni.charAt(i) <= '9')) {
				i++;
			}
			if(i == longDNI) {
				charsValidos = true;
			}
		}
		return charsValidos;
	}
	
	private void registrarDNI(String dni) {
		try {
			Socket socket = new Socket(IP,PORT);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
