package servidor_secundario.persistencia_secundaria;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import servidor_secundario.Cliente;
import servidor_secundario.RepositorioClientes;

public class PersistenciaXML implements I_Persistencia {

	private RepositorioClientes repositorioClientes;
	
	private ArrayList<DatosLlamados> listaLlamados;
	private ArrayList<DatosRegistros> listaRegistros;
	
	public PersistenciaXML(RepositorioClientes repositorioClientes) {
		this.repositorioClientes = repositorioClientes;
		this.listaLlamados = new ArrayList<DatosLlamados>();
		this.listaRegistros = new ArrayList<DatosRegistros>();
		
	}
	
	@Override
	public void persistirLlamado(String fecha, String box, String dni) { // tendriamos un gran archivo de llamados.
		String nombre = this.repositorioClientes.buscarNombreCliente(dni);
		String categoria = this.repositorioClientes.buscarCategoriaCliente(dni);
		Cliente cliente = new Cliente(dni, nombre, categoria);
		this.listaLlamados.add(new DatosLlamados(fecha, box, cliente));
		
	}

	@Override
	public void persistirRegistro(String fecha, String dni) { // tendriamos un gran archivo de registro de clientes.
		String nombre = this.repositorioClientes.buscarNombreCliente(dni);
		String categoria = this.repositorioClientes.buscarCategoriaCliente(dni);
		Cliente cliente = new Cliente(dni, nombre, categoria);
		this.listaRegistros.add(new DatosRegistros(fecha, cliente));
		
	}
	
	@Override
	public void persistirDatos() {
		try {
			XMLEncoder encoderLlamados = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_LLAMADOS)));
			XMLEncoder encoderRegistros = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(NOMBRE_ARCHIVO_REGISTROS)));
			encoderLlamados.writeObject(this.listaLlamados);
			encoderRegistros.writeObject(this.listaRegistros);
			encoderLlamados.close();
			encoderRegistros.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
