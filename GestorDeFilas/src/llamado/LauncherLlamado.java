package llamado;

import llamado.controlador_llamado.ControladorEliminacion;
import llamado.controlador_llamado.ControladorLlamado;
import llamado.monitoreo_llamado.ManejadorErroresLlamado;
import llamado.monitoreo_llamado.MonitoreoLlamado;
import llamado.vista_llamado.VistaLlamadoTV;

public class LauncherLlamado {

	public static void main(String[] args) {
		
		VistaLlamadoTV vistaLlamados = new VistaLlamadoTV();
		ControladorLlamado controladorL = new ControladorLlamado(vistaLlamados);
		ControladorEliminacion controladorE = new ControladorEliminacion(vistaLlamados);
		// disponibilidad
		ManejadorErroresLlamado manejadorErroresLlamado = new ManejadorErroresLlamado(controladorL);
		MonitoreoLlamado monitoreoLlamado = new MonitoreoLlamado();
		
	}

}
