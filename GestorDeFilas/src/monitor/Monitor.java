package monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// disponibilidad
public class Monitor {
	
	private static final int PORT_1 = 3200; // puerto para hacer ping al componente Llamado
	private static final int PORT_2 = 3210; // puerto para hacer ping al servidor primario
	private static final int PORT_3 = 3220; // puerto para hacer ping al servidor secundario
	private static final int PORT_4 = 3230; // puerto para informar errores al componente Atencion
	private static final int PORT_5 = 3240; // puerto para informar errores al servidor primario
	private static final int PORT_6 = 3250; // puerto para informar errores al servidor secundario
	private static final int PORT_7 = 3260; // puerto para informar errores al componente Registro
	private static final int PORT_9 = 3280; // puerto para avisar al servidor 2 que haga la resinronizacion.
	
	private String ipLlamado;
	private String ipServ1;
	private String ipServ2;
	private String ipAtencion;
	private String ipRegistro;
	private int servidorActivo = 1;
	private boolean llamadoEnLinea = true;
	private HiloMonitor hilo;
	
	public Monitor(String ipLlamado, String ipAtencion, String ipRegistro, String ipServ1, String ipServ2) {
		this.ipLlamado = ipLlamado;
		this.ipServ1 = ipServ1;
		this.ipServ2 = ipServ2;
		this.ipAtencion = ipAtencion;
		this.ipRegistro = ipRegistro;
		
		this.hilo = new HiloMonitor(this);
		this.hilo.start();
		
	}
	
	// PARA MI ESTE PING NO SERÍA NECESARIO.
	// POR AHI LO QUE SÍ ESTARÍA BUENO ES QUE SOLO SEA UN PING COMO TAL, Y MOSTRAR POR CONSOLA SI ESTÁ 'on' O NO.
	// Y LO MISMO PARA LOS COMPONENTES 'atencion' Y 'registro'. (igual para hacer algo así el monitor deberia saber
	// todos las posibles IP y puerto de todos los totem y puestos de atencion que haya)
	// CON ESTO ULTIMO ME DI CUENTA QUE LOS 'avisos' PARA HACER EL CAMBIO DE SERVER, TENDRIA QUE SER A TODAS LAS POSIBLES IP-PUERTO
	// COSA QUE NOSOTROS LOCALMENTE COMO ES LA MISMA IP, DA ERROR AL POR EJEMPLO ABRIR 2 'atencion' YA QUE ESCUCHAN EL MISMO PUERTO EN LA MISMA IP.
	public void pingLlamado() { // táctica ping/echo
		try {
			Socket socket = new Socket(ipLlamado, PORT_1);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("ping");
			String msg = in.readLine();
			if(!msg.equals("ping")) { // error por recibir un mensaje no esperado
				this.avisoaAServ1("llamado"); // para que el servidor primario no mande llamados al TV
				this.avisoaAServ2("llamado"); // para que el servidor primario no mande llamados al TV
				this.llamadoEnLinea = false;
				System.out.println("No hay conexión con la mini-pc.");
			}else {
				if(!this.llamadoEnLinea) { //antes no andaba y ahora si. 
					this.llamadoEnLinea = true;
				}
			}
			out.close();
			socket.close();
		}
		catch (Exception e) { // por error de conexión
			//e.printStackTrace();
			this.avisoaAServ1("llamado");
			this.avisoaAServ2("llamado");
			this.llamadoEnLinea = false;
			System.out.println("No hay conexión con la mini-pc.");
		}
	}
	
	public void pingServidorPrimario() { // táctica ping/echo
		try {
			Socket socket = new Socket(ipServ1, PORT_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("ping");
			String msg = in.readLine();
			if(!msg.equals("ping")) { // error por recibir un mensaje no esperado.
				this.avisoaAServ2("serv1"); // para que el servidor secundario se active (empiece a escuchar)
				this.avisoaAAtencion("serv1", this.ipServ2); // para que el componente atencion se empiece a comunicar con el servidor secundario
				this.avisoaARegistro("serv1", this.ipServ2); // para que el componente registro se empiece a comunicar con el servidor secundario
				this.servidorActivo = 2;
			}
			else { //anduvo todo bien, primer servidor anda.
				if(this.servidorActivo == 2) { //antes no andaba
					this.avisoaAAtencion("serv2", ipServ1);
					this.avisoaARegistro("serv2", ipServ1);
					this.resincronizar();
					this.servidorActivo = 1;
					System.out.println("No hay conexión con el servidor primario.");
				}
				/*
				 * Puede ser que si las personas se siguen registrando mientras se hace la resincronizacion
				 * se mezcle el orden de los clientes. (los nuevos clientes van a quedar en el medio de la cola.
				 */
			}
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			this.avisoaAServ2("serv1");
			this.avisoaAAtencion("serv1", this.ipServ2);
			this.avisoaARegistro("serv1", this.ipServ2);
			this.servidorActivo = 2;
			System.out.println("No hay conexión con el servidor primario.");
		}
	}
	
	// ESTE PING TAMPOCO SE BIEN SI ES NECESARIO, O SEA EL PING COMO TAL ESTARIA PARA TENERLO POR EL HECHO DE MONITOREAR.
	// PERO SI NO ANDA EL SERVIDOR2, CON TODO ESTO UNICAMENTE HACEMOS UN print.out.
	public void pingServidorSecundario() { // táctica ping/echo
		try {
			Socket socket = new Socket(ipServ2, PORT_3);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("ping");
			String msg = in.readLine();
			if(!msg.equals("ping")) { // error por recibir un mensaje no esperado.
				this.avisoaAServ1("serv2"); // para que el servidor primario sepa que falló el servidor 2 y no le siga mandando dni's.
				System.out.println("No hay conexión con el servidor secundario.");
			}
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			this.avisoaAServ1("serv2");
			System.out.println("No hay conexión con el servidor secundario.");
		}
	}
	
	private void avisoaAAtencion(String componente, String ip) { // informar errores al componente 'atencion'
		try {
			Socket socket = new Socket(ipAtencion, PORT_4);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = componente + "#" + ip; // para despues saber como parsear el mensaje.
			out.println(msg);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void avisoaARegistro(String componente, String ip) { // informar errores al componente 'registro'
		try {
			Socket socket = new Socket(this.ipRegistro, PORT_7);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = componente + "#" + ip; // para despues saber como parsear el mensaje.
			out.println(msg);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void avisoaAServ1(String componente) { // informar errores al servidor primario
		try {
			Socket socket = new Socket(this.ipServ1, PORT_5);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(componente);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void avisoaAServ2(String componente) { // informar errores al servidor secundario
		try {
			Socket socket = new Socket(this.ipServ2, PORT_6);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(componente);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void resincronizar() {
		try {
			Socket socket = new Socket(ipServ2, PORT_9);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("res");
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			this.avisoaAServ1("serv2");
		}
	}
	

}
