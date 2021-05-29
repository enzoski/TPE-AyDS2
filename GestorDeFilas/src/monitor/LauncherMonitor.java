package monitor;

public class LauncherMonitor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ipPruebas = "192.168.0.159";
		// ipLlamado, ipAtencion, ipRegistro, ipServ1, ipServ2
		Monitor monitor = new Monitor(ipPruebas, ipPruebas, ipPruebas, ipPruebas, ipPruebas);
		
		System.out.print("Monitor monitoreando...");

	}

}
