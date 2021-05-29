package servidor_secundario.sincronizacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_secundario.fila_servidor.GestionFila;

public class SincronizadorAgregacion {
	
	private static final int PORT_SYNC_1 = 2500;
	
	private GestionFila gestorFila;
	private Agregador hilo; // hilo para...
	
	public SincronizadorAgregacion(GestionFila gestorFila) {
		this.gestorFila = gestorFila;
		// instanciamos y activamos los hilos de los 'server socket'
		this.hilo = new Agregador(this);
		this.hilo.start();
		
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

}