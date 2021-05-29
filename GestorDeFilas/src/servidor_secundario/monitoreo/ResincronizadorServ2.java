package servidor_secundario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

import servidor_secundario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_secundario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_secundario.fila_servidor.GestionFila;

public class ResincronizadorServ2 {
	
	private ComunicacionLlamados comunicacionLlamados;
	private ComunicacionDeshabilitacion comunicacionDeshabilitacion;
	private GestionFila gestionFila;
	private String ipServ1;
	private HiloResincronizacion hilo;
	private static final int PORT_9 = 3280; // puerto para avisar al servidor 2 que haga la resinronizacion.
	private static final int PORT_10 = 3300; // puerto para enviar clientes a servidor 1. -> ACA USO EL MISMO QUE USA PARA REGISTRAR CLIENTES DE FORMA NORMAL.
	
	
	public ResincronizadorServ2(ComunicacionLlamados cl,ComunicacionDeshabilitacion cd, GestionFila gf,String ipServ1) {
		this.comunicacionLlamados = cl;
		this.comunicacionDeshabilitacion = cd;
		this.gestionFila = gf;
		this.ipServ1 = ipServ1;
		this.hilo = new HiloResincronizacion(this);
		this.hilo.start();
	}
	
	public void resincronizar() {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT_9);
			while (true) {
				Socket socket = serverSocket.accept();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg = in.readLine();
				if(msg.equals("res")) {
					this.envioFila();
					this.deshabilitarServ2();
				}
				socket.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void envioFila() {
		Queue<String> fila = this.gestionFila.getClientes();
		String[] fila_vector = (String[]) fila.toArray();  //VER SI ESTO ESTA BIEN 
		int i;
		int cant = fila.size();
		for(i=0;i< cant; i++) {
			this.envioCliente(fila_vector[i]);
		}
	}
	
	private void envioCliente(String dni) {
		try {
			Socket socket = new Socket(this.ipServ1, PORT_10);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	private void deshabilitarServ2() {
		this.comunicacionLlamados.desactivarServer();
		this.comunicacionDeshabilitacion.desactivarServer();
		this.gestionFila.desactivarServer();
	}
	
	
}
