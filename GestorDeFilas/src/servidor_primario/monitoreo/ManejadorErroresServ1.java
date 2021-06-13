package servidor_primario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_primario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;

/**
 * Clase que hace de intermediaria entre la comunicación de llamados y el monitor del sistema.
 * Esperamos a recibir ciertos errores que nos informe el monitor, debido al no funcionamiento
 * de algun componente del sistema, y tomaremos la acción necesaria.
 * 
 */
public class ManejadorErroresServ1 {
	
	private static final int PORT_5 = 3240; // puerto para recibir informes de errores por parte del Monitor
	
	private ComunicacionLlamados comunicacionLlamados;
	private HiloErroresServ1 hilo;
	
	public ManejadorErroresServ1(ComunicacionLlamados comunicacionLlamados) {
		this.comunicacionLlamados = comunicacionLlamados;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloErroresServ1(this);
		this.hilo.start();
		
	}
	
	public synchronized void recibirError() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_5);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String componente = in.readLine();
				if(componente.equals("llamado")) //falló la mini-pc del TV
					this.comunicacionLlamados.errorLlamado(); // lo podriamos obviar?
				else
					if(componente.equals("serv2")) //falló el servidor secundario
						System.out.println("El servidor secundario no funciona."); // lo podriamos obviar y ponerlo en el Monitor o en el catch de Sincronizacion
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
