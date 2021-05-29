package llamado.monitoreo_llamado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import llamado.controlador_llamado.ControladorLlamado;

public class ManejadorErroresLlamado {
	
	private static final int PORT_8 = 3270; // puerto para...
	
	private ControladorLlamado controladorLlamado;
	private HiloErroresLlamado hilo;
	
	public ManejadorErroresLlamado(ControladorLlamado controladorLlamado) {
		this.controladorLlamado = controladorLlamado;
		this.hilo = new HiloErroresLlamado(this);
		this.hilo.start();
		
	}
	
	public synchronized void recibirError() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_8);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String componente = in.readLine();
				if(componente.equals("serv1"))
					this.controladorLlamado.cambiarServidor(2);
				else 
					if(componente.equals("serv1"))
						this.controladorLlamado.cambiarServidor(1);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
