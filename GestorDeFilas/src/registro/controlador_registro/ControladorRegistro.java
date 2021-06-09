package registro.controlador_registro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import registro.vista_registro.I_VistaRegistro;
import registro.vista_registro.VistaRegistro;
import registro.vista_registro.VistaRegistroConfirmacion;

/**
 * Clase que hace de intermediario entre la interfaz de usuario y el servidor del sistema.
 * Controla los eventos que ocurren en la vista de registro de clientes, y cuando sea oportuno,
 * le informa al componente que gestiona la fila de clientes, el DNI de un cliente que se quiso registrar.
 *
 */
public class ControladorRegistro implements ActionListener {
	
	private int PORT = 2080; // puerto para realizar el registro de clientes (DNIs)
	private String ipServidor; // IP del servidor
	
	// el controlador tiene la referencia de todas las ventanas que "controla"
	private VistaRegistro vistaRegistro;
	private VistaRegistroConfirmacion vistaConfirmacion;
	private int intentosRegistro = 2;
	//private static int numTotem = 0; *
	
	public ControladorRegistro(VistaRegistro vistaRegistro, VistaRegistroConfirmacion vistaConfirmacion, String ipServidor) {
		
		this.ipServidor = ipServidor;
		
		this.vistaRegistro = vistaRegistro;
		this.vistaRegistro.setControlador(this); //le indicamos a la vista que el controlador será su action listener
		this.vistaRegistro.abrirVentana();
		
		this.vistaConfirmacion = vistaConfirmacion;
		this.vistaConfirmacion.setControlador(this);
		// por defecto las ventanas permanecen ocultas, por eso no 'cerramos' esta
		
		// POR AHORA QUEDA EN VEREMOS LO DEL NUMERO DE TOTEM *
		//numTotem++;
		//this.vistaRegistro.mostrarNumTotem(String.valueOf(numTotem));
		
	}
	
	public void actionPerformed(ActionEvent arg0) { //el controlador va a estar "escuchando" los eventos que ocurran en las vistas
		// ingreso del dni
		if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_0)) this.vistaRegistro.mostrarDigito("0");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_1)) this.vistaRegistro.mostrarDigito("1");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_2)) this.vistaRegistro.mostrarDigito("2");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_3)) this.vistaRegistro.mostrarDigito("3");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_4)) this.vistaRegistro.mostrarDigito("4");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_5)) this.vistaRegistro.mostrarDigito("5");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_6)) this.vistaRegistro.mostrarDigito("6");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_7)) this.vistaRegistro.mostrarDigito("7");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_8)) this.vistaRegistro.mostrarDigito("8");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_NUM_9)) this.vistaRegistro.mostrarDigito("9");
		else if(arg0.getActionCommand().equals(I_VistaRegistro.AC_BORRAR)) this.vistaRegistro.borrarUltimoDigito();
		// pasar a la otra ventanita
		else
			if(arg0.getActionCommand().equals(I_VistaRegistro.AC_REGISTRAR)) {
				String dni = this.vistaRegistro.getDniIngresado();
				//this.vistaRegistro.cerrarVentana();
				this.vistaRegistro.setEnabled(false);
				this.vistaConfirmacion.mostrarDni(dni);
				this.vistaConfirmacion.abrirVentana();
			}
			// modificar o confirmar el dni ingresado
			else
				if(arg0.getActionCommand().equals(I_VistaRegistro.AC_MODIFICAR)) {
					this.vistaRegistro.setEnabled(true); //si cerramos la ventanita con la 'X', esto nunca se haría. Salvo que deshabilitemos esa 'X'.
					this.vistaConfirmacion.cerrarVentana();
					//this.vistaRegistro.abrirVentana();
				}
				else
					if(arg0.getActionCommand().equals(I_VistaRegistro.AC_CONFIRMAR)) {
						String dni = this.vistaRegistro.getDniIngresado();
						this.vistaRegistro.setEnabled(true);
						this.vistaConfirmacion.cerrarVentana();
						this.enviarDNI(dni);
						//la ventana no llama como tal a este método, mas bien es algo indirecto; es la que origina/provoca que se active
						//habria que ver si es coherente con el diagrama de secuencia, pero así se aplicaría el patron MVC.
					}
	}
	
	private void enviarDNI(String dni) {
		int longDNI = dni.length();
		if(longDNI == 7 || longDNI == 8) {
			this.registrarDNI(dni);
			this.vistaRegistro.limpiarCampoDNI();
		}
		else
			this.vistaRegistro.errorDNI(); // dni no valido
	}
	
	private void registrarDNI(String dni) { // va el mensaje a GestionFila (servidor)
		try {
			Socket socket = new Socket(this.ipServidor, PORT);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(dni);
			String respuesta = in.readLine();
			if(respuesta.equals("existe"))
				this.vistaRegistro.registroExitoso();
			else
				this.vistaRegistro.errorCliente(); // dni no existente (en el repositorio de clientes)
			
			this.intentosRegistro = 2;
			
			out.close();
			socket.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			if(this.intentosRegistro > 0) {
				this.intentosRegistro--; // quizas estaria bueno poner un sleep que abarque mas de los 30s de ping del monitor.
				/*
				try {
					// lo ideal seria hacer esto en un hilo, pero bueno, aunque se frize toda la ventana, hay que esperar por la confirmación de exito o no.
					Thread.sleep(17000); // 17s * 2 intentos mas = 34s
				} catch (InterruptedException e1) { // DE QUEDAR ASÍ, AGREGAR TODo ESTO TAMBIÉN A atencion
					// TODO Auto-generated catch block
					e1.printStackTrace();
					// A VECES VA MEDIO MAL, MEPA QUE SE FRIZEA TODO ESTE HILO DE EJECUCION, Y CUANDO SE QUIERE HACER
					// CAMBIO DE SERVER, SE DEMORA TAMBIEN. O SEA, LO ESTUVE PROBANDO AL CERRAR EL SERVER PRIMARIO Y
					// ANTES DE QUE EL MONITOR AVISE EL CAMBIO DE SERVER, INTENTAR REGISTRAR UN DNI.
				}
				*/
				this.registrarDNI(dni);
			}else
				this.vistaRegistro.errorConexion();
		}

	}
	
	public void cambiarServidor(String nuevaIP, int nuevoServer) {
		this.ipServidor = nuevaIP;
		if(nuevoServer == 2)
			this.PORT = 3080;
		else 
			this.PORT = 2080;
		
	}

	
}
