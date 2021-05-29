package servidor_secundario.sincronizacion;

public class Eliminador extends Thread {
	
	private SincronizadorEliminacion sincronizador;
	
	public Eliminador(SincronizadorEliminacion sincronizador) {
		this.sincronizador = sincronizador;
	}
	
	@Override
	public void run() {
		this.sincronizador.eliminar();
	}

	
}