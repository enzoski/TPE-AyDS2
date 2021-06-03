package servidor_primario.fila_servidor;

import java.util.Queue;

public class OrdenLlamadoPorCategoria implements I_OrdenLlamado {
	
	private Queue<String> clientes;
	
	public OrdenLlamadoPorCategoria(Queue<String> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String proximoCliente() {
		// TODO Auto-generated method stub
		return null;
	}

}
