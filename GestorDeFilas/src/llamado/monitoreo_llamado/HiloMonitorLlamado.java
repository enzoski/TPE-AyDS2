package llamado.monitoreo_llamado;

public class HiloMonitorLlamado extends Thread {
	
	private MonitoreoLlamado monitoreoLlamado;
	 
	public HiloMonitorLlamado(MonitoreoLlamado monitoreoLlamado) {
		this.monitoreoLlamado = monitoreoLlamado;
	}
	
	@Override
	public void run() {
		this.monitoreoLlamado.echoLlamado();
	}

}
