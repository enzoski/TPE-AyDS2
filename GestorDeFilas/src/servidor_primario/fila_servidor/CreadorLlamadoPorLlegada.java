package servidor_primario.fila_servidor;

import java.util.Queue;

public class CreadorLlamadoPorLlegada extends CreadorAlgoritmoLlamado{

	public CreadorLlamadoPorLlegada(Queue<String> clientes) {
		super(clientes);
	}
	
	@Override
	public I_LlamadoStrategy crearAlgoritmoLlamado() {
		// TODO Auto-generated method stub
		return new LlamadoPorLlegadaStrategy(this.getClientes());
	}
	

}
