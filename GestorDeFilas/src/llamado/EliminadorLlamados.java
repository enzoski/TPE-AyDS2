package llamado;

public class EliminadorLlamados extends Thread {
	
	ControladorLlamado controlador;
	
	public EliminadorLlamados(ControladorLlamado controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		this.controlador.eliminarBox();
	}

}
