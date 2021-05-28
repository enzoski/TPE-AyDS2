package servidor_secundario.monitoreo;

public class HiloMonitorServ2 extends Thread {
	
	private MonitoreoServSec monitoreoServSec;
	 
	public HiloMonitorServ2(MonitoreoServSec monitoreoServSec) {
		this.monitoreoServSec = monitoreoServSec;
	}
	
	@Override
	public void run() {
		this.monitoreoServSec.echoServ2();
	}

}