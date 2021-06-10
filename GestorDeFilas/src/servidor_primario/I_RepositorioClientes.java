package servidor_primario;

public interface I_RepositorioClientes {
	
	public static final String NOMBRE_ARCHIVO_REPOSITORIO = "repositorio_clientes.xml";
	
	public boolean existeCliente(String dni);
	public Cliente getCliente(String dni);

}
