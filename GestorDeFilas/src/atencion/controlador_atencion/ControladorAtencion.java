package atencion.controlador_atencion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import atencion.monitoreo_atencion.ManejadorErroresAtencion;
import atencion.vista_atencion.VistaAtencionInicio;
import atencion.vista_atencion.VistaAtencionLlamarCliente;

/**
 * Clase que hace de intermediario entre la interfaz de usuario y el servidor del sistema.
 * Controla los eventos que ocurren en la vista del puesto de atención (box), y cuando sea oportuno,
 * le informa al servidor que se quiere llamar al próximo cliente o bien que se quiere desconectar el box.
 *
 */
public class ControladorAtencion implements ActionListener {
	
	private int boxActual = -1;
	
	private int PORT_1 = 2090; // puerto para deshabilitar box
	private int PORT_2 = 2100; // puerto para llamar prox cliente
	private int PORT_3 = 3700; // puerto para avisar al monitor que se activo un box
	
	private String ipServidor; // IP del servidor
	private String ipMonitor; // IP del monitor
	
	private VistaAtencionInicio vistaInicio;
	private VistaAtencionLlamarCliente vistaLlamarCliente;
	
	// disponibilidad (táctica reintento)
	private int intentosLlamado = 2; //maxima cantidad de intentos para comunicarse con el servidor para hacer un llamado.
	private int intentosDeshabilitacion = 2; //maxima cantidad de intentos para comunicarse con el servidor para deshabilitar un box.
	
	ManejadorErroresAtencion manejadorErroresAtencion; // lo instanciaremos al activar el box, o lo 'pararemos' al desactivar el box.
	private boolean avisoHabilitadoAMonitor = false;
	
	public ControladorAtencion(VistaAtencionInicio vistaInicio, VistaAtencionLlamarCliente vistaLlamarCliente, String ipServidor, String ipMonitor) {
		
		this.ipServidor = ipServidor;
		this.ipMonitor = ipMonitor;
		
		this.vistaInicio = vistaInicio;
		this.vistaInicio.setControlador(this);
		this.vistaInicio.abrirVentana();
		
		this.vistaLlamarCliente = vistaLlamarCliente;
		this.vistaLlamarCliente.setControlador(this);
		
		while(!this.avisoHabilitadoAMonitor) {
			this.avisoHabilitadoAMonitor = this.avisoActivacion(); // avisamos al monitor que hay una nueva instancia de 'atencion' (box)
			//System.out.println(this.avisoHabilitadoAMonitor);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(atencion.vista_atencion.I_VistaAtencion.AC_HABILITAR)) {
			String box = this.vistaInicio.getBoxIngresado();
			try {
				int numBox = Integer.parseInt(box);
				this.habilitarBox(numBox);
				
			}
			catch (NumberFormatException e) {
				this.vistaInicio.errorBox();
				this.vistaInicio.limpiarCampoBox();
			}
		}
		else
			if(arg0.getActionCommand().equals(atencion.vista_atencion.I_VistaAtencion.AC_LLAMAR))
					this.llamarProximoCliente();
			else // FIJARSE SI HAY UN EVENTO DE 'CERRAR VENTANA CON LA CRUZ'.
				if(arg0.getActionCommand().equals(atencion.vista_atencion.I_VistaAtencion.AC_DESCONECTAR))
					this.deshabilitarBox();
		
	}
	
	private void habilitarBox(int num) {
		if(num >= 0) {
			this.boxActual = num;
			this.vistaInicio.cerrarVentana();
			this.vistaLlamarCliente.mostrarNumBoxHabilitado(String.valueOf(num));
			this.vistaLlamarCliente.abrirVentana();
			//recien aca deberiamos activar el manejador de errores.
			this.manejadorErroresAtencion = new ManejadorErroresAtencion(this);
			if(!this.avisoHabilitadoAMonitor) { // por si desactivamos el box, y luego volvemos a activarlo, hay que reavisar al monitor
				this.avisoHabilitadoAMonitor = this.avisoActivacion();
				//System.out.println(this.avisoHabilitadoAMonitor);
			}
		}
		else {
			this.vistaInicio.errorBox();
			this.vistaInicio.limpiarCampoBox();
		}
	}
	
