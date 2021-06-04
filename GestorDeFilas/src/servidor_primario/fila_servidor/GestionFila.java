package servidor_primario.fila_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import servidor_primario.RepositorioClientes;
import servidor_primario.persistencia_primaria.I_Persistencia;
import servidor_primario.sincronizacion.Sincronizador;

/**
 * Clase que gestiona el agregar o eliminar DNIs de la fila de clientes.
 * 
 */
public class GestionFila {
	
	private static final int PORT_1 = 2080; // puerto donde vienen los DNIs de los clientes
	
	// Utilizamos una LinkedList ya que implementa la interfaz Queue que nos permite hacer '.poll()'.
	private Queue<String> clientes = new LinkedList<String>(); // lista doblemente enlazada
	private RegistradorDNI hilo; // hilo para registrar los DNIs
	
	// disponibilidad
	private Sincronizador sincronizador;
	
	// aca pondriamos un atributo con la interfaz que gestiona el orden de llamados.
	private I_OrdenLlamado algoritmoLlamado;
	
	//repositorio clientes
	private RepositorioClientes repositorioClientes;
	
	//persistencia (logs de llamados y registros de clientes)
	private I_Persistencia persistencia;
	
	public GestionFila(String ipServ2, String tipoOrdenLlamado, RepositorioClientes repositorioClientes, I_Persistencia persistencia) { // IP del servidor secundario
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new RegistradorDNI(this);
		this.hilo.start();
		
		this.sincronizador = new Sincronizador(ipServ2);
		
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
				this.clientes.add(dni);
				this.sincronizador.agregar(dni); //disponibilidad
				String fecha = ""; // PEDIRLE AL SISTEMA QUE NOS DE LA FECHA Y HORA ACTUAL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				this.persistencia.persistirRegistro(fecha, dni);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String proximoCliente() {
		this.sincronizador.eliminar();
		// '.poll()' es una forma optimizada de devolver y eliminar la cabeza de la lista (primer elemento).
		// return this.clientes.poll(); // devuelve null si no hay mas clientes (DNIs)
		
		// podriamos retornar Interfaz.proximoCliente()
		return this.algoritmoLlamado.proximoCliente();
	}
	
	public void agregarCliente(String dni) {
		this.clientes.add(dni);
	}
	
	public void reAgregarCliente(String dni) {
		LinkedList<String> aux = (LinkedList<String>) this.clientes; //tuve que hacer el cast para usar el método addFirst()
		aux.addFirst(dni); // agrega el DNI al principio de la fila
	}
}
