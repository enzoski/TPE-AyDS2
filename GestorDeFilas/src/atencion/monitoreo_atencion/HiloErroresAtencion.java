package atencion.monitoreo_atencion;

public class HiloErroresAtencion extends Thread {
	
	private ManejadorErroresAtencion manejadorErroresAtencion;
	 
	public HiloErroresAtencion(ManejadorErroresAtencion manejadorErroresAtencion) {
		this.manejadorErroresAtencion = manejadorErroresAtencion;
	}
	
	@Override
	public void run() {
		this.manejadorErroresAtencion.recibirError();
	}

}
