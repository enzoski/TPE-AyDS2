package servidor;

import java.util.Scanner;

import servidor.comunicacion_servidor.deshabilitador_servidor.ComunicacionDeshabilitacion;
import servidor.comunicacion_servidor.llamados_servidor.ComunicacionLlamados;
import servidor.fila_servidor.GestionFila;

public class LauncherServidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.print("Ingrese la direcci�n IP de la mini-PC que mostrar� los llamados: ");
		Scanner sc = new Scanner(System.in);
		String ipLlamado = sc.nextLine();
		sc.close();
		System.out.print("Servidor escuchando...");
		
		GestionFila gestionFila = new GestionFila();
		ComunicacionDeshabilitacion comunicacionD = new ComunicacionDeshabilitacion(ipLlamado);
		ComunicacionLlamados comunicacionL = new ComunicacionLlamados(gestionFila, ipLlamado);
	}

}
