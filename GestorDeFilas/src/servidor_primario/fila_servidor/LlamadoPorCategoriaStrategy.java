package servidor_primario.fila_servidor;

import java.util.Iterator;
import java.util.Queue;

import servidor_primario.I_RepositorioClientes;

public class LlamadoPorCategoriaStrategy implements I_LlamadoStrategy {
	
	private Queue<String> clientes;
	private I_RepositorioClientes repositorioClientes;
	
	public LlamadoPorCategoriaStrategy(Queue<String> clientes, I_RepositorioClientes repositorioClientes) {
		this.clientes = clientes;
		this.repositorioClientes = repositorioClientes;
	}

	@Override
	public String proximoCliente() {
		String dniProx = this.buscarClienteCategoria("Premium");
		if(dniProx.equals("")) {
			dniProx = this.buscarClienteCategoria("Plus");
			if(dniProx.equals("")) {
				dniProx = this.buscarClienteCategoria("Normal");
				if(dniProx.equals("")) {
					dniProx = this.buscarClienteCategoria("Basico");
				}
			}
		}
		if(dniProx.equals(""))
			dniProx = null;
		else{
			this.clientes.remove(dniProx);
		}
		return dniProx;
	}
	
	private String buscarClienteCategoria(String categoria) {
		String dniProxCliente = "";
		Iterator<String> it = this.clientes.iterator();
		while(it.hasNext() && dniProxCliente.equals("")) {
			String dniActual = it.next();
			String categoriaActual = this.repositorioClientes.getCliente(dniActual).getCategoria();
			if(categoriaActual.equals(categoria))
				dniProxCliente = dniActual;
		}
		return dniProxCliente;
		
	}

}
