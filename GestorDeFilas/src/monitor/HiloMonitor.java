package monitor;

public class HiloMonitor extends Thread {
	
	private Monitor monitor;
	
	public HiloMonitor(Monitor monitor) {
		this.monitor = monitor;
	}
	
	@Override
	public void run() {
		while(true) {
			this.monitor.pingLlamado();
			this.monitor.pingServidorPrimario();
			this.monitor.pingServidorSecundario();
			try {
				Thread.sleep(30000); // 30 segundos entre ping y ping
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
