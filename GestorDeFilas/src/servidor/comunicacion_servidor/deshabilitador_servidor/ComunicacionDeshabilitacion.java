package servidor.comunicacion_servidor.deshabilitador_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que gestiona los pedidos de deshabilitación de box's (por parte del componente de atención),
 * mandándole el n° de box correspondiente al componente que muestra por pantalla los llamados a clientes.
 * 
 */
public class ComunicacionDeshabilitacion {

	private static final int PORT_1 = 2090; // de aca viene el aviso de deshabilitacion
	private static final int PORT_2 = 2120; // aca va el aviso de deshabilitacion
	
	private String ipLlamado; // IP de la mini-PC
	private DeshabilitadorBox hilo; // hilo para deshabilitar un box
	
	public ComunicacionDeshabilitacion(String ipLlamado) {
		this.ipLlamado = ipLlamado;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new DeshabilitadorBox(this);
		this.hilo.start();
	}
	
	public synchronized void deshabilitarBox() { // viene el mensaje desde ControladorAtencion
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String box = in.readLine();
				this.avisoDeshabilitacion(box);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void avisoDeshabilitacion(String box) { // va el mensaje a ControladorEliminacionLlamado
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
