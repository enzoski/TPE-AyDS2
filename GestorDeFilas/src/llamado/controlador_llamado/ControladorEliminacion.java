package llamado.controlador_llamado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import llamado.vista_llamado.VistaLlamadoTV;

public class ControladorEliminacion {

	private VistaLlamadoTV vistaLlamados;
	private static final int PORT_2= 2120; //puerto para eliminar un box que se desconectó
	private EliminadorLlamados hilo;
	
	public ControladorEliminacion(VistaLlamadoTV vistaLlamados) {
		this.vistaLlamados = vistaLlamados;
		this.vistaLlamados.abrirVentana();
		// instanciamos y activamos los hilos de los 'server socket'
		this.hilo = new EliminadorLlamados(this);
		this.hilo.start();
	}
	
	public synchronized void eliminarBox() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_2);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new
				InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				this.vistaLlamados.eliminarUltimoLlamado(msg);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
