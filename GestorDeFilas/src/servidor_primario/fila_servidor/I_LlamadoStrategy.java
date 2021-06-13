package servidor_primario.fila_servidor;

// Patrón de diseño GoF: Strategy (diferentes estrategias/algoritmos para obtener el DNI del proximo cliente a ser llamado)
public interface I_LlamadoStrategy {
	
	public String proximoCliente();

}
