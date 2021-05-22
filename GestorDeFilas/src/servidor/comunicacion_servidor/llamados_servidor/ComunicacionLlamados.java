package servidor.comunicacion_servidor.llamados_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor.fila_servidor.GestionFila;

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
				// le respondemos al componente 'atencion', mandandole el próximo DNI, y él decidirá qué hacer si es null
				out.println(dni);
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
