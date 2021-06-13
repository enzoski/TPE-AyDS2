package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que recibe un aviso por parte del componente 'atencion' (box) o 'registro' (totem),
 * cuando estos son instanciados (ejecutados); as� el monitor empezar� a monitorearlos.
 *
 */
public class RecibidorComponentesActivos {
	
	private static final int PORT= 3700;
	
	private Monitor monitor;
	private HiloComponentesActivos hilo;
	
	public RecibidorComponentesActivos(Monitor monitor) {
		this.monitor = monitor;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloComponentesActivos(this);
		this.hilo.start();
	}
	
	public void activarComponente() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				//parseo del componente y n�mero recibido de un componente
				String[] arreglo = msg.split("#");
				String componente = arreglo[0]; // puede ser Box o Totem
				String num = arreglo[1]; // corresponde al n�mero de instancia
				int numero= Integer.valueOf(num);
				if(componente.equals("box"))
					this.monitor.agregarBoxActivo(numero);
				else
					if(componente.equals("totem"))
						this.monitor.agregarTotemActivo(numero);
					else
						if(componente.equals("box_desactivado"))
							this.monitor.eliminarBoxActivo(numero);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
