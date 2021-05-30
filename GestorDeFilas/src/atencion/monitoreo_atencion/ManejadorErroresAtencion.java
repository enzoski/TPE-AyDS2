package atencion.monitoreo_atencion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import atencion.controlador_atencion.ControladorAtencion;

public class ManejadorErroresAtencion {
	
	private static final int PORT_4 = 3230; // puerto para recibir informes de errores por parte del Monitor
	
	private ControladorAtencion controladorAtencion;
	private HiloErroresAtencion hilo;
	
	public ManejadorErroresAtencion(ControladorAtencion controladorAtencion) {
		this.controladorAtencion = controladorAtencion;
		this.hilo = new HiloErroresAtencion(this);
		this.hilo.start();
		
	}
	
	public synchronized void recibirError() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_4);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				//parseo del componente e ip recibido del monitor
				String[] arreglo = msg.split("#");
				String componente = arreglo[0];
				String ip = arreglo[1];
				if(componente.equals("serv1"))
					this.controladorAtencion.cambiarServidor(ip,2); //empiezo a usar el server 2
				else
					if(componente.equals("serv2"))
						this.controladorAtencion.cambiarServidor(ip,1); //empiezo a usar el server 1
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
