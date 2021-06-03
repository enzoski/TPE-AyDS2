package servidor_primario.comunicacion_servidor.llamados_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_primario.fila_servidor.GestionFila;

/**
 * Clase que gestiona los pedidos de llamar al próximo cliente (por parte del componente de atención),
 * mandándole el n° de box y DNI correspondientes al componente que muestra por pantalla los llamados a clientes.
 * 
 */
public class ComunicacionLlamados {

	// Para gestionar la comunicación entre los distintos componenetes del sistema, haremos uso de varios ServerSocket
	// a lo largo de la aplicación, que escuchen puertos diferentes para diferenciar los mensajes recibidos de Sockets Clientes.
	// Cada ServerSocket será activado por un hilo, ya que sino sus ciclos 'while(true)' congelarían toda la aplicación.
	private static final int PORT_3 = 2100; // puerto donde viene llamar prox cliente
	private static final int PORT_4 = 2110; //puerto para hacer llamados a prox cliente
	
	private String ipLlamado; // IP de la mini-PC
	private GestionFila gestorFila; // referencia a la clase que gestiona la fila de clientes (DNIs)
	private RecibidorLlamados hilo; // hilo para recibir pedidos de llamados
	
	// disponibilidad
	private boolean flag = true;
	
	public ComunicacionLlamados(GestionFila gestorFila, String ipLlamado) {
		this.ipLlamado = ipLlamado;
		this.gestorFila = gestorFila;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new RecibidorLlamados(this);
		this.hilo.start();
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
				if(dni != null) // si fuera null, no haríamos la comunicacion con el componente 'llamado' y listo
					this.realizarLlamado(box, dni);
				if(this.flag) // si hay conexión con la mini-pc de la TV de llamados
					out.println(dni); // le respondemos al componente 'atencion', mandandole el próximo DNI, y él decidirá qué hacer si es null
				else {
					out.println("errorTV"); // le respondemos al componente 'atencion' que hubo un error al intentar comunicarse con la TV
					this.gestorFila.reAgregarCliente(dni); // volvemos a colocar el dni al principio de la fila
					System.out.println("No se alcanzó el TV [DNI: " + dni + "]"); // EN EL FUTURO PODEMOS MANDAR ESTO DEVUELTA A LA COLA.
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

	public void realizarLlamado(String box,String dni) { // va el mensaje a ControladorLlamado
		try {
			Socket socket = new Socket(ipLlamado, PORT_4);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// mandamos la dupla box-dni con un 'token' entre medio para luego saber cómo parsear el mensaje
			String msj = box + "#" + dni;
			out.println(msj);
			out.close();
			socket.close();
			this.flag = true;
		}
		catch (Exception e) {
			//e.printStackTrace();
			this.flag = false;
		}
	}
	
	// pensandolo bien, es necesario que el monitor avise al server ante un fallo de 'llamado'?
	// total eso se detecta en el socket de acá arriba. Si entra al catch, justamente no le pudo mandar nada a la TV.
	public void errorLlamado() {
		this.flag = false;
	}
	
	// REPOSITORIO CLIENTES
	public void buscarNombreCliente(String dni) {
		// armar bien el XML y luego implementar esto
	}
	
	public void buscarCategoriaCliente(String dni) {
		// armar bien el XML y luego implementar esto
	}
	
	
}
