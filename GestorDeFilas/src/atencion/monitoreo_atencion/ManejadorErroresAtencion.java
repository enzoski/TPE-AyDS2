package atencion.monitoreo_atencion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import atencion.controlador_atencion.ControladorAtencion;

/**
 * Clase que hace de intermediaria entre el controlador de atencion y el monitor del sistema.
 * Esperamos a recibir ciertos errores que nos informe el monitor, debido al no funcionamiento
 * de algun componente del sistema. En este caso, ante la caida del servidor primario, nos conectaremos
 * al servidor secundario, y cuando el primario vuelva a funcionar, nos volveremos a conectar a él.
 * Adicionalmente, cuando desde la vista de atencion desconectemos un box, y se le de aviso al monitor de ese evento,
 * acá nos avisará que se concretó la desactivacion y procederemos a dejar de recibir errores (cerramos el server socket).
 * 
 */
public class ManejadorErroresAtencion {
	
	private int PORT_4 = 4000; // puerto base para recibir informes de errores por parte del Monitor
	
	private ControladorAtencion controladorAtencion;
	private HiloErroresAtencion hilo;
	
	private boolean flag = true;
	
	public ManejadorErroresAtencion(ControladorAtencion controladorAtencion) {
		this.controladorAtencion = controladorAtencion;
		// esto debe hacerse solo cuando el box se habilita, sino devolverá siempre -1.
		this.PORT_4 += this.controladorAtencion.getNumBox(); // puerto absoluto (así cada box escuchará puertos distintos)
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloErroresAtencion(this);
		this.hilo.start();
	}
	
	public synchronized void recibirError() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_4);
			while (this.flag) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				//parseo del componente e ip recibido del monitor
				String[] arreglo = msg.split("#");
				String componente = arreglo[0];
				String ip = arreglo[1];
				if(componente.equals("serv1")) //falló el servidor primario
					this.controladorAtencion.cambiarServidor(ip, 2); //empiezo a usar el server 2
				else
					if(componente.equals("serv2")) //falló el servidor secundario (o bien se reactivó el primario)
						this.controladorAtencion.cambiarServidor(ip, 1); //empiezo a usar el server 1
					else 
						if(componente.equals("desactivar")) //el monitor nos confirma la desactivación del box
							this.flag = false; //dejo de escuchar errores
				socket.close();
			}
			serverSocket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
