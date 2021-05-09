package servidor.fila_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;


public class GestionFila {
	private Queue<String> clientes = new LinkedList<String>();
	private static final int PORT_1 = 2080;
	
	private RegistradorDNI hilo_1; // hilo para registrar los DNIs
	
	public GestionFila() {
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo_1 = new RegistradorDNI(this);
		this.hilo_1.start();
	}
	
	public void registro() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new
				InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				this.clientes.add(msg);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String proximoCliente() {
		return clientes.poll(); //devuelve null si no hay mas clientes
	}
	
	
}
