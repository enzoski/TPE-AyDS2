package llamado.monitoreo_llamado;

import atencion.monitoreo_atencion.ManejadorErroresAtencion;

public class HiloErroresLlamado extends Thread {
	
	private ManejadorErroresLlamado manejadorErroresLlamado;
	 
	public HiloErroresLlamado(ManejadorErroresLlamado manejadorErroresLlamado) {
		this.manejadorErroresLlamado = manejadorErroresLlamado;
	}
	
	@Override
	public void run() {
		this.manejadorErroresLlamado.recibirError();
	}

}
