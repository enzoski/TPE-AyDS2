package servidor_primario;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RepositorioClientes {
	
	public static final String NOMBRE_ARCHIVO_REPOSITORIO = "repositorio_clientes.xml";
	
	public RepositorioClientes() {
		
	}
	
	// SI AL BUSCAR EN EL XML NO ENCUENTRA EL DNI, PODRIAMOS DEVOLVER UNA CATEGORIA 'TEMPORAL' QUE SEA LA MENOR CATEGORIA.
	public String buscarNombreCliente(String dni) {
		Cliente cliente = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REPOSITORIO)));
			String dniActual = "";
			while(!dni.equals(dniActual)) {
				cliente = (Cliente) decoder.readObject();
				dniActual = cliente.getDni();
			}
				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e2) {
			// por el momento vamos a asumir que todos los clientes estan registrados -> PREGUNTAR
			cliente = new Cliente(dni, dni, "Basico"); // pero igualmente por las dudas dejamos esta forma de solucionar el error.
			e2.printStackTrace();
		}
		
		return cliente.getNombre();
	}
	
	public String buscarCategoriaCliente(String dni) {
		Cliente cliente = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REPOSITORIO)));
			String dniActual = "";
			while(!dni.equals(dniActual)) {
				cliente = (Cliente) decoder.readObject();
				dniActual = cliente.getDni();
			}
				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e2) {
			// por el momento vamos a asumir que todos los clientes estan registrados -> PREGUNTAR
			cliente = new Cliente(dni, dni, "Basico"); // pero igualmente por las dudas dejamos esta forma de solucionar el error.
			e2.printStackTrace();
		}
		
		return cliente.getCategoria();
	}

}
