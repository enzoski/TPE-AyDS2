package servidor;

public class DeshabilitadorBox extends Thread {
	
	Comunicacion comunicacion;
	
	public DeshabilitadorBox(Comunicacion comunicacion) {
		this.comunicacion = comunicacion;
	}
	
	@Override
	public void run() {
		this.comunicacion.deshabilitarBox();
	}

}
