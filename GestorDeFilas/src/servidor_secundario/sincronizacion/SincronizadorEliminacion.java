package servidor_secundario.sincronizacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_secundario.fila_servidor.GestionFila;

public class SincronizadorEliminacion {
	
	private static final int PORT_SYNC_2 = 2510;
	
	private GestionFila gestorFila;
	private Eliminador hilo; // hilo para...
	
	public SincronizadorEliminacion(GestionFila gestorFila) {
		this.gestorFila = gestorFila;
		// instanciamos y activamos los hilos de los 'server socket'
		this.hilo = new Eliminador(this);
		this.hilo.start();
		
	}
	
	public synchronized void eliminar() { // viene el mensaje desde ControladorAtencion ??
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_SYNC_2);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				if(msg.equals("eliminar"))
					this.gestorFila.eliminarCliente();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}