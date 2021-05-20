package llamado.controlador_llamado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import llamado.vista_llamado.VistaLlamadoTV;

/**
 * Clase que hace de intermediario entre la pantalla que muestra los llamados a clientes y el servidor del sistema.
 * Espera recibir el aviso por parte del servidor de que se elimine el último llamado de un determinado n° de box
 * (debido a su desconexión), y cuando esto ocurra, le informa a la vista que lo elimine del listado de llamados.
 *
 */
public class ControladorEliminacion { // LE AGREGARIA EL 'LLAMADO' AL FINAL DEL NOMBRE DE LA CLASE.

	private static final int PORT_2 = 2120; // puerto para eliminar un box que se desconectó
	
	private VistaLlamadoTV vistaLlamados;
	private EliminadorLlamados hilo; // hilo para eliminar un box
	
	public ControladorEliminacion(VistaLlamadoTV vistaLlamados) {
		this.vistaLlamados = vistaLlamados;
		this.vistaLlamados.abrirVentana();
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new EliminadorLlamados(this);
		this.hilo.start();
	}
	
	public synchronized void eliminarBox() { // viene el mensaje desde ComunicacionDeshabilitacion (servidor)
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_2);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String box = in.readLine();
				this.vistaLlamados.eliminarUltimoLlamado(box);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
