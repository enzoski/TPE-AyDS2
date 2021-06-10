package servidor_secundario;

public class Cliente {
	
	private String dni;
	private String nombre;
	private String categoria; // Basico, Normal, Plus y Premium
	
	public Cliente() {
		// Es necesario declarar un constructor vacio para que se realice bien la serializacion XML.
	}
	
	public Cliente(String dni, String nombre, String categoria) { // despues ver si sacamos estos parametros.
		this.dni = dni;
		this.nombre = nombre;
		this.categoria = categoria;
		
	}

	// Es necesario implementar los getters y setters de todos los atributos para que se realice bien la serializacion XML.
	public String getDni() {
		return dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public String toString() {
		return this.dni + "-" + this.nombre + "-" + this.categoria;
	}

	/*
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", categoria=" + categoria + "]";
	}
	*/
	

}
