package atencion.monitoreo_atencion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import atencion.controlador_atencion.ControladorAtencion;

public class ManejadorErroresAtencion {
	
	private int PORT_4 = 4000; // puerto para recibir informes de errores por parte del Monitor
	private ControladorAtencion controladorAtencion;
	private HiloErroresAtencion hilo;
	
	private boolean flag = true;
	
	public ManejadorErroresAtencion(ControladorAtencion controladorAtencion) {
		this.controladorAtencion = controladorAtencion;
		this.hilo = new HiloErroresAtencion(this);
		this.hilo.start();
		this.PORT_4 += this.controladorAtencion.getNumBox(); // ESTO DEBE HACERSE SOLO CUANDO EL BOX SE HABILITA, SINO DEVOLVERÁ SIEMPRE -1.
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
	
	public void desactivarManejador() {
		this.flag = false;
		this.hilo.stop();
	}
	
	

}
