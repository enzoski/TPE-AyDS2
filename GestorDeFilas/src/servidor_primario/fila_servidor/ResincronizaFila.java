package servidor_primario.fila_servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que recibe los DNIs de la fila de clientes del servidor secundario, cuando sea necesario
 * resincronizar el estado de nuestra fila, por haber tenido algun falló en este servidor primario
 * que provocó su desconexión, y ahora estamos activos nuevamente.
 * @author Enzo
 *
 */
public class ResincronizaFila {
	
	private static final int PORT_10 = 3300; // puerto para recibir por parte del servidor secundario los DNIs de la fila
	
	private GestionFila gestor;
	private HiloResincronizador hilo;
	
	public ResincronizaFila(GestionFila gestor) {
		this.gestor = gestor;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloResincronizador(this);
		this.hilo.start();
	}
	
	public synchronized void registro() { // viene el mensaje desde el servidor 2 [ResincronizadorServ2]
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_10);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String dni = in.readLine();
				this.gestor.agregarCliente(dni);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
