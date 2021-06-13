package servidor_secundario.comunicacion_servidor.llamados_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import servidor_secundario.I_RepositorioClientes;
import servidor_secundario.fila_servidor.GestionFila;
import servidor_secundario.persistencia_secundaria.PersistenciaTemplate;

/**
 * Clase que gestiona los pedidos de llamar al próximo cliente (por parte del componente de atención),
 * mandándole el n° de box y DNI correspondientes al componente que muestra por pantalla los llamados a clientes.
 * 
 */
public class ComunicacionLlamados {

	// Para gestionar la comunicación entre los distintos componenetes del sistema, haremos uso de varios ServerSocket
	// a lo largo de la aplicación, que escuchen puertos diferentes para diferenciar los mensajes recibidos de Sockets Clientes.
	// Cada ServerSocket será activado por un hilo, ya que sino sus ciclos 'while(true)' congelarían toda la aplicación.
	private static final int PORT_3 = 3100; // puerto donde viene llamar prox cliente
	private static final int PORT_4 = 2110; //puerto para hacer llamados a prox cliente
	
	private String ipLlamado; // IP de la mini-PC
	private GestionFila gestorFila; // referencia a la clase que gestiona la fila de clientes (DNIs)
	private RecibidorLlamados hilo; // hilo para recibir pedidos de llamados
	
	// disponibilidad
	private boolean flag = true;
	
	// repositorio
	private I_RepositorioClientes repositorioClientes;
		
	//persistencia (logs de llamados y registros de clientes) [Patrón de diseño GoF: Template Method]
	private PersistenciaTemplate persistencia;
	
	public ComunicacionLlamados(GestionFila gestorFila, String ipLlamado, I_RepositorioClientes repositorioClientes, PersistenciaTemplate persistencia) {
		this.ipLlamado = ipLlamado;
		this.gestorFila = gestorFila;
		
		this.repositorioClientes = repositorioClientes;
		this.persistencia = persistencia;
		
	}
	
	public synchronized void pedidoLlamado() { // viene el mensaje desde ControladorAtencion
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_3);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String box = in.readLine();
				String dni = this.gestorFila.proximoCliente();
				if(dni != null) { // si fuera null, no haríamos la comunicacion con el componente 'llamado' y listo
					String nombre = this.repositorioClientes.getCliente(dni).getNombre();
					this.realizarLlamado(box, nombre);
				}
				if(this.flag) { // si hay conexión con la mini-pc de la TV de llamados
					out.println(dni); // le respondemos al componente 'atencion', mandandole el próximo DNI, y él decidirá qué hacer si es null
					if(dni != null) { // si no hay mas clientes por llamar, no se persiste esa petición de llamado
						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // para darle formato a una fecha y hora
						LocalDateTime now = LocalDateTime.now(); // instancia de la fecha y hora actual del sistema
						String fecha = dtf.format(now);
						this.persistencia.persistirLlamado(fecha, box, dni);
					}
				}
				else {
					out.println("errorTV"); // le respondemos al componente 'atencion' que hubo un error al intentar comunicarse con la TV
					this.gestorFila.reAgregarCliente(dni); // volvemos a colocar el dni al principio de la fila
					System.out.println("No se alcanzó el TV [DNI: " + dni + "]");
					// de esta forma, evitamos que 'atencion' realice llamados (sacar dni's de la fila) cuando la mini-pc de la TV no anda
				}
				out.close();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void realizarLlamado(String box, String nombre) { // va el mensaje a ControladorLlamado
		try {
			Socket socket = new Socket(ipLlamado, PORT_4);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// mandamos la dupla box-dni con un 'token' entre medio para luego saber cómo parsear el mensaje
			String msj = box + "#" + nombre;
			out.println(msj);
			out.close();
			socket.close();
			this.flag = true;
		}
		catch (Exception e) {
			this.flag = false;
		}
	}
	
	// disponibilidad
	public void activarServer() {
		this.hilo = new RecibidorLlamados(this); // si lo inicializabamos en el constructor, no andaba
		this.hilo.start();
	}
		
	public void desactivarServer() {
		this.hilo.stop();
	}
	
	public void errorLlamado() {
		this.flag = false;
	}
	
}
