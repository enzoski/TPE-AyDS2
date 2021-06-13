package servidor_primario.fila_servidor;

import java.util.Queue;

public class CreadorLlamadoPorDNI extends CreadorAlgoritmoLlamado{
	
	public CreadorLlamadoPorDNI(Queue<String> clientes) {
		super(clientes);
	}

	@Override
	public I_LlamadoStrategy crearAlgoritmoLlamado() {
		// TODO Auto-generated method stub
		return new LlamadoPorDNIStrategy(this.getClientes());
	}
	
	
}
