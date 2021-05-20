package llamado.controlador_llamado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import llamado.vista_llamado.VistaLlamadoTV;

/**
 * Clase que hace de intermediario entre la pantalla que muestra los llamados a clientes y el servidor del sistema.
 * Espera recibir el aviso por parte del servidor de que se muestre un nuevo llamado (DNI-box) en pantalla,
 * y cuando esto ocurra, le informa a la vista que lo agregue al listado de llamados.
 *
 */
public class ControladorLlamado {
	
	private static final int PORT_1 = 2110; //puerto para hacer llamados
	
	private VistaLlamadoTV vistaLlamados;
	private AtendedorLlamados hilo; // hilo para hacer llamados
	
	public ControladorLlamado(VistaLlamadoTV vistaLlamados) {
		this.vistaLlamados = vistaLlamados;
		this.vistaLlamados.abrirVentana();
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new AtendedorLlamados(this);
		this.hilo.start();
	}
	
	public synchronized void hacerLlamado() { // viene el mensaje desde ComunicacionLlamados (servidor)
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				//parseo del num de box y dni recibido del servidor
				String[] arreglo = msg.split("#");
				String box = arreglo[0];
				String dni = arreglo[1];
				//eliminar ultimo llamado del box para agregar el nuevo
				this.vistaLlamados.eliminarUltimoLlamado(box);
				this.vistaLlamados.mostrarLlamado(dni,box);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
