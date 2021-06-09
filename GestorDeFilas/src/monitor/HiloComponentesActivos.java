package monitor;

public class HiloComponentesActivos extends Thread{
	
	private RecibidorComponentesActivos recibidor;
	
	public HiloComponentesActivos(RecibidorComponentesActivos recibidor) {
		this.recibidor = recibidor;
	}
	
	@Override
	public void run() {
		this.recibidor.activarComponente();
	}
}
