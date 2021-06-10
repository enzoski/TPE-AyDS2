package servidor_secundario.persistencia_secundaria;

import servidor_secundario.I_RepositorioClientes;

public abstract class PersistenciaTemplate {
	public static final String NOMBRE_ARCHIVO_LLAMADOS = "historial_llamados.xml";
	public static final String NOMBRE_ARCHIVO_REGISTROS = "historial_registros.xml";
	
	public abstract void persistirDatosLlamado(String llamado);
	public abstract void persistirDatosRegistro(String registro);
	private I_RepositorioClientes repositorioClientes;
	
	
	public PersistenciaTemplate(I_RepositorioClientes repositorioClientes) {
		this.repositorioClientes = repositorioClientes;
	}
	
	public void persistirLlamado(String fecha, String box, String dni) {
		String llamado = fecha + " - Box: " + box + " - "+ this.repositorioClientes.getCliente(dni).toString();
		this.persistirDatosLlamado(llamado);
	}
	
	
	public void persistirRegistro(String fecha, String dni) {
		String registro = fecha + " - " +this.repositorioClientes.getCliente(dni).toString();
		this.persistirDatosRegistro(registro);
	}
	
}
