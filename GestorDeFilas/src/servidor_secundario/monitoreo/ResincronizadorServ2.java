package servidor_secundario.monitoreo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Queue;

import servidor_secundario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_secundario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_secundario.fila_servidor.GestionFila;

/**
 * Clase que implementa la táctica de Disponibilidad: Resincronización de estado (recuperarse de fallas)
 * Cuando el monitor del sistema nos avise que debemos realizar la resincronizacion del servidor primario
 * (ya que se reactivó), le mandaremos todos los DNIs de nuestra fila de clientes, conservando el orden.
 * Adicionalmente, dejaremos de aceptar conexiones ya que todos los componentes se volverán a conectar al primario.
 *
 */
public class ResincronizadorServ2 {
	
	private ComunicacionLlamados comunicacionLlamados;
	private ComunicacionDeshabilitacion comunicacionDeshabilitacion;
	private GestionFila gestionFila;
	private String ipServ1;
	private HiloResincronizacion hilo;
	private static final int PORT_9 = 3280; // puerto para recibir por parte del monitor el aviso de resincronizacion.
	private static final int PORT_10 = 3300; // puerto para enviar clientes a servidor 1.
	
	
	public ResincronizadorServ2(ComunicacionLlamados cl, ComunicacionDeshabilitacion cd, GestionFila gf, String ipServ1) {
		this.comunicacionLlamados = cl;
		this.comunicacionDeshabilitacion = cd;
		this.gestionFila = gf;
		this.ipServ1 = ipServ1;
		// instanciamos y activamos el hilo del 'server socket'
		this.hilo = new HiloResincronizacion(this);
		this.hilo.start();
	}
	
	public void resincronizar() { // viene el mensaje desde el monitor del sistema
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
					// YO ACA PRIMERO DESHABILITARIA EL SERVER2 Y LUEGO MANDARIA LA FILA
					// COSA DE PREVENIR QUE JUSTO SE REGISTRE ALGUIEN O SE HAGA UN LLAMADO (les daria error de conexión)
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
		Iterator<String> it = fila.iterator();
		while(it.hasNext()) {
			String dni = it.next();
			this.envioCliente(dni);
		}
	}
	
	private void envioCliente(String dni) { // va el mensaje al servidor 1 [ResincronizaFila]
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
		// a futuro probar si los 'hilo.stop()' realmente hacen que el server socket deje de escuchar
		// o si habria que tener una referencia al socket para luego hacerle un 'socket.close()'
	}
	
	
}
