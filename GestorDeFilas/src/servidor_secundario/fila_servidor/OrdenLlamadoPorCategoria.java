package servidor_secundario.fila_servidor;

import java.util.Iterator;
import java.util.Queue;

import servidor_secundario.I_RepositorioClientes;
import servidor_secundario.RepositorioClientes;

public class OrdenLlamadoPorCategoria implements I_OrdenLlamado {
	
	private Queue<String> clientes;
	private I_RepositorioClientes repositorioClientes;
	
	public OrdenLlamadoPorCategoria(Queue<String> clientes, I_RepositorioClientes repositorioClientes) {
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
		return dniProx;
	}
	
	public String buscarClienteCategoria(String categoria) {
		String dniProxCliente = "";
		Iterator<String> it = this.clientes.iterator();
		while(it.hasNext() && dniProxCliente.equals("")) {
			String dniActual = it.next();
			String categoriaActual = this.repositorioClientes.buscarCategoriaCliente(dniActual);
			if(categoriaActual.equals(categoria))
				dniProxCliente = dniActual;
		}
		return dniProxCliente;
		
	}

}
