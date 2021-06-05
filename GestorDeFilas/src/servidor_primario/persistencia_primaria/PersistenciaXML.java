package servidor_primario.persistencia_primaria;

import servidor_primario.RepositorioClientes;

public class PersistenciaXML implements I_Persistencia {

	private RepositorioClientes repositorioClientes;
	
	public PersistenciaXML(RepositorioClientes repositorioClientes) {
		this.repositorioClientes = repositorioClientes;
		
	}
	
	@Override
	public void persistirLlamado(String fecha, String box, String dni) { // tendriamos un gran archivo de llamados.
		String nombre = this.repositorioClientes.buscarNombreCliente(dni);
		String categoria = this.repositorioClientes.buscarCategoriaCliente(dni);
		// acá agregariamos todo esto al XML, onda con un append.
		
	}

	@Override
	public void persistirRegistro(String fecha, String dni) { // tendriamos un gran archivo de registro de clientes.
		String nombre = this.repositorioClientes.buscarNombreCliente(dni);
		String categoria = this.repositorioClientes.buscarCategoriaCliente(dni);
		// acá agregariamos todo esto al XML, onda con un append.
		
	}

}
