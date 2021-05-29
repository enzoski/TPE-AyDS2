package servidor_secundario.monitoreo;

public class HiloResincronizacion extends Thread{
	
	private ResincronizadorServ2 resincronizador;
	
	public HiloResincronizacion(ResincronizadorServ2 resincronizador) {
		this.resincronizador = resincronizador;
	}
	
	@Override
	public void run() { 
		this.resincronizador.resincronizar();
	}

}
