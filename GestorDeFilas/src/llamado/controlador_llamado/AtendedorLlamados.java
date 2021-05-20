package llamado.controlador_llamado;

public class AtendedorLlamados extends Thread {

	private ControladorLlamado controlador;
	
	public AtendedorLlamados(ControladorLlamado controlador) {
		this.controlador = controlador;
	}
	
	@Override
	public void run() {
		this.controlador.hacerLlamado();
	}

	
}
