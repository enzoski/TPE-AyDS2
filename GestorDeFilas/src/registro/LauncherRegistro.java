package registro;

import java.util.Scanner;

import registro.controlador_registro.ControladorRegistro;
import registro.monitoreo_registro.ManejadorErroresRegistro;
import registro.vista_registro.VistaRegistro;
import registro.vista_registro.VistaRegistroConfirmacion;

public class LauncherRegistro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("Ingrese la dirección IP del servidor: ");
		Scanner sc = new Scanner(System.in);
		String ipServidor = sc.nextLine();
		sc.close();
		
		VistaRegistro vistaRegistro = new VistaRegistro();
		VistaRegistroConfirmacion vistaConfirmacion = new VistaRegistroConfirmacion();
		ControladorRegistro controlador = new ControladorRegistro(vistaRegistro, vistaConfirmacion, ipServidor);
		// disponibilidad
		ManejadorErroresRegistro manejadorErroresRegistro = new ManejadorErroresRegistro(controlador);
	}

}
