package llamado;

public class EliminadorLlamados extends Thread {
	
	ControladorEliminacion controlador;
	
	public EliminadorLlamados(ControladorEliminacion controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		this.controlador.eliminarBox();
	}

}
