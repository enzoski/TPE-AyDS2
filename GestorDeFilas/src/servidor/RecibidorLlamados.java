package servidor;

public class RecibidorLlamados extends Thread {
	
	
	 private ComunicacionLlamados comunicacion;
	 
	 public RecibidorLlamados(ComunicacionLlamados comunicacion) {
		this.comunicacion = comunicacion;
	}
	
	@Override
	public void run() {
		this.comunicacion.pedidoLlamado();
	}
	

}
