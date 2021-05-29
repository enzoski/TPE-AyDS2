package servidor_primario.fila_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ResincronizaFila {
	
	private static final int PORT_10 = 3300; 
	private GestionFila gestor;
	private HiloResincronizador hilo;
	
	public ResincronizaFila(GestionFila gestor) {
		this.gestor = gestor;
		this.hilo = new HiloResincronizador(this);
		this.hilo.start();
	}
	public synchronized void registro() { // viene el mensaje desde servidor 2
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_10);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String dni = in.readLine();
				this.gestor.agregarCliente(dni);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
