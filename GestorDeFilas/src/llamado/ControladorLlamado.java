package llamado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorLlamado {
	
	private VistaLlamadoTV vistaLlamados;
	private static final int PORT_1= 2110; //puerto para hacer llamados
	private AtendedorLlamados hilo; // hilo para hacer llamados

	
	public ControladorLlamado(VistaLlamadoTV vistaLlamados) {
		this.vistaLlamados = vistaLlamados;
		this.vistaLlamados.abrirVentana();
		// instanciamos y activamos los hilos de los 'server socket'
		this.hilo = new AtendedorLlamados(this);
		this.hilo.start();
	}
	
	public synchronized void hacerLlamado() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new
				InputStreamReader(socket.getInputStream()));
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
