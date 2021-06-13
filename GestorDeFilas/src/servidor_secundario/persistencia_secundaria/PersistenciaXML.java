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

public class PersistenciaXML extends PersistenciaTemplate {

	
	public PersistenciaXML(I_RepositorioClientes repositorioClientes) {
		super(repositorioClientes);
		
	}
	
	@Override
	public void persistirDatosLlamado(String llamado) {
		try {
			XMLDecoder decoderLlamado = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_LLAMADOS)));
			ArrayList<String> listaLlamados = (ArrayList<String>) decoderLlamado.readObject(); // leemos la lista
			listaLlamados.add(llamado); // le agregamos el llamado
			decoderLlamado.close();
			XMLEncoder encoderLlamados = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_LLAMADOS)));
			encoderLlamados.writeObject(listaLlamados); // reescribimos el archivo con la nueva lista
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
	
	@Override
	public void persistirDatosRegistro(String registro) {
		try {
			XMLDecoder decoderRegistro = new XMLDecoder(new BufferedInputStream(new FileInputStream(NOMBRE_ARCHIVO_REGISTROS)));
			ArrayList<String> listaRegistros = (ArrayList<String>) decoderRegistro.readObject(); // leemos la lista
			listaRegistros.add(registro); // le agregamos el registro
			decoderRegistro.close();
			XMLEncoder encoderRegistro = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_REGISTROS)));
			encoderRegistro.writeObject(listaRegistros); // reescribimos el archivo con la nueva lista
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
