package servidor_secundario.monitoreo;

public class HiloErroresServ2 extends Thread {
	
	private ManejadorErroresServ2 manejadorErroresServ2;
	 
	public HiloErroresServ2(ManejadorErroresServ2 manejadorErroresServ2) {
		this.manejadorErroresServ2 = manejadorErroresServ2;
	}
	
	@Override
	public void run() {
		this.manejadorErroresServ2.recibirError();
	}

}
