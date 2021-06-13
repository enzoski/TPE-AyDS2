package servidor_secundario.fila_servidor;

import java.util.Queue;

// Patr�n de dise�o GoF: Factory Method (cada algoritmo de llamado tendr� una clase responsable de crear su instancia)
public abstract class CreadorAlgoritmoLlamado {
	
	private Queue<String> clientes;
	
	public CreadorAlgoritmoLlamado(Queue<String> clientes) {
		this.clientes = clientes;
	}
	
	// crearemos instancias de cualquier tipo de algoritmo que implemente a la interfaz I_LlamadoStrategy
	public abstract I_LlamadoStrategy crearAlgoritmoLlamado();
	
	public Queue<String> getClientes(){
		return clientes;
	}
	
	
}
