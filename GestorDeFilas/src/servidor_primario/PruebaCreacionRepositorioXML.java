package servidor_primario;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PruebaCreacionRepositorioXML {

	// Si llega a haber problemas con el pathname que se usa "por defecto", habria que especificarlo usando:
	// https://docs.oracle.com/javase/7/docs/api/java/io/FileOutputStream.html#FileOutputStream(java.io.File,%20boolean)
	public static final String NOMBRE_ARCHIVO_REPOSITORIO = "repositorio_clientes.xml";
	
	private static XMLEncoder encoder = null; // para escribir objetos en un XML
	private static XMLDecoder decoder = null; // para leer objetos de un XML
	
	
	public static void main(String[] args) {
		
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		listaClientes.add(new Cliente("11111111", "Johnny Depp", "Premium"));
		listaClientes.add(new Cliente("22222222", "Tobey Maguire", "Plus"));
		listaClientes.add(new Cliente("33333333", "Andrew Garfield", "Normal"));
		listaClientes.add(new Cliente("44444444", "Tom Holland", "Basico"));
		
		abrirArchivoEscritura(NOMBRE_ARCHIVO_REPOSITORIO);
		escrituraXML(listaClientes);
		cerrarArchivoEscritura();
		// Estuve probando que si no hacemos el .close() del archivo, si quisieramos abrir el XML no veriamos nada.
		// Entonces mientras el servidor este funcionando, no podriamos abrir los archivos de los logs (persistencia)
		// para ver qué se está escribiendo.
		// Tambien probé de abrir el xml en modo append (escribir al final), cosa de que por cada escritura, podamos hacer
		// un open (new) y un close; pero no escribe como nosotros querriamos (ver comentario en el método de escritura).
		// [HASTA ACÁ FUI PROBANDO DE ESCRIBIR DIRECTTAMENTE OBJETOS TIPO CLIENTE, DESPUES PROBE CON UNA LISTA]
		// Otra opcion sería hacer esto:
		// "What you need to do is read the contents of the xml file into an object (e.g. ArrayList) first before writing,
		// then append your new content to your object then write your object to the xml file
		
		abrirArchivoLectura(NOMBRE_ARCHIVO_REPOSITORIO);
		lecturaXML();
		cerrarArchivoLectura();

	}
	
	public static void abrirArchivoEscritura(String nombreArchivo) {
		try {
			// Encontré el modo 'append' (https://docs.oracle.com/javase/7/docs/api/java/io/FileOutputStream.html#FileOutputStream(java.lang.String,%20boolean)),
			// pero si cerraba y volvia a abrir el archivo, me agregaba toda una nueva estructura de XML debajo de la anterior, como si fueran 2 archivos en 1.
			// Entonces ahora lo dejé en modo 'normal'. [ESTO LO HABIA PROBADO AGREGANDO DIRECTAMENTE OBJETOS TIPO CLIENTE, NO UNA LISTA]
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombreArchivo)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void abrirArchivoLectura(String nombreArchivo) {
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(nombreArchivo)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void escrituraXML(ArrayList<Cliente> listaClientes) {
		encoder.writeObject(listaClientes);
	}
	
	public static void lecturaXML() {
		ArrayList<Cliente> listaClientes = (ArrayList<Cliente>) decoder.readObject();
		System.out.println(listaClientes);
	}
	
	public static void cerrarArchivoEscritura() {
		encoder.close();
	}
	
	public static void cerrarArchivoLectura() {
		decoder.close();
	}

}
