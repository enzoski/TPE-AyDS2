package servidor_primario.persistencia_primaria;

import servidor_primario.Cliente;

public class DatosRegistros {
	
	private String fecha;
	private Cliente cliente;
	
	public DatosRegistros() {
		
	}
	
	public DatosRegistros(String fecha, Cliente cliente) {
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	

}
