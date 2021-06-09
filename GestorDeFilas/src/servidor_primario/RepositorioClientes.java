package servidor_primario;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RepositorioClientes implements I_RepositorioClientes {
	
	public RepositorioClientes() {
		
	}
	
	@Override
	public String buscarNombreCliente(String dni) {
		String cliente = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REPOSITORIO)));
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
			// Se asume que todos los clientes estan registrados, por lo cual no deberíamos entrar a este catch.
			e2.printStackTrace();
		}
		
		//return cliente.getNombre();
		return cliente.split("-")[1]; // parseo del nombre del cliente
	}
	
	@Override
	public String buscarCategoriaCliente(String dni) {
		String cliente = null;
		try {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REPOSITORIO)));
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
			// Se asume que todos los clientes estan registrados, por lo cual no deberíamos entrar a este catch.
			e2.printStackTrace();
		}
		
		//return cliente.getCategoria();
		return cliente.split("-")[2]; // parseo de la categoria del cliente
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
		//return cliente.getNombre();
		return existe;
	}
	
	

}
