package servidor_secundario.sincronizacion;

public class Eliminador extends Thread {
	
	private Sincronizador sincronizador;
	
	public Eliminador(Sincronizador sincronizador) {
		this.sincronizador = sincronizador;
	}
	
	@Override
	public void run() {
		this.sincronizador.eliminar();
	}

	
}