	private void deshabilitarBox() {//agregar al diagrama de secuencia lo del socket.
		this.avisoDeshabilitacion(this.boxActual); // avisa al server el nro de box que se desactiva para borrar sus llamados de la TV
		this.avisoDesactivacion(); // avisa al monitor que deje de monitorearnos
		this.avisoHabilitadoAMonitor = false;
		this.boxActual = -1;
		this.vistaLlamarCliente.limpiarCampoProxDNI();
		this.vistaLlamarCliente.cerrarVentana();
		this.vistaInicio.abrirVentana();
	}
	
	private void avisoDeshabilitacion(int box) { // va el mensaje a ComunicacionDeshabilitacion (servidor)
		try {
			Socket socket = new Socket(this.ipServidor, PORT_1);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(box);
			out.close();
			socket.close();
			this.intentosDeshabilitacion = 2; // pudimos comunicarnos con el servidor -> reiniciamos el contador de intentos.
		}
		catch (Exception e) {
			//e.printStackTrace();
			if(this.intentosDeshabilitacion > 0) 
			{
				this.intentosDeshabilitacion--;
				this.avisoDeshabilitacion(box);
			}else
				this.vistaLlamarCliente.errorConexion();
		}
	}
	
	private void llamarProximoCliente() { // va el mensaje a ComunicacionLlamados (servidor)
		try {
			Socket socket = new Socket(this.ipServidor, PORT_2);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(this.boxActual);
			// luego de mandarle al servidor el box actual, él nos devuelve el próximo DNI que va a ser llamado
			String dni = in.readLine();
			if(dni.equals("errorTV")) { // hubo un error de comunicacion con la TV que muestra los llamados
				this.vistaLlamarCliente.errorLlamadosTV();
				this.vistaLlamarCliente.mostrarDNIProximoCliente("");
			}
			else
				if(dni.equals("null")) { // no hay mas clientes (DNIs) esperando a ser atendidos
					this.vistaLlamarCliente.errorProxCliente();
					this.vistaLlamarCliente.mostrarDNIProximoCliente("");
				}
				else
					this.vistaLlamarCliente.mostrarDNIProximoCliente(dni);
			out.close();
			socket.close();
			this.intentosLlamado = 2; // pudimos comunicarnos con el servidor -> reiniciamos el contador de intentos.
		}
		catch (Exception e) {
			//e.printStackTrace();
			if(this.intentosLlamado > 0) {
				this.intentosLlamado--;
				this.llamarProximoCliente();
			}
			else
				this.vistaLlamarCliente.errorConexion();
		}
	}
	
	// disponibilidad
	
	public void cambiarServidor(String nuevaIP, int numServerNuevo) { // lo usa ManejadorErroresAtencion
		this.ipServidor = nuevaIP;
		if(numServerNuevo == 2) {
			this.PORT_1 = 3090; // nuevoPuertoElim
			this.PORT_2 = 3100; // nuevoPuertoLlamado
		}
		else {
			this.PORT_1 = 2090; // nuevoPuertoElim
			this.PORT_2 = 2100; // nuevoPuertoLlamado
		}
		
	}
	
	private boolean avisoActivacion() { // avisamos al manejador de comp. act. del monitor, que hay una nueva instancia (num) de 'atencion' (box)
		boolean aviso=false;
		if(this.boxActual > 0) {
			try {
				Socket socket = new Socket(this.ipMonitor, PORT_3);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out.println("box#" + this.boxActual);
				out.close();
				socket.close();
				aviso=true;
			}
			catch (Exception e) {
				aviso=false;
			}
		}
		return aviso;
	}
	
	private void avisoDesactivacion() { // avisamos al manejador de comp. act. del monitor, que desactivamos el box
		try {
			Socket socket = new Socket(this.ipMonitor, PORT_3);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("box_desactivado#" + this.boxActual);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("No se pudo avisar al monitor que se desactivó el box, debido a un error de conexión.");
		}
	}
	
	public int getNumBox() {
		return this.boxActual;
	}
	
	
}
