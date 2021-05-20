package servidor.fila_servidor;

public class RegistradorDNI extends Thread {
	
	private GestionFila gestionFila;
	
	public RegistradorDNI(GestionFila gestionFila) {
		this.gestionFila = gestionFila;
	}
	
	@Override
	public void run() {
		this.gestionFila.registro();
	}

	
}
