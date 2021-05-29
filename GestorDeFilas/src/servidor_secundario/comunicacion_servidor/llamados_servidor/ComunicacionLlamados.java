package servidor_secundario.comunicacion_servidor.llamados_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_secundario.fila_servidor.GestionFila;

/**
 * Clase que gestiona los pedidos de llamar al pr�ximo cliente (por parte del componente de atenci�n),
 * mand�ndole el n� de box y DNI correspondientes al componente que muestra por pantalla los llamados a clientes.
 * 
 */
public class ComunicacionLlamados {

	// Para gestionar la comunicaci�n entre los distintos componenetes del sistema, haremos uso de varios ServerSocket
	// a lo largo de la aplicaci�n, que escuchen puertos diferentes para diferenciar los mensajes recibidos de Sockets Clientes.
	// Cada ServerSocket ser� activado por un hilo, ya que sino sus ciclos 'while(true)' congelar�an toda la aplicaci�n.
	private static final int PORT_3 = 3100; // puerto donde viene llamar prox cliente
	private static final int PORT_4 = 2110; //puerto para hacer llamados a prox cliente

	// disponibilidad
	private boolean flag = true;
	
	private String ipLlamado; // IP de la mini-PC
	private GestionFila gestorFila; // referencia a la clase que gestiona la fila de clientes (DNIs)
	private RecibidorLlamados hilo; // hilo para recibir pedidos de llamados
	
	public ComunicacionLlamados(GestionFila gestorFila, String ipLlamado) {
		this.ipLlamado = ipLlamado;
		this.gestorFila = gestorFila;
		// instanciamos y activamos el hilo del 'server socket'
		//this.hilo = new RecibidorLlamados(this);
		//this.hilo.start(); sacamos esto porque estamos en el server secundario y no debe empezar a escuchar de una.
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
				if(dni != null) // si fuera null, no har�amos la comunicacion con el componente 'llamado' y listo
					this.realizarLlamado(box, dni);
				if(this.flag) // le respondemos al componente 'atencion', mandandole el pr�ximo DNI, y �l decidir� qu� hacer si es null
					out.println(dni);
				else
					System.out.println("No se alcanz� el TV [DNI: " + dni + "]"); // EN EL FUTURO PODEMOS MANDAR ESTO DEVUELTA A LA COLA.
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
			// mandamos la dupla box-dni con un 'token' entre medio para luego saber c�mo parsear el mensaje
			String msj = box + "#" + dni;
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
