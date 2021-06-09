package servidor_secundario.fila_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

import servidor_secundario.I_RepositorioClientes;
import servidor_secundario.persistencia_secundaria.I_Persistencia;

/**
 * Clase que gestiona el agregar o eliminar DNIs de la fila de clientes.
 * 
 */
public class GestionFila {
	
	private static final int PORT_1 = 3080; // puerto donde vienen los DNIs de los clientes
	
	// Utilizamos una LinkedList ya que implementa la interfaz Queue que nos permite hacer '.poll()'.
	private Queue<String> clientes = new LinkedList<String>(); // lista doblemente enlazada
	private RegistradorDNI hilo; // hilo para registrar los DNIs
	
	// aca pondriamos un atributo con la interfaz que gestiona el orden de llamados.
	private I_OrdenLlamado algoritmoLlamado;
		
	//repositorio clientes
	private I_RepositorioClientes repositorioClientes;
		
	//persistencia (logs de llamados y registros de clientes)
	private I_Persistencia persistencia;
	
	// para saber si el servidor secundario debe o no persistir el historial de llamados y registros [PERSISTENCIA]
	private boolean servidorActivo = false;
	
	public GestionFila(String tipoOrdenLlamado, I_RepositorioClientes repositorioClientes, I_Persistencia persistencia) {
		
		this.repositorioClientes = repositorioClientes;
		this.persistencia = persistencia;
		
		// quizas aca se podria aplicar algun patron
		if(tipoOrdenLlamado.equals("llegada"))
			this.algoritmoLlamado = new OrdenLlamadoPorLlegada(this.clientes);
		else
			if(tipoOrdenLlamado.equals("categoria"))
				this.algoritmoLlamado = new OrdenLlamadoPorCategoria(this.clientes, this.repositorioClientes);
			else
				if(tipoOrdenLlamado.equals("DNI"))
					this.algoritmoLlamado = new OrdenLlamadoPorDNI(this.clientes);
		
	}
	
	public synchronized void registro() { // viene el mensaje desde ControladorRegistro
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String dni = in.readLine();
				// Verificamos que el DNI que se quiere registrar, esté en el repositorio de clientes, y le respondemos a 'registro' si se registró bien o no.
				if(this.repositorioClientes.existeCliente(dni)) {
					this.clientes.add(dni);
					//persistencia
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // para darle formato a una fecha y hora
					LocalDateTime now = LocalDateTime.now(); // instancia de la fecha y hora actual del sistema
					String fecha = dtf.format(now);
					this.persistencia.persistirRegistro(fecha, dni);
					out.println("existe");
				}
				else
					out.println("no existe");
				out.close();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String proximoCliente() {
		return this.algoritmoLlamado.proximoCliente(); // devuelve null si no hay mas clientes (DNIs)
	}
	
	public void agregarCliente(String dni) {
		this.clientes.add(dni);
	}
	
	public void eliminarCliente() {
		this.clientes.poll();
	}
	
	public Queue<String> getClientes() {
		return this.clientes;
	}
	
	public void reAgregarCliente(String dni) {
		LinkedList<String> aux = (LinkedList<String>) this.clientes; //tuve que hacer el cast para usar el método addFirst()
		aux.addFirst(dni); // agrega el DNI al principio de la fila
	}
	
	// disponibilidad
	public void activarServer() {
		this.hilo = new RegistradorDNI(this); // si lo inicializabamos en el constructor, no andaba
		this.hilo.start();
		this.servidorActivo = true;
	}
			
	public void desactivarServer() {
		this.hilo.stop();
		this.servidorActivo = false;
	}
	
	public boolean isServidorActivo() {
		return servidorActivo;
	}
	
	
}
