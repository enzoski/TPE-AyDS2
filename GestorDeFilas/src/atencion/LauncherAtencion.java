package atencion;

import java.util.Scanner;

import atencion.controlador_atencion.ControladorAtencion;
import atencion.vista_atencion.VistaAtencionInicio;
import atencion.vista_atencion.VistaAtencionLlamarCliente;

public class LauncherAtencion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("Ingrese la dirección IP del servidor: ");
		Scanner sc = new Scanner(System.in);
		String ipServidor = sc.nextLine();
		sc.close();
		
		VistaAtencionInicio vistaInicio = new VistaAtencionInicio();
		VistaAtencionLlamarCliente vistaLlamarCliente = new VistaAtencionLlamarCliente();
		ControladorAtencion controlador = new ControladorAtencion(vistaInicio, vistaLlamarCliente, ipServidor);

	}

}
