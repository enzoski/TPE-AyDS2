package servidor_primario;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import servidor_primario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_primario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_primario.fila_servidor.GestionFila;
import servidor_primario.fila_servidor.ResincronizaFila;
import servidor_primario.monitoreo.ManejadorErroresServ1;
import servidor_primario.monitoreo.MonitoreoServPri;
import servidor_primario.persistencia_primaria.PersistenciaTemplate;
import servidor_primario.persistencia_primaria.PersistenciaXML;

public class LauncherServidor {

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
		String ipLlamado = ipLocal;
		String ipServ2 = ipLocal;
		*/
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese la dirección IP de la mini-PC que mostrará los llamados: ");
		String ipLlamado = sc.nextLine();
		System.out.print("Ingrese la dirección IP del servidor secundario que estará sincronizado: ");
		String ipServ2 = sc.nextLine();
		System.out.print("Ingrese el algoritmo de llamado de clientes (llegada|categoria|DNI): ");
		String algoritmo = sc.nextLine();
		while( !( algoritmo.equals("llegada") || algoritmo.equals("DNI") || algoritmo.equals("categoria") ) ) {
			System.out.print("Por favor ingrese un algoritmo de llamado de clientes valido (llegada|categoria|DNI): ");
			algoritmo = sc.nextLine();
		}
		sc.close();
		
		// El servidor consta de 2 sub-componentes:
		// uno que gestiona la fila de clientes, y otro que gestiona la comunicación entre todo el sistema.
		I_RepositorioClientes repositorioClientes = new RepositorioClientes();
		PersistenciaTemplate persistencia = new PersistenciaXML(repositorioClientes);
		GestionFila gestionFila = new GestionFila(ipServ2, algoritmo, repositorioClientes, persistencia); // IP DEL SERVIDOR SECUNDARIO PARA QUE SE VAYA SINCRONIZANDO
		ComunicacionDeshabilitacion comunicacionD = new ComunicacionDeshabilitacion(ipLlamado);
		ComunicacionLlamados comunicacionL = new ComunicacionLlamados(gestionFila, ipLlamado, repositorioClientes, persistencia);
		// disponibilidad
		ManejadorErroresServ1 manejadorErroresServ1 = new ManejadorErroresServ1(comunicacionL);
		MonitoreoServPri monitoreoServPri = new MonitoreoServPri();
		ResincronizaFila resincronizdor = new ResincronizaFila(gestionFila);
		
		System.out.println("Servidor primario escuchando...");
		
	}

}
