package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;


public class GestionFila {
	private Queue<String> clientes = new LinkedList<String>();
	private static final int PORT_1 = 2080;
	
	public GestionFila() {
		//activamos el 'server socket'
		this.registro();
	}
	
	public void registro() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_1);
			while (true) {
			Socket socket = serverSocket.accept();
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new
			InputStreamReader(socket.getInputStream()));
			String msg = in.readLine();
			this.clientes.add(msg);
			System.out.println(msg);
			socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String proximoCliente() {
		return clientes.poll(); //devuelve null si no hay mas clientes
	}
	
	
}
