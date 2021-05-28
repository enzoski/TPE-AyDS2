package llamado.monitoreo_llamado;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MonitoreoLlamado {
	
	private static final int PORT_1 = 3200; // puerto para hacer echo al componente Llamado (responderle)
	
	private HiloMonitorLlamado hilo;
	
	public MonitoreoLlamado() {
		this.hilo = new HiloMonitorLlamado(this);
		this.hilo.start();
		
	}
	
	public synchronized void echoLlamado() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				// le respondemos al componente 'llamado'
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
