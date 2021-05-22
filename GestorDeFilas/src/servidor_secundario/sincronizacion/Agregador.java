package servidor_secundario.sincronizacion;

public class Agregador extends Thread {
	
	private Sincronizador sincronizador;
	
	public Agregador(Sincronizador sincronizador) {
		this.sincronizador = sincronizador;
	}
	
	@Override
	public void run() {
		this.sincronizador.agregar();
	}

	
}
