package servidor_secundario.fila_servidor;

import java.util.Queue;

public class CreadorLlamadoPorLlegada extends CreadorAlgoritmoLlamado{

	
	public CreadorLlamadoPorLlegada(Queue<String> clientes) {
		super(clientes);
	}
	@Override
	public I_LlamadoStrategy crearAlgoritmoLlamado() {
		return new LlamadoPorLlegadaStrategy(this.getClientes());
	}

}
