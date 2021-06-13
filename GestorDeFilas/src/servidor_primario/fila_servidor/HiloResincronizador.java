package servidor_primario.fila_servidor;

public class HiloResincronizador extends Thread{
	private ResincronizaFila resincronizaFila;
	 
	public HiloResincronizador(ResincronizaFila resincronizaFila) {
		this.resincronizaFila = resincronizaFila;
	}
	
	@Override
	public void run() {
		this.resincronizaFila.registro();
	}
}
