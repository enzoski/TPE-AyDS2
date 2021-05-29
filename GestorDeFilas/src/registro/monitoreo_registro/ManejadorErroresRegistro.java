package registro.monitoreo_registro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import registro.controlador_registro.ControladorRegistro;

public class ManejadorErroresRegistro {
	
	private static final int PORT_7 = 3260; // puerto para...
	
	private ControladorRegistro controladorRegistro;
	private HiloErroresRegistro hilo;
	
	public ManejadorErroresRegistro(ControladorRegistro controladorRegistro) {
		this.controladorRegistro = controladorRegistro;
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
				if(componente.equals("serv1"))
					this.controladorRegistro.cambiarServidor(ip,2); //empiezo a usar el server 2
				else 
					if(componente.equals("serv2"))
						this.controladorRegistro.cambiarServidor(ip,1); //empiezo a usar el server 1
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
