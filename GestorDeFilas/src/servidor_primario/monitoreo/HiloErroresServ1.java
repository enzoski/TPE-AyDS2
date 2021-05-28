package servidor_primario.monitoreo;

public class HiloErroresServ1 extends Thread {
	
	private ManejadorErroresServ1 manejadorErroresServ1;
	 
	public HiloErroresServ1(ManejadorErroresServ1 manejadorErroresServ1) {
		this.manejadorErroresServ1 = manejadorErroresServ1;
	}
	
	@Override
	public void run() {
		this.manejadorErroresServ1.recibirError();
	}

}
