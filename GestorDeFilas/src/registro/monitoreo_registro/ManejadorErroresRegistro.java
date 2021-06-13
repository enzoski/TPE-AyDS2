package registro.monitoreo_registro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import registro.controlador_registro.ControladorRegistro;

/**
 * Clase que hace de intermediaria entre el controlador de registro y el monitor del sistema.
 * Esperamos a recibir ciertos errores que nos informe el monitor, debido al no funcionamiento
 * de algun componente del sistema. En este caso, ante la caida del servidor primario, nos conectaremos
 * al servidor secundario, y cuando el primario vuelva a funcionar, nos volveremos a conectar a él.
 * 
 */
public class ManejadorErroresRegistro {
	
	private int PORT_7 = 4100; // puerto base para recibir informes de errores por parte del Monitor
	
	private ControladorRegistro controladorRegistro;
	private HiloErroresRegistro hilo;
	
	public ManejadorErroresRegistro(ControladorRegistro controladorRegistro) {
		this.controladorRegistro = controladorRegistro;
		this.PORT_7 += this.controladorRegistro.getNumTotem(); // puerto absoluto (así cada totem escuchará puertos distintos)
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloErroresRegistro(this);
		this.hilo.start();
	}
	
	public synchronized void recibirError() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_7);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				//parseo del componente e ip recibido del monitor
				String[] arreglo = msg.split("#");
				String componente = arreglo[0];
				String ip = arreglo[1];
				if(componente.equals("serv1")) //falló el servidor primario
					this.controladorRegistro.cambiarServidor(ip, 2); //empiezo a usar el server 2
				else 
					if(componente.equals("serv2")) //falló el servidor secundario (realmentente significa que se reactivó el primario)
						this.controladorRegistro.cambiarServidor(ip, 1); //empiezo a usar el server 1
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
