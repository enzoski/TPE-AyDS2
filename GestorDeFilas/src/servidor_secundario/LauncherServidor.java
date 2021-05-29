package servidor_secundario;

import java.util.Scanner;

import servidor_secundario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_secundario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_secundario.fila_servidor.GestionFila;
import servidor_secundario.monitoreo.ManejadorErroresServ2;
import servidor_secundario.monitoreo.MonitoreoServSec;
import servidor_secundario.monitoreo.ResincronizadorServ2;
import servidor_secundario.sincronizacion.Sincronizador;

public class LauncherServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("Ingrese la dirección IP de la mini-PC que mostrará los llamados: ");
		Scanner sc = new Scanner(System.in);
		String ipLlamado = sc.nextLine();
		sc.close();
		System.out.print("Servidor escuchando...");
		
		String ipServ1 ="192.168.0.159"; //ACA LA DEBERIA PEDIR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// El servidor consta de 2 sub-componentes:
		// uno que gestiona la fila de clientes, y otro que gestiona la comunicación entre todo el sistema.
		GestionFila gestionFila = new GestionFila();
		ComunicacionDeshabilitacion comunicacionD = new ComunicacionDeshabilitacion(ipLlamado);
		ComunicacionLlamados comunicacionL = new ComunicacionLlamados(gestionFila, ipLlamado);
		// disponibilidad
		Sincronizador sincronizador = new Sincronizador(gestionFila);
		ManejadorErroresServ2 manejadorErroresServ2 = new ManejadorErroresServ2(comunicacionL, comunicacionD, gestionFila);
		MonitoreoServSec monitoreoServSec = new MonitoreoServSec();
		ResincronizadorServ2 resincronizador = new ResincronizadorServ2(comunicacionL,comunicacionD,gestionFila,ipServ1);
	}

}
