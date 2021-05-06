package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Comunicacion {

	//recibe pedidos de llamados de atencion con un nro de box.
	//le solicita el proximo cliente a GestionFila.
	//si no hay clientes le avisa a atencion que no hay clientes (que aparezca msj en pantalla de atencion)
	// si hay proximocliente-> toma el nro box y ese dni y lo manda a llamado (socket cliente)
	
	private static final int PORT_1 = 90; //de aca viene el aviso de deshabilitacion
	private static final int PORT_2 = 120; //aca va el aviso de deshabilitacion
	private static final int PORT_3 = 100; // puerto donde viene llamar prox cliente
	private static final int PORT_4= 110; //puerto para hacer llamados a prox cliente
	private static final String IP_llamado = "168.2.0.1";
	private GestionFila gestorFila = new GestionFila(); //no se si esto va aca o se deberia pasar en el contructor.
	
	public void deshabilitarBox() { //viene el msj desde controladorAtencion
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			String box = in.readLine();
			this.avisoDeshabilitacion(box);
			socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void avisoDeshabilitacion(String box) { //va el msj a ControladorLlamado
		try {
			Socket socket = new Socket(IP_llamado,PORT_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(box);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void pedidoLlamado() { //viene el msj desde controladorAtencion
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_3);
			while (true) {
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			String box = in.readLine();
			String dni = this.gestorFila.proximoCliente();
			if(dni != null)
				this.realizarLlamado(box,dni);
			else 
				//avisar error a atencion.
			socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void realizarLlamado(String box,String dni) { //va el msj a ControladorLlamado
		try {
			Socket socket = new Socket(IP_llamado,PORT_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msj = box + dni;
			out.println(msj);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
