package servidor;

public class RecibidorLlamados extends Thread {
	
	Comunicacion comunicacion;
	
	public RecibidorLlamados(Comunicacion comunicacion) {
		this.comunicacion = comunicacion;
	}
	
	@Override
	public void run() {
		this.comunicacion.pedidoLlamado();
	}

}
