package llamado.controlador_llamado;

public class EliminadorLlamados extends Thread {
	
	private ControladorEliminacion controlador;
	
	public EliminadorLlamados(ControladorEliminacion controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		this.controlador.eliminarBox();
	}

	
}
