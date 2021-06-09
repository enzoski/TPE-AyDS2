package servidor_primario.persistencia_primaria;

public interface I_Persistencia { // persistencia de 'logs' (clientes registrados y llamados realizados, 2 archivos diferentes)
	
	public static final String NOMBRE_ARCHIVO_LLAMADOS = "historial_llamados.xml";
	public static final String NOMBRE_ARCHIVO_REGISTROS = "historial_registros.xml";
	
	public void persistirLlamado(String fecha, String box, String dni);
	public void persistirRegistro(String fecha, String dni);

}
