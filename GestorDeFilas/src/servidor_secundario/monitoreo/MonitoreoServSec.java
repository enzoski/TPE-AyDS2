package servidor_secundario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que espera recibir 'ping' por parte del Monitor del Sistema, y le responde con un 'echo'.
 *
 */
public class MonitoreoServSec {
	
	private static final int PORT_3 = 3220; // puerto para hacer Echo al Monitor (responderle)
	
	private HiloMonitorServ2 hilo;
	
	public MonitoreoServSec() {
		this.hilo = new HiloMonitorServ2(this);
		this.hilo.start();
		
	}
	
	public synchronized void echoServ2() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_3);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				// le respondemos al componente 'monitor' lo mismo que nos mandó
				out.println(msg);
				out.close();
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}