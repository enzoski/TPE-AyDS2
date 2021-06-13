package servidor_primario;

public interface I_RepositorioClientes {
	
	public static final String NOMBRE_ARCHIVO_REPOSITORIO = "repositorio_clientes.xml";
	
	public Cliente getCliente(String dni);
	public boolean existeCliente(String dni);

}
