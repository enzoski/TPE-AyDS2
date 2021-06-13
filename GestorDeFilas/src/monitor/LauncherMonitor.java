package monitor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class LauncherMonitor {

	public static void main(String[] args) {
		
		// PARA PRUEBAS LOCALES
		String ipLocal = "";
		try {
			ipLocal = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ipLlamado  = ipLocal;
		String ipAtencion = ipLocal;
		String ipRegistro = ipLocal;
		String ipServ1    = ipLocal;
		String ipServ2    = ipLocal;
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.println("Ingrese la dirección IP de las máquinas que serán monitoreadas.");
		System.out.print("Mini-PC de llamados por TV: "); String ipLlamado = sc.nextLine();
		System.out.print("Puesto de atención: ");         String ipAtencion = sc.nextLine();
		System.out.print("Dispositivo de registro: ");    String ipRegistro = sc.nextLine();
		System.out.print("Servidor primario: ");          String ipServ1 = sc.nextLine();
		System.out.print("Servidor secundario: ");        String ipServ2 = sc.nextLine();
		sc.close();
		*/
		
		// Por como lo planteamos, podriamos monitorear un solo totem y un solo box.
		// [ARREGLADO, YA PODEMOS VARIOS, PERO DEBEN TENER LA MISMA IP (arreglamos el tema de puertos)]
		Monitor monitor = Monitor.getMonitor(ipLlamado, ipAtencion, ipRegistro, ipServ1, ipServ2);
		RecibidorComponentesActivos recibidor = new RecibidorComponentesActivos(monitor);
		System.out.println("Monitor monitoreando...");

	}

}
