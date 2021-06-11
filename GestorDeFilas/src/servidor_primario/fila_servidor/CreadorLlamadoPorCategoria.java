package servidor_primario.fila_servidor;

import java.util.Queue;

import servidor_primario.I_RepositorioClientes;

public class CreadorLlamadoPorCategoria extends CreadorAlgoritmoLlamado{
	
	I_RepositorioClientes repositorioClientes;
	
	public CreadorLlamadoPorCategoria(Queue<String> clientes,I_RepositorioClientes repositorioClientes) {
		super(clientes);
		this.repositorioClientes = repositorioClientes;
	}

	@Override
	public I_LlamadoStrategy crearAlgoritmoLlamado() {
		// TODO Auto-generated method stub
		return new LlamadoPorCategoriaStrategy(this.getClientes(),this.repositorioClientes);
	}



}
