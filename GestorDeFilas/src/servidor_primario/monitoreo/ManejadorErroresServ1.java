package servidor_primario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import servidor_primario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;

public class ManejadorErroresServ1 {
	
	private static final int PORT_5 = 3240; // puerto para...
	
	private ComunicacionLlamados comunicacionLlamados;
	private HiloErroresServ1 hilo;
	
	public ManejadorErroresServ1(ComunicacionLlamados comunicacionLlamados) {
		this.comunicacionLlamados = comunicacionLlamados;
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
				if(componente.equals("llamado"))
					this.comunicacionLlamados.errorLlamado();
				else
					if(componente.equals("serv2"))
						System.out.println("El servidor secundario no funciona.");
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
