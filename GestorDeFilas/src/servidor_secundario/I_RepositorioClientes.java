package servidor_secundario;

public interface I_RepositorioClientes {
	
	public static final String NOMBRE_ARCHIVO_REPOSITORIO = "repositorio_clientes.xml";
	
	public String buscarNombreCliente(String dni);
	public String buscarCategoriaCliente(String dni);
	public boolean existeCliente(String dni);

}
