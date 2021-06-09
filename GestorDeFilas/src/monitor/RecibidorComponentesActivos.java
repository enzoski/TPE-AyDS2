package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RecibidorComponentesActivos {
	
	private static final int PORT= 3700;
	private Monitor monitor;
	private HiloComponentesActivos hilo;
	
	public RecibidorComponentesActivos(Monitor monitor) {
		this.monitor = monitor;
		this.hilo = new HiloComponentesActivos(this);
		this.hilo.start();
	}
	
	public void  activarComponente() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				
				//parseo del componente e ip recibido del monitor
				String[] arreglo = msg.split("#");
				String componente = arreglo[0]; // puede ser Box o Totem
				String num = arreglo[1];
				int numero= Integer.valueOf(num);
				if(componente.equals("box"))
					this.monitor.agregarBoxActivo(numero);
				else
					if(componente.equals("totem"))
						this.monitor.agregarTotemActivo(numero);
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
