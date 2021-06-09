package servidor_primario;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PruebaCreacionRepositorioXML { // AL FINAL, BORRAR ESTA CLASE

	// Si llega a haber problemas con el pathname que se usa "por defecto", habria que especificarlo usando:
	// https://docs.oracle.com/javase/7/docs/api/java/io/FileOutputStream.html#FileOutputStream(java.io.File,%20boolean)
	public static final String NOMBRE_ARCHIVO_REPOSITORIO = "repositorio_clientes.xml";
	
	private static XMLEncoder encoder = null; // para escribir objetos en un XML
	private static XMLDecoder decoder = null; // para leer objetos de un XML
	
	
	public static void main(String[] args) {
		
		// HAY PROBLEMAS SI QUEREMOS LEER EL REPOSITORIO DESDE EL SERVER SECUNDARIO, YA QUE LOS CLIENTES GRABADOS
		// SON DE TIPO "servidor_primario.Cliente", o sea, otro paquete.
		
		abrirArchivoEscritura(NOMBRE_ARCHIVO_REPOSITORIO);
		escrituraXML("11111111-Johnny Depp-Premium");
		escrituraXML("22222222-Tobey Maguire-Plus");
		escrituraXML("33333333-Andrew Garfield-Normal");
		escrituraXML("44444444-Emma Watson-Premium");
		escrituraXML("55555555-Chris Evans-Normal");
		escrituraXML("66666666-Christian Bale-Basico");
		escrituraXML("77777777-Heath Ledger-Basico");
		escrituraXML("88888888-Elizabeth Olsen-Plus");
		escrituraXML("99999999-Gary Oldman-Premium");
		escrituraXML("12345678-David Thewlis-Premium");
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
		lecturaXML();
		lecturaXML();
		lecturaXML();
		lecturaXML();
		lecturaXML();
		lecturaXML();
		lecturaXML();
		lecturaXML();
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
	
	public static void escrituraXML(String cliente) {
		encoder.writeObject(cliente);
	}
	
	public static void lecturaXML() {
		String cliente = (String) decoder.readObject();
		System.out.println(cliente);
	}
	
	public static void cerrarArchivoEscritura() {
		encoder.close();
	}
	
	public static void cerrarArchivoLectura() {
		decoder.close();
	}

}
