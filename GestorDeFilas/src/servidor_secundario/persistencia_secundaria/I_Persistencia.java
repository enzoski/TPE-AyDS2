package servidor_secundario.persistencia_secundaria;

public interface I_Persistencia { // persistencia de 'logs' (clientes registrados y llamados realizados, 2 archivos diferentes)
	
	public static final String NOMBRE_ARCHIVO_LLAMADOS = "log_llamados.xml";
	public static final String NOMBRE_ARCHIVO_REGISTROS = "log_registros.xml";
	
	public void persistirLlamado(String fecha, String box, String dni);
	public void persistirRegistro(String fecha, String dni);

}
