package registro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ControladorRegistro implements ActionListener {
	
	private static final String IP = "192.168.0.7"; // inicializarla bien cuando lo sepamos
	private static final int PORT = 80; //podriamos poner un puerto distinto para cada 'subsistema'
	
	// el controlador tiene la referencia de todas las ventanas que "controla"
	private VistaRegistro vistaRegistro;
	private VistaRegistroConfirmacion vistaConfirmacion;
	
	public ControladorRegistro(VistaRegistro vistaRegistro, VistaRegistroConfirmacion vistaConfirmacion) {
		
		this.vistaRegistro = vistaRegistro;
		this.vistaRegistro.setControlador(this); //le indicamos a la vista que el controlador será su action listener
		this.vistaRegistro.abrirVentana(); //revisar bien donde iria esto
		
		this.vistaConfirmacion = vistaConfirmacion;
		this.vistaConfirmacion.setControlador(this);
		// por defecto las ventanas permanecen ocultas, por eso no 'cerramos' esta
		
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
				this.vistaRegistro.setEnabled(false); //ver si hacemos esto, o la cerramos, o la dejamos activa
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
						this.enviarDNI(dni);
						//la ventana no llama como tal a este método, mas bien es algo indirecto; es la que origina/provoca que se active
						//habria que ver si es coherente con el diagrama de secuencia, pero así se aplicaría el patron MVC.
					}
	}
	
	private void enviarDNI(String dni) {
		boolean valido = this.verificarDNI(dni);
		if(valido)
			this.registrarDNI(dni);
		else
			this.vistaRegistro.errorDNI(); //decidir si le pasamos por parámetro qué mensaje mostrar, o si lo escribimos en el codigo de la ventana.
	}
	
	// no verificamos que se ingresen solo numeros del 0 al 9, ya que por cómo está diseñada la vista, no hay manera de que se ingrese otra cosa
	private boolean verificarDNI(String dni) {
		int longDNI = dni.length();
		boolean longitudValida = longDNI == 7 || longDNI == 8; // ver si sacamos la condicion del 7.
		return longitudValida;
	}
	
	private void registrarDNI(String dni) {
		try {
			Socket socket = new Socket(IP,PORT);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(dni);
			out.close();
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
