package registro;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import registro.controlador_registro.ControladorRegistro;
import registro.monitoreo_registro.ManejadorErroresRegistro;
import registro.vista_registro.VistaRegistro;
import registro.vista_registro.VistaRegistroConfirmacion;

public class LauncherRegistro {

	public static void main(String[] args) {
		
		// PARA PRUEBAS LOCALES
		/*
		String ipLocal = "";
		try {
			ipLocal = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ipServidor = ipLocal;
		String ipMonitor = ipLocal;
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese el número de totem que va a instanciar: ");
		String numTotem = sc.nextLine();
		System.out.print("Ingrese la dirección IP del servidor: ");
		String ipServidor = sc.nextLine();
		System.out.print("Ingrese la dirección IP del monitor: ");
		String ipMonitor = sc.nextLine();
		sc.close();
		
		VistaRegistro vistaRegistro = new VistaRegistro();
		VistaRegistroConfirmacion vistaConfirmacion = new VistaRegistroConfirmacion();
		ControladorRegistro controlador = new ControladorRegistro(vistaRegistro, vistaConfirmacion, ipServidor, ipMonitor, Integer.valueOf(numTotem));
		// disponibilidad
		ManejadorErroresRegistro manejadorErroresRegistro = new ManejadorErroresRegistro(controlador);
		// a diferencia de 'atencion', no hay problema de instanciar el manejador de errores acá, ya que el controlador asigna directamente el n° de totem.
		
	}

}
