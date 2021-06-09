package servidor_secundario.persistencia_secundaria;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import servidor_secundario.I_RepositorioClientes;

public class PersistenciaXML implements I_Persistencia {

	private I_RepositorioClientes repositorioClientes;
	
	public PersistenciaXML(I_RepositorioClientes repositorioClientes) {
		this.repositorioClientes = repositorioClientes;
		
	}
	
	@Override
	public void persistirLlamado(String fecha, String box, String dni) { // tendriamos un gran archivo de llamados.
		String nombre = this.repositorioClientes.buscarNombreCliente(dni);
		String categoria = this.repositorioClientes.buscarCategoriaCliente(dni);
		String llamado = fecha + " - Box: " + box + "- DNI: " + dni + " - " + nombre + " - Categoria: " + categoria;
		this.persistirDatosLlamado(llamado);
		
	}

	@Override
	public void persistirRegistro(String fecha, String dni) { // tendriamos un gran archivo de registro de clientes.
		String nombre = this.repositorioClientes.buscarNombreCliente(dni);
		String categoria = this.repositorioClientes.buscarCategoriaCliente(dni);
		String registro = fecha + "- DNI: " + dni + " - " + nombre + " - Categoria: " + categoria;
		this.persistirDatosRegistro(registro);
		
	}
	
	private void persistirDatosLlamado(String llamado) {
		try {
			XMLDecoder decoderLlamado = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_LLAMADOS)));
			ArrayList<String> listaLlamados = (ArrayList<String>) decoderLlamado.readObject();
			listaLlamados.add(llamado);
			decoderLlamado.close();
			XMLEncoder encoderLlamados = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_LLAMADOS)));
			encoderLlamados.writeObject(listaLlamados);
			encoderLlamados.close();
		} catch (FileNotFoundException e) { // para el primer llamado que se quiera persistir, primero hay que crear el archivo XML.
			//e.printStackTrace();
			try {
				XMLEncoder encoderLlamados = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_LLAMADOS)));
				ArrayList<String> listaLlamados = new ArrayList<String>();
				listaLlamados.add(llamado);
				encoderLlamados.writeObject(listaLlamados);
				encoderLlamados.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				// ASUMIMOS QUE ACÁ NO DEBERÍA ENTRAR NUNCA!!! ESTAMOS CREANDO EL ARCHIVO, NO LEYENDOLO.
				// quizas con algo como "new File("path/to/file.txt").isFile();" podriamos mejorar esto (si es que permite path relativos).
			}
		}
	}
	
	private void persistirDatosRegistro(String registro) {
		try {
			XMLDecoder decoderRegistro = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REGISTROS)));
			ArrayList<String> listaRegistros = (ArrayList<String>) decoderRegistro.readObject();
			listaRegistros.add(registro);
			decoderRegistro.close();
			XMLEncoder encoderRegistro = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_REGISTROS)));
			encoderRegistro.writeObject(listaRegistros);
			encoderRegistro.close();
		} catch (FileNotFoundException e) { // para el primer registro que se quiera persistir, primero hay que crear el archivo XML.
			//e.printStackTrace();
			try {
				XMLEncoder encoderRegistro = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_REGISTROS)));
				ArrayList<String> listaRegistro = new ArrayList<String>();
				listaRegistro.add(registro);
				encoderRegistro.writeObject(listaRegistro);
				encoderRegistro.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				// ASUMIMOS QUE ACÁ NO DEBERÍA ENTRAR NUNCA!!! ESTAMOS CREANDO EL ARCHIVO, NO LEYENDOLO.
				// quizas con algo como "new File("path/to/file.txt").isFile();" podriamos mejorar esto (si es que permite path relativos).
			}
		}
	}

}
