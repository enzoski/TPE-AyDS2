package servidor_secundario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_secundario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_secundario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_secundario.fila_servidor.GestionFila;

/**
 * Clase que hace de intermediaria entre la comunicación-gestión y el monitor del sistema.
 * Esperamos a recibir ciertos errores que nos informe el monitor, debido al no funcionamiento
 * de algun componente del sistema, y tomaremos la acción necesaria.
 * 
 */
public class ManejadorErroresServ2 {
	
	private static final int PORT_6 = 3250; // puerto para recibir informes de errores por parte del Monitor
	
	private ComunicacionLlamados comunicacionLlamados;
	private ComunicacionDeshabilitacion comunicacionDeshabilitacion;
	private GestionFila gestionFila;
	private HiloErroresServ2 hilo;
	
	public ManejadorErroresServ2(ComunicacionLlamados cLlamados, ComunicacionDeshabilitacion cDeshabilitacion, GestionFila gFila) {
		this.comunicacionLlamados = cLlamados;
		this.comunicacionDeshabilitacion = cDeshabilitacion;
		this.gestionFila = gFila;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloErroresServ2(this);
		this.hilo.start();
		
	}
	
	public synchronized void recibirError() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_6);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String componente = in.readLine();
				if(componente.equals("serv1")) { //falló el servidor primario
					// empezamos a escuchar en todos lados
					this.comunicacionLlamados.activarServer();
					this.comunicacionDeshabilitacion.activarServer();
					this.gestionFila.activarServer();
				} else 
					if(componente.equals("llamado")) //falló la mini-pc del TV
						this.comunicacionLlamados.errorLlamado();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
