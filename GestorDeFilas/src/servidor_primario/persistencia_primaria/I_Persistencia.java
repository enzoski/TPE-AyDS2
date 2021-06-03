package servidor_primario.persistencia_primaria;

import servidor_primario.Cliente;

public interface I_Persistencia { // persistencia de 'logs' (clientes registrados y llamados realizados, 2 archivos diferentes)
	
	public void persistirLlamado(String fecha, int box, Cliente cliente);
	public void persistirRegistro(String fecha, Cliente cliente);

}
