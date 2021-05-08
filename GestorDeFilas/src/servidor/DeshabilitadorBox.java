package servidor;

public class DeshabilitadorBox extends Thread {
	
	private ComunicacionDeshabilitacion comunicacion;
	
	public DeshabilitadorBox(ComunicacionDeshabilitacion comunicacion) {
		this.comunicacion = comunicacion;
	}
	
	@Override
	public void run() {
		this.comunicacion.deshabilitarBox();
	}


}
