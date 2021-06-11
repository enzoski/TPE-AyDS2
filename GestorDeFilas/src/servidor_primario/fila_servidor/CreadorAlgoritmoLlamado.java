package servidor_primario.fila_servidor;

import java.util.Queue;

public abstract class CreadorAlgoritmoLlamado {
	
	private Queue<String> clientes;
	
	public CreadorAlgoritmoLlamado(Queue<String> clientes) {
		this.clientes = clientes;
	}
	
	
	public abstract I_LlamadoStrategy crearAlgoritmoLlamado();
	
	public Queue<String> getClientes(){
		return clientes;
	}
}
