package servidor_secundario.persistencia_secundaria;

public interface I_Persistencia { // persistencia de 'logs' (clientes registrados y llamados realizados, 2 archivos diferentes)
	
	public void persistirLlamado(String fecha, String box, String dni);
	public void persistirRegistro(String fecha, String dni);

}
