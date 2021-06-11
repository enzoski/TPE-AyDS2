package servidor_primario.fila_servidor;

import java.util.Queue;

public class LlamadoPorLlegadaStrategy implements I_LlamadoStrategy {

	private Queue<String> clientes; 
	
	public LlamadoPorLlegadaStrategy(Queue<String> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public String proximoCliente() {
		// TODO Auto-generated method stub
		return this.clientes.poll();
	}

}
