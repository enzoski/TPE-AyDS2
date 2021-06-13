package servidor_primario.sincronizacion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clase que implementa la táctica de Disponibilidad: Redundancia pasiva (recuperarse de fallas)
 * Ahora se cuenta con un segundo servidor (pasivo) que lo vamos a mantener sincronizado.
 * Cada vez que se registre un cliente, además de guardarlo en nuestra fila de DNIs, se lo mandaremos
 * al servidor secundario para que lo agregue a su fila, y cada vez que se llame a un cliente, le informaremos
 * que elimine dicho DNI de su fila. Así si este servidor falla, el secundario estará listo para activarse.
 * Complementaremos esta táctica con la de Monitor y con la de Resincronización de estado.
 *
 */
public class Sincronizador {
	
	private static final int PORT_SYNC_1 = 2500; // puerto para informarle al servidor secundario qué dni debe agregar
	private static final int PORT_SYNC_2 = 2510; // puerto para informarle al servidor secundario qué dni debe eliminar
	
	private String ipServ2; // IP del servidor secundario
	
	public Sincronizador(String ipServ2) {
		this.ipServ2 = ipServ2;
		
	}
	
	public void agregar(String dni) { // va el mensaje a SincronizadorAgregacion (servidor_secundario)
		try {
			Socket socket = new Socket(this.ipServ2, PORT_SYNC_1);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error de sincronización de DNIs: el servidor secundario no funciona.");
		}

	}
	
	public void eliminar(String dni) { // va el mensaje a SincronizadorEliminacion (servidor_secundario)
		try {
			Socket socket = new Socket(this.ipServ2, PORT_SYNC_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//out.println("eliminar");
			out.println(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("Error de sincronización de DNIs: el servidor secundario no funciona.");
		}

	}

}
