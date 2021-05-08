package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ComunicacionLlamados {

	
	private static final int PORT_3 = 2100; // puerto donde viene llamar prox cliente
	private static final int PORT_4= 2110; //puerto para hacer llamados a prox cliente
	private static final String IP_llamado = "192.168.1.75";
	private GestionFila gestorFila; //no se si esto va aca o se deberia pasar en el contructor.
	private RecibidorLlamados hilo; // hilo para recibir pedidos de llamados
	
	public ComunicacionLlamados(GestionFila gestorFila) {
		this.gestorFila = gestorFila;
		this.hilo = new RecibidorLlamados(this);
		this.hilo.start();
	}
	
	public synchronized void pedidoLlamado() { //viene el msj desde controladorAtencion
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_3);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new
				InputStreamReader(socket.getInputStream()));
				String box = in.readLine();
				String dni = this.gestorFila.proximoCliente();
				System.out.println(box + "|" + dni);
				if(dni != null) {
					this.realizarLlamado(box,dni);
					//System.out.println(box + "|" + dni);
				}
				// ver que pasa si el dni es null
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void realizarLlamado(String box,String dni) { //va el msj a ControladorLlamado
		try {
			Socket socket = new Socket(IP_llamado,PORT_4);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msj = box + '#' + dni;
			out.println(msj);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
