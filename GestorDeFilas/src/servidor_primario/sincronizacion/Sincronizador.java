package servidor_primario.sincronizacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Sincronizador {
	
	private static final int PORT_SYNC_1 = 2500;
	private static final int PORT_SYNC_2 = 2510;
	
	private String ipServ2; // IP del servidor secundario
	
	public Sincronizador(String ipServ2) {
		this.ipServ2 = ipServ2;
		
	}
	
	public void agregar(String dni) { // va el mensaje a SincronizadorSecundario (servidor_secundario)
		try {
			Socket socket = new Socket(this.ipServ2, PORT_SYNC_1);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminar() { // va el mensaje a SincronizadorSecundario (servidor_secundario)
		try {
			Socket socket = new Socket(this.ipServ2, PORT_SYNC_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("eliminar");
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
