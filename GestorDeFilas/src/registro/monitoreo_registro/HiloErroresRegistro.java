package registro.monitoreo_registro;

public class HiloErroresRegistro extends Thread {
	
	private ManejadorErroresRegistro manejadorErroresRegistro;
	 
	public HiloErroresRegistro(ManejadorErroresRegistro manejadorErroresRegistro) {
		this.manejadorErroresRegistro = manejadorErroresRegistro;
	}
	
	@Override
	public void run() {
		this.manejadorErroresRegistro.recibirError();
	}

}
