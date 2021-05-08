package servidor;

public class RegistradorDNI extends Thread {
	
	GestionFila gestionFila;
	
	public RegistradorDNI(GestionFila gestionFila) {
		this.gestionFila = gestionFila;
	}
	
	@Override
	public void run() {
		this.gestionFila.registro();
	}

}
