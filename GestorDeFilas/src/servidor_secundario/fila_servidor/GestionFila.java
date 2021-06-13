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
import servidor_secundario.persistencia_secundaria.PersistenciaTemplate;

/**
 * Clase que gestiona el agregar o eliminar DNIs de la fila de clientes.
 * 
 */
public class GestionFila {
	
	private static final int PORT_1 = 3080; // puerto donde vienen los DNIs de los clientes
	
	// Utilizamos una LinkedList ya que implementa la interfaz Queue que nos permite hacer '.poll()'.
	private Queue<String> clientes = new LinkedList<String>(); // lista doblemente enlazada
	private RegistradorDNI hilo; // hilo para registrar los DNIs
	
	// atributo del tipo de la interfaz que gestiona el orden de llamados [Patrón de diseño GoF: Strategy]
	private I_LlamadoStrategy algoritmoLlamado;
		
	//repositorio clientes
	private I_RepositorioClientes repositorioClientes;
		
	//persistencia (logs de llamados y registros de clientes) [Patrón de diseño GoF: Template Method]
	private PersistenciaTemplate persistencia;
	
	public GestionFila(String tipoOrdenLlamado, I_RepositorioClientes repositorioClientes, PersistenciaTemplate persistencia) {
		
		this.repositorioClientes = repositorioClientes;
		this.persistencia = persistencia;
		
		// Instanciamos al Factory del algoritmo de llamados correspondiente, para luego pedirle una instancia.
		// Factory Method + Strategy
		CreadorAlgoritmoLlamado creador = null;
		if(tipoOrdenLlamado.equals("llegada"))
			creador = new CreadorLlamadoPorLlegada(this.clientes);
		else
			if(tipoOrdenLlamado.equals("categoria"))
				creador = new CreadorLlamadoPorCategoria(this.clientes, this.repositorioClientes);
			else
				if(tipoOrdenLlamado.equals("DNI"))
					creador = new CreadorLlamadoPorDNI(this.clientes);
		this.algoritmoLlamado = creador.crearAlgoritmoLlamado();
		
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
	
	public void eliminarCliente(String dni) {
		//this.clientes.poll(); AHORA COMO HAY DISTINTOS ALGORITMOS DE LLAMADOS, HAY QUE ELIMINAR UN DNI CONCRETO AL SINCRONIZAR.
		this.clientes.remove(dni);
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
	}
			
	public void desactivarServer() {
		this.hilo.stop();
	}
	
	
}
