package atencion.controlador_atencion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import atencion.vista_atencion.VistaAtencionInicio;
import atencion.vista_atencion.VistaAtencionLlamarCliente;

/**
 * Clase que hace de intermediario entre la interfaz de usuario y el servidor del sistema.
 * Controla los eventos que ocurren en la vista del puesto de atención (box), y cuando sea oportuno,
 * le informa al servidor que se quiere llamar al próximo cliente o bien que se quiere desconectar el box.
 *
 */
public class ControladorAtencion implements ActionListener {
	
	private int PORT_1 = 2090; // puerto para deshabilitar box
	private int PORT_2 = 2100; // puerto para llamar prox cliente
	
	private String ipServidor; // IP del servidor
	private VistaAtencionInicio vistaInicio;
	private VistaAtencionLlamarCliente vistaLlamarCliente;
	private int boxActual = -1;
	
	public ControladorAtencion(VistaAtencionInicio vistaInicio, VistaAtencionLlamarCliente vistaLlamarCliente, String ipServidor) {
		
		this.ipServidor = ipServidor;
		
		this.vistaInicio = vistaInicio;
		this.vistaInicio.setControlador(this);
		this.vistaInicio.abrirVentana();
		
		this.vistaLlamarCliente = vistaLlamarCliente;
		this.vistaLlamarCliente.setControlador(this);
		
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
		}
		else {
			this.vistaInicio.errorBox();
			this.vistaInicio.limpiarCampoBox();
		}
	}
	
	private void deshabilitarBox() {//agregar al diagrama de secuencia lo del socket.
		// avisar al server el nro de box
		this.avisoDeshabilitacion(this.boxActual);
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
		}
		catch (Exception e) {
			//e.printStackTrace();
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
			if(dni.equals("null")) { // no hay mas clientes (DNIs) esperando a ser atendidos
				this.vistaLlamarCliente.errorProxCliente();
				this.vistaLlamarCliente.mostrarDNIProximoCliente("");
			}
			else
				this.vistaLlamarCliente.mostrarDNIProximoCliente(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			this.vistaLlamarCliente.errorConexion();
		}
	}
	
	public void cambiarServidor(String nuevaIP,int numServerNuevo) {
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

	public void llamadoInhabilitado() { // TO DO
		// TODO Auto-generated method stub
		
	}
	public void llamadoHabilitado() { // TO DO
		// TODO Auto-generated method stub
		
	}
	
}
