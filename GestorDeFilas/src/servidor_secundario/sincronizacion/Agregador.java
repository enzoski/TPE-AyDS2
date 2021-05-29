package servidor_secundario.sincronizacion;

public class Agregador extends Thread {
	
	private SincronizadorAgregacion sincronizador;
	
	public Agregador(SincronizadorAgregacion sincronizador) {
		this.sincronizador = sincronizador;
	}
	
	@Override
	public void run() {
		this.sincronizador.agregar();
	}

	
}
