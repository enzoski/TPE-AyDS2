package servidor_secundario;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Clase que implementa operaciones de lectura de un archivo XML que contiene datos de clientes.
 *
 */
public class RepositorioClientes implements I_RepositorioClientes {
	
	public RepositorioClientes() {
		
	}
	
	@Override
	public Cliente getCliente(String dni) {
		String cliente = null;
		Cliente objetoCliente = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REPOSITORIO)));
			String dniActual = "";
			while(!dni.equals(dniActual)) {
				cliente = (String) decoder.readObject();
				//dniActual = cliente.getDni();
				dniActual = cliente.split("-")[0]; // parseo del dni del cliente
			}
			String nombre = cliente.split("-")[1]; // parseo del nombre del cliente
			String categoria = cliente.split("-")[2]; // parseo de la categoria del cliente
			objetoCliente = new Cliente(dni,nombre,categoria);
				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e2) {
			// Se asume que todos los clientes estan registrados, por lo cual no deberíamos entrar a este catch.
			e2.printStackTrace();
		}
		
		return objetoCliente;
	}

	@Override
	public boolean existeCliente(String dni) {
		boolean existe = true;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REPOSITORIO)));
			String cliente;
			String dniActual = "";
			while(!dni.equals(dniActual)) {
				cliente = (String) decoder.readObject();
				//dniActual = cliente.getDni();
				dniActual = cliente.split("-")[0]; // parseo del dni del cliente
			}
				
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e2) {
			// El cliente no existirá si llegamos a fin de archivo y no encontramos el dni.
			// e2.printStackTrace();
			existe = false;
		}
		
		return existe;
	}
	

}
