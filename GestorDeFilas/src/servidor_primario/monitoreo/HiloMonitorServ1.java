package servidor_primario.monitoreo;

public class HiloMonitorServ1 extends Thread {
	
	private MonitoreoServPri monitoreoServPri;
	 
	public HiloMonitorServ1(MonitoreoServPri monitoreoServPri) {
		this.monitoreoServPri = monitoreoServPri;
	}
	
	@Override
	public void run() {
		this.monitoreoServPri.echoServ1();
	}

}
