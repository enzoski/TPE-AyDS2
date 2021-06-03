package servidor_primario.fila_servidor;

import java.util.Queue;

public class OrdenLlamadoPorDNI implements I_OrdenLlamado {
	
	private Queue<String> clientes;
	
	public OrdenLlamadoPorDNI(Queue<String> clientes) { // de forma ascendente (primero los DNI menores, o sea, la gente mas grande)
		this.clientes = clientes;
	}

	@Override
	public String proximoCliente() {
		// hacer un metodo que nos desvuelva el mín DNI.
		return null;
	}

}
