package servidor_secundario;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import servidor_secundario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_secundario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_secundario.fila_servidor.GestionFila;
import servidor_secundario.monitoreo.ManejadorErroresServ2;
import servidor_secundario.monitoreo.MonitoreoServSec;
import servidor_secundario.monitoreo.ResincronizadorServ2;
import servidor_secundario.persistencia_secundaria.I_Persistencia;
import servidor_secundario.persistencia_secundaria.PersistenciaXML;
import servidor_secundario.sincronizacion.SincronizadorAgregacion;
import servidor_secundario.sincronizacion.SincronizadorEliminacion;

public class LauncherServidor {

	public static void main(String[] args) {
		
		// PARA PRUEBAS LOCALES
		String ipLocal = "";
		try {
			ipLocal = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ipLlamado = ipLocal;
		String ipServ1 = ipLocal;
		
		/*
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese la dirección IP de la mini-PC que mostrará los llamados: ");
		String ipLlamado = sc.nextLine();
		System.out.print("Ingrese la dirección IP del servidor primario que será resincronizado cuando corresponda: ");
		String ipServ1 = sc.nextLine();
		sc.close();
		*/
		
		// El servidor consta de 2 sub-componentes:
		// uno que gestiona la fila de clientes, y otro que gestiona la comunicación entre todo el sistema.
		RepositorioClientes repositorioClientes = new RepositorioClientes();
		I_Persistencia persistencia = new PersistenciaXML(repositorioClientes);
		GestionFila gestionFila = new GestionFila("llegada", repositorioClientes, persistencia);
		ComunicacionDeshabilitacion comunicacionD = new ComunicacionDeshabilitacion(ipLlamado);
		ComunicacionLlamados comunicacionL = new ComunicacionLlamados(gestionFila, ipLlamado, repositorioClientes, persistencia);
		// disponibilidad
		SincronizadorAgregacion sincronizadorA = new SincronizadorAgregacion(gestionFila);
		SincronizadorEliminacion sincronizadorE = new SincronizadorEliminacion(gestionFila);
		ManejadorErroresServ2 manejadorErroresServ2 = new ManejadorErroresServ2(comunicacionL, comunicacionD, gestionFila);
		MonitoreoServSec monitoreoServSec = new MonitoreoServSec();
		ResincronizadorServ2 resincronizador = new ResincronizadorServ2(comunicacionL,comunicacionD,gestionFila,ipServ1);
		
		System.out.println("Servidor secundario escuchando...");
		
	}

}
