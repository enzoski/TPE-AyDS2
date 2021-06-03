package servidor_primario;

public class Cliente {
	
	private String nombre;
	private String dni;
	private String categoria;
	
	public Cliente(String nombre, String dni, String categoria) { // despues ver si sacamos estos parametros.
		this.nombre = nombre;
		this.dni = dni;
		this.categoria = categoria;
		
	}

	public String getNombre() {
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	public String getCategoria() {
		return categoria;
	}
	

}
