package servidor_secundario.sincronizacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_secundario.fila_servidor.GestionFila;

public class Sincronizador {
	
	private static final int PORT_SYNC_1 = 2500;
	private static final int PORT_SYNC_2 = 2510;
	
	private GestionFila gestorFila;
	private Agregador hilo_1; // hilo para...
	private Eliminador hilo_2; // hilo para...
	
	public Sincronizador(GestionFila gestorFila) {
		this.gestorFila = gestorFila;
		// instanciamos y activamos los hilos de los 'server socket'
		this.hilo_1 = new Agregador(this);
		this.hilo_2 = new Eliminador(this);
		this.hilo_1.start();
		this.hilo_2.start();
		
	}
	
	public synchronized void agregar() { // viene el mensaje desde ControladorAtencion ??
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_SYNC_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String dni = in.readLine();
				this.gestorFila.agregarCliente(dni);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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