package servidor_secundario.fila_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Clase que gestiona el agregar o eliminar DNIs de la fila de clientes.
 * 
 */
public class GestionFila {
	
	private static final int PORT_1 = 3080; // puerto donde vienen los DNIs de los clientes
	
	// Utilizamos una LinkedList ya que implementa la interfaz Queue que nos permite hacer '.poll()'.
	private Queue<String> clientes = new LinkedList<String>(); // lista doblemente enlazada
	private RegistradorDNI hilo; // hilo para registrar los DNIs
	
	public GestionFila() {
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new RegistradorDNI(this);
		//this.hilo.start(); sacamos esto porque estamos en el server secundario y no debe empezar a escuchar de una.
	}
	
	public synchronized void registro() { // viene el mensaje desde ControladorRegistro
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String dni = in.readLine();
				this.clientes.add(dni);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String proximoCliente() {
		// '.poll()' es una forma optimizada de devolver y eliminar la cabeza de la lista (primer elemento).
		return this.clientes.poll(); // devuelve null si no hay mas clientes (DNIs)
	}
	
	public void agregarCliente(String dni) {
		this.clientes.add(dni);
	}
	
	public void eliminarCliente() {
		this.clientes.poll();
	}
	
	// disponibilidad
	public void activarServer() {
		this.hilo.start();
	}
			
	public void desactivarServer() {
		this.hilo.stop();
	}
	
	
}
