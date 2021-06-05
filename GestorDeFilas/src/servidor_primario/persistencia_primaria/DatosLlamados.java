package servidor_primario.persistencia_primaria;

import servidor_primario.Cliente;

public class DatosLlamados {
	
	private String fecha;
	private String box;
	private Cliente cliente;
	
	public DatosLlamados() {
		
	}
	
	public DatosLlamados(String fecha, String box, Cliente cliente) {
		this.fecha = fecha;
		this.box = box;
		this.cliente = cliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
