package servidor_secundario.persistencia_secundaria;

import servidor_secundario.I_RepositorioClientes;

// Patrón de diseño GoF: Template Method
// (definimos el esqueleto de los algoritmos que persisten llamados y registros,
// dejando sin implementar el tipo de persistencia que se hará, pudiendose así
// persistir en distintos tipos de archivos, como de texto, binarios, XML, etc.)
public abstract class PersistenciaTemplate {
	
	public static final String NOMBRE_ARCHIVO_LLAMADOS = "historial_llamados.xml"; // la extension la podriamos poner en la clase concreta.
	public static final String NOMBRE_ARCHIVO_REGISTROS = "historial_registros.xml";
	
	private I_RepositorioClientes repositorioClientes;
	
	public PersistenciaTemplate(I_RepositorioClientes repositorioClientes) {
		this.repositorioClientes = repositorioClientes;
	}
	
	public void persistirLlamado(String fecha, String box, String dni) {
		String llamado = fecha + " - Box: " + box + " - " + this.repositorioClientes.getCliente(dni).toString();
		this.persistirDatosLlamado(llamado); //metodo hook
	}
	
	
	public void persistirRegistro(String fecha, String dni) {
		String registro = fecha + " - " + this.repositorioClientes.getCliente(dni).toString();
		this.persistirDatosRegistro(registro); //metodo hook
	}
	
	public abstract void persistirDatosLlamado(String llamado);
	public abstract void persistirDatosRegistro(String registro);
	
}
