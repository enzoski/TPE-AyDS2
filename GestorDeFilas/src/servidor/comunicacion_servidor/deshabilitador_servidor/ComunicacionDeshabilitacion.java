package servidor.comunicacion_servidor.deshabilitador_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ComunicacionDeshabilitacion {

	
	private static final int PORT_1 = 2090; //de aca viene el aviso de deshabilitacion
	private static final int PORT_2 = 2120; //aca va el aviso de deshabilitacion
	private String ipLlamado; // la ip de la mini-PC
	private DeshabilitadorBox hilo;
	
	public ComunicacionDeshabilitacion(String ipLlamado) {
		this.ipLlamado = ipLlamado;
		// instanciamos y activamos los hilos de los 'server socket'
		this.hilo = new DeshabilitadorBox(this);
		this.hilo.start();
	}
	
	public synchronized void deshabilitarBox() { //viene el msj desde controladorAtencion
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new
				InputStreamReader(socket.getInputStream()));
				String box = in.readLine();
				this.avisoDeshabilitacion(box);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void avisoDeshabilitacion(String box) { //va el msj a ControladorLlamado
		try {
			Socket socket = new Socket(this.ipLlamado, PORT_2);
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
	
	
}
