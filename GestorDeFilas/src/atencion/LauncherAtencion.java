package atencion;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import atencion.controlador_atencion.ControladorAtencion;
import atencion.vista_atencion.VistaAtencionInicio;
import atencion.vista_atencion.VistaAtencionLlamarCliente;

public class LauncherAtencion {

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
		System.out.print("Ingrese la dirección IP del servidor: ");
		String ipServidor = sc.nextLine();
		System.out.print("Ingrese la dirección IP del monitor: ");
		String ipMonitor = sc.nextLine();
		sc.close();
		
		VistaAtencionInicio vistaInicio = new VistaAtencionInicio();
		VistaAtencionLlamarCliente vistaLlamarCliente = new VistaAtencionLlamarCliente();
		ControladorAtencion controlador = new ControladorAtencion(vistaInicio, vistaLlamarCliente, ipServidor, ipMonitor);
		// disponibilidad
		//ManejadorErroresAtencion manejadorErroresAtencion = new ManejadorErroresAtencion(controlador);
		// debemos instanciar el manejador de errores recien cuando se habilita el box, sino no sabremos su n° de box y los puertos se repetirán.

	}

}
