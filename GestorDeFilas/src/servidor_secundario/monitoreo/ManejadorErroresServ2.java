package servidor_secundario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_secundario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_secundario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_secundario.fila_servidor.GestionFila;

public class ManejadorErroresServ2 {
	
	private static final int PORT_6 = 3250; // puerto para...
	
	private ComunicacionLlamados comunicacionLlamados;
	private ComunicacionDeshabilitacion comunicacionDeshabilitacion;
	private GestionFila gestionFila;
	private HiloErroresServ2 hilo;
	
	public ManejadorErroresServ2(ComunicacionLlamados cLlamados, ComunicacionDeshabilitacion cDeshabilitacion, GestionFila gFila) {
		this.comunicacionLlamados = cLlamados;
		this.comunicacionDeshabilitacion = cDeshabilitacion;
		this.gestionFila = gFila;
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
				if(componente.equals("serv1")) {
					this.comunicacionLlamados.activarServer();
					this.comunicacionDeshabilitacion.activarServer();
					this.gestionFila.activarServer();
				} else 
					if(componente.equals("llamado"))
						this.comunicacionLlamados.errorLlamado();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
