package servidor_primario;

import java.util.Scanner;

import llamado.monitoreo_llamado.ManejadorErroresLlamado;
import llamado.monitoreo_llamado.MonitoreoLlamado;
import servidor_primario.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor_primario.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor_primario.fila_servidor.GestionFila;
import servidor_primario.fila_servidor.ResincronizaFila;
import servidor_primario.monitoreo.ManejadorErroresServ1;
import servidor_primario.monitoreo.MonitoreoServPri;
import servidor_primario.sincronizacion.Sincronizador;

public class LauncherServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("Ingrese la dirección IP de la mini-PC que mostrará los llamados: ");
		Scanner sc = new Scanner(System.in);
		String ipLlamado = sc.nextLine();
		sc.close();
		System.out.print("Servidor escuchando...");
		
		// El servidor consta de 2 sub-componentes:
		// uno que gestiona la fila de clientes, y otro que gestiona la comunicación entre todo el sistema.
		GestionFila gestionFila = new GestionFila("192.168.0.159"); // DESPUES VER BIEN COMO PASARLE LA IP.
		ComunicacionDeshabilitacion comunicacionD = new ComunicacionDeshabilitacion(ipLlamado);
		ComunicacionLlamados comunicacionL = new ComunicacionLlamados(gestionFila, ipLlamado);
		// disponibilidad
		Sincronizador sincronizador = new Sincronizador("192.168.0.159"); // IP DEL SERVER SECUNDARIO
		ManejadorErroresServ1 manejadorErroresServ1 = new ManejadorErroresServ1(comunicacionL);
		MonitoreoServPri monitoreoServPri = new MonitoreoServPri();
		ResincronizaFila resincronizdor = new ResincronizaFila(gestionFila);
	}

}